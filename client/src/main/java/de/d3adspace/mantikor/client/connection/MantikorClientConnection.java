package de.d3adspace.mantikor.client.connection;

import de.d3adspace.mantikor.commons.HttpResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.function.Consumer;

public class MantikorClientConnection extends
  SimpleChannelInboundHandler<HttpResponse> {

  private final Consumer<HttpResponse> httpResponseConsumer;

  public MantikorClientConnection(Consumer<HttpResponse> httpResponseConsumer) {
    this.httpResponseConsumer = httpResponseConsumer;
  }

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                              HttpResponse httpResponse) throws Exception {
    httpResponseConsumer.accept(httpResponse);
  }
}
