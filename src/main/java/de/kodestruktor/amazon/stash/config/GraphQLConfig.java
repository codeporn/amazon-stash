package de.kodestruktor.amazon.stash.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

import com.introproventures.graphql.jpa.query.schema.GraphQLExecutor;
import com.introproventures.graphql.jpa.query.schema.GraphQLSchemaBuilder;
import com.introproventures.graphql.jpa.query.schema.impl.GraphQLJpaExecutor;
import com.introproventures.graphql.jpa.query.schema.impl.GraphQLJpaSchemaBuilder;

/**
 * @author Christoph Wende
 */
@Configuration
public class GraphQLConfig {

  @Autowired
  private EntityManager entityManager;

  @Value("${graphQL.schema.name}")
  private String schemaName;

  @Value("${graphQL.schema.description}")
  private String schemaDescription;

  @Bean
  public GraphQLExecutor graphQLExecutor() {
    return new GraphQLJpaExecutor(this.graphQLSchemaBuilder().build());
  }

  @Bean
  public GraphQLSchemaBuilder graphQLSchemaBuilder() {
    return new GraphQLJpaSchemaBuilder(this.entityManager).name(this.schemaName).description(this.schemaDescription);
  }
}
