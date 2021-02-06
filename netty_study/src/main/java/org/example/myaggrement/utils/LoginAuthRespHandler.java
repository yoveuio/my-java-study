package org.example.myaggrement.utils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.myaggrement.entity.MessageHeader;
import org.example.myaggrement.entity.NettyMessage;
import org.example.myaggrement.entity.enumerate.MessageType;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yoveuio
 * @version 1.0
 * @className LoginAuthRespHandler
 * @description 服务器端握手应答信息报文
 * @date 2021/2/4 21:54
 */
public class LoginAuthRespHandler extends ChannelInboundHandlerAdapter {
    private final Map<String, Boolean> nodeCheck = new ConcurrentHashMap<>();
    private final String[] whiteList = {"127.0.0.1"};

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;

        if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.LOGIN_REQ.getValue()) {
            String nodeIndex = ctx.channel().remoteAddress().toString();
            NettyMessage loginResp = null;

            if (nodeCheck.containsKey(nodeIndex)) {
                loginResp = buildResponse((byte)-1);
            } else {
                InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
                String ip = address.getAddress().getHostAddress();
                boolean isOK = false;

                for (String WIP: whiteList) {
                    if (WIP.equals(ip)) {
                        isOK = true;
                        break;
                    }
                }

                loginResp = isOK ? buildResponse((byte)0) : buildResponse((byte)-1);
                if (isOK) nodeCheck.put(nodeIndex, true);
            }
            System.out.println("The login response is : " + loginResp +
                    " body [" + loginResp.getBody() + "]");
            ctx.writeAndFlush(loginResp);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildResponse (byte result) {
        NettyMessage message = new NettyMessage();
        MessageHeader header = new MessageHeader();
        header.setType(MessageType.LOGIN_RESP.getValue());
        message.setHeader(header);
        message.setBody(result);
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
