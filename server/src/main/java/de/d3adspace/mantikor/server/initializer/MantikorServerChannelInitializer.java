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

package de.d3adspace.mantikor.server.initializer;

import de.d3adspace.mantikor.codec.netty.HTTPRequestDecoder;
import de.d3adspace.mantikor.codec.netty.HTTPResponseEncoder;
import de.d3adspace.mantikor.server.MantikorServer;
import de.d3adspace.mantikor.server.connection.MantikorConnection;
import de.d3adspace.mantikor.server.processor.HTTPRequestProcessor;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import java.util.Objects;

/**
 * Initializer for all connections.
 *
 * @author Felix 'SasukeKawaii' Klauke
 */
public class MantikorServerChannelInitializer extends ChannelInitializer<SocketChannel> {

  private final HTTPRequestProcessor requestProcessor;

  private MantikorServerChannelInitializer(HTTPRequestProcessor requestProcessor) {
    this.requestProcessor = requestProcessor;
  }

  public static MantikorServerChannelInitializer withRequestProcessor(HTTPRequestProcessor requestProcessor) {
    Objects.requireNonNull(requestProcessor, "Request processor should not be null.");

    return new MantikorServerChannelInitializer(requestProcessor);
  }

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {
    ChannelPipeline pipeline = socketChannel.pipeline();

    MantikorConnection connection = MantikorConnection.create(socketChannel, requestProcessor);

    pipeline.addLast("httpRequestDecoder", new HTTPRequestDecoder());
    pipeline.addLast("httpResponseEncoder", new HTTPResponseEncoder());
    pipeline.addLast("mantikorConnection", connection);
  }
}
