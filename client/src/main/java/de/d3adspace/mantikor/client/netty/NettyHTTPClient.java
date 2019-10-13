package de.d3adspace.mantikor.client.netty;

import de.d3adspace.mantikor.client.initializer.MantikorClientChannelInitializer;
import de.d3adspace.mantikor.client.utils.NettyUtils;
import de.d3adspace.mantikor.commons.HTTPRequest;
import de.d3adspace.mantikor.commons.HTTPResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;

import java.net.SocketAddress;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class NettyHTTPClient {

  private final SocketAddress target;

  /**
   * Worker group for netty.
   */
  private EventLoopGroup workerGroup;

  /**
   * Channel to communicate with the server
   */
  private Channel channel;

  private CompletableFuture<HTTPResponse> responseFuture;

  public NettyHTTPClient(SocketAddress target) {
    this.target = target;
  }

  private void connect() {

    responseFuture = new CompletableFuture<>();
    workerGroup = NettyUtils.createEventLoopGroup(4);

    Class<? extends Channel> channelClazz = NettyUtils.getChannel();
    ChannelHandler channelInitializer = new MantikorClientChannelInitializer(
      httpResponse -> responseFuture.complete(httpResponse));

    Bootstrap bootstrap = new Bootstrap();

    try {
      channel = bootstrap
        .channel(channelClazz)
        .group(workerGroup)
        .option(ChannelOption.TCP_NODELAY, true)
        .option(ChannelOption.SO_BACKLOG, 50)
        .handler(channelInitializer)
        .connect(target)
        .sync().channel();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void disconnect() {

    channel.close();
    workerGroup.shutdownGracefully();
  }

  public Future<HTTPResponse> sendRequest(HTTPRequest request) {

    connect();
    channel.writeAndFlush(request);
    disconnect();

    return responseFuture;
  }
}
