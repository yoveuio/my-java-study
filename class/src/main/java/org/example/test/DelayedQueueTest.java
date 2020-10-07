package org.example.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName test
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/7/22 17:30
 * @Version 1.0
 */

public class DelayedQueueTest {
    public static void main(String[] args) throws InterruptedException {
        Item item1 = new Item("item1", 20, TimeUnit.SECONDS);
        DelayQueue<Item> queue = new DelayQueue<>();
        queue.put(item1);
        System.out.println("begin time:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        Item take = queue.take();
        System.out.format("name:{%s}, time:{%s}\n", take.name,
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
    }
}

class Item implements Delayed {
    private long time;
    String name;

    public Item(String name, long time, TimeUnit unit) {
        this.name = name;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        // 从这个打印可以看出，随着时间靠近队列首位元素，频率越来越快。
        System.out.println(time - System.currentTimeMillis());
        return unit.convert(this.time - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        Item item = (Item) o;
        long diff = this.time - item.time;
        if (diff <= 0) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Item{" +
                "time=" + time +
                ", name='" + name + '\'' +
                '}';
    }
}