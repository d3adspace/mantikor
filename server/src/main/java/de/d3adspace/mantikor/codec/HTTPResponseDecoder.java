package de.d3adspace.mantikor.codec;

import de.d3adspace.mantikor.commons.HTTPResponse;
import de.d3adspace.mantikor.commons.parser.HTTPResponseParser;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class HTTPResponseDecoder extends ByteToMessageDecoder {

    private final HTTPResponseParser responseParser = new HTTPResponseParser();

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        String rawData = byteBuf.toString(CharsetUtil.UTF_8);

        HTTPResponse httpResponse = responseParser.parse(rawData);

        list.add(httpResponse);
    }
}
