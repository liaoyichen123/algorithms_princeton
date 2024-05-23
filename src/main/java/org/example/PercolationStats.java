package org.example;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


import java.lang.Math;


public class PercolationStats {

    /**
     * 1.96 is used for a 95% confidence level
     */
    private static final double CONFIDENT_INTERVAL_95 = 1.96;
    /**
     * Recorder of results of trials on counting numbers of open function call
     */
    private final int[] resultRecoder;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException(String.format("Illegal n=%d, trials=%d ", n, trials));
        }
        resultRecoder = new int[trials];

        for (int trialCounts = 0; trialCounts < trials; trialCounts++) {
            Percolation percolation = new Percolation(n);
            String[] randomCoordinates = this.getRandomCoordinatesArray(n);
            int openCounter = 0;
            for (String coordinate : randomCoordinates) {
                if (percolation.percolates()) {
                    break;
                }
                String[] rowAndCol = coordinate.split(",");
                int row = Integer.parseInt(rowAndCol[0]);
                int col = Integer.parseInt(rowAndCol[1]);
                percolation.open(row, col);
                openCounter++;
            }
            resultRecoder[trialCounts] = openCounter;
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(resultRecoder);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(resultRecoder);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - ((CONFIDENT_INTERVAL_95 * this.stddev()) / Math.sqrt(resultRecoder.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + ((CONFIDENT_INTERVAL_95 * this.stddev()) / Math.sqrt(resultRecoder.length));
    }


    /**
     * @param gridWidth width of the grid
     * @return: a string array of coordinates: Consists all coordinates of a grid, row and col is separated by ","
     * e.g. [15,67, 98,52, 53,125, 79,95...]
     */
    private String[] getRandomCoordinatesArray(int gridWidth) {
        String[] coordinates = new String[gridWidth * gridWidth];
        int index = 0;
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridWidth; j++) {
                coordinates[index++] = String.format("%d,%d", i + 1, j + 1);
            }
        }
        StdRandom.shuffle(coordinates);
        return coordinates;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(n, trails);
        String confidence = percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi();
        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = " + confidence);

    }
}



