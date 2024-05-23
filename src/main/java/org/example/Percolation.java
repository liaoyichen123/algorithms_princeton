package org.example;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    /**
     * The (n * n + 2) size array that store states of connection within the grid, the last two array is for storing
     * two virtual sites to accelerate finding if top and bottom row are connected
     */
    private final WeightedQuickUnionUF weightedQuickUnionUF;

    private final WeightedQuickUnionUF weightedQuickUnionUFTopOnly;

    private final int intWidth;

    /**
     * Store states of sites, 0: blocked, 1: open
     */
    private final int[][] arrayGrid;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Percolation.<init>: expected a value greater than zero instead of n: " + n);
        }
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        weightedQuickUnionUFTopOnly = new WeightedQuickUnionUF(n * n + 1);
        intWidth = n;
        arrayGrid = new int[n][n];
        // Connect two virtual sites to top or bottom row
        for (int i = 1; i <= intWidth; i++) {
            // Connect top virtual site with the top row
            weightedQuickUnionUF.union(n * n, this.dimensionReduction(1,i));
            // Connect bottom virtual site with the bottom row
            weightedQuickUnionUF.union(n * n + 1, this.dimensionReduction(intWidth,i));
            // Connect top virtual site with the top row for the top only
            weightedQuickUnionUFTopOnly.union(n * n, this.dimensionReduction(1,i));
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!this.isInBound(row, col)) {
            throw new IllegalArgumentException(String.format("Percolation.open: row: %d col: %d out of bound", row, col));
        }
        if (!isOpen(row, col)) {
            arrayGrid[row - 1][col - 1] = 1;
            // Search all its neighboring blocks and joining it if the block is a next to an open block
            // Search up
            if (row - 1 >= 1) {
                if (isOpen(row - 1, col)) {
                    weightedQuickUnionUF.union(this.dimensionReduction(row - 1, col), this.dimensionReduction(row, col));
                    weightedQuickUnionUFTopOnly.union(this.dimensionReduction(row - 1, col), this.dimensionReduction(row, col));
                }
            }
            // Search down
            if (row + 1 <= intWidth) {
                if (isOpen(row + 1, col)) {
                    weightedQuickUnionUF.union(this.dimensionReduction(row + 1, col), this.dimensionReduction(row, col));
                    weightedQuickUnionUFTopOnly.union(this.dimensionReduction(row + 1, col), this.dimensionReduction(row, col));
                }
            }
            // Search left
            if (col - 1 >= 1) {
                if (isOpen(row, col - 1)) {
                    weightedQuickUnionUF.union(this.dimensionReduction(row, col - 1), this.dimensionReduction(row, col));
                    weightedQuickUnionUFTopOnly.union(this.dimensionReduction(row, col - 1), this.dimensionReduction(row, col));
                }
            }
            // Search right
            if (col + 1 <= intWidth) {
                if (isOpen(row, col + 1)) {
                    weightedQuickUnionUF.union(this.dimensionReduction(row, col + 1), this.dimensionReduction(row, col));
                    weightedQuickUnionUFTopOnly.union(this.dimensionReduction(row, col + 1), this.dimensionReduction(row, col));
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!this.isInBound(row, col)) {
            throw new IllegalArgumentException(String.format("Percolation.isOpen: row: %d col: %d out of bound", row, col));
        }
        return arrayGrid[row - 1][col - 1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!this.isInBound(row, col)) {
            throw new IllegalArgumentException(String.format("Percolation.isFull: row: %d col: %d out of bound", row, col));
        }
        // A full block has to be an open block
        if (isOpen(row, col)) {
            // When block(row, col) is open, check if the top virtual point is connected with block(row, col)
            return  this.weightedQuickUnionUFTopOnly.find(intWidth * intWidth)
                    == this.weightedQuickUnionUFTopOnly.find(this.dimensionReduction(row, col));
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int intOpenSiteCounter = 0;
        // iterate through arrayGrid to check states of each site.
        for (int rowIndex = 0; rowIndex < intWidth; rowIndex++) {
            for (int colIndex = 0; colIndex < intWidth; colIndex++) {
                if (arrayGrid[rowIndex][colIndex] == 1) {
                    intOpenSiteCounter++;
                }
            }
        }
        return intOpenSiteCounter;
    }

    // does the system percolate?
    public boolean percolates() {
        // Check if the parent of the top virtual points is equals to the parent of the bottom virtual point.
        return this.weightedQuickUnionUF.find(intWidth * intWidth) == this.weightedQuickUnionUF.find(intWidth * intWidth + 1);
    }

    private Boolean isInBound(int row, int col) {
        return row > 0 && col > 0 && row <= intWidth && col <= intWidth;
    }

    private int dimensionReduction(int row, int col) {
        if (!this.isInBound(row, col)) {
            throw new IllegalArgumentException(String.format("Percolation.dimensionReduction: row: %d col: %d out of bound", row, col));
        }
        return intWidth * (row - 1) + (col - 1);
    }

}
