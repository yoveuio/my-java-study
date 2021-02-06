package org.example.myaggrement.utils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.myaggrement.entity.MessageHeader;
import org.example.myaggrement.entity.NettyMessage;
import org.example.myaggrement.entity.enumerate.MessageType;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author yoveuio
 * @version 1.0
 * @className HeartBeatReqHandler
 * @description 心跳请求报文
 *  握手成功之后，由客户端定期发送心跳信息。由于NioEventLoop是一个Schedule，因而支持定时器的执行
 *  心跳计时器的单位是毫秒，默认为5000，即5秒发送一条心跳信息
 * @date 2021/2/4 22:11
 */
public class HeartBeatReqHandler extends ChannelInboundHandlerAdapter {
    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(2);
        NettyMessage message = (NettyMessage) msg;
        if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.LOGIN_RESP.getValue()) {
            // 可以通过协程优化
            heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatTask(ctx), 0, 5000,
                    TimeUnit.MILLISECONDS);
        }
        else if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.HEARTBEAT_RESP.getValue()) {
            System.out.println("Client receive server heart beat message :---> " + message);
        }
        else {
            ctx.fireChannelRead(msg);
        }
    }

    private class HeartBeatTask implements Runnable{
        private final ChannelHandlerContext ctx;

        public HeartBeatTask(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            NettyMessage message = buildHeatBeat();
            System.out.println("Client send heart beat message to server: --->" + heartBeat);
            ctx.writeAndFlush(heartBeat);
        }

        private NettyMessage buildHeatBeat() {
            NettyMessage message = new NettyMessage();
            MessageHeader header = new MessageHeader();
            header.setType(MessageType.HEARTBEAT_RESP.getValue());
            message.setHeader(header);
            return message;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (heartBeat != null) {
            heartBeat.cancel(true);
            heartBeat = null;
        }

        ctx.fireExceptionCaught(cause);
    }
}
