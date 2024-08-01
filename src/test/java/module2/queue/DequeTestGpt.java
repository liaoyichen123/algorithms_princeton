package module2.queue;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class DequeTestGpt {
    @Test
    public void testIsEmpty() {
        Deque<Integer> deque = new Deque<>();
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testSize() {
        Deque<Integer> deque = new Deque<>();
        assertEquals(0, deque.size());
        deque.addFirst(1);
        assertEquals(1, deque.size());
        deque.addLast(2);
        assertEquals(2, deque.size());
    }

    @Test
    public void testAddFirst() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());
        assertEquals(Integer.valueOf(1), deque.removeFirst());
    }

    @Test
    public void testAddLast() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());
        assertEquals(Integer.valueOf(1), deque.removeLast());
    }

    @Test
    public void testRemoveFirst() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addLast(2);
        assertEquals(Integer.valueOf(1), deque.removeFirst());
        assertEquals(1, deque.size());
        assertEquals(Integer.valueOf(2), deque.removeFirst());
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testRemoveLast() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addLast(2);
        assertEquals(Integer.valueOf(2), deque.removeLast());
        assertEquals(1, deque.size());
        assertEquals(Integer.valueOf(1), deque.removeLast());
        assertTrue(deque.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFirstNull() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddLastNull() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFirstEmpty() {
        Deque<Integer> deque = new Deque<>();
        deque.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveLastEmpty() {
        Deque<Integer> deque = new Deque<>();
        deque.removeLast();
    }

    @Test
    public void testIterator() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        Iterator<Integer> iterator = deque.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorRemoveUnsupported() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        Iterator<Integer> iterator = deque.iterator();
        iterator.remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNoSuchElement() {
        Deque<Integer> deque = new Deque<>();
        Iterator<Integer> iterator = deque.iterator();
        iterator.next();
    }
}
