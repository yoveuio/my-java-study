package org.example.myaggrement;

/**
 * @author yoveuio
 * @version 1.0
 * @className Main
 * @description Netty私有协议栈的开发
 *  主要功能如下：
 *      1.基于Netty的NIO通信框架，提高性能的异步通信能力
 *      2.提供信息的编解码框架，可以实现POJO的序列化和反序列化
 *      3.提供基于IP地址的白名单接入认证机制
 *      4.连路的有效性校验机制
 *      5.链路的断连重连机制
 *
 *  信息定义：Netty协议栈定义包含两部分：消息头和消息体
 *      消息头定义：
 *      名称：crcCode；类型：整型int；长度：32；描述：1）魔数0xABEF，表明是Netty协议消息，两个字节
 *                                                 2）主版本号：1~255，1个字节
 *                                                 3）此版本号：1~255，1个字节
 *      名称：length；类型：整型int；长度：32；描述：消息长度，整个消息，包括消息头的消息体
 *      名称：sessionID；类型：长整型long；长度：64；描述：集群节点内唯一，由会话ID生成器生成
 *      名称：type；类型：Byte；长度：8；描述：0：业务内请求信息
 *                                           1：业务内相应信息
 *                                           2：业务ONE WAY信息
 *                                           3：握手请求信息
 *                                           4：握手应答信息
 *                                           5：心跳请求信息
 *                                           6：心跳应答信息
 *      名称：priority；类型：Byte；长度：8；描述：消息优先级
 *      名称：attachment；类型：Map<String, Object>；长度：变长；描述可选字段，用于扩展消息头
 *
 * @date 2021/1/31 21:15
 */
public class Main {

}
