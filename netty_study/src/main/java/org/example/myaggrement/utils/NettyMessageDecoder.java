package org.example.myaggrement.utils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.example.myaggrement.entity.MessageBody;
import org.example.myaggrement.entity.MessageHeader;
import org.example.myaggrement.entity.NettyMessage;

import java.util.List;

/**
 * @author yoveuio
 * @version 1.0
 * @className NettyMessageDecoder
 * @description Netty解码工具
 * @date 2021/2/3 22:24
 */
public class NettyMessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        NettyMessage.NettyMessageBuilder builder = NettyMessage.builder();
        MessageHeader.MessageHeaderBuilder headerBuilder = MessageHeader.builder();
        MessageBody.MessageBodyBuilder<Object> messageBody = MessageBody.builder();
    }
}
