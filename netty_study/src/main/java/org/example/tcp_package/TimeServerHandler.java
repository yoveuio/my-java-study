package org.example.tcp_package;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

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
        String body = (String) msg;
        System.out.println("The time server receive order : " + body +
                " ; the counter is : " + ++counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?
                new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        ctx.writeAndFlush(currentTime);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
