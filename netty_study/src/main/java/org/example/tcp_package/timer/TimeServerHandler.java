package org.example.tcp_package.timer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.myaggrement.entity.NettyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName TimeServerHandler
 * @Description 处理客户端的消息并处理
 * @Author yoveuio
 * @Date 2020/10/15 18:58
 * @Version 1.0
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //msg已经被StringDecoder译码为String类型
        NettyMessage body = (NettyMessage) msg;
        System.out.println("The time server receive order : " + body +
                " ; the counter is : " + ++counter);
        System.out.println(body);
        ctx.writeAndFlush(body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
