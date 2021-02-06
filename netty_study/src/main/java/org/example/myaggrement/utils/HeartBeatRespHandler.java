package org.example.myaggrement.utils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.myaggrement.entity.MessageHeader;
import org.example.myaggrement.entity.NettyMessage;
import org.example.myaggrement.entity.enumerate.MessageType;

/**
 * @author yoveuio
 * @version 1.0
 * @className HeartBeatRespHandler
 * @description 心跳应答
 * @date 2021/2/4 22:37
 */
public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(1);
        NettyMessage message = (NettyMessage) msg;
        if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.getValue()) {
            System.out.println("Receive client heart beat message : --->" + message);
            NettyMessage heartBeat = buildHeatBeat();
            System.out.println("Send heart response message to client : ---> " + heartBeat);
            ctx.writeAndFlush(heartBeat);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildHeatBeat() {
        NettyMessage message = new NettyMessage();
        MessageHeader header = new MessageHeader();
        header.setType(MessageType.HEARTBEAT_RESP.getValue());
        message.setHeader(header);
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
