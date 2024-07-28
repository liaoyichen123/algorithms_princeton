package week2.queue;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class DequeTest {

    @Test
    public void isEmpty() {
        Deque<Integer> deque = new Deque<>();
        assertTrue(deque.isEmpty());
        Integer int_0 = 0;
        Integer int_1 = 1;
        deque.addFirst(int_0);
        deque.addFirst(int_1);
        deque.removeFirst();
        deque.removeLast();
        assertTrue(deque.isEmpty());
    }

    @Test
    public void size() {
        Deque<Integer> deque = new Deque<>();
        Integer int_0 = 0;
        Integer int_1 = 1;
        deque.addFirst(int_0);
        deque.addFirst(int_1);
        assertEquals(2, deque.size());
    }

    @Test
    public void addFirst() {
        Deque<Integer> deque = new Deque<>();
        Integer int_0 = 0;
        Integer int_1 = 1;
        Integer int_2 = 2;
        deque.addFirst(int_0);
        deque.addFirst(int_1);
        deque.addFirst(int_2);
        assertEquals(3, deque.size());
        assertEquals(Integer.valueOf(2), deque.removeFirst());
    }

    @Test
    public void addLast() {
        Deque<Integer> deque = new Deque<>();
        Integer int_0 = 0;
        Integer int_1 = 1;
        Integer int_2 = 2;
        deque.addLast(int_0);
        deque.addLast(int_1);
        deque.addLast(int_2);
        assertEquals(3, deque.size());
    }

    @Test
    public void removeFirst() {
        Deque<Integer> deque = new Deque<>();
        Integer int0 = 0;
        Integer int1 = 1;
        Integer int2 = 2;
        deque.addFirst(int0);
        deque.addFirst(int1);
        deque.addFirst(int2);
        assertEquals(Integer.valueOf(2),deque.removeFirst());
        assertEquals(2, deque.size());
        assertEquals(Integer.valueOf(1),deque.removeFirst());
        assertEquals(1, deque.size());
        assertEquals(Integer.valueOf(0),deque.removeFirst());
        assertEquals(0, deque.size());
    }

    @Test
    public void removeLast() {
        Deque<Integer> deque = new Deque<>();
        Integer int0 = 0;
        Integer int1 = 1;
        Integer int2 = 2;
        deque.addFirst(int0);
        deque.addFirst(int1);
        deque.addFirst(int2);
        assertEquals(Integer.valueOf(0),deque.removeLast());
        assertEquals(2, deque.size());
        assertEquals(Integer.valueOf(1),deque.removeLast());
        assertEquals(1, deque.size());
        assertEquals(Integer.valueOf(2),deque.removeLast());
        assertEquals(0, deque.size());
    }

    @Test
    public void iteratorHasNext() {
        Deque<Integer> deque = new Deque<>();
        Integer int_0 = 0;
        Iterator<Integer> dequeIterator = deque.iterator();
        assertFalse(dequeIterator.hasNext()); // test empty deque
        deque.addFirst(int_0);
        assertTrue(dequeIterator.hasNext());
        deque.removeFirst();
        assertFalse(dequeIterator.hasNext());
    }
}