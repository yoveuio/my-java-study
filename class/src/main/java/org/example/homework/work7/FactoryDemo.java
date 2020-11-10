package org.example.homework.work7;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.Properties;

/**
 * @ClassName FactoryDemo
 * @Description 通过配置文件+反射优化工厂模式
 * @Author yoveuio
 * @Date 2020/11/5 11:30
 * @Version 1.0
 */
public class FactoryDemo {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        InputStream input = FactoryDemo.class.getResourceAsStream("/application.properties");
        properties.load(input);
        AbstractOperation operator = OperateFactory.createOperate(properties.getProperty("class-driver"));
        operator.setNumberA(10);
        operator.setNumberB(20);
        System.out.println(operator.getResult());
    }
}
