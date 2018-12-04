package de.kodestruktor.amazon.stash.service.vertx.verticle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import de.kodestruktor.amazon.stash.event.GraphEvent;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Christoph Wende
 */
@Slf4j
@Component
public class DashboardVerticle extends AbstractVerticle {


  @Value("${eventBus.server.port}")
  private int eventBusServerPort;

  @Value("${eventBus.server.host}")
  private String eventBusServerHost;

  @Value("${eventBus.server.path}")
  private String eventBusServerPath;

  @Value("${eventBus.address.query}")
  private String queryAddress;

  @Value("${eventBus.address.graph}")
  private String graphAddress;

  private EventBus eventBus;

  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void start() throws Exception {

    final Router router = Router.router(this.vertx);
    final BridgeOptions opts = new BridgeOptions();

    opts.addOutboundPermitted(new PermittedOptions().setAddress(this.graphAddress));
    opts.addInboundPermitted(new PermittedOptions().setAddress(this.queryAddress));

    final SockJSHandler ebHandler = SockJSHandler.create(this.vertx).bridge(opts);
    router.route("/" + this.eventBusServerPath + "/*").handler(ebHandler);
    router.route().handler(StaticHandler.create());

    this.vertx.createHttpServer().requestHandler(router::accept).listen(this.eventBusServerPort, this.eventBusServerHost);
    this.eventBus = this.vertx.eventBus();
    this.consume(this.queryAddress);
  }

  public void emitGraph(final JsonObject payload) {
    this.eventBus.publish(this.graphAddress, payload);
  }

  public void emitGraph(final String payload) {
    this.eventBus.publish(this.graphAddress, payload);
  }

  public void emit(final String address, final JsonObject payload) {
    this.eventBus.publish(address, payload);
  }

  public void emit(final String address, final String payload) {
    this.eventBus.publish(address, payload);
  }

  public void consume(final String address) {
    this.eventBus.consumer(address).handler(command -> {
      log.info("Received graph request [{}]", command.body());
      this.applicationEventPublisher.publishEvent(new GraphEvent(command.body().toString()));
    });
  }
}