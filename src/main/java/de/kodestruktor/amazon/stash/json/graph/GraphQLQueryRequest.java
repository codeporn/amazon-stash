package de.kodestruktor.amazon.stash.json.graph;


import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

import java.util.Map;

/**
 * GraphQL JSON HTTP Request Wrapper Class
 *
 * @author Christoph Wende
 */

@Validated
public class GraphQLQueryRequest {

  @NotNull
  private String query;

  private Map<String, Object> variables;

  public GraphQLQueryRequest() {
  }

  public GraphQLQueryRequest(final String query) {
    super();
    this.query = query;
  }

  public String getQuery() {
    return this.query;
  }

  public void setQuery(final String query) {
    this.query = query;
  }

  public Map<String, Object> getVariables() {
    return this.variables;
  }

  public void setVariables(final Map<String, Object> variables) {
    this.variables = variables;
  }

}