package org.example.myaggrement.util;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author yoveuio
 * @version 1.0
 * @className LoginAuthReqHandler
 * @description 握手消息
 * @date 2021/2/1 18:09
 */
public class LoginAuthReqHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    }
}
