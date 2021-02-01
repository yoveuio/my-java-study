package org.example.websocket;

/**
 * @author yoveuio
 * @version 1.0
 * @className Main
 * @description
 *  http协议的弊端：
 *      1.HTTP协议是半双工协议，意味着在同一时刻，只有一个方向上的数据传输
 *      2.HTTP信息相比于其他二进制通信冗长而繁琐
 *      3.针对服务器推送的黑客攻击，例如长时间轮询
 *      为了解决HTTP效率低下的弊端，HTML5定义了WebSocket协议，能更好地节省服务器资源和带宽并达到实时通信
 *
 *  WebSocket的特点
 *      1.单一的TCP连接，采用全双工模式通信
 *      2.对代理、防火墙和路由器透明
 *      3.无头部信息、Cookie和身份验证
 *      4.无安全开销
 *      5.通过"ping/pong"帧保持链路激活
 *
 *
 * @date 2021/1/31 21:04
 */
public class Main {
}
