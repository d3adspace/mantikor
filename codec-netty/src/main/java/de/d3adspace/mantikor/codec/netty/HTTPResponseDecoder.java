package de.d3adspace.mantikor.codec.netty;

import de.d3adspace.mantikor.commons.HTTPResponse;
import de.d3adspace.mantikor.commons.parser.HTTPResponseParser;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * @author Ruby Hale <ruby@d3adspace.de>
 */
public class HTTPResponseDecoder extends ByteToMessageDecoder {

  private final HTTPResponseParser responseParser = new HTTPResponseParser();

  @Override
  protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf,
      List<Object> list) throws Exception {

    // Read raw bytes
    byte[] bytes = new byte[byteBuf.readableBytes()];
    byteBuf.readBytes(bytes);

    // Wrap into String
    String rawData = new String(bytes);

    HTTPResponse httpResponse = responseParser.parse(rawData);

    list.add(httpResponse);
  }
}
