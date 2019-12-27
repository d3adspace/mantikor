package de.d3adspace.mantikor.client.netty;

import de.d3adspace.constrictor.netty.NettyUtils;
import de.d3adspace.mantikor.client.initializer.MantikorClientChannelInitializer;
import de.d3adspace.mantikor.commons.HttpRequest;
import de.d3adspace.mantikor.commons.HttpResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;

import java.net.SocketAddress;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class NettyHttpClient {
  private final SocketAddress target;
  private EventLoopGroup workerGroup;
  private Channel channel;

  private CompletableFuture<HttpResponse> responseFuture;

  public NettyHttpClient(SocketAddress target) {
    this.target = target;
  }

  private void connect() {
    responseFuture = new CompletableFuture<>();
    workerGroup = NettyUtils.createEventLoopGroup(4, "Worker");
    tryBootstrap();
  }

  private void tryBootstrap() {
    try {
      bootstrap();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void bootstrap() throws InterruptedException {
    Class<? extends Channel> channelClazz = NettyUtils.getSocketChannel();
    ChannelHandler channelInitializer = new MantikorClientChannelInitializer(
      httpResponse -> responseFuture.complete(httpResponse));
    Bootstrap bootstrap = new Bootstrap();
    setupChannel(channelClazz, channelInitializer, bootstrap);
  }

  private void setupChannel(
    Class<? extends Channel> channelClazz,
    ChannelHandler channelInitializer,
    Bootstrap bootstrap
  ) throws InterruptedException {
    channel = bootstrap.channel(channelClazz)
      .group(workerGroup)
      .option(ChannelOption.TCP_NODELAY, true)
      .option(ChannelOption.SO_BACKLOG, 50)
      .handler(channelInitializer)
      .connect(target)
      .sync().channel();
  }

  private void disconnect() {
    channel.close();
    workerGroup.shutdownGracefully();
  }

  /**
   * Execute an {@link HttpRequest}. The future of the response will be
   * returned.
   *
   * @param request HTTPRequest to be executed.
   * @return Future of the response.
   */
  public Future<HttpResponse> sendRequest(HttpRequest request) {
    connect();
    channel.writeAndFlush(request);
    disconnect();
    return responseFuture;
  }
}
