package org.example;

import java.util.Objects;

public class QuickFindUF {
    public Integer[] id;

    public QuickFindUF(Integer numberOfObjects) {
        this.id = new Integer[numberOfObjects];
        for (int i = 0; i < id.length; i++){
            id[i] = i;
        }
    }

    public void union(Integer p, Integer q) {
        Integer oldValue = id[p];
        for (int i = 0; i < id.length; i++) {
            if (Objects.equals(id[i],oldValue)) {
                id[i] = id[q];
            }
        }
    }


    public Boolean connected(Integer p, Integer q) {
        return Objects.equals(id[p], id[q]);
    }

}
