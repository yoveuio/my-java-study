package homework.work6;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName IteratorTest
 * @Description 测试HashSet的快速失败
 * @Author yoveuio
 * @Date 2020/10/29 10:51
 * @Version 1.0
 */
/*
public class IteratorTest {
    Set<Integer> set = new HashSet<Integer>() {{
        add(1);
        add(2);
        add(3);
    }};

    public void print(){
        for (Integer next : set) {
            System.out.println(next);
        }
    }

    public void addE(){
        set.add(4);
    }

    public static void main(String[] args) {
        IteratorTest iteratorTest = new IteratorTest();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(iteratorTest::print);
        executorService.execute(iteratorTest::addE);
        executorService.execute(iteratorTest::print);
        executorService.shutdown();
    }
}
*/


public class IteratorTest {
    HashSet<Integer> set = new HashSet<Integer>();

    public IteratorTest() {
        set.add(1);
        set.add(2);
    }

    public void addSet() {
        set.add(3);
    }

    public void printSet() {
        for (Integer i : set) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        IteratorTest test = new IteratorTest();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(test::printSet);
        executorService.execute(test::addSet);
        executorService.shutdown();
    }

}
