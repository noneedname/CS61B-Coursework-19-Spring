package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int gridDimension;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufWithoutBottom;
    private int[][] isOpenGrid;
    private int numOpenGrid;
    private final int virtualUp;
    private final int virtualDown;

    private final int[][] directions = new int[][] {{-1, 0},  {0, -1}, {0, 1}, {1, 0}};

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Dimension cannot less or equal to zero!");
        }
        numOpenGrid = 0;
        virtualUp = 0;
        gridDimension = N;
        virtualDown = gridDimension * gridDimension + 1;
        uf = new WeightedQuickUnionUF(gridDimension * gridDimension + 2);
        ufWithoutBottom = new WeightedQuickUnionUF(gridDimension * gridDimension + 1);
        isOpenGrid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                isOpenGrid[i][j] = 0;
            }
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (isOpenGrid[row][col] == 1) {
            return;
        }
        isOpenGrid[row][col] = 1;
        numOpenGrid++;
        connectAround(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return isOpenGrid[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return ufWithoutBottom.connected(xyTo1D(row, col), virtualUp);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOpenGrid;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(virtualUp, virtualDown);
    }

    // throw exception when the input argument is invalid.
    private void validate(int row, int col) {
        if (row < 0 || row >= gridDimension || col < 0 || col >= gridDimension) {
            throw new IndexOutOfBoundsException("Illegal position input!!");
        }
    }

    // change 2-D grid to 1-D array.
    private int xyTo1D(int row, int col) {
        return row * gridDimension + col + 1;
    }

    // connect the opening blocks around a block.
    private void connectAround(int row, int col) {
        if (row == 0) {
            uf.union(xyTo1D(row, col), virtualUp);
            ufWithoutBottom.union(xyTo1D(row, col), virtualUp);
        }
        if (row == gridDimension - 1) {
            uf.union(xyTo1D(row, col), virtualDown);
        }
        for (int[] direction : directions) {
            int adjRow = row + direction[0];
            int adjCol = col + direction[1];
            if (adjRow >= 0 && adjRow < gridDimension && adjCol >= 0 && adjCol < gridDimension && isOpen(adjRow, adjCol)) {
                uf.union(xyTo1D(row, col), xyTo1D(adjRow, adjCol));
                ufWithoutBottom.union(xyTo1D(row, col), xyTo1D(adjRow, adjCol));
            }
        }
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {

    }

}
