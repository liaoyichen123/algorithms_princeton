package org.example;

public class WeightedQuickUnionUF {

    public Integer[] id;

    public Integer[] weight;

    public WeightedQuickUnionUF(Integer numberOfObjects) {
        this.id = new Integer[numberOfObjects];
        for (int i = 0; i < id.length; i++){
            id[i] = i;
        }
    }

    public void union (Integer p, Integer q) {
        id[q] = p;
    }

    public Integer getRoot (Integer p) {
        while (!id[p].equals(p)) {
            p = id[p];
        }
        return p;
    }

    public Integer getWeight (Integer p) {
        return 0;
    }



    public Boolean connected (Integer p, Integer q) {
        return true;
    }
}
