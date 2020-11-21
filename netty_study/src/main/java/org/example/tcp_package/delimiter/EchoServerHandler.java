package org.example.tcp_package.delimiter;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @ClassName EchoServerHandler
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/5 21:28
 * @Version 1.0
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter{
    int counter = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("This is " + ++counter + " times receive client : [" +
                body + "]");
        body += "$_";
        ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(echo);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
