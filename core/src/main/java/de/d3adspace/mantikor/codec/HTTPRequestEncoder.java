package de.d3adspace.mantikor.codec;

import de.d3adspace.mantikor.commons.HTTPRequest;
import de.d3adspace.mantikor.commons.composer.HTTPRequestComposer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

import java.nio.CharBuffer;

public class HTTPRequestEncoder extends MessageToByteEncoder<HTTPRequest> {

    private final HTTPRequestComposer requestComposer = new HTTPRequestComposer();

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, HTTPRequest request, ByteBuf byteBuf) throws Exception {

        String compose = requestComposer.compose(request);
        ByteBufUtil.encodeString(channelHandlerContext.alloc(), CharBuffer.wrap(compose.toCharArray()), CharsetUtil.UTF_8);
    }
}
