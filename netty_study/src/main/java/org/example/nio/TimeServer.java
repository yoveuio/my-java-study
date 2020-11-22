package org.example.nio;

import java.io.IOException;

/**
 * @ClassName TimeServer
 * @Description 本代码通过BIO和NIO进行比较
 *  NIO的实现比BIO复杂很多，但是使用就是越来越广泛，其优点如下
 *      1.客户端发器的连接操作是异步的，可以通过再多路复用器注册OP_CONNECT等待后续结果
 *        不需要像之前的客户端一样被同步阻塞
 *      2.SocketChannel的读写操作都是异步的，如果没有可读写的数据他不会同步等待，直接返回
 *        这样IO通信线路就可以处理其他的链路，不需要同步等待这个链路可用
 *      3.线程模型的优化：由于JDK的Selector再Linux等主流操作系统上通过epoll实现，他没有句柄数的限制
 *        这意味着一个Selector线程可以同时处理成千上万个客户端来连接，而且性能不会随着客户端的增加而线性下降
 *        适合高性能、高负载的网络服务器
 * @Author yoveuio
 * @Date 2020/9/20 11:22
 * @Version 1.0
 */

public class TimeServer {

    /**
     * 同步式的TimeServer
     *  通过服务端的accept()方法，直接建立一条线程与客户端进行通信
     * 伪异步IO模型的TimeServer
     *  将客户端的Socket封装成一个Task(实现Runnable接口)，并投递到后端线程池进行处理。
     *  虽然避免了每个请求都创建一个独立线程造成的线程资源耗尽问题。但是由于底层的通行任然采用同步阻塞模型
     *  无法从根本上解决问题
     *  原因：Java读写操作都是同步阻塞的，阻塞的时间都是取决与对方I/O线程的处理速度和网路IO的传输速度。
     *  一个健康的应用程序不应该依赖对方的处理速度，否则可靠性非常差。出现实验室运行良好，线上运行雪崩的情况
     *
     *
     * @param args 命令行参数列表，用于控制端口号
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int port = 8080;
        //命令行修改端口号
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        //Multiplex：多路复用
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);

        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}
