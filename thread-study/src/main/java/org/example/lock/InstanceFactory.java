package org.example.lock;


/**
 * @ClassName InstanceFactory
 * @Description 基于类初始化实现延迟初始化
 * @Author yoveuio
 * @Date 2020/10/20 17:20
 * @Version 1.0
 */
public class InstanceFactory {
    private static class InstanceHolder{
        public static InstanceFactory instanceFactory = new InstanceFactory();
    }
    public static InstanceFactory getInstance() {
        return InstanceHolder.instanceFactory;
    }
}
