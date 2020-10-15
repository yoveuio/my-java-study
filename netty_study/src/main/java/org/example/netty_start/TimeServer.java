package org.example.netty_start;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @ClassName TimeServer
 * @Description
 *  介绍使用netty框架实现一个简单的NIO程序
 *  官方提供NIO接口实现TimeServer流程
 *      1.创建ServerSocketChannel，配置它危非阻塞模式
 *      2.绑定监听，配置TCP参数，例如backlog大小
 *      3.创建一个独立的IO线程，用于轮询多路复用器
 *      4.创建Selector，将之间创建的ServerSocketChannel注册到Selector上，监听SelectionKey.ACCEPT
 *      5.启动IO线程，再循环体中执行Selector.select()方法，轮询就绪的Channel
 *      6.当轮询到了处于就绪状态的Channel时，就对其进行处理
 *      7.设置新接入的客户端链路SocketChannel为非阻塞模式，配置其他的一些TCP参数
 *      8.将SocketChannel注册到Selector，监听OP_READ操作位
 * @Author yoveuio
 * @Date 2020/10/12 21:01
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
