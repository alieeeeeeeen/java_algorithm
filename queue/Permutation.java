package queue;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int i = 0;
        int j = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        while (i < j) {
           StdOut.println(rq.dequeue());
           i++;
        }
    }
}
