package org.example.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName AsyncTimeServerHandler
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/10 19:24
 * @Version 1.0
 */
public class AsyncTimeServerHandler implements Runnable{
    private int port;

    CountDownLatch latch;
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public AsyncTimeServerHandler(int port) {
        this.port = port;
        try {
            //创建异步的服务端通道AsynchronousServerSocketChannel，然后调用它的bind方法绑定监听端口。
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("The time server is start in port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //初始化CountDownLatch对象，他的作用是在完成一组执行的操作之前，允许当前的线程一直阻塞。
        //让线程在这里阻塞，用于演示
        latch = new CountDownLatch(1);
        //接收客户端的连接
        doAccept();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收客户端连接，只调用一个方法
     */
    private void doAccept() {
        /*
         * 接收两个参数:异步接收连接
         *  attachment:要附加到IO操作的对象;可以是null
         *  handler:消费结果的处理程序
         */
        asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
    }
}
