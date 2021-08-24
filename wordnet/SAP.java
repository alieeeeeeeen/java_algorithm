package wordnet;


import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

public class SAP {
    // constructor takes a digraph (not necessarily a DAG)
    private static final int INFINITY = Integer.MAX_VALUE;
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

        int ancestor = -1;
        int sap = INFINITY;
        BreadthFirstDirectedPaths bfv = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfw = new BreadthFirstDirectedPaths(G, w);

        for (int i = 0; i < G.V(); i++) {
            if (bfv.hasPathTo(i) && bfw.hasPathTo(i)) {
                if (bfv.distTo(i) + bfw.distTo(i) < sap) {
                    sap = bfv.distTo(i) + bfw.distTo(i);
                    ancestor = i;
                }
            }
        }
        return ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (!validV(v) || !validV(w)) throw new IndexOutOfBoundsException();

        int sap = INFINITY;
        BreadthFirstDirectedPaths bfv = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfw = new BreadthFirstDirectedPaths(G, w);

        for (int i = 0; i < G.V(); i++) {
            if (bfv.hasPathTo(i) && bfw.hasPathTo(i)) {
                if (bfv.distTo(i) + bfw.distTo(i) < sap) {
                    sap = bfv.distTo(i) + bfw.distTo(i);
                }
            }
        }

        if (sap != INFINITY)
            return sap;
        return -1;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (!validV(v) || !validV(w)) throw new IndexOutOfBoundsException();

        int ancestor = -1;
        int sap = 0;
        BreadthFirstDirectedPaths bfv = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfw = new BreadthFirstDirectedPaths(G, w);

        for (int i = 0; i < G.V(); i++) {
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

    }
}
