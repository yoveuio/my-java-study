package org.example.netty_start;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * @ClassName ChildChannelHandler
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/12 21:57
 * @Version 1.0
 */
public class ChildChannelHandler implements ChannelHandler {
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}
