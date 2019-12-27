package de.d3adspace.mantikor.codec.netty;

import de.d3adspace.mantikor.commons.HttpResponse;
import de.d3adspace.mantikor.commons.composer.HttpResponseComposer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class HttpResponseEncoder extends MessageToByteEncoder<HttpResponse> {
  private final HttpResponseComposer responseComposer =
    new HttpResponseComposer();

  @Override
  protected void encode(
    ChannelHandlerContext channelHandlerContext,
    HttpResponse response,
    ByteBuf byteBuf
  ) {
    String composedResponse = responseComposer.compose(response);
    ByteBufUtil.writeUtf8(byteBuf, composedResponse);
  }
}
