package org.example.myaggrement.entity.enumerate;/**
  * @ClassName MessageType
  * @Description TODO
  * @Author yoveuio
  * @Date 2021/2/1 18:18
  * @Version 1.0
  */
public enum MessageType {
    MAGIC_NUMBER("魔数", 0xBABE0101),


    LOGIN_RESP("握手信息", 1);
    ;

    String name;
    int value;

    MessageType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static MessageType getByValue(int value) {
        for (MessageType messageType: MessageType.values()) {
            if (messageType.getValue() == value) {
                return messageType;
            }
        }
        throw new NumberFormatException();
    }
}
