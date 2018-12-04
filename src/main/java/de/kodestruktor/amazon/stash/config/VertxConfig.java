package de.kodestruktor.amazon.stash.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Christoph Wende
 */
@Slf4j
@Configuration
public class VertxConfig {

  @Value("${vertx.fileCache.base}")
  private String vertxFileCacheBase;

  @PostConstruct
  private void init() {
    final String vertxFileCache = this.vertxFileCacheBase + "/.vertx";
    log.info("Initializing vert.x file cache base to [{}]", vertxFileCache);
    System.setProperty("vertx.cacheDirBase", vertxFileCache);
  }

  @Bean
  public Vertx vertx() {
    return Vertx.vertx();
  }

}