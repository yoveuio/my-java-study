package org.example.myaggrement.entity;

import lombok.*;

import java.util.Map;

/**
 * @author yoveuio
 * @version 1.0
 * @className NettyMessage
 * @description TODO
 * @date 2021/2/2 23:12
 */
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class NettyMessage {
    private final int crcCode = 0xFAFABABE;

    private int version;
    private long sessionID;
    private byte type;
    private byte priority;
    Map<String, Object> attachment;

}
