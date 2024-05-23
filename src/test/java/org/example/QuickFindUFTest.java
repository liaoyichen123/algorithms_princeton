package org.example;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class QuickFindUFTest {

    private QuickFindUF quickFindUF;

    private static final int NUMBER_OF_OBJECTS = 10;

    @Before
    public void setUp() {
        quickFindUF = new QuickFindUF(NUMBER_OF_OBJECTS);
    }

    @Test
    public void constructorTest() {
        assertEquals(NUMBER_OF_OBJECTS, quickFindUF.id.length);
        for (int i = 0; i < NUMBER_OF_OBJECTS; i++) {
            assertEquals(Integer.valueOf(i), quickFindUF.id[i]);
        }
    }

    @Test
    public void unionTest() {
        quickFindUF.union(4, 3);
        assertEquals(Integer.valueOf(3), quickFindUF.id[3]);
        assertEquals(Integer.valueOf(3), quickFindUF.id[4]);
        quickFindUF.union(3, 8);
        assertEquals(Integer.valueOf(8), quickFindUF.id[3]);
        assertEquals(Integer.valueOf(8), quickFindUF.id[4]);
        assertEquals(Integer.valueOf(8), quickFindUF.id[8]);
        quickFindUF.union(6, 5);
        assertEquals(Integer.valueOf(5), quickFindUF.id[5]);
        assertEquals(Integer.valueOf(5), quickFindUF.id[6]);
        quickFindUF.union(9, 4);
        assertEquals(Integer.valueOf(5), quickFindUF.id[5]);
        assertEquals(Integer.valueOf(5), quickFindUF.id[6]);
        assertEquals(Integer.valueOf(8), quickFindUF.id[3]);
        assertEquals(Integer.valueOf(8), quickFindUF.id[4]);
        assertEquals(Integer.valueOf(8), quickFindUF.id[8]);
        assertEquals(Integer.valueOf(8), quickFindUF.id[9]);
        quickFindUF.union(2, 1);
        assertEquals(Integer.valueOf(1), quickFindUF.id[1]);
        assertEquals(Integer.valueOf(1), quickFindUF.id[2]);
        quickFindUF.union(5, 0);
        assertEquals(Integer.valueOf(0), quickFindUF.id[0]);
        assertEquals(Integer.valueOf(0), quickFindUF.id[5]);
        assertEquals(Integer.valueOf(0), quickFindUF.id[6]);
        quickFindUF.union(7, 2);
        assertEquals(Integer.valueOf(1), quickFindUF.id[2]);
        assertEquals(Integer.valueOf(1), quickFindUF.id[7]);
        quickFindUF.union(6, 1);
        assertEquals(Integer.valueOf(1), quickFindUF.id[0]);
        assertEquals(Integer.valueOf(1), quickFindUF.id[1]);
        assertEquals(Integer.valueOf(1), quickFindUF.id[2]);
        assertEquals(Integer.valueOf(1), quickFindUF.id[5]);
        assertEquals(Integer.valueOf(1), quickFindUF.id[6]);
        assertEquals(Integer.valueOf(1), quickFindUF.id[7]);
    }

    @Test
    public void connectedTest() {
        quickFindUF.id = new Integer[] {1, 1, 1, 8, 8, 1, 1, 1, 8, 8};
        assertEquals(false,quickFindUF.connected(7,8));
        assertEquals(false,quickFindUF.connected(9,0));
        assertEquals(true,quickFindUF.connected(0,1));
        assertEquals(true,quickFindUF.connected(0,7));
        assertEquals(true,quickFindUF.connected(8,4));
        assertEquals(true,quickFindUF.connected(4,8));
    }

}