package kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.LinkedList;
import java.util.List;

public class KdTree {

    private enum Separator { VERTICAL, HORIZONTAL }
    private Node root;
    private int size;

    private static class Node {

        private final Separator sepr;
        private final RectHV rect;
        private final Point2D p;
        private Node leftBottom;
        private Node rightTop;

        Node(Point2D p, Separator sepr, RectHV rect) {
            this.p = p;
            this.sepr = sepr;
            this.rect = rect;
        }

        private boolean isRightTop(Point2D that) {
            return (this.sepr == Separator.HORIZONTAL && this.p.y() > that.y()) ||
                    (this.sepr == Separator.VERTICAL && this.p.x() > that.x());
        }

        private Separator nextSpr() {
            return this.sepr == Separator.VERTICAL ? Separator.HORIZONTAL : Separator.VERTICAL;
        }

        private RectHV nextRightTop() {
            return sepr == Separator.VERTICAL ?
                    new RectHV(p.x(), rect.ymin(), rect.xmax(), rect.ymax()):
                    new RectHV(rect.xmin(), p.y(), rect.xmax(), rect.ymax());
        }
        private RectHV nextLeftBottom() {
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

    public void insert(Point2D p) {
        checkNull(p);

        // for the root node
        if (root == null) {
            root = new Node(p, Separator.HORIZONTAL, new RectHV(0,0,1,1));
            size++;
            return;
        }

        Node prev = null;
        Node cur = root;

        do {
            if (cur.p.equals(p)) return;

            prev = cur;
            if (cur.isRightTop(p)) {
                cur = cur.leftBottom;
            } else {
                cur = cur.rightTop;
            }

            if (prev.isRightTop(p)) {
                prev.leftBottom = new Node(p, prev.nextSpr(), prev.nextLeftBottom());
            } else {
                prev.rightTop = new Node(p, prev.nextSpr(), prev.nextRightTop());
            }
            size++;
        } while (cur != null);


    }

//    public boolean contains(Point2D p) {
//        checkNull(p);
//    }
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
}