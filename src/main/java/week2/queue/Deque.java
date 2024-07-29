package week2.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;

    private Node last;

    private Integer numberOfNode = 0;

    private class Node {
        Item item;
        Node nextNode;
        Node previousNode;

        /**
         * First <-- previousNode <-- Node <-- nextNode <- last
         *
         * @param item         Node
         * @param previousNode first <-- previousNode <-- Node
         * @param nextNode     Node <-- nextNode <- last
         */
        private Node(Item item, Node previousNode, Node nextNode) {
            this.item = item;
            this.previousNode = previousNode;
            this.nextNode = nextNode;
        }
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return numberOfNode.equals(0);
    }

    // return the number of items on the deque
    public int size() {
        return numberOfNode;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null item");
        }
        if (this.isEmpty()) {
            Node newFirst = new Node(item, null, null);
            this.first = newFirst;
            this.last = newFirst;
        } else {
            Node oldFirst = this.first;
            Node newFirst = new Node(item, null, oldFirst);
            this.first = newFirst;
            oldFirst.previousNode = newFirst;
        }
        numberOfNode++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null item");
        }
        if (this.isEmpty()) {
            Node newLast = new Node(item, null, null);
            this.last = newLast;
            this.first = newLast;
        } else {
            Node oldLast = this.last;
            Node newLast = new Node(item, oldLast, null);
            this.last = newLast;
            oldLast.nextNode = newLast;
        }
        numberOfNode++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Empty queue");
        }
        Node oldFirst = this.first;
        if (oldFirst.nextNode != null) {
            // When next node exist
            this.first = oldFirst.nextNode;
        } else {
            // Reaching the end of the deque
            this.first = null;
            this.last = null;
        }
        this.numberOfNode--;
        return oldFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Empty queue");
        }
        Node oldLast = this.last;
        if (oldLast.previousNode != null) {
            // When previous node exist
            this.last = oldLast.previousNode;
        } else {
            // Reaching the end of the deque
            this.last = null;
            this.first = null;
        }
        this.numberOfNode--;
        return oldLast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        @Override
        public boolean hasNext() {
            return !Deque.this.isEmpty();
        }

        @Override
        public Item next() {
            return removeFirst();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("This deque does not support remove method");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        // Test isEmpty and size methods
        System.out.println("Is deque empty? " + deque.isEmpty()); // true
        System.out.println("Deque size: " + deque.size()); // 0

        // Test addFirst and addLast methods
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(0);
        deque.addLast(3);

        System.out.println("Is deque empty? " + deque.isEmpty()); // false
        System.out.println("Deque size: " + deque.size()); // 4

        // Test removeFirst and removeLast methods
        System.out.println("Remove first: " + deque.removeFirst()); // 0
        System.out.println("Remove last: " + deque.removeLast()); // 3

        System.out.println("Deque size: " + deque.size()); // 2

        // Test iterator
        deque.addFirst(-1);
        deque.addLast(4);

        System.out.print("Deque contents: ");
        for (int item : deque) {
            System.out.print(item + " ");
        }
        // Output should be: -1 1 2 4

        System.out.println("\nIs deque empty? " + deque.isEmpty()); // false
        System.out.println("Deque size: " + deque.size()); // 0 (after iteration and removal)

        // Test exception cases
        try {
            deque.addFirst(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage()); // Null item
        }

        try {
            deque.addLast(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage()); // Null item
        }

        try {
            deque.removeFirst();
        } catch (NoSuchElementException e) {
            System.out.println("Caught exception: " + e.getMessage()); // Empty queue
        }

        try {
            deque.removeLast();
        } catch (NoSuchElementException e) {
            System.out.println("Caught exception: " + e.getMessage()); // Empty queue
        }
    }

}