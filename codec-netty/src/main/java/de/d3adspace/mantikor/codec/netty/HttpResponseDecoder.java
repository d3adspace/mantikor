package de.d3adspace.mantikor.codec.netty;

import de.d3adspace.mantikor.commons.HttpResponse;
import de.d3adspace.mantikor.commons.parser.HttpResponseFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class HttpResponseDecoder extends ByteToMessageDecoder {

  private final HttpResponseFactory responseParser = new HttpResponseFactory();

  @Override
  protected void decode(
    ChannelHandlerContext channelHandlerContext,
    ByteBuf byteBuf,
    List<Object> list
  ) throws Exception {
    byte[] bytes = new byte[byteBuf.readableBytes()];
    byteBuf.readBytes(bytes);
    String rawData = new String(bytes);
    HttpResponse httpResponse = responseParser.parse(rawData);
    list.add(httpResponse);
  }
}
