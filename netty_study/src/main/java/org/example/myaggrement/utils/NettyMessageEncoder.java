package org.example.myaggrement.utils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.example.myaggrement.codec.protostuff.ProtostuffSerialization;
import org.example.myaggrement.entity.NettyMessage;

/**
 * @author yoveuio
 * @version 1.0
 * @className NettyMessageEncoder
 * @description 消息编码器
 * @date 2021/2/3 21:51
 */
public class NettyMessageEncoder extends MessageToByteEncoder<NettyMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, ByteBuf out) throws Exception {
        out.writeBytes(ProtostuffSerialization.serialize(msg));
    }
}
