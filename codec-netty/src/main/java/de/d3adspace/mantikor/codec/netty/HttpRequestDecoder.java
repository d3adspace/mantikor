package de.d3adspace.mantikor.codec.netty;

import de.d3adspace.mantikor.commons.HttpRequest;
import de.d3adspace.mantikor.commons.parser.HttpRequestMessageFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class HttpRequestDecoder extends ByteToMessageDecoder {

  private final HttpRequestMessageFactory requestParser =
    new HttpRequestMessageFactory();

  @Override
  protected void decode(
    ChannelHandlerContext channelHandlerContext,
    ByteBuf byteBuf,
    List<Object> list
  ) {
    byte[] bytes = new byte[byteBuf.readableBytes()];
    byteBuf.readBytes(bytes);
    String rawData = new String(bytes);
    HttpRequest request = requestParser.parse(rawData);
    list.add(request);
  }
}
