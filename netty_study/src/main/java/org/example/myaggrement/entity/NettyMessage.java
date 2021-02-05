package org.example.myaggrement.entity;

import lombok.*;

/**
 * @author yoveuio
 * @version 1.0
 * @className NettyMessage
 * @description TODO
 * @date 2021/2/2 23:12
 */
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public final class NettyMessage {
    private MessageHeader header;
    private Object body;
}
