package de.d3adspace.mantikor.client.connection;

import de.d3adspace.mantikor.commons.HTTPResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.function.Consumer;

public class MantikorClientConnection extends
  SimpleChannelInboundHandler<HTTPResponse> {

  private final Consumer<HTTPResponse> httpResponseConsumer;

  public MantikorClientConnection(Consumer<HTTPResponse> httpResponseConsumer) {
    this.httpResponseConsumer = httpResponseConsumer;
  }

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext,
    HTTPResponse httpResponse) throws Exception {

    httpResponseConsumer.accept(httpResponse);
  }
}
