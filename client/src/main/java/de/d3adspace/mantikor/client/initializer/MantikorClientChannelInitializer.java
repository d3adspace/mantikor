package de.d3adspace.mantikor.client.initializer;

import de.d3adspace.mantikor.client.connection.MantikorClientConnection;
import de.d3adspace.mantikor.codec.netty.HTTPRequestEncoder;
import de.d3adspace.mantikor.codec.netty.HTTPResponseDecoder;
import de.d3adspace.mantikor.commons.HTTPResponse;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import java.util.function.Consumer;

public class MantikorClientChannelInitializer extends
  ChannelInitializer<SocketChannel> {

  private final Consumer<HTTPResponse> httpResponseConsumer;

  public MantikorClientChannelInitializer(
    Consumer<HTTPResponse> httpResponseConsumer) {
    this.httpResponseConsumer = httpResponseConsumer;
  }

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {

    ChannelPipeline pipeline = socketChannel.pipeline();

    pipeline.addLast(new HTTPResponseDecoder());
    pipeline.addLast(new HTTPRequestEncoder());
    pipeline.addLast(new MantikorClientConnection(httpResponseConsumer));
  }
}
