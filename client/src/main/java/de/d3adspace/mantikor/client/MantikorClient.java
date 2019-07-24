package de.d3adspace.mantikor.client;

import de.d3adspace.mantikor.commons.HTTPRequest;
import de.d3adspace.mantikor.commons.HTTPResponse;
import java.net.SocketAddress;
import java.util.concurrent.Future;

/**
 * @author Ruby Hale <ruby@d3adspace.de>
 */
public interface MantikorClient {

  /**
   * Execute an HTTP call with the given request.
   *
   * @param remote The remote server.
   * @param request The request.
   * @return The future with the response.
   */
  Future<HTTPResponse> execute(SocketAddress remote, HTTPRequest request);
}
