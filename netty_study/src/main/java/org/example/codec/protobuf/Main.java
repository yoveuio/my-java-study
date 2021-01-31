package org.example.codec.protobuf;


/**
 * @author yoveuio
 * @version 1.0
 * @className Protobuf
 * @description
 *  Google Protobuf的优点：
 *      1.谷歌内部长期使用，产品成熟度较高
 *      2.跨语言、支持多种语言，包括C++，Java和python
 *      3.编码后的信息更小，更加有利于存储和传输
 *      4.编解码的性能非常高
 *      5.支持不同协议版本的前向兼容
 *      6.支持定义可选和必选字段
 *
 *  msgpack和protobuf的对比
 *      msgpack的序列化速度比protobuf要快一些，但反序列化要比protobuf要慢一些，但总体都接近
 *      msgpack可以直接序列化类对象，但protobuf需要先写描述映射文件(.proto)
 *      msgpack支持的基本类型比protobuf支持的要全面些
 *      protobuf的描述文件无法实现类的继承（不知V2.0以上会如何）
 *      然而，msgpack在unity下的ios与wp平台下无法支持，但protobuf可以支持全平台
 * @date 2021/1/29 21:54
 */
public class Main {
    public static void main(String[] args) {
    }
}
