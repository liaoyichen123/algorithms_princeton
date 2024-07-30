package week2.queue;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Integer numberOfItem = 0;

    private Node first;

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

    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return Integer.valueOf(0).equals(this.numberOfItem);
    }

    // return the number of items on the randomized queue
    public int size() {
        return numberOfItem;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (this.isEmpty()) {
            this.first = new Node(item, null, null);
        } else {
            Node oldFirst = this.first;
            Node newNode = new Node(item, null, oldFirst);
            oldFirst.previousNode = newNode;
            this.first = newNode;
        }
        numberOfItem++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("empty queue");
        } else {
            int randomIndex = StdRandom.uniformInt(this.numberOfItem);
            Node retNode = first;
            for (int i = 0; i < randomIndex; i++) {
                retNode = retNode.nextNode;
            }
            if (retNode.nextNode == null && retNode.previousNode == null) {
                // reaching the last node
                this.first = null;
            } else if (retNode.previousNode == null) {
                // reaching the head
                this.first = retNode.nextNode;
                retNode.nextNode.previousNode = null;
            } else if (retNode.nextNode == null) {
                // reaching the tail
                retNode.previousNode.nextNode = null;
            } else {
                // reaching the node between two nodes
                retNode.nextNode.previousNode = retNode.previousNode;
                retNode.previousNode.nextNode = retNode.nextNode;
            }
            numberOfItem--;
            return retNode.item;
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("empty queue");
        } else {
            int randomIndex = StdRandom.uniformInt(this.numberOfItem);
            Node retNode = first;
            if (randomIndex != 0) {
                for (int i = 0; i < randomIndex; i++) {
                    retNode = retNode.nextNode;
                }
            }
            return retNode.item;
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        @Override
        public boolean hasNext() {
            return !RandomizedQueue.this.isEmpty();
        }

        @Override
        public Item next() {
            return dequeue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("RandomizedQueue does not support remove");
        }

    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}
