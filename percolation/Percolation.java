package percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF backwashUF;
    private int size;
    private int top;
    private int bottom;
    private boolean[] isOpened;
    private int openCount = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        top = 0;
        size = n;
        bottom = size * size + 1;
        isOpened = new boolean[n * n + 2];
        isOpened[top] = true;
        isOpened[bottom] = true;
        backwashUF = new WeightedQuickUnionUF(n * n + 2); // 0 and last node as virtual sites
        uf = new WeightedQuickUnionUF(n * n + 1);
    }

    public int getIndexOf(int i, int j) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (j < 0 || j >= size) {
            throw new IndexOutOfBoundsException();
        }

        return i * size + j + 1; // get index of site
    }

    // opens the site (row, col) if it is not open already
    public void open(int i, int j) {
        int index = getIndexOf(i, j);
        isOpened[index] = true;
        openCount++;
        if (i == 0) {
            uf.union(index, top);
            backwashUF.union(index, top);
        }
        if (i == size - 1) {
            backwashUF.union(index, bottom);
        }
        if ((i - 1) >= 0 && (i - 1) < size && isOpen(i - 1, j)) {
            union(i, j, i - 1, j);
        }
        if ((i + 1) >= 0 && (i + 1) < size && isOpen(i + 1, j)) {
            union(i, j, i + 1, j);
        }
        if ((j - 1) >= 0 && (j - 1) < size && isOpen(i, j - 1)) {
            union(i, j, i, j - 1);
        }
        if ((j + 1) >= 0 && (j + 1) < size  && isOpen(i, j + 1)) {
            union(i, j, i, j + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int i, int j) {
        return isOpened[getIndexOf(i, j)];
    }

    // is the site (row, col) full?
    public boolean isFull(int i, int j) {
        return uf.find(top) == uf.find(getIndexOf(i, j));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return backwashUF.find(top) == backwashUF.find(bottom);
    }

    private void union(int iA, int jA, int iB, int jB) {
        uf.union(getIndexOf(iA, jA), getIndexOf(iB, jB));
        backwashUF.union(getIndexOf(iA, jA), getIndexOf(iB, jB));
    }
}
