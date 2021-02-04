package org.example.myaggrement.utils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.example.myaggrement.codec.protostuff.ProtostuffSerialization;
import org.example.myaggrement.entity.NettyMessage;

/**
 * @author yoveuio
 * @version 1.0
 * @className NettyMessageDecoder
 * @description Netty解码工具
 * @date 2021/2/3 22:24
 */
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

    /**
     * 支持自动的TCP粘包和半包处理，只需要给出信息长度的字段偏移量和消息长度自身所占的字节，Netty就能完成对半包的处理；
     * 同时实现ByteToMessageDecoder类
     * 返回就是整包消息或者为空
     * @param maxFrameLength 帧的最大长度。 如果帧的长度大于此值，则将抛出TooLongFrameException
     * @param lengthFieldOffset 长度字段的偏移量
     * @param lengthFieldLength 长度字段的长
     */
    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        try {
            byte[] dstBytes = new byte[in.readableBytes()];
            in.readBytes(dstBytes, 0, in.readableBytes());
            return ProtostuffSerialization.deserialize(dstBytes, NettyMessage.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
