package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WeightedQuickUnionUFTest {

    private WeightedQuickUnionUF weightedQuickUnionUF;

    private static final Integer NUMBER_OF_OBJECTS = 10;

    @Before
    public void setUp() {
        weightedQuickUnionUF = new WeightedQuickUnionUF(NUMBER_OF_OBJECTS);
    }

    @Test
    public void constructorTest() {
        assertEquals(NUMBER_OF_OBJECTS, Integer.valueOf(weightedQuickUnionUF.id.length));
        for (Integer i = 0; i < NUMBER_OF_OBJECTS; i++) {
            assertEquals(i,weightedQuickUnionUF.id[i]);
        }
    }
}
