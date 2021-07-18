package collinear;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
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

        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);

        ArrayList<LineSegment> ls = new ArrayList<>();

        for(int i = 0; i < sortedPoints.length - 3; i++) {
            Point p1 = sortedPoints[i];
             for (int j = i + 1; j < sortedPoints.length - 2; j++) {
                Point p2 = sortedPoints[j];
                double slope12 = p1.slopeTo(p2);
                for (int m = j + 1; m < sortedPoints.length - 1; m++) {
                    Point p3 = sortedPoints[m];
                    double slope13 = p1.slopeTo(p3);
                    if (slope12 == slope13) {
                        for (int n = m + 1; n < sortedPoints.length; n++) {
                            Point p4 = sortedPoints[n];
                            double slope14 = p1.slopeTo(p4);
                            if (slope12 == slope14) {
                                ls.add(new LineSegment(p1, p4));
                            }
                        }
                    }
                }
             }
        }
        lines = ls.toArray(new LineSegment[0]);
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
            StdOut.println("segment" + segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
