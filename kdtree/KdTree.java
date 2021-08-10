package kdtree;

import edu.princeton.cs.algs4.*;
import org.w3c.dom.css.Rect;

public class KdTree {

    private enum Separator { VERTICAL, HORIZONTAL }
    private Node root;
    private int size;

    private static class Node {

        private final Separator sepr;
        private final RectHV rect;
        private final Point2D p;
        private Node left;
        private Node right;

        Node(Point2D p, Separator sepr, RectHV rect) {
            this.p = p;
            this.sepr = sepr;
            this.rect = rect;
        }

        private boolean isLeft(Point2D that) {
            if (sepr == Separator.VERTICAL) {
                return p.x() > that.x();
            } else {
                return p.y() > that.y();
            }
        }

        private Separator nextSpr() {
            return this.sepr == Separator.VERTICAL ? Separator.HORIZONTAL : Separator.VERTICAL;
        }

        private RectHV nextRight() {
            return sepr == Separator.VERTICAL ?
                new RectHV(p.x(), rect.ymin(), rect.xmax(), rect.ymax()):
                    new RectHV(rect.xmin(), p.y(), rect.xmax(), rect.ymax());
        }

        private RectHV nextLeft() {
            return sepr == Separator.VERTICAL ?
                    new RectHV(rect.xmin(), rect.ymin(), p.x(), rect.ymax()):
                    new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), p.y());
        }
    }

    public KdTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void draw() {
        drawKdTree(root, new RectHV(0,0,1,1));
    }

    private void drawPoint(Point2D p) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        p.draw();
    }

    private void drawBlue(double x0, double y0, double x1, double y1) {
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius();
        StdDraw.line(x0, y0, x1, y1);
    }

    private void drawBRed(double x0, double y0, double x1, double y1) {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius();
        StdDraw.line(x0, y0, x1, y1);
    }

    private void drawKdTree(Node node, RectHV hv) {
        if (node.sepr == Separator.VERTICAL) {
            drawBRed(node.p.x(), hv.ymin(), node.p.x(), hv.ymax());
        } else {
            drawBlue(hv.xmin(), node.p.y(), hv.xmax(), node.p.y());
        }
        drawPoint(node.p);
        if (node.left != null)  drawKdTree(node.left, node.nextLeft());
        if (node.right != null) drawKdTree(node.right, node.nextRight());
    }

    public void insert(Point2D p) {
        checkNull(p);

        // for the root node
        if (root == null) {
            root = new Node(p, Separator.VERTICAL, new RectHV(0,0,1,1));
            size++;
            return;
        }

        Node prev = null;
        Node cur = root;

        do {
            if (cur.p.equals(p)) return;

            prev = cur;
            if (cur.isLeft(p)) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }

        } while (cur != null);


        if (prev.isLeft(p)) {
            prev.left= new Node(p, prev.nextSpr(), prev.nextLeft());
        } else {
            prev.right = new Node(p, prev.nextSpr(), prev.nextRight());
        }
        size++;
    }

    public boolean contains(Point2D p) {
        checkNull(p);
        Node node = root;
        while (node != null) {
            if (node.p.equals(p)) {
                return true;
            } else {
                node = node.isLeft(p) ? node.left : node.right;
            }
        }
        return false;
    }
//
//    public Iterable<Point2D> range(RectHV rect) {
//        checkNull(rect);
//    }
//
//    private void addAll(Node node, RectHV rect, List<Point2D> results) {
//    }
//
//    public Point2D nearest(Point2D p) {
//        checkNull(p);
//    }
//
//    private Point2D nearest(Point2D target, Point2D closest, Node node) {
//        if (node == null) {
//            return closest;
//        }
//    }

    private void checkNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
    }

    public static void main(String[] args) {
        RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
        StdDraw.enableDoubleBuffering();
        KdTree kdtree = new KdTree();

        In in = new In(args[0]);

        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();

            Point2D p = new Point2D(x, y);
            if (rect.contains(p)) {
                StdOut.printf("%8.6f %8.6f\n", x, y);
                kdtree.insert(p);
                StdDraw.clear();
                kdtree.draw();
                StdDraw.show();
            }
            StdDraw.pause(20);
        }
    }
}