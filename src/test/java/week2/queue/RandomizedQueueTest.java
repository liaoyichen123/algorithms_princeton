package week2.queue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.Assert.*;

public class RandomizedQueueTest {

    RandomizedQueue<Integer> defaultRandomizedQueue;

    private static final int TEST_INTEGER_QUEUE_SIZE = 8;

    @Before
    public void creatDefaultTestObject() {
        defaultRandomizedQueue = new RandomizedQueue<>();
        for (int i = 0; i < TEST_INTEGER_QUEUE_SIZE; i++) {
            defaultRandomizedQueue.enqueue(i);
        }
    }

    @Test
    public void isEmpty() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        assertTrue(randomizedQueue.isEmpty());
        Integer int0 = 0;
        randomizedQueue.enqueue(int0);
        assertFalse(randomizedQueue.isEmpty());
        Integer int1 = 1;
        randomizedQueue.enqueue(int1);
        assertFalse(randomizedQueue.isEmpty());
        randomizedQueue.dequeue();
        assertFalse(randomizedQueue.isEmpty());
        randomizedQueue.dequeue();
        assertTrue(randomizedQueue.isEmpty());
    }

    @Test
    public void size() {
        assertEquals(defaultRandomizedQueue.size(), TEST_INTEGER_QUEUE_SIZE);
    }

    @Test
    public void enqueue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        assertTrue(randomizedQueue.isEmpty());
        Integer int0 = 0;
        randomizedQueue.enqueue(int0);
        assertEquals(randomizedQueue.size(), 1);
    }

    @Test
    public void dequeue() {
        ArrayList<Integer> arrayList = new ArrayList<>(TEST_INTEGER_QUEUE_SIZE);
        for (int i = 0; i < TEST_INTEGER_QUEUE_SIZE; i++) {
            arrayList.add(defaultRandomizedQueue.dequeue());
        }
        assertTrue(defaultRandomizedQueue.isEmpty());
        assertEquals(arrayList.size(), TEST_INTEGER_QUEUE_SIZE);
    }

    @Test
    public void sample() {
        Set<Integer> integerSet = new HashSet<>();
        for (int i = 0; i < TEST_INTEGER_QUEUE_SIZE * 100; i++) {
            integerSet.add(defaultRandomizedQueue.sample());
        }
        assertEquals(integerSet.size(), TEST_INTEGER_QUEUE_SIZE);
    }

    @Test
    public void iterator() {
        Set<Integer> integerSet = new HashSet<>();
        for (Integer randomInt : defaultRandomizedQueue) {
            integerSet.add(randomInt);
        }
        assertEquals(integerSet.size(), TEST_INTEGER_QUEUE_SIZE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void nullParameterEnqueue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(null);
    }

    @Test (expected = NoSuchElementException.class)
    public void emptyQueueDequeue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.dequeue();
    }

    @Test (expected = NoSuchElementException.class)
    public void emptyQueueSample() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.sample();
    }

    @Test (expected = NoSuchElementException.class)
    public void emptyQueueIterator() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.iterator().next();
    }

    @Test (expected = UnsupportedOperationException.class)
    public void iteratorRemove() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.iterator().remove();
    }
}