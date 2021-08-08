package kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> points;
    public PointSET() {
        points = new TreeSet<>();
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        checkNull(p);
        if (!contains(p)) {
           points.add(p);
        }
    }

    public boolean contains(Point2D p) {
        checkNull(p);
        return points.contains(p);
    }

    public void draw() {
        points.forEach(Point2D::draw);
    }

    public Iterable<Point2D> range(RectHV rect) {
        List<Point2D> rangePoints = new ArrayList<>();
        for (Point2D that: points) {
            if (that.x() >= rect.xmin() && that.x() <= rect.xmax()
                    && that.y() >= rect.ymin() && that.y() <= rect.ymax()) {
                rangePoints.add(that);
            }
        }
        return rangePoints;
    }

    public Point2D nearest(Point2D p) {
        checkNull(p);
        Double minDistance = Double.POSITIVE_INFINITY;
        Point2D closePoint = null;
        for (Point2D that: points) {
            if (p.distanceSquaredTo(that) < minDistance) {
                minDistance = p.distanceSquaredTo(that);
                closePoint = that;
            }
        }
        return closePoint;
    }

    private void checkNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
    }
}
