package org.example.tcp_package.delimiter;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @ClassName EchoClientHandler
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/14 21:45
 * @Version 1.0
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    static final String ECHO_REQ = "Hi, yoveuio.Welcome to Netty.$_";

    public EchoClientHandler(){}

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i=0; i<10; ++i) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("This is " + ++counter + " times receive server:" +
                msg + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
