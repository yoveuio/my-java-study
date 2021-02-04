package org.example.myaggrement.entity;

import lombok.*;
import org.example.myaggrement.codec.protostuff.ProtostuffSerialization;

import java.util.HashMap;

/**
 * @author yoveuio
 * @version 1.0
 * @className NettyMessage
 * @description TODO
 * @date 2021/2/2 23:12
 */
@Setter @Getter
@AllArgsConstructor
@ToString
@Builder
public final class NettyMessage {
    private MessageHeader header;
    private MessageBody body;

    public static void main(String[] args) {
        MessageHeader header = new MessageHeader(0x0001, 3, 0x0001, (byte)1, (byte)1, new HashMap<>());
        byte[] serialize = ProtostuffSerialization.serialize(new NettyMessage(header, new MessageBody<Integer>(1, 1)));
        for (byte b: serialize) {
            System.out.print(b);
            System.out.print(' ');
        }
        System.out.println();
        System.out.println(new String(serialize));
        System.out.println(ProtostuffSerialization.deserialize(serialize, NettyMessage.class));

    }
}
