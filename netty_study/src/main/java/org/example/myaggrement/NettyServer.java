package org.example.myaggrement;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.example.myaggrement.entity.NettyConstant;
import org.example.myaggrement.utils.HeartBeatRespHandler;
import org.example.myaggrement.utils.LoginAuthRespHandler;
import org.example.myaggrement.utils.NettyMessageDecoder;
import org.example.myaggrement.utils.NettyMessageEncoder;

/**
 * @author yoveuio
 * @version 1.0
 * @className NettyServer
 * @description 主要工作握手的接入认证，不用关信断连重连等
 * @date 2021/2/5 21:37
 */
public class NettyServer {
    public void bind() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("decoder",
                                new NettyMessageDecoder(1024 * 1024, 4, 4));
                        ch.pipeline().addLast("encoder", new NettyMessageEncoder());
                        // 一段时间之内没有读取任何数据引发ReadTimeoutException异常
                        ch.pipeline().addLast("readTimeOutHandler", new ReadTimeoutHandler(50));
                        ch.pipeline().addLast("loginAuthHandler", new LoginAuthRespHandler());
                        ch.pipeline().addLast("HeartBeatHandler", new HeartBeatRespHandler());
                    }
                });
        b.bind(NettyConstant.REMOTE_IP, NettyConstant.REMOTE_PORT).sync();
        System.out.println("Netty server start ok: " + NettyConstant.REMOTE_IP + " : " +
                NettyConstant.REMOTE_PORT);
    }

    public static void main(String[] args) throws Exception {
        new NettyServer().bind();
    }
}
