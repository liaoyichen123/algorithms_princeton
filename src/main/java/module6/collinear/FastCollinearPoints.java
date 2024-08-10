package module6.collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class FastCollinearPoints {

    private final ArrayList<LineSegment> lineSegmentArrayList = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        // verify input array
        if (points == null || points.length < 4) {
            throw new IllegalArgumentException("less than 4 points in the input");
        }
        // verify every point
        // check for null point
        for (Point point : points) {
            if (point == null) throw new IllegalArgumentException("Point in array is null");
        }
        // Sort and check if  same points are in the input array
        Arrays.sort(points);
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i - 1]) == 0)
                throw new IllegalArgumentException("Duplicate points detected");
        }
        ArrayList<TempLineSegment> tempLineSegmentList = new ArrayList<>();
        for (Point point : points) {
            Point[] slopeSortedArray = Arrays.copyOf(points, points.length);
            Comparator<Point> pointComparator = point.slopeOrder();
            Arrays.sort(slopeSortedArray, pointComparator);
            int sameSlopeCounter = 0;
            double slopeToLastPoint = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < slopeSortedArray.length; i++) {
                double slopeToThisPoint = point.slopeTo(slopeSortedArray[i]);
                if (Double.compare(slopeToThisPoint, slopeToLastPoint) == 0) {
                    sameSlopeCounter++;
                } else {
                    sameSlopeCounter = 0;
                }
                if (sameSlopeCounter == 2) {
                    tempLineSegmentList.add(new TempLineSegment(slopeSortedArray[i - 3], slopeSortedArray[i]));
                }
                slopeToLastPoint = slopeToThisPoint;
            }
        }

        // remove duplicated subsegments in the pointList
        Collections.sort(tempLineSegmentList); // sort by slope of each line segment
        Point minPointInThisSegment = null;
        Point maxPointInThisSegment = null;
        int segmentListSize = tempLineSegmentList.size();
        int segmentListIndex = 0;
        Double lastSegmentSlope = null;
        if (segmentListSize > 0) {
            while (segmentListIndex < segmentListSize) {
                TempLineSegment currentSegment = tempLineSegmentList.get(segmentListIndex);
                if (segmentListIndex == 0) {
                    if (currentSegment.a.compareTo(currentSegment.b) <= 0) {
                        // point a is smaller or equals b
                        minPointInThisSegment = currentSegment.a;
                        maxPointInThisSegment = currentSegment.b;
                    } else {
                        minPointInThisSegment = currentSegment.b;
                        maxPointInThisSegment = currentSegment.a;
                    }
                    lastSegmentSlope = currentSegment.getSlope();
                } else if (Double.compare(lastSegmentSlope, currentSegment.getSlope()) == 0) {
                    // if two segments have same slope, find if there is point to update min/max
                    if (currentSegment.a.compareTo(maxPointInThisSegment) > 0) {
                        maxPointInThisSegment = currentSegment.a;
                    }
                    if (currentSegment.b.compareTo(maxPointInThisSegment) > 0) {
                        maxPointInThisSegment = currentSegment.b;
                    }
                    if (currentSegment.a.compareTo(minPointInThisSegment) < 0) {
                        minPointInThisSegment = currentSegment.a;
                    }
                    if (currentSegment.b.compareTo(minPointInThisSegment) < 0) {
                        minPointInThisSegment = currentSegment.b;
                    }
                } else if (Double.compare(lastSegmentSlope, currentSegment.getSlope()) != 0) {
                    // When reaching another line segment
                    lineSegmentArrayList.add(new LineSegment(minPointInThisSegment, maxPointInThisSegment));
                    // update minPint, maxPoint, and
                    if (currentSegment.a.compareTo(currentSegment.b) <= 0) {
                        // point a is smaller or equals currentSegment to the new line segment
                        minPointInThisSegment = currentSegment.a;
                        maxPointInThisSegment = currentSegment.b;
                    } else {
                        minPointInThisSegment = currentSegment.b;
                        maxPointInThisSegment = currentSegment.a;
                    }
                    lastSegmentSlope = currentSegment.getSlope();
                }
                segmentListIndex++;
            }
            lineSegmentArrayList.add(new LineSegment(minPointInThisSegment, maxPointInThisSegment));
        }
    }

    public class TempLineSegment implements Comparable<TempLineSegment> {

        private final Point a;

        private final Point b;

        public TempLineSegment(Point a, Point b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(TempLineSegment that) {
            double thisSlope = this.a.slopeTo(this.b);
            double thatSlope = that.a.slopeTo(that.b);
            return Double.compare(thisSlope, thatSlope);
        }

        public double getSlope() {
            return this.a.slopeTo(this.b);
        }

    }

    public int numberOfSegments() {
        // the number of line segments
        return lineSegmentArrayList.size();
    }

    public LineSegment[] segments() {
        // the line segments
        return lineSegmentArrayList.toArray(new LineSegment[0]);
    }
}
