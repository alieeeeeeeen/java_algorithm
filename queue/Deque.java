package queue;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size;

    private class Node {
        Item item;
        Node prev, next;

        Node(Item item) {
            this.item = item;
        }
    }

    public Deque() {
        first = null;
        last = null;
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
        Node node = new Node(item);
        if (isEmpty()) {
            first = node;
            last = first;
        } else {
            node.next = first;
            first.prev = node;
            first = node;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node node = new Node(item);
        if (isEmpty()) {
            last = node;
            first = last;
        } else {
            node.prev = last;
            last.next = node;
            last = node;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node node = first;
        if (size() == 1) {
            first = null;
            last = null;
        } else {
            first.next.prev = null;
            first = first.next;
        }
        node.next = null;
        size--;
        return node.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node node = last;
        if (size() == 1) {
            first = null;
            last = null;
        } else {
            last.prev.next = null;
            last = last.prev;
        }
        node.next = null;
        size--;
        return node.item;
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
    public static void main(String[] args) {
//        Deque<Integer> d1 = new Deque<Integer>();
//        // size
//        d1.addFirst(1);
//        d1.addLast(2);
//
//        d1.addFirst(3);
//        d1.addLast(4);
//
//        // Test FirstElement
//        StdOut.println(3 == d1.first.item);
//
//        // Test LastElement
//        StdOut.println(4 == d1.last.item);
//
//        // Test the size of queue
//        StdOut.println(4 == d1.size());
//
//        // Test each item
//        Integer[] items = {3, 1, 2, 4};
//        int i = 0;
//        for (Integer item: d1) {
//            StdOut.println(items[i] == item);
//            i++;
//        }

//
//        Deque<Integer> deque = new Deque<>();
//        deque.isEmpty()  ;
//        deque.addFirst(2);
//        deque.removeFirst() ;
//        deque.isEmpty()      ;
//        deque.addFirst(5);
//        deque.removeFirst();

//        Deque<Integer> deque = new Deque<>();
//        deque.addFirst(1);
//        deque.removeLast()     ;
//        deque.addFirst(3);
//        StdOut.println(deque.removeLast()     );


//        Deque<Integer> deque = new Deque<>();
//        deque.addLast(1);
//        deque.addLast(2);
//        deque.addFirst(3);
//        deque.addFirst(4);
//        deque.addFirst(5);
//        deque.size()           ;
//        deque.addFirst(7);
//        deque.addLast(8);
//        StdOut.println(deque.last.item);
    }
}
