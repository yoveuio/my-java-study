package org.example.myaggrement.entity;

import lombok.*;

/**
 * @ClassName MessageBody
 * @Description TODO
 * @Author yoveuio
 * @Date 2021/2/3 21:48
 * @Version 1.0
 */
@Setter
@Getter
@AllArgsConstructor
@ToString
@Builder
public final class MessageBody<T> {
    private int status;
    private T body;
}
