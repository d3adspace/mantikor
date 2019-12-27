package de.d3adspace.mantikor.client;

import de.d3adspace.mantikor.commons.HttpRequest;
import de.d3adspace.mantikor.commons.HttpResponse;

import java.net.SocketAddress;
import java.util.concurrent.Future;

public interface MantikorClient {
  /**
   * Execute an HTTP call with the given request.
   *
   * @param remote  The remote server.
   * @param request The request.
   * @return The future with the response.
   */
  Future<HttpResponse> execute(SocketAddress remote, HttpRequest request);
}
