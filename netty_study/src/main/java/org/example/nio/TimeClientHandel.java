package org.example.nio;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName TimeClientHandel
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/9/28 21:16
 * @Version 1.0
 */
public class TimeClientHandel implements Runnable {

    private final String host;
    private final int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;

    public TimeClientHandel(String host, int port) {
        this.host = host==null ? "127.0.0.1" : host;
        this.port = port;

        try {
            //初始化NIO的多路复用器和SocketChannel对象
            //创建了socketChannel对象之后需要将其设置为异步非阻塞模式
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    @Override
    public void run() {
        try {
            //尝试发送连接请求，连接失败退出系统
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while(!stop) {
            try {
                //与服务端一样，对多路复用器进行轮询
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(key);
                    } catch (IOException e) {
                        e.printStackTrace();
                        key.cancel();
                        if (key.channel() != null) {
                            key.channel().close();
                        }
                    }
                }
                System.out.println(1);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException{
        if (key.isValid()) {
            SocketChannel sc = (SocketChannel) key.channel();
            //说明当前通道还没有完成握手请求，需要完成TCP的握手
            if (key.isConnectable()) {
                //接收到服务端的ACK应答信息，并且判断连接结果
                if (sc.finishConnect()) {
                    //将当前Channel的读操作注册到多路复用器上
                    sc.register(selector, SelectionKey.OP_READ);
                    //将请求信息传递给服务端
                    doWrite(sc);
                } else {
                    System.exit(1);
                }
            }
            //处理服务端应答信息
            if (key.isReadable()) {
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println("Now is : " + body);
                    //调用完成之后关闭客户端
                    this.stop = true;
                } else if (readBytes < 0) {
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    private void doConnect() throws IOException {
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            //如果连接成功，则将SocketChannel注册到多路复用器上，并注册OP_READ
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        } else {
            //如果连接失败，说明服务端没有返回握手信息，需要注册到selector中，这样就能轮询到这个channel
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    /**
     * 对消息编码并进行发送
     * 由于发送是异步的会存在半包写的问题
     * @param sc SocketChannel通道
     * @throws IOException 如果消息发送失败则抛出异常
     */
    private void doWrite(SocketChannel sc) throws IOException{
        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        sc.write(writeBuffer);
        if (!writeBuffer.hasRemaining()) {
            System.out.println("Send order 2 server succeed");
        }
    }
}
