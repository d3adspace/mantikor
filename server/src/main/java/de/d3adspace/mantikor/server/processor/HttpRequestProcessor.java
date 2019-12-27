package de.d3adspace.mantikor.server.processor;

import de.d3adspace.mantikor.commons.HttpRequest;
import de.d3adspace.mantikor.commons.HttpResponse;

public interface HttpRequestProcessor {

  /**
   * Process the given request and compose a response.
   *
   * @param request The request.
   * @return The response.
   */
  HttpResponse processRequest(HttpRequest request);
}
