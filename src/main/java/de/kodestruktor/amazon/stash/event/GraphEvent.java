package de.kodestruktor.amazon.stash.event;

/**
 * Event emitted after graph request was received.
 *
 * @author Christoph Wende
 */
public class GraphEvent {

  private final String payload;

  public GraphEvent(final String payload) {
    this.payload = payload;
  }

  public String getPayload() {
    return this.payload;
  }

}
