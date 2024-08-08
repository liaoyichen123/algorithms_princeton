package module6.collinear;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BruteCollinearPointsTest {

    @Test
    public void numberOfSegmentsSingleCollinearCase() {
        Point[] points = {new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(5, 5)};
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        assertEquals(1, bruteCollinearPoints.numberOfSegments());
    }

    @Test
    public void numberOfSegmentsNoneCollinearCase() {
        Point[] points = {new Point(1, 1), new Point(1, 2), new Point(5, 3), new Point(87, 4)};
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        assertEquals(0, bruteCollinearPoints.numberOfSegments());
    }

    @Test
    public void numberOfSegmentsInput6Case() {
        Point[] points = {new Point(19000, 10000),
                new Point(18000, 10000),
                new Point(32000, 10000),
                new Point(21000, 10000),
                new Point(1234, 5678),
                new Point(14000, 10000)};
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        assertEquals(1, bruteCollinearPoints.numberOfSegments());
    }

    @Test
    public void numberOfSegmentsInput8Case() {
        Point[] points = {new Point(10000, 0),
                new Point(0, 10000),
                new Point(3000, 7000),
                new Point(7000, 3000),
                new Point(20000, 21000),
                new Point(3000, 4000),
                new Point(14000, 15000),
                new Point(6000, 7000)};
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        assertEquals(2, bruteCollinearPoints.numberOfSegments());
    }

    @Test
    public void segmentsSingleCollinearCase() {
        Point[] points = {new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4)};
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        LineSegment[] lineSegments = bruteCollinearPoints.segments();
        assertEquals(1, lineSegments.length);
        assertEquals("(1, 1) -> (4, 4)", lineSegments[0].toString());
    }

    @Test
    public void segmentsNoneCollinearCase() {
        Point[] points = {new Point(1, 1), new Point(1, 2), new Point(5, 3), new Point(87, 4)};
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        LineSegment[] lineSegments = bruteCollinearPoints.segments();
        assertEquals(0, lineSegments.length);
    }

    @Test
    public void segmentsInput6Case() {
        Point[] points = {new Point(19000, 10000),
                new Point(18000, 10000),
                new Point(32000, 10000),
                new Point(21000, 10000),
                new Point(1234, 5678),
                new Point(14000, 10000)};
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        LineSegment[] lineSegments = bruteCollinearPoints.segments();
        for (LineSegment lineSegment : lineSegments) {
            assertEquals("(14000, 10000) -> (32000, 10000)", lineSegment.toString());
        }
    }

    @Test
    public void segmentsInput8Case() {
        Point[] points = {new Point(10000, 0),
                new Point(0, 10000),
                new Point(3000, 7000),
                new Point(7000, 3000),
                new Point(20000, 21000),
                new Point(3000, 4000),
                new Point(14000, 15000),
                new Point(6000, 7000)};
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        LineSegment[] lineSegments = bruteCollinearPoints.segments();
        // order of those point can be either p -> q or q -> p and points in the LineSegment class are private so i had to test it by calling toString
        assertEquals("[(10000, 0) -> (0, 10000), (3000, 4000) -> (20000, 21000)]", Arrays.deepToString(lineSegments));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullConstructor() {
        new BruteCollinearPoints(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullPointInArray() {
        Point[] points = new Point[] {
                new Point(10000, 0),
                null,
                new Point(3000, 7000),
                new Point(7000, 3000)
        };
        new BruteCollinearPoints(points);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDuplicatePoints() {
        Point[] points = new Point[] {
                new Point(10000, 0),
                new Point(3000, 7000),
                new Point(3000, 7000),
                new Point(7000, 3000)
        };
        new BruteCollinearPoints(points);
    }


}