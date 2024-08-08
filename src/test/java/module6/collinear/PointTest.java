package module6.collinear;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void slopeToVerticalCase() {
        Point pointA = new Point(1,3);
        Point pointB = new Point(1,5);
        assertEquals(Double.POSITIVE_INFINITY, pointA.slopeTo(pointB), 1e-9);
    }

    @Test
    public void slopeToHorizontalCase() {
        Point pointA = new Point(3,2);
        Point pointB = new Point(8,2);
        assertEquals(0.0, pointA.slopeTo(pointB), 1e-9);
    }

    @Test
    public void slopeToOverlapCase() {
        Point pointA = new Point(3,3);
        Point pointB = new Point(3,3);
        assertEquals(Double.NEGATIVE_INFINITY, pointA.slopeTo(pointB), 1e-9);
    }

    @Test
    public void slopeToPositiveCase() {
        Point pointA = new Point(1,1);
        Point pointB = new Point(2,2);
        assertEquals(1, pointA.slopeTo(pointB), 1e-9);
    }

    @Test
    public void slopeToNegativeCase() {
        Point pointA = new Point(-1,3);
        Point pointB = new Point(4,-6);
        assertEquals(-1.8, pointA.slopeTo(pointB), 1e-9);
    }

    @Test
    public void slopeToEdgeCase() {
        Point pointA = new Point(32767,32767);
        Point pointB = new Point(-32767,-32767);
        assertEquals(1, pointA.slopeTo(pointB), 1e-9);
    }


    @Test
    public void compareToDifferenceInYCase() {
        // one point is smaller because of difference in y coordinate
        Point pointA = new Point(1,1);
        Point pointB = new Point(1,2);
        assertTrue(pointA.compareTo(pointB) < 0);
        assertFalse(pointA.compareTo(pointB) > 0);
        assertNotEquals(0, pointA.compareTo(pointB));
    }

    @Test
    public void compareToDifferenceInXCase() {
        // one point is smaller because of difference in x coordinate
        Point pointA = new Point(1,1);
        Point pointB = new Point(2,1);
        assertTrue(pointA.compareTo(pointB) < 0);
        assertFalse(pointA.compareTo(pointB) > 0);
        assertNotEquals(0, pointA.compareTo(pointB));
    }

    @Test
    public void compareToEqualCase() {
        // one point is smaller because of difference in x coordinate
        Point pointA = new Point(1,1);
        Point pointB = new Point(1,1);
        assertEquals(0, pointA.compareTo(pointB));
    }

    @Test
    public void compareCaseInput8() {
        Point pointA = new Point(10000, 0);
        Point pointB = new Point(0, 10000);
        assertTrue(pointA.compareTo(pointB) < 0);
    }

    @Test
    public void slopeOrderEqualSlope() {
        Point pointA = new Point(1,1);
        Point pointB = new Point(2,2);
        Point pointOrigin = new Point(0, 0);
        Comparator<Point> originPointComparator = pointOrigin.slopeOrder();
        assertEquals(0,originPointComparator.compare(pointA,pointB));
    }

    @Test
    public void slopeOrderGreater() {
        Point pointA = new Point(1,3);
        Point pointB = new Point(2,2);
        Point pointOrigin = new Point(0, 0);
        Comparator<Point> originPointComparator = pointOrigin.slopeOrder();
        assertTrue(originPointComparator.compare(pointA,pointB) > 0);
    }

    @Test
    public void slopeOrderSmaller() {
        Point pointA = new Point(3,1);
        Point pointB = new Point(2,2);
        Point pointOrigin = new Point(0, 0);
        Comparator<Point> originPointComparator = pointOrigin.slopeOrder();
        assertTrue(originPointComparator.compare(pointA,pointB) < 0);
    }

    @Test
    public void slopeOrderSort() {
        Point[] points = {new Point(3,1), new Point(1,3), new Point(2,2)};
        Point[] expectedOrder = {new Point(3,1), new Point(2,2), new Point(1,3)};
        Arrays.sort(points);
        // The API definition in the prompt disallow override equal() and hashcode(), so that assertArrayEquals() didn't work
        for (int i = 0; i < points.length; i++) {
            assertEquals(0,points[i].compareTo(expectedOrder[i]));
        }
    }
}