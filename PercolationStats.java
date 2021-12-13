/* *****************************************************************************
 *  Name:              Maksim Tikhonov
 *  Coursera User ID:  d951be14d5a09d1ae01e539156f6cd0c
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final int trials;
    private int openCellsCount;
    private final double[] openCellsWeight;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();

        this.trials = trials;

        openCellsWeight = new double[trials];

        for (int trial = 0; trial < trials; trial++) {
            Percolation grid = new Percolation(n);

            while (!grid.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!grid.isOpen(row, col)) {
                    this.openCellsCount++;
                    grid.open(row, col);
                }
            }

            openCellsWeight[trial] = (double)
                    this.openCellsCount / (n * n);
            this.openCellsCount = 0;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.openCellsWeight);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (trials == 1) {
            return 0.0;
        }
        return StdStats.stddev(this.openCellsWeight);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - ((this.stddev()) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + ((this.stddev()) / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length != 2) {
            return;
        }

        final int sitesInLine = Integer.parseInt(args[0]);
        final int trials = Integer.parseInt(args[1]);

        // Stopwatch stopwatch = new Stopwatch();
        PercolationStats percolationStats = new PercolationStats(sitesInLine, trials);
        // double elapsedTime = stopwatch.elapsedTime();
        // System.out.println("elapsed time             = ",
        //                                  elapsedTime));
        System.out.println("mean                    = " +
                                         percolationStats.mean());
        System.out.println("stddev                  = " +
                                         percolationStats.stddev());
        System.out.println("95% confidence interval = [" +
                                         percolationStats.confidenceLo()+ ", " + percolationStats.confidenceHi() + "]");
    }
}