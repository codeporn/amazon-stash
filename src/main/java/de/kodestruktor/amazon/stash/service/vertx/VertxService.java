package de.kodestruktor.amazon.stash.service.vertx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import de.kodestruktor.amazon.stash.service.vertx.verticle.DashboardVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

/**
 * @author Christoph Wende
 */
@Service
public class VertxService implements IVertxService {

  protected static final Logger LOG = LoggerFactory.getLogger(VertxService.class);

  @Autowired
  private Vertx vertx;

  @Autowired
  private DashboardVerticle dashboardVerticle;

  @PostConstruct
  public void init() {

    this.vertx.deployVerticle(this.dashboardVerticle, new Handler<AsyncResult<String>>() {

      @Override
      public void handle(final AsyncResult<String> event) {
        LOG.debug("Dashboard verticle deployed [{}]", Boolean.valueOf(event.succeeded()));
      }
    });
  }
}
