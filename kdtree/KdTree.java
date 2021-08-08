package kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.w3c.dom.css.Rect;

public class KdTree {

    private Node root;
    private int size;

    public KdTree() {
        root = null;
        size = 0;
    }

    private static class Node {
        private final Point2D point2D;
        private final boolean horizontalSplit;
        private Node left;
        private Node right;

        private Node(Point2D point2D, Node left, Node right, boolean horizontalSplit) {
            this.point2D = point2D;
            this.horizontalSplit = horizontalSplit;
            this.left = left;
            this.right = right;
        }

        private void draw() {
            if (left != null) {
                left.draw();
            }
            point2D.draw();
            if (right != null) {
                right.draw();
            }
        }

        private void print(int level) {
            System.out.println(point2D + " level: " + level + " horizontalSplit: " + horizontalSplit);
            if (left != null) {
                System.out.print("  left of" + point2D + ": ");
                left.print(level + 1);
            }
            if (right != null) {
                System.out.print("  right of" + point2D + ": ");
                right.print(level + 1);
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        checkNull(p);
        if (root == null) {
            throw new IllegalArgumentException();
        }
        if (root == null || !contains(p)) {
            size++;
            root = insert(root, p, true);
        }

    }

    private Node insert(Node node, Point2D point, boolean sep) {
        if (node == null) {
            return new Node(point, null, null, true);
        }
        if (node.horizontalSplit) {
            if (point.x() < node.point2D.x()) {
                node.left = insert(node.left, point, !sep);
            } else {
                node.right = insert(node.right, point, !sep);
            }
        } else {
            if (point.y() < node.point2D.y()) {
                node.left = insert(node.left, point, !sep);
            } else {
                node.right = insert(node.right, point, !sep);
            }
        }
        return node;
    }

    public boolean contains(Point2D p) {

    }

    public void draw() {

    }

    public Iterable<Point2D> range(RectHV rect) {

    }

    public Point2D nearest(Point2D p) {

    }

    private void checkNull(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
    }
}
