package de.d3adspace.mantikor.codec.netty;

import de.d3adspace.mantikor.commons.HttpRequest;
import de.d3adspace.mantikor.commons.composer.HttpRequestComposer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class HttpRequestEncoder extends MessageToByteEncoder<HttpRequest> {

  private final HttpRequestComposer requestComposer = new HttpRequestComposer();

  @Override
  protected void encode(
    ChannelHandlerContext channelHandlerContext,
    HttpRequest request,
    ByteBuf byteBuf
  ) throws Exception {
    String compose = requestComposer.compose(request);
    ByteBufUtil.writeUtf8(byteBuf, compose);
  }
}
