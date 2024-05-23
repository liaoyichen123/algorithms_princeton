package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class PercolationTest {



    @Test
    public void testConstructorPositive() {
        int n = 4;
        Percolation percolation = new Percolation(n);
        assertFalse(percolation.percolates());
    }

    @Test
    public void testConstructorVirtualPoints() {
        int n = 4;
        Percolation percolation = new Percolation(n);
        percolation.open(1,1);
        percolation.open(3,1);
        percolation.open(2,1);
        percolation.open(4,1);
        assertTrue(percolation.percolates());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegative() {
        int n = 0;
        Percolation percolation = new Percolation(n);
    }

    @Test
    public void openStraightDown() {
        Percolation percolation = new Percolation(3);
        percolation.open(1,1);
        percolation.open(3,1);
        percolation.open(2,1);
        assertTrue(percolation.percolates());
    }

    @Test
    public void openZigDown() {
        Percolation percolation = new Percolation(4);
        percolation.open(1,1);
        percolation.open(2,1);
        percolation.open(2,2);
        percolation.open(3,2);
        percolation.open(3,3);
        percolation.open(3,4);
        percolation.open(4,4);
        assertTrue(percolation.percolates());
    }

    @Test
    public void openTwoWayDown() {
        Percolation percolation = new Percolation(4);
        percolation.open(1,1);
        percolation.open(2,1);
        percolation.open(2,2);
        percolation.open(3,2);
        percolation.open(3,3);
        percolation.open(3,4);
        percolation.open(4,4);
        percolation.open(3,1);
        percolation.open(4,1);
        assertTrue(percolation.percolates());
    }

    @Test(expected = IllegalArgumentException.class)
    public void openNegative() {
        Percolation percolation = new Percolation(4);
        percolation.open(0,0);
    }

    @Test
    public void isOpenTrue() {
        Percolation percolation = new Percolation(8);
        percolation.open(1, 1);
        assertTrue(percolation.isOpen(1, 1));
        percolation.open(8, 8);
        assertTrue(percolation.isOpen(8, 8));
    }

    @Test
    public void isOpenFalse() {
        Percolation percolation = new Percolation(8);
        assertFalse(percolation.isOpen(1, 1));
        assertFalse(percolation.isOpen(8, 8));
        assertFalse(percolation.isOpen(6, 4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void isOpenNegative() {
        Percolation percolation = new Percolation(8);
        percolation.isOpen(0, 0);
    }


    @Test
    public void isFullTrue() {
        Percolation percolation = new Percolation(8);
        percolation.open(1,1);
        percolation.open(2,1);
        assertTrue(percolation.isFull(2,1));
    }

    @Test
    public void isFullFalse() {
        Percolation percolation = new Percolation(8);
        percolation.open(1,1);
        assertFalse(percolation.isFull(2,1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void isFullNegative() {
        Percolation percolation = new Percolation(8);
        assertFalse(percolation.isFull(0,0));
    }

    @Test
    public void isFullIsolatedOpenSiteWhenPercolated() {
        Percolation percolation = new Percolation(4);
        percolation.open(1,1);
        percolation.open(2,1);
        percolation.open(3,1);
        percolation.open(4,1);
        percolation.open(4,4); // this is the isolated site
        assertFalse(percolation.isFull(4, 4));
    }

    @Test
    public void numberOfOpenSites() {
        Percolation percolation = new Percolation(8);
        percolation.open(1, 1);
        percolation.open(1, 2);
        percolation.open(3, 3);
        assertEquals(percolation.numberOfOpenSites(), 3);
    }

    @Test
    public void percolatesFalseCase() {
        Percolation percolation = new Percolation(8);
        assertFalse(percolation.percolates());
    }

//    @Test
//    public void dimensionReductionTopLeft() {
//        Percolation percolation = new Percolation(3);
//        assertEquals(percolation.dimensionReduction(1, 1), 0);
//    }
//
//    @Test
//    public void dimensionReductionBottomRight() {
//        Percolation percolation = new Percolation(3);
//        assertEquals(percolation.dimensionReduction(3, 3), 8);
//    }
//
//    @Test
//    public void dimensionReductionMid() {
//        Percolation percolation = new Percolation(3);
//        assertEquals(percolation.dimensionReduction(2, 2), 4);
//    }
//
//    @Test (expected = IllegalArgumentException.class)
//    public void dimensionReductionNegative() {
//        Percolation percolation = new Percolation(3);
//        percolation.dimensionReduction(6, 3);
//    }
}