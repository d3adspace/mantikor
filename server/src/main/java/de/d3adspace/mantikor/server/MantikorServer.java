package de.d3adspace.mantikor.server;

import de.d3adspace.constrictor.netty.NettyUtils;
import de.d3adspace.mantikor.commons.HttpRequest;
import de.d3adspace.mantikor.commons.HttpResponse;
import de.d3adspace.mantikor.commons.codec.HttpHeaders;
import de.d3adspace.mantikor.server.config.MantikorConfig;
import de.d3adspace.mantikor.server.initializer.MantikorServerChannelInitializer;
import de.d3adspace.mantikor.server.processor.HttpRequestProcessor;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MantikorServer implements Mantikor, HttpRequestProcessor {
  private final MantikorConfig config;
  private final Logger logger;
  private EventLoopGroup bossGroup;
  private EventLoopGroup workerGroup;
  private Channel channel;

  protected MantikorServer(MantikorConfig config) {
    this.config = config;
    this.logger = LoggerFactory.getLogger(MantikorServer.class);
  }

  @Override
  public void start() {
    bossGroup = NettyUtils.createEventLoopGroup(1, "netty-boss");
    workerGroup = NettyUtils.createEventLoopGroup(4, "netty-worker");
    var serverChannelClazz = NettyUtils.getServerSocketChannel();
    var channelHandler = MantikorServerChannelInitializer
      .withRequestProcessor(this);
    setupBootstrap(serverChannelClazz, channelHandler);
  }

  private void setupBootstrap(
    Class<? extends ServerChannel> serverChannelClazz,
    MantikorServerChannelInitializer channelHandler
  ) {
    logger.info("I am going to start the web server on {}:{}",
      config.getServerHost(), config.getServerPort());
    trySetupBootstrap(serverChannelClazz, channelHandler);
    logger.info("Started the web server on {}:{}",
      config.getServerHost(), config.getServerPort());
  }

  private void trySetupBootstrap(
    Class<? extends ServerChannel> serverChannelClazz,
    MantikorServerChannelInitializer channelHandler
  ) {
    ServerBootstrap serverBootstrap = new ServerBootstrap();
    try {
      setupChannel(serverBootstrap, serverChannelClazz, channelHandler);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void setupChannel(
    ServerBootstrap serverBootstrap,
    Class<? extends ServerChannel> serverChannelClazz,
    MantikorServerChannelInitializer channelHandler
  ) throws InterruptedException {
    channel = serverBootstrap
      .group(bossGroup, workerGroup)
      .channel(serverChannelClazz)
      .childHandler(channelHandler)
      .option(ChannelOption.TCP_NODELAY, true)
      .bind(config.getServerHost(), config.getServerPort())
      .sync().channel();
  }

  @Override
  public void stop() {
    logger.info("Server is going to stop.");

    channel.close();

    bossGroup.shutdownGracefully();
    workerGroup.shutdownGracefully();
  }

  @Override
  public boolean isRunning() {

    return channel != null && channel.isOpen();
  }

  /**
   * Process the given request by generating a response and preparing it for
   * delivery.
   *
   * @param request The request.
   * @return The response.
   */
  @Override
  public HttpResponse processRequest(HttpRequest request) {
    Objects.requireNonNull(request, "Request shoult not be null.");
    var response = handleRequest(request);
    writeDefaultHeaders(response);
    return response;
  }

  private void writeDefaultHeaders(HttpResponse response) {
    HttpHeaders headers = response.getHeaders();
    headers.addHeader(HttpHeaders.KEY_SERVER, "Mantikor");
    headers.addHeader(HttpHeaders.KEY_DATE,
      new SimpleDateFormat().format(new Date()));
    headers.addHeader(HttpHeaders.KEY_CONNECTION, "keep-alive");
    headers
      .addHeader(HttpHeaders.KEY_CONTENT_LENGTH,
        String.valueOf(response.getBodySize()));
  }

  /**
   * Handle an incoming http request.
   *
   * @param request The request.
   * @return The response.
   */
  protected abstract HttpResponse handleRequest(HttpRequest request);
}
