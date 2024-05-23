package org.example;

public class QuickUnionUF {

    public Integer[] id;

    public QuickUnionUF(Integer numberOfObjects) {
        this.id = new Integer[numberOfObjects];
        for (int i = 0; i < numberOfObjects; i++) {
            id[i] = i;
        }
    }

    public void union(Integer p, Integer q) {
        id[p] = q;
    }


    public Boolean connected(Integer p, Integer q) {
        // Search roots of the tree
        while (!id[p].equals(p)) {
            p = id[p];
        }
        while (!id[q].equals(q)) {
            q = id[q];
        }
        return p.equals(q);
    }
}
