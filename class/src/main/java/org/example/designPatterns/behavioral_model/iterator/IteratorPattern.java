package org.example.designPatterns.behavioral_model.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yoveuio
 * @version 1.0
 * @className IteratorPattern
 * @description 迭代器模式
 * @date 2021/2/15 12:36
 */
public class IteratorPattern {
    public static void main(String[] args) {

    }

    /**
     * 抽象聚合
     */
    interface Aggregate {
        void add(Object obj);

        void remove(Object obj);

        Iterator getIterator();
    }

    /**
     * 具体聚合
     */
    static class ConcreteAggregate implements Aggregate {
        private List<Object> list = new ArrayList<>();

        @Override
        public void add(Object obj) {
            list.add(obj);
        }

        @Override
        public void remove(Object obj) {
            list.remove(obj);
        }

        @Override
        public Iterator getIterator() {
            return new ConcreteIterator(list);
        }
    }

    interface Iterator {
        Object first();

        Object next();

        boolean hasNext();
    }

     static class ConcreteIterator implements Iterator {
        private List<Object> list = null;
        private int index = -1;

        public ConcreteIterator(List<Object> list) {
            this.list = list;
        }

        @Override
        public Object first() {
            index = 0;
            return list.get(index);
        }

        @Override
        public Object next() {
            Object obj = null;
            if (this.hasNext()) {
                obj = list.get(++index);
            }
            return obj;
        }

        @Override
        public boolean hasNext() {
            return index < list.size() - 1;
        }
    }
}
