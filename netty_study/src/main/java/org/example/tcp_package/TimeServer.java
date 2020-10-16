package org.example.tcp_package;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @ClassName TimeServer
 * @Description TimeServer
 *  利用LineBasedFrameDecoder解决TCP粘包和拆包问题
 *  工作原理：
 *  依次遍历ByteBuf中的可读字节，判断是否有'\n'或者'\r\n',如果有，就以此位置为结束位置，
 *  从可读索引到结束位置区间的字节就组成了一行。同时支持配置单行的最大长度。
 *  如果连续读取到最大长度后仍然没有发现换行符，就会抛出异常，同时忽略吊之前读到的异常码流。
 *
 *  StringDecoder：
 *  此功能非常简单，就是将接收到的对象转换成字符串，然后继续调用后面的Handler。
 *  LineBasedFrameDecoder+StringDecoder就是换行切换的文本解码器
 * @Author yoveuio
 * @Date 2020/10/15 19:46
 * @Version 1.0
 */
public class TimeServer {

    public void bind(int port) {
        //NioEventLoopGroup是线程组，包含了一组NIO线程，专门用于网络事件的处理。实际上就是两个Reactor数组
        //一个用于服务端接收客户端的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //用于进行SocketChannel的网络读写
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //用于启动NIO服务端的辅助启动类，目的是降低服务端的开发复杂度
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //对应ServerSocketChannel类
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .option(ChannelOption.SO_BACKLOG, 1024)
                //绑定IO时间处理器，类似于Reactor模式中的Handler类
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        ch.pipeline().addLast(new StringDecoder());

                        ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new TimeServerHandler());
                    }
                });

        try {
            //绑定端口，同步等待成功
            //ChannelFuture的作用类似于java.util.concurrent.Future,主要用于异步操作的通知回调
            ChannelFuture f = serverBootstrap.bind().sync();
            System.out.println("Server start listen at " + port);
            //等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //退出线程
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length>0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        new TimeServer().bind(port);
    }
}
