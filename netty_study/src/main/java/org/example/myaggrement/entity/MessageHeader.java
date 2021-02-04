package org.example.myaggrement.entity;

import lombok.*;

import java.util.Map;

/**
 * @author yoveuio
 * @version 1.0
 * @className Header
 * @description TODO
 * @date 2021/2/3 21:45
 */
@Setter
@Getter
@AllArgsConstructor
@ToString
@Builder
public final class MessageHeader {
    private final int crcCode = 0xBABE0101;
    private int version;
    private int length;
    private long sessionID;
    private byte type;
    private byte priority;
    Map<String, Object> attachment;
}
