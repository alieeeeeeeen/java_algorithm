package queue;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items; // length == the capacity of the items
    private int size; // the number of content

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if(item == null) {
            throw new NullPointerException();
        }
        if(size == items.length) {
            resize(items.length * 2);
        }
        items[size++] = item;
    }

    private void resize(int len) {
        @SuppressWarnings("unchecked")
        Item[] temp = (Item[]) new Object[len];
        int i = 0, j = 0;
        while (i < size) {
            temp[i++] = items[j++];
        }
        items = temp;
    }

    // remove and return a random item
    public Item dequeue() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        Integer i = StdRandom.uniform(size);
        Item item = items[i];
        items[i] = items[size - 1];
        items[--size] = null;
        if(size < items.length && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        Item sample = null;
        while(sample == null) {
            Integer i = StdRandom.uniform(size);
            sample = items[i];
        }
        return sample;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Item[] temp;
        private int copySize;

        public RandomizedQueueIterator() {
            copySize = size;
            temp = (Item[]) new Object[size];
            int i = 0;
            while(i < copySize) {
                temp[i] = items[i];
                i++;
            }
        }

        @Override
        public boolean hasNext() {
            return copySize > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            StdOut.println(copySize);
            int random = StdRandom.uniform(copySize);
            Item tempItem = temp[random];
            temp[random] = temp[copySize - 1];
            temp[--copySize] = null;
            return tempItem;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
//        StdOut.println(rq.isEmpty());

        rq.enqueue(1);
//        StdOut.println(rq.size() == 1);

        rq.enqueue(2);
//        StdOut.println(rq.size() == 2);

        rq.dequeue();
        StdOut.println(rq.iterator().next());
//        StdOut.println(rq.size() == 1);

//        StdOut.println(rq.sample());
//        StdOut.println(rq.iterator().next());

//        rq.dequeue();
    }
}