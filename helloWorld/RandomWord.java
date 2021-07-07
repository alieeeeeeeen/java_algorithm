package hello;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String word = "";
        int i;
        for(i = 1; !StdIn.isEmpty(); i++) {
            String item = StdIn.readString();
            boolean prob = StdRandom.bernoulli((double) 1/i);
            if(prob) {
                word = item;
            } else {
                continue;
            }
        }
        StdOut.println(word);
    }
}