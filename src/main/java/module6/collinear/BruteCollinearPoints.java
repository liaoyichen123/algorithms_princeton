package module6.collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class BruteCollinearPoints {

    private final ArrayList<LineSegment> lineSegmentArrayList = new ArrayList<>();


    /**
     * finds all line segments containing 4 points
     *
     * @param points an array that contains more than 4 points
     */
    public BruteCollinearPoints(Point[] points) {
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
            if (points[i].compareTo(points[i - 1]) == 0) throw new IllegalArgumentException("Duplicate points detected");
        }
        List<TempLineSegment> tempLineSegmentList = new ArrayList<>();
        // For loop that iterating through combination of C(n, 4). Check all combination for
        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p + 1; q < points.length - 2; q++) {
                for (int r = q + 1; r < points.length - 1; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        // Comparing slope(p,q) == slope(p,r) && slope(p,q) == slope(p,s)
                        if (Double.compare(points[p].slopeTo(points[q]), points[p].slopeTo(points[r])) == 0
                                && Double.compare(points[p].slopeTo(points[q]), points[p].slopeTo(points[s])) == 0) {
                            Point[] collinerPoint = {points[p], points[q], points[s], points[r]};
                            Arrays.sort(collinerPoint);
                            tempLineSegmentList.add(new TempLineSegment(collinerPoint[0], collinerPoint[3]));
                        }
                    }
                }
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


    /**
     * @return
     */
    public int numberOfSegments() {
        return lineSegmentArrayList.size();
    }

    /**
     * @return
     */
    public LineSegment[] segments() {
        return lineSegmentArrayList.toArray(new LineSegment[0]);
    }


}
