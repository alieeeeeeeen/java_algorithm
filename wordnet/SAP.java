package wordnet;

import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SAP {
    // constructor takes a digraph (not necessarily a DAG)
    private final Digraph G;

    public SAP(Digraph G) {
        if (G == null) throw new NullPointerException();
        this.G = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (!validV(v) || !validV(w)) throw new IndexOutOfBoundsException();

        int a = ancestor(v, w);
        if (a == -1) return -1;
        BreadthFirstDirectedPaths bfv = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfw = new BreadthFirstDirectedPaths(G, w);

        int length1 = bfv.distTo(a);
        int length2 = bfw.distTo(a);
        return length1 + length2;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (!validV(v) || !validV(w)) throw new IndexOutOfBoundsException();

        BreadthFirstDirectedPaths bf = new BreadthFirstDirectedPaths(G, v);
        return ancestor(bf, w);
    }

    private int ancestor(BreadthFirstDirectedPaths bf, int w) {
        for (int vertex: G.adj(w)) {
            if (bf.hasPathTo(vertex)) {
                return vertex;
            } else {
                return ancestor(bf, vertex);
            }
        }
        return -1;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (!validV(v) || !validV(w)) throw new IndexOutOfBoundsException();

        int sap = 0;
        BreadthFirstDirectedPaths bfv = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfw = new BreadthFirstDirectedPaths(G, w);

        for (int i = 0; i <= G.V(); i++) {
            if (bfv.hasPathTo(i) && bfw.hasPathTo(i)) {
                if (bfv.distTo(i) + bfw.distTo(i) < sap) {
                    sap = bfv.distTo(i) + bfw.distTo(i);
                }
            }
        }
        return sap;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (!validV(v) || !validV(w)) throw new IndexOutOfBoundsException();

        int ancestor = -1;
        int sap = 0;
        BreadthFirstDirectedPaths bfv = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfw = new BreadthFirstDirectedPaths(G, w);

        for (int i = 0; i <= G.V(); i++) {
            if (bfv.hasPathTo(i) && bfw.hasPathTo(i)) {
                if (bfv.distTo(i) + bfw.distTo(i) < sap) {
                    sap = bfv.distTo(i) + bfw.distTo(i);
                    ancestor = i;
                }
            }
        }
        return ancestor;
    }

    private boolean validV(int v) {
        return v >= 0 && v < G.V();
    }

    private boolean validV(Iterable<Integer> v) {
        if (v.iterator().hasNext())
            return v.iterator().next() >= 0 && v.iterator().next() < G.V();
        return true;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
