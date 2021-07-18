package collinear;

import edu.princeton.cs.algs4.StdDraw;
import java.util.Comparator;

public class Point {
    private final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(this.x, this.y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public int compareTo(Point that) {
        if(this.y < that.y || ((this.y == that.y) && (this.x < that.x))) {
            return -1;
        }
        if (this.y > that.y) {
            return 1;
        }
        return 0;
    }

    public double slopeTo(Point that) {
        if (this.x == that.x) {
            return this.y == that.y ?
                    Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        }
        return (that.y - this.y) / (that.x - this.x);
    }

    public Comparator<Point> slopeOrder() {
        return new SlopeComparator(this);
    }

    private class SlopeComparator implements Comparator<Point> {
        private final Point point;

        SlopeComparator(Point point) {
            this.point = point;
        }

        @Override
        public int compare(Point p1, Point p2) {
            if(this.point.slopeTo(p1) > this.point.slopeTo(p2)) {
                return -1;
            }
            if(this.point.slopeTo(p1) < this.point.slopeTo(p2)) {
                return 1;
            }
            return 0;
        }
    }
}
