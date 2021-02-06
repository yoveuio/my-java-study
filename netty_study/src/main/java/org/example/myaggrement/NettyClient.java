package org.example.myaggrement;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.example.myaggrement.entity.NettyConstant;
import org.example.myaggrement.utils.HeartBeatReqHandler;
import org.example.myaggrement.utils.LoginAuthReqHandler;
import org.example.myaggrement.utils.NettyMessageDecoder;
import org.example.myaggrement.utils.NettyMessageEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yoveuio
 * @version 1.0
 * @className NettyClient
 * @description 客户端主要用于初始化系统资源，根据配置信息发起连接
 * @date 2021/2/5 20:55
 */
public class NettyClient {
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    EventLoopGroup group = new NioEventLoopGroup();

    public void connect(int port, String host) {
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 限制单条消息太大导致内存移出或者畸形码流导致阶码错位引起内存分配失败
                            ch.pipeline().addLast("MessageDecoder",
                                    new NettyMessageDecoder(1024 * 1024,
                                            4, 4));
                            ch.pipeline().addLast("MessageEncoder",
                                    new NettyMessageEncoder());
                            ch.pipeline().addLast("readTimeoutHandler",
                                    new ReadTimeoutHandler(50));
                            ch.pipeline().addLast("LoginAuthHandler",
                                    new LoginAuthReqHandler());
                            ch.pipeline().addLast("HeartBeatHandler",
                                    new HeartBeatReqHandler());
                        }
                    });
            // 发起异步连接操作 一般不允许系统随随便便地使用随机端口
            ChannelFuture future = b.connect(
                    new InetSocketAddress(host, port),
                    new InetSocketAddress(NettyConstant.LOCAL_IP, NettyConstant.LOCAL_PORT)).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 断线重连机制。
            // 已经发送入队列中的消息不能丢失，应该采用通知机制，将发送失败的消息通知给业务侧，让业务做决定
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        connect(NettyConstant.REMOTE_PORT, NettyConstant.REMOTE_IP);
                        System.out.println("reconnect");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        new NettyClient().connect(NettyConstant.REMOTE_PORT, NettyConstant.REMOTE_IP);
    }
}
