package org.example.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @ClassName AcceptCompletionHandler
 * @Description 用于消除异步IO操作结果的处理程序
 *  实现了CompletionHandler接口允许指定完成处理程序以消耗异步操作的结果。当IO操作完成时。
 *  将调用completed方法，如果失败将会调用failed方法
 * @Author yoveuio
 * @Date 2020/10/10 19:50
 * @Version 1.0
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {

    /**
     * 当IO操作完成时调用
     * @param result IO操作的结果
     * @param attachment 被附加到IO操作的对象
     */
    @Override
    public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
        //处理新接入的客户端。当一个客户端连接成功之后，再异步接收新的客户端连接。形成一个循环
        attachment.asynchronousServerSocketChannel.accept(attachment, this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //第一个buffer指接收缓冲区，第二个buffer指操作结果附加到的对象，通知回调的时候作为入参使用
        //CompletionHandler用于接收回调的业务Handler
        result.read(buffer, buffer, new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
        exc.printStackTrace();
        attachment.latch.countDown();
    }
}
