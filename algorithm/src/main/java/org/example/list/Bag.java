package org.example.list;

/**
 * @ClassName Bag
 * @Description 单链表
 * @Author yoveuio
 * @Date 2020/7/16 10:51
 * @Version 1.0
 */
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<I> implements Iterable<I> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private I[] theItems;

    public Bag() {
        clear();
    }

    private void clear() {
        size = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity < size) {
            return;
        } else {
            I[] oldIs = theItems;
            theItems = (I[]) new Object[newCapacity];
            for (int i = 0; i < size(); i++) {
                theItems[i] = oldIs[i];
            }
        }
    }

    public void add(I item) {
        if (theItems.length == size()) {
            ensureCapacity(2 * size() + 1);
        }
        theItems[size()] = item;
        size++;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public @NotNull Iterator<I> iterator() {
        return new BagIterator();
    }

    private class BagIterator implements Iterator<I> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public I next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return theItems[current++];
        }

    }

}
