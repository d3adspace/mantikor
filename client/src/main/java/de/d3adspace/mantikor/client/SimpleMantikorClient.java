package de.d3adspace.mantikor.client;

import de.d3adspace.mantikor.client.netty.NettyHTTPClient;
import de.d3adspace.mantikor.commons.HTTPRequest;
import de.d3adspace.mantikor.commons.HTTPResponse;
import java.net.SocketAddress;
import java.util.concurrent.Future;

public class SimpleMantikorClient implements MantikorClient {

  @Override
  public Future<HTTPResponse> execute(SocketAddress remote,
    HTTPRequest request) {

    NettyHTTPClient httpClient = new NettyHTTPClient(remote);
    return httpClient.sendRequest(request);
  }
}
