package de.d3adspace.mantikor.server.processor;

import de.d3adspace.mantikor.commons.HTTPRequest;
import de.d3adspace.mantikor.commons.HTTPResponse;

public interface HTTPRequestProcessor {

  /**
   * Process the given request and compose a response.
   *
   * @param request The request.
   * @return The response.
   */
  HTTPResponse processRequest(HTTPRequest request);
}
