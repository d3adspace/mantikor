package de.d3adspace.mantikor.server.connection;

import de.d3adspace.mantikor.commons.HttpRequest;
import de.d3adspace.mantikor.server.processor.HttpRequestProcessor;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Objects;

public class MantikorConnection extends
  SimpleChannelInboundHandler<HttpRequest> {
  private final Channel channel;
  private final HttpRequestProcessor requestProcessor;

  private MantikorConnection(
    Channel channel,
    HttpRequestProcessor requestProcessor
  ) {
    this.channel = channel;
    this.requestProcessor = requestProcessor;
  }

  /**
   * Create request processor based connection for a specific channel.
   *
   * @param channel Channel.
   * @param requestProcessor Request processor.
   * @return Connection.
   */
  public static MantikorConnection create(
    Channel channel,
    HttpRequestProcessor requestProcessor
  ) {
    Objects.requireNonNull(channel, "Channel should not be null.");
    Objects.requireNonNull(requestProcessor,
      "Request processor should not be null.");
    return new MantikorConnection(channel, requestProcessor);
  }

  @Override
  protected void channelRead0(
    ChannelHandlerContext channelHandlerContext,
    HttpRequest request
  ) {
    var response = requestProcessor.processRequest(request);
    channel.writeAndFlush(response);
  }
}
