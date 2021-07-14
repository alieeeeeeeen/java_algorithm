package queue;

import edu.princeton.cs.algs4.StdOut;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private Deque<Item> deque;
    private int size;

    private class Node {
        Item item;
        Node prev, next;

        Node(Item item) {
            this.item = item;
        }
    }

    public Deque() {
        first = new Node(null);
        last = new Node(null);
        first.next = last;
        last.prev = first;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newFirst = new Node(item);
        if(first.item == null) {
            first.item = newFirst.item;
        } else {
            first.prev = newFirst;
            first.prev.next = first;
            first = newFirst;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newLast = new Node(item);
        if(last.item == null) {
            last.item = newLast.item;
        } else {
            last.next = newLast;
            last.next.prev = last;
            last = newLast;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if(size == 0) {
            throw new NoSuchElementException();
        }
        Node oldFirst = first;
        first.next.prev = null;
        first.next = null;
        size--;
        return oldFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if(size == 0) {
            throw new NoSuchElementException();
        }
        Node oldLast = last;
        last.prev.next = null;
        last.prev = null;
        size--;
        return oldLast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    @Test
    public static void main(String[] args) {
        Deque<Integer> d1 = new Deque<Integer>();
        // size
        d1.addFirst(1);
        d1.addLast(2);

        d1.addFirst(3);
        d1.addLast(4);

        // Test FirstElement
        assertEquals(new Integer(3), d1.first.item);

        // Test LastElement
        assertEquals(new Integer(4), d1.last.item);

        // Test the size of queue
        assertEquals(4, d1.size());

        // Test each item
        Integer[] items = {3, 1, 2, 4};
        int i = 0;
        for(Integer item: d1) {
            assertEquals(items[i], item);
            i++;
        }
    }
}
