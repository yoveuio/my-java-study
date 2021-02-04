package org.example.myaggrement.utils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.example.myaggrement.codec.protostuff.ProtostuffSerialization;
import org.example.myaggrement.entity.NettyMessage;

import java.nio.charset.StandardCharsets;
import java.util.Map;

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
        if (msg == null || msg.getHeader() == null) {
            throw  new Exception("The encode message is null");
        }
        out.writeInt(msg.getHeader().getCrcCode());
        out.writeInt(msg.getHeader().getVersion());
        out.writeInt(msg.getHeader().getLength());
        out.writeLong(msg.getHeader().getSessionID());
        out.writeByte(msg.getHeader().getType());
        out.writeByte(msg.getHeader().getPriority());
        out.writeInt(msg.getHeader().getAttachment().size());

        String key = null;
        byte[] keyArray = null;
        Object value = null;
        byte[] valueArray = null;
        for (Map.Entry<String, Object> param: msg.getHeader().getAttachment().entrySet()) {
            key = param.getKey();
            keyArray = key.getBytes(StandardCharsets.UTF_8);
            value = param.getValue();
            valueArray = ProtostuffSerialization.serialize(value);
            out.writeInt(keyArray.length);
            out.writeBytes(keyArray);
            out.writeInt(valueArray.length);
            out.writeBytes(valueArray);
        }

        if (msg.getBody() != null) {
            valueArray = ProtostuffSerialization.serialize(msg.getBody());
            out.writeInt(valueArray.length);
            out.writeBytes(valueArray);
        }
        else {
            out.writeInt(0);
        }
    }
}
