package de.d3adspace.mantikor.codec.netty;

import de.d3adspace.mantikor.server.commons.HTTPRequest;
import de.d3adspace.mantikor.server.commons.composer.HTTPRequestComposer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class HTTPRequestEncoder extends MessageToByteEncoder<HTTPRequest> {

    private final HTTPRequestComposer requestComposer = new HTTPRequestComposer();

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, HTTPRequest request, ByteBuf byteBuf) throws Exception {

        String compose = requestComposer.compose(request);
        ByteBufUtil.writeUtf8(byteBuf, compose);
    }
}
