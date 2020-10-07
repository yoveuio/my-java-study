package org.example.container;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @ClassName ThreadLocalRandomTest
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/6/13 13:11
 * @Version 1.0
 */
public class ThreadLocalRandomTest {


    public static void main(String[] args) {

        int len = 10;
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i=0; i<len; ++i){

            System.out.println(random.nextInt(5));

        }

    }

}
