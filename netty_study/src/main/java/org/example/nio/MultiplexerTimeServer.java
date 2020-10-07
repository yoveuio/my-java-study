package org.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName MultiplexTimeServer
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/9/25 21:32
 * @Version 1.0
 */
public class MultiplexerTimeServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel socketChannel;

    private volatile boolean stop;

    /**
     * 构造方法，并且进行资源的初始化工作
     * 如果出现异常，例如端口号被占用则系统退出
     * <p>
     * 要点：
     * 每一个ServerSocketChannel在Selector中注册时，都会创建一个selectionKey作为表示
     * 选择键将Channel与Selector建立了关系，并维护了channel事件
     * 可以通过cancel方法取消键,取消的键不会立即从selector中移除,而是添加到cancelledKeys中,
     * 在下一次select操作时移除它.所以在调用某个key时,需要使用isValid进行校验.
     *
     *  {@code OP_ACCEPT 操作位用于接受操作——1<<4}
     *  {@code OP_CONNECT 用于套接字连接操作的操作集位——1<<3}
     *  {@code OP_READ 读操作的操作位——1<<2}
     *  {@code OP_WRITE 写操作的操作位——1<<0}
     * @param port 端口号
     */
    public MultiplexerTimeServer(int port) {
        try {
            //创建多路复用器Selector、ServerSocketChannel
            selector = Selector.open();
            socketChannel = ServerSocketChannel.open();
            //将channel设置为非阻塞模式
            //连接指示（对连接的请求）的最大队列长度被设置为backlog参数。如果队列满时收到连接指示，则拒绝该连接
            //将Channel注册到Selector，并且监听SelectionKey.OP_ACCEPT操作位
            //SelectionKey.OP_ACCEPT代表连接可接收
            socketChannel.configureBlocking(false);
            socketChannel.socket().bind(new InetSocketAddress(port), 1024);
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    /**
     * 创建一个线程，让selector循环询问遍历注册在其上的channel。
     */
    public void run() {
        while (!stop) {
            try {
                //让selector每一秒被唤醒一次
                selector.select(1000);
                //当有就绪状态的channel时，返回该Channel的SelectionKey集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    //remove方法删除上次调用的next方法获得的元素
                    //remove方法必须紧跟着next方法执行，否则当集合出现结构性变化时会出异常
                    //这里的remove方法是删除set集合中的元素。iterator可以安全地删除集合中的元素
                    iterator.remove();
                    try {
                        //对有就绪状态的Channel集合迭代，完成网络的异步读写操作
                        handleInput(key);
                    } catch (IOException e) {
                        e.printStackTrace();
                        key.cancel();
                        if (key.channel() != null) {
                            key.channel().close();
                        }
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        //多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据Key的操作位得知网络事件类型，通过ServerSocketChannel的accept接收客户端的连接请求
     * 并创建SocketChannel实例。完成上诉操作相当于完成了TCP的三次握手，TCP链路建立。
     *
     * 需要注意：我们需要将新创建的SocketChannel设置为异步非阻塞，同时对TCP参数进行设置，例如TCP接收和发送缓冲区的大小等。
     * 为了简单起见，下例并没有对参数进行设置
     *
     * @param key
     * @throws IOException
     */
    private void handleInput(SelectionKey key) throws IOException {
        //判断键是否有效
        if (key.isValid()) {
            //处理新接入的请求信息
            if (key.isAcceptable()) {
                //下列代码即相当于创建TCP连接
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                //在selector里面添加一个新的连接
                //将建立成功的SocketChannel放入到监听的队列，用以接收消息
                sc.register(selector, SelectionKey.OP_READ);
            }
            //如果是可读消息的请求
            if (key.isReadable()) {
                //读取数据
                SocketChannel sc = (SocketChannel) key.channel();
                //分配字节缓冲区,并调用read方法读取码流，它的read方法时非阻塞的
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                //通过read方法返回的值有三种结果：
                //返回值大于0：读取到了字节
                //返回值为-1：链路已经关闭。需要关闭SocketChannel释放资源
                //返回值等于0：没有读取到字节，属于正常场景可以忽略
                if (readBytes > 0) {
                    //对缓冲区当前位置(有效数据的位置)进行标识。并将position指针移至开头
                    //这样读写都只对有效数据进行操作了
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    //将收到的消息进行处理之后发送响应消息
                    String body = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println("The time server receive order: " +
                            body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                            new Date(System.currentTimeMillis()).toString() : "Bad ORDER";
                    doWrite(sc, currentTime);
                } else if (readBytes < 0) {
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}