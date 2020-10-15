import homework.aysn.Bank;

import java.io.*;
import java.lang.invoke.MethodHandle;
import java.nio.charset.Charset;
import java.util.*;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * @ClassName Test
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/8/30 13:18
 * @Version 1.0
 */
public class Test{

    ThreadLocal<Integer> local = new ThreadLocal<>();

    public static <E extends Number> List<? super E> process(List<E> list) {
        return list;
    }

    public static void main(String[] args) throws IOException {
        try {
            Thread.currentThread().wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Properties properties = new Properties();
        Writer writer = new FileWriter("hello.txt");


        properties.setProperty("hello", "hello world!");
        properties.setProperty("1", "I am fine!");
        properties.setProperty("2", "How are you?");

        properties.store(writer, "hello");

        InputStream input = new FileInputStream("hello.txt");
        PrintStream printStream = new PrintStream("hello.txt");
        OutputStream output = new FileOutputStream("hello.txt");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output, Charset.forName("GBK"));
        properties.list(printStream);
        Set<String> strings = properties.stringPropertyNames();
        for (String string : strings) {
            System.out.println(string);
        }
        Enumeration<?> enumeration = properties.propertyNames();
        while(enumeration.hasMoreElements()) {
            System.out.println(properties.getProperty((String) enumeration.nextElement()));
        }


    }


}
