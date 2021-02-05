package org.example.myaggrement.utils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.myaggrement.entity.MessageHeader;
import org.example.myaggrement.entity.NettyMessage;
import org.example.myaggrement.entity.enumerate.MessageType;

/**
 * @author yoveuio
 * @version 1.0
 * @className LoginAuthReqHandler
 * @description 握手和安全认证
 * @date 2021/2/4 21:29
 */
public class LoginAuthReqHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(buildLoginReqA());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        // 判断是否是握手应答信息，如果不是就透传给后面的ChannelHandler进行处理
        if (message.getHeader() != null &&
                message.getHeader().getType() == MessageType.LOGIN_RESP.getValue()) {
            byte loginResult = (Byte) message.getBody();
            if (loginResult != 0) {
                // fail
                ctx.close();
            } else {
                System.out.println("Login is Ok: " + message);
                ctx.fireChannelRead(message);
            }
        }
        else {
            ctx.fireChannelRead(message);
        }
    }

    private NettyMessage buildLoginReqA() {
        // 当客户端和服务端TCP三次握手之后，由客户端构造握手请求信息发送给服务端，由于采用IP白名单认证机制
        // 不需要携带请求体
        NettyMessage message = new NettyMessage();
        MessageHeader header = new MessageHeader();
        header.setType(MessageType.LOGIN_REQ.getValue());
        message.setHeader(header);
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.fireExceptionCaught(cause);
    }
}
