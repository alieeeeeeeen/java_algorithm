package collinear;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private final Point[] points;
    private LineSegment[] lines;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
        for (Point p : points) {
            if (p == null) {
                throw new NullPointerException();
            }
        }
        this.points = points;
        Point p = points[0];
        int i = 1;
        Boolean flag = true;
        while (i < 3) {
            if (p.slopeTo(points[i]) == p.slopeTo(points[i + 1])) {
                i++;
            } else {
                flag = false;
                break;
            }
        }
        lines = new LineSegment[i];
        for (int j = 0; flag && j < i; j++) {
            lines[j] = new LineSegment(points[j], points[j + 1]);
        }
    }

    public int numberOfSegments() {
        return lines.length;
    }

    public LineSegment[] segments() {
        return lines;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
