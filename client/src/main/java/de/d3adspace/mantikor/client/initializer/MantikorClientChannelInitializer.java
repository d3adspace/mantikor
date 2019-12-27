package de.d3adspace.mantikor.client.initializer;

import de.d3adspace.mantikor.client.connection.MantikorClientConnection;
import de.d3adspace.mantikor.codec.netty.HttpRequestEncoder;
import de.d3adspace.mantikor.codec.netty.HttpResponseDecoder;
import de.d3adspace.mantikor.commons.HttpResponse;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import java.util.function.Consumer;

public class MantikorClientChannelInitializer extends
  ChannelInitializer<SocketChannel> {

  private final Consumer<HttpResponse> httpResponseConsumer;

  public MantikorClientChannelInitializer(
    Consumer<HttpResponse> httpResponseConsumer) {
    this.httpResponseConsumer = httpResponseConsumer;
  }

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {
    ChannelPipeline pipeline = socketChannel.pipeline();
    pipeline.addLast(new HttpResponseDecoder());
    pipeline.addLast(new HttpRequestEncoder());
    pipeline.addLast(new MantikorClientConnection(httpResponseConsumer));
  }
}
