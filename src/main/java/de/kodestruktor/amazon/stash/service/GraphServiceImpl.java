package de.kodestruktor.amazon.stash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.introproventures.graphql.jpa.query.schema.GraphQLExecutor;
import de.kodestruktor.amazon.stash.json.graph.GraphQLQueryRequest;
import de.kodestruktor.amazon.stash.service.vertx.verticle.DashboardVerticle;
import graphql.ExecutionResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Christoph Wende
 */
@Slf4j
@Service
public class GraphServiceImpl implements GraphService {


  @Autowired
  private DashboardVerticle dashboardVerticle;

  @Autowired
  private GraphQLExecutor graphQLExecutor;

  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public void processWebsocketQuery(final String query) {
    final ExecutionResult result = this.graphQLExecutor.execute(query);
    try {
      this.dashboardVerticle.emitGraph(this.mapper.writeValueAsString(result));
    } catch (final JsonProcessingException e) {
      log.debug("Error deserializing ", e);
    }

  }

  @Override
  public ExecutionResult executeGraphQLQuery(final String query) throws IOException {
    return this.graphQLExecutor.execute(query, null);
  }

  @Override
  public ExecutionResult executeGraphQLQuery(final String query, final String variables) throws IOException {
    final Map<String, Object> variablesMap = this.variablesStringToMap(variables);
    return this.graphQLExecutor.execute(query, variablesMap);
  }

  @Override
  public ExecutionResult executeGraphQLQuery(final GraphQLQueryRequest queryRequest) throws IOException {
    return this.graphQLExecutor.execute(queryRequest.getQuery(), queryRequest.getVariables());
  }

  /**
   * Convert String argument to a Map as expected by {@link GraphQLJpaExecutor#execute(String, Map)}. GraphiQL posts both query and variables as JSON
   * encoded String, so Spring MVC mapping is useless here. See: http://graphql.org/learn/serving-over-http/
   *
   * @param json JSON encoded string variables
   * @return a {@link HashMap} object of variable key-value pairs
   * @throws IOException exception
   */
  @SuppressWarnings("unchecked")
  private Map<String, Object> variablesStringToMap(final String json) throws IOException {
    Map<String, Object> variables = null;

    if (json != null && !json.isEmpty()) {
      variables = this.mapper.readValue(json, Map.class);
    }

    return variables;
  }
}
