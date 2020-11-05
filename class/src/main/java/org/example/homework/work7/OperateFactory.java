package org.example.homework.work7;

import java.lang.reflect.Constructor;

/**
 * @ClassName OperateFactory
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/5 11:28
 * @Version 1.0
 */
public class OperateFactory {
    public static AbstractOperation createOperate(String operate) throws Exception {
        AbstractOperation operation;
        Class<?> abstractOperation = Class.forName(operate);
        Constructor<?> constructor = abstractOperation.getConstructor();
        operation = (AbstractOperation) constructor.newInstance();
        return operation;
    }
}
