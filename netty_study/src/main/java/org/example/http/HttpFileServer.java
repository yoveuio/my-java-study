package org.example.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author yoveuio
 * @version 1.0
 * @className HttpFileServer
 * @description http文件服务器
 * @date 2021/1/31 19:58
 */
public class HttpFileServer {
    private static final String DEFAULT_URL = "org/example/http/";

    public void run(final int port, final String url) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // HTTP请求信息解码
                            ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
                            // 多个信息转换成单一的FullHttpRequest对象
                            ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                            // HTTP响应信息编码
                            ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
                            // 支持异步发送较大的码流
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            // 用于文件服务器的业务逻辑处理
                            ch.pipeline().addLast("fileServerHandler", new HttpFileServerHandler());
                        }
                    });
            ChannelFuture future = b.bind("127.0.0.1", port).sync();
            System.out.println("HTTP文件目录服务器启动，网址是：" + "http://192.168.1.102");
            future.channel().closeFuture().sync();
        }
        finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length == 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        String url = DEFAULT_URL;
        if (args.length > 1)
            url = args[1];
        new HttpFileServer().run(port, url);
    }
}
