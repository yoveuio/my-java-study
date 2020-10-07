package org.example.tree;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * @ClassName TreeTest
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/7/3 9:27
 * @Version 1.0
 */
public class TreeTest {

    public static void main(String[] args) {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        Random random = new Random();
        int[] a = new int[1000000];

        while (true){
            test1(tree, random, a);
        }
//        for (int i=0; i<10; ++i){
//            tree.put(i, "test"+i);
//        }
//        for (int i=0; i<10; ++i){
//            System.out.println(i+tree.get(i));
//        }
//        tree.delete(3);
//        tree.delete(7);
//        for (int i=0; i<10; ++i){
//            System.out.println(i+tree.get(i));
//        }
    }

    public static void test1(RedBlackTree<Integer, String> tree, Random random, int[] a){
        long startTime, endTime;

        startTime=System.currentTimeMillis();
        for (int i=0; i<1000000; ++i){
            a[i] = random.nextInt(1000000);
        }
        endTime=System.currentTimeMillis();
        System.out.println("插入递增数组：程序运行时间： "+(endTime-startTime)+"ms");


        startTime=System.currentTimeMillis();
        for (int i=0; i<1000000; ++i){
            tree.put(i, "1");
        }
        endTime=System.currentTimeMillis();
        System.out.println("插入递增数组：程序运行时间： "+(endTime-startTime)+"ms");

        startTime=System.currentTimeMillis();
        for (int i=0; i<1000000; ++i){
            tree.put(a[i], "1");
        }
        endTime=System.currentTimeMillis();
        System.out.println("插入随机数字：随机程序运行时间： "+(endTime-startTime)+"ms");

        for (int i=0; i<1000000; ++i){
        }

        startTime=System.currentTimeMillis();
        for (int i=0; i<1000000; ++i){
            tree.select(i);
        }
        endTime=System.currentTimeMillis();
        System.out.println("查找递增数组：程序运行时间： "+(endTime-startTime)+"ms");

        startTime=System.currentTimeMillis();
        for (int i=0; i<1000000; ++i){
            tree.select(a[i]);
        }
        endTime=System.currentTimeMillis();
        System.out.println("查找随机数字：程序运行时间： "+(endTime-startTime)+"ms");

    }

}
