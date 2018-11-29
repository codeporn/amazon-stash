package de.kodestruktor.amazon.stash.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

/**
 * @author Christoph Wende
 */
@Configuration
@EnableReactiveMongoRepositories(basePackages = "de.kodestruktor.amazon.stash.repo")
public class MongoConfig extends AbstractReactiveMongoConfiguration {

  @Override
  @Bean
  public MongoClient reactiveMongoClient() {
    return MongoClients.create();
  }

  @Override
  protected String getDatabaseName() {
    return "reactive";
  }
}
