package org.example.tcp_package;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * @ClassName TimeClientHandler
 * @Description 处理客户端
 * @Author yoveuio
 * @Date 2020/10/15 19:47
 * @Version 1.0
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

    private int counter;

    private byte[] req;

    public TimeClientHandler() {
        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for (int i=0; i<100; ++i) {
            message = Unpooled.buffer(req.length);
            //将消息输入输入缓冲区
            message.writeBytes(req);
            //为了节约系统资源,消息不会直接进入channel,而是先传入缓冲区,然后一次性传入channel
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String)msg;
        System.out.println("Now is : " + body + " : the counter is : " + ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("Unexpected exception from downstream : " +
                cause.getMessage());
        ctx.close();
    }
}
