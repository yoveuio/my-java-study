package org.example.myaggrement.util;

import com.google.protobuf.Any;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.myaggrement.entity.NettyMessageProto;
import org.example.myaggrement.entity.enumerate.MessageType;

/**
 * @author yoveuio
 * @version 1.0
 * @className LoginAuthReqHandler
 * @description 握手消息
 * @date 2021/2/1 18:09
 */
public class LoginAuthReqHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessageProto.NettyMessage message = (NettyMessageProto.NettyMessage) msg;
        int type;
        if (message.getHeader() != null
        && (type = message.getHeader().getType().byteAt(0) & MessageType.LOGIN_RESP.getValue()) == 1) {
            Any content = message.getObject().getContent();

        }
    }
}
