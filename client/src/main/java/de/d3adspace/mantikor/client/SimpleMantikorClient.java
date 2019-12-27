package de.d3adspace.mantikor.client;

import de.d3adspace.mantikor.client.netty.NettyHttpClient;
import de.d3adspace.mantikor.commons.HttpRequest;
import de.d3adspace.mantikor.commons.HttpResponse;

import java.net.SocketAddress;
import java.util.concurrent.Future;

public class SimpleMantikorClient implements MantikorClient {
  @Override
  public Future<HttpResponse> execute(SocketAddress remote,
                                      HttpRequest request) {
    NettyHttpClient httpClient = new NettyHttpClient(remote);
    return httpClient.sendRequest(request);
  }
}
