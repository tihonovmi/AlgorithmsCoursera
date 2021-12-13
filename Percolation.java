/* *****************************************************************************
 *  Name:              Maksim Tikhonov
 *  Coursera User ID:  d951be14d5a09d1ae01e539156f6cd0c
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final Site[][] grid;
    private final WeightedQuickUnionUF dataStructure;
    private int numberOfOpenSites = 0;
    private final int sitesInLine;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException();
        }


        grid = new Site[n + 1][n + 1];
        sitesInLine = n;
        dataStructure = new WeightedQuickUnionUF(n * n + 2);
        int numberInDataStructure = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                grid[i][j] = new Site(numberInDataStructure++);
            }
        }

        // connect top row sites to virtual top site
        for (int i = 1; i <= n; i++) {
            dataStructure.union(i, 0);
        }

        // connect bottom row sites to virtual bottom site
        for (int i = n * n; i > n * n - n; i--) {
            dataStructure.union(i, (n * n) + 1);
        }
    }

    // opens the site (row, col) if it is not open already
    // open and union with isOpen around
    public void open(int row, int col) {
        Site currentSite = getSite(row, col);

        if (!currentSite.isOpen) {
            currentSite.setOpen();
            numberOfOpenSites++;

            if (row == 1) currentSite.setFullOpen();

            int up = row - 1;
            int down = row + 1;
            int left = col - 1;
            int right = col + 1;

            if (up > 0 && isOpen(up, col)) {
                dataStructure.union(currentSite.numInDtStrct, getSite(up, col).numInDtStrct);
                if (getSite(up, col).isFullOpen) currentSite.setFullOpen();
            }
            if (down <= sitesInLine && isOpen(down, col)) {
                dataStructure.union(currentSite.numInDtStrct, getSite(down, col).numInDtStrct);
                if (getSite(down, col).isFullOpen) currentSite.setFullOpen();
            }
            if (left > 0 && isOpen(row, left)) {
                dataStructure.union(currentSite.numInDtStrct, getSite(row, left).numInDtStrct);
                if (getSite(row, left).isFullOpen) currentSite.setFullOpen();
            }
            if (right <= sitesInLine && isOpen(row, right)) {
                dataStructure.union(currentSite.numInDtStrct, getSite(row, right).numInDtStrct);
                if (getSite(row, right).isFullOpen) currentSite.setFullOpen();
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return getSite(row, col).isOpen;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        Site currentSite = getSite(row, col);

        if (!currentSite.isOpen) return false;
        if (currentSite.isFullOpen) return true;
        if (dataStructure.find(0) == dataStructure.find(currentSite.numInDtStrct)) return true;

        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {

        int virtualTop = 0;
        int virtualBottom = sitesInLine * sitesInLine + 1;

        // if 1 site
        if (virtualBottom == 2) {
            return isOpen(1, 1);
        }

        // if no sites
        if (virtualBottom == 0) {
            return false;
        }

        return dataStructure.find(virtualTop) == dataStructure.find(virtualBottom);
    }

    private void checkRowColRange(int row, int col) {
        if (row < 1 || row > grid.length - 1 || col < 1 || col > grid.length -1)
            throw new IllegalArgumentException("row = " + row + ", col = " + col);
    }

    private Site getSite(int row, int col) {
        checkRowColRange(row, col);
        return grid[row][col];
    }

    private static class Site {
        private final int numInDtStrct;
        private boolean isOpen;
        private boolean isFullOpen;

        public Site(int numInDtStrct) {
            this.numInDtStrct = numInDtStrct;
            this.isOpen = false;
            this.isFullOpen = false;
        }

        public int getNumInDtStrct() {
            return numInDtStrct;
        }

        public boolean isOpen() {
            return isOpen;
        }

        public void setOpen() {
            isOpen = true;
        }

        public void setFullOpen() {
            isFullOpen = true;
        }

        public boolean isFullOpen() {
            return isFullOpen;
        }
    }
}