package org.example.myaggrement.codec.protostuff;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yoveuio
 * @version 1.0
 * @className SerializationUtil2
 * @description protostuff序列化
 * 并将schema对象缓存起来
 * @date 2021/2/2 23:05
 */
public class ProtostuffSerialization {
    // 避免每次序列化都要重新申请Buffer空间
    private static LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
    // 缓存schema对象的map
    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();

    /**
     * 根据获取相应类型的schema方法
     *
     * @param clazz 获得视图的类
     * @return 返回
     */
    @SuppressWarnings({"unchecked", "unused"})
    public static  <T> Schema<T> getSchema(Class<T> clazz) {
        // 先尝试从缓存schema map中获取相应类型的schema
        Schema<T> schema = (Schema<T>) cachedSchema.get(clazz);
        // 如果没有获取到对应的schema，则创建一个该类型的schema
        // 同时将其添加到schema map中
        if (schema == null) {
            schema = RuntimeSchema.getSchema(clazz);
            if (schema != null) {
                cachedSchema.put(clazz, schema);
            }
        }
        // 返回schema对象
        return schema;
    }

    /**
     * 序列化方法，将对象序列化为字节数组（对象 ---> 字节数组）
     *
     * @param obj 序列化对象
     * @return 字节码
     */
    @SuppressWarnings("unchecked")
    public static <T> byte[] serialize(T obj) {
        // 获取泛型对象的类型
        Class<T> clazz = (Class<T>) obj.getClass();
        // 创建泛型对象的schema对象
        Schema<T> schema = getSchema(clazz);
        try {
            // 序列化
            // 返回序列化对象
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化方法，将字节数组反序列化为对象（字节数组 ---> 对象）
     */
    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        // 创建泛型对象的schema对象
        Schema<T> schema = getSchema(clazz);
        // 根据schema实例化对象
        T message = schema.newMessage();
        // 将字节数组中的数据反序列化到message对象
        ProtostuffIOUtil.mergeFrom(data, message, schema);
        // 返回反序列化对象
        return message;
    }
}
