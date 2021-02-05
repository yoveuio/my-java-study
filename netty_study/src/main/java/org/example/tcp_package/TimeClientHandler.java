package org.example.tcp_package;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.myaggrement.entity.MessageHeader;
import org.example.myaggrement.entity.NettyMessage;

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
        MessageHeader header = new MessageHeader(0x0001, 1, 1L, (byte)1, (byte)1, null);
        NettyMessage message = new NettyMessage(header, 1);
        for (int i=0; i<100; ++i) {
            //为了节约系统资源,消息不会直接进入channel,而是先传入缓冲区,然后一次性传入channel
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //msg已经被StringDecoder译码为String类型
        System.out.println(msg.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("Unexpected exception from downstream : " +
                cause.getMessage());
        ctx.close();
    }
}
