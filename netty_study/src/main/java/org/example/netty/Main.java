package org.example.netty;

import io.netty.buffer.*;
import io.netty.channel.AbstractChannel;
import io.netty.channel.Channel;


/**
 * @author yoveuio
 * @version 1.0
 * @className Main
 * @description netty源码解析
 * @date 2021/2/15 18:52
 */
@SuppressWarnings("unused")
public class Main {
    static ByteBuf byteBuf = Unpooled.buffer();

    /**
     * AbstractByteBuf
     */
    private AbstractByteBuf abstractByteBuf;

    /**
     * 对引用进行技术，类似于JVM内存回收的对象引用计数器，用于跟踪对象的分配和销毁
     */
    private AbstractReferenceCountedByteBuf abstractReferenceCountedByteBuf;

    /**
     * `UnpooledHeapByteBuf`是基于堆内存进行内存分配的字节缓冲区，
     * 它没有基于对象池技术实现，也就意味着每次IO的读写都会创建一个新的`UnpooledHeapByteBuf`，
     * 频繁进行大块内存的分配和回收对性能会造成一定影响，但是相比于堆外内存的申请和释放，它的成本会是低一些。
     * 相比于`PooledHeapByteBuf`，其实现原理更加简单，也不容易出现内存管理方面的问题。
     */
    private UnpooledHeapByteBuf unpooledHeapByteBuf;

    /**
     * 分配器
     */
    private ByteBufAllocator allocator;

    /**
     * Channel抽象类，抽象了多种功能
     */
    private Channel channel;

    /**
     * 采用聚合的方式封装了各种功能
     */
    private AbstractChannel abstractChannel;

    public static void main(String[] args) {
        byteBuf.writeInt(1);
    }
}
