package org.example.netty_start;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

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
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChildChannelHandler());

        try {
            //绑定端口，同步等待成功
            ChannelFuture f = serverBootstrap.bind(port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
