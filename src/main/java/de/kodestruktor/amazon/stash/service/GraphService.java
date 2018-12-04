package de.kodestruktor.amazon.stash.service;

import java.io.IOException;

import de.kodestruktor.amazon.stash.json.graph.GraphQLQueryRequest;
import graphql.ExecutionResult;

/**
 * @author Christoph Wende
 */
public interface GraphService {

  /**
   * Process and answer a query received via Websocket.
   *
   * @param query the query to execute
   */
  void processWebsocketQuery(final String query);

  /**
   * Forwards a given query to the GraphQL executor and returns the result
   *
   * @param query the query to execute
   * @return the result of the passed query
   * @throws IOException in case an exception occurrs
   */
  ExecutionResult executeGraphQLQuery(final String query) throws IOException;

  /**
   * Forwards a given query with variables to the GraphQL executor and returns the result
   *
   * @param query     the query to execute
   * @param variables the variables for the passed query
   * @return the result of the passed query
   * @throws IOException in case an exception occurrs
   */
  ExecutionResult executeGraphQLQuery(final String query, final String variables) throws IOException;

  /**
   * Forwards a given query with variables to the GraphQL executor and returns the result
   *
   * @param queryRequest the query wrapper to execute
   * @return the result of the passed query
   * @throws IOException in case an exception occurrs
   */
  ExecutionResult executeGraphQLQuery(final GraphQLQueryRequest queryRequest) throws IOException;
}
