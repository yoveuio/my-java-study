package org.example.myaggrement.entity.enumerate;/**
  * @ClassName MessageType
  * @Description TODO
  * @Author yoveuio
  * @Date 2021/2/1 18:18
  * @Version 1.0
  */
public enum MessageType {
    LOGIN_REQ("握手请求报文", (byte) 1),
    LOGIN_RESP("握手应答报文", (byte) 2),
    HEARTBEAT_REQ("心跳请求报文", (byte) 3),
    HEARTBEAT_RESP("心跳应答报文", (byte) 4)
    ;

    String name;
    byte value;

    MessageType(String name, byte value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public byte getValue() {
        return value;
    }

    public static MessageType getByValue(byte value) {
        for (MessageType messageType: MessageType.values()) {
            if (messageType.getValue() == value) {
                return messageType;
            }
        }
        throw new NumberFormatException();
    }
}
