package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuickUnionUFTest {

    private QuickUnionUF quickUnionUF;

    private static final int NUMBER_OF_OBJECTS = 10;
    @Before
    public void setUp() {
        quickUnionUF = new QuickUnionUF(NUMBER_OF_OBJECTS);
    }

    @Test
    public void constructorTest() {
        assertEquals(NUMBER_OF_OBJECTS, quickUnionUF.id.length);
        for (Integer i = 0; i < NUMBER_OF_OBJECTS; i++) {
            assertEquals(i, quickUnionUF.id[i]);
        }
    }

    @Test
    public void testUnion() {
        quickUnionUF.union(4,3);
        assertEquals(Integer.valueOf(3),quickUnionUF.id[3]);
        assertEquals(Integer.valueOf(3),quickUnionUF.id[4]);
        quickUnionUF.union(3,8);
        assertEquals(Integer.valueOf(8),quickUnionUF.id[3]);
        quickUnionUF.union(6,5);
        assertEquals(Integer.valueOf(5),quickUnionUF.id[6]);
        quickUnionUF.union(9,4);
    }

    @Test
    public void testConnected() {
        quickUnionUF.id = new Integer[] {0, 1, 1, 8, 3, 0, 5, 7, 8, 8};
        assertEquals(false, quickUnionUF.connected(5,4));
        assertEquals(false,quickUnionUF.connected(9,6));
        assertEquals(true,quickUnionUF.connected(0,6));
        assertEquals(true,quickUnionUF.connected(9,4));
    }
}
