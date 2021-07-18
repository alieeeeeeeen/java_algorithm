package collinear;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;

public class FastCollinearPoints {
    private final LineSegment[] lines;

    public FastCollinearPoints(Point[] points) {

        if (points == null) {
            throw new NullPointerException();
        }
        for (Point p : points) {
            if (p == null) {
                throw new NullPointerException();
            }
        }

        LinkedList<LineSegment> ls = new LinkedList<>();
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);

        for (int i = 0; i < sortedPoints.length; i++) {
            Point p1 = sortedPoints[i];
            Point[] innerSortedPoints = sortedPoints.clone();
            Arrays.sort(innerSortedPoints, p1.slopeOrder());

            int j = 1;
            while (j < innerSortedPoints.length) {
                LinkedList<Point> backupPoints = new LinkedList<>();
                double slop1x = p1.slopeTo(innerSortedPoints[j]);

                do {
                    backupPoints.add(innerSortedPoints[j++]);
                } while (j < innerSortedPoints.length && slop1x == p1.slopeTo(innerSortedPoints[j]));


                if (backupPoints.size() >= 3 && p1.compareTo(backupPoints.peek()) < 0) {
                    Point last = backupPoints.removeLast();
                    ls.add(new LineSegment(p1, last));
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
        // read the n points from a file
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println("segment" + segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
