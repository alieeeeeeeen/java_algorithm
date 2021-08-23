package wordnet;

import edu.princeton.cs.algs4.*;

public class SAP {
    // constructor takes a digraph (not necessarily a DAG)
    private final Digraph G;
    private int ancestor;

    public SAP(Digraph G) {
        if (G == null) throw new NullPointerException();
        this.G = new Digraph(G);
        this.ancestor = -1;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        int ancestor = ancestor(v, w);
        if (ancestor == -1) return -1;
        BreadthFirstDirectedPaths bfv = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfw = new BreadthFirstDirectedPaths(G, w);

        int length1 = bfv.distTo(ancestor);
        int length2 = bfw.distTo(ancestor);
        return length1 + length2;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        BreadthFirstDirectedPaths bf = new BreadthFirstDirectedPaths(G, v);
        return predecessor(bf, w);
    }

    private int predecessor(BreadthFirstDirectedPaths bf, int w) {
        for (int vertex: G.adj(w)) {
            if (bf.hasPathTo(vertex)) {
                ancestor = vertex;
                break;
            } else {
                predecessor(bf, vertex);
            }
        }
        return ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
//    public int length(Iterable<Integer> v, Iterable<Integer> w) {
//
//    }
//
//    // a common ancestor that participates in shortest ancestral path; -1 if no such path
//    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
//
//    }

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
