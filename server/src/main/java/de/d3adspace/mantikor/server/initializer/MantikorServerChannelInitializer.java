package de.d3adspace.mantikor.server.initializer;

import de.d3adspace.mantikor.codec.netty.HttpRequestDecoder;
import de.d3adspace.mantikor.codec.netty.HttpResponseEncoder;
import de.d3adspace.mantikor.server.connection.MantikorConnection;
import de.d3adspace.mantikor.server.processor.HttpRequestProcessor;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import java.util.Objects;

/**
 * Initializer for all connections.
 *
 * @author Felix 'SasukeKawaii' Klauke
 */
public class MantikorServerChannelInitializer extends
  ChannelInitializer<SocketChannel> {

  private final HttpRequestProcessor requestProcessor;

  private MantikorServerChannelInitializer(
    HttpRequestProcessor requestProcessor) {
    this.requestProcessor = requestProcessor;
  }

  /**
   * Create a channel initializer with a pre-defined request processor.
   *
   * @param requestProcessor Request processor.
   * @return Channel initializer.
   */
  public static MantikorServerChannelInitializer withRequestProcessor(
    HttpRequestProcessor requestProcessor
  ) {
    Objects.requireNonNull(requestProcessor,
      "Request processor should not be null.");
    return new MantikorServerChannelInitializer(requestProcessor);
  }

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {
    ChannelPipeline pipeline = socketChannel.pipeline();

    MantikorConnection connection = MantikorConnection
      .create(socketChannel, requestProcessor);

    pipeline.addLast("httpRequestDecoder", new HttpRequestDecoder());
    pipeline.addLast("httpResponseEncoder", new HttpResponseEncoder());
    pipeline.addLast("mantikorConnection", connection);
  }
}
