package org.example.myaggrement.codec.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yoveuio
 * @version 1.0
 * @className TestSubscribeReqProto
 * @description protobuf初步测试
 * @date 2021/1/29 23:19
 */
public class TestSubscribeReqProto {

    private static byte[] encode (SubscribeReqProto.SubscribeReq req) {
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
        // 通过newBuilder创建SubscribeReqProto.SubscribeReq的Builder实例
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        // 进行属性设置
        builder.setSubReqID(1);
        builder.setUserName("yoveuio");
        builder.setProductName("Netty Notepad");
        List<String> address = new ArrayList<>();
        address.add("ChangSha KaiFuQu");
        address.add("ChangShaLiGongDaXue");
        builder.addAllAddress(address);
        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
        System.out.println("Before encode: " + req.toString());
        SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
        System.out.println("After decode: " + req.toString());
        System.out.println("Assert equal : --> " + req2.equals(req));
    }
}
