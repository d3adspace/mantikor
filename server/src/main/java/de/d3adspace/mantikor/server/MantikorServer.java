/*
 * Copyright (c) 2017 D3adspace
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.d3adspace.mantikor.server;

import de.d3adspace.mantikor.commons.HTTPRequest;
import de.d3adspace.mantikor.commons.HTTPResponse;
import de.d3adspace.mantikor.commons.codec.HTTPHeaders;
import de.d3adspace.mantikor.server.config.MantikorConfig;
import de.d3adspace.mantikor.server.initializer.MantikorServerChannelInitializer;
import de.d3adspace.mantikor.server.utils.NettyUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Basic server abstraction.
 *
 * @author Felix 'SasukeKawaii' Klauke
 */
public abstract class MantikorServer implements Mantikor {

  /**
   * The config for the server.
   */
  private final MantikorConfig config;

  /**
   * The logger for server actions
   */
  private final Logger logger;

  /**
   * Boss group for netty.
   */
  private EventLoopGroup bossGroup;

  /**
   * Worker group for netty.
   */
  private EventLoopGroup workerGroup;

  /**
   * The server channel.
   */
  private Channel channel;

  /**
   * Create a new server based on a config.
   *
   * @param config The config.
   */
  protected MantikorServer(MantikorConfig config) {
    this.config = config;
    this.logger = LoggerFactory.getLogger(MantikorServer.class);
  }

  @Override
  public void start() {
    bossGroup = NettyUtils.createEventLoopGroup(1);
    workerGroup = NettyUtils.createEventLoopGroup(4);

    Class<? extends ServerChannel> serverChannelClazz = NettyUtils.getServerChannelClass();
    ChannelHandler channelHandler = new MantikorServerChannelInitializer(this);

    logger.info("I am going to start the web server on {}:{}", config.getServerHost(),
        config.getServerPort());

    ServerBootstrap serverBootstrap = new ServerBootstrap();
    try {
      channel = serverBootstrap
          .group(bossGroup, workerGroup)
          .channel(serverChannelClazz)
          .childHandler(channelHandler)
          .option(ChannelOption.TCP_NODELAY, true)
          .bind(config.getServerHost(), config.getServerPort())
          .sync().channel();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    logger.info("Started the web server on {}:{}", config.getServerHost(),
        config.getServerPort());

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
   * Process the given request by generating a response and preparing it for delivery.
   *
   * @param request The request.
   * @return The response.
   */
  public HTTPResponse processRequest(HTTPRequest request) {
    // Let the implementation create a response
    HTTPResponse response = handleRequest(request);

    // Write some default headers
    HTTPHeaders headers = response.getHeaders();
    headers.addHeader(HTTPHeaders.KEY_SERVER, "Mantikor");
    headers.addHeader(HTTPHeaders.KEY_DATE, new Date().toString());
    headers.addHeader(HTTPHeaders.KEY_CONNECTION, "keep-alive");
    headers
        .addHeader(HTTPHeaders.KEY_CONTENT_LENGTH, String.valueOf(response.getBody().getLength()));

    return response;
  }

  /**
   * Handle an incoming http request.
   *
   * @param request The request.
   * @return The response.
   */
  protected abstract HTTPResponse handleRequest(HTTPRequest request);
}
