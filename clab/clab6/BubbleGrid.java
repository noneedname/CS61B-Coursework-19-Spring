public class BubbleGrid {

    private int[][] grid;
    private UnionFind uf;
    private final int numRow;
    private final int numCol;
    private final int cellingUnit = 0;
    private final int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        numRow = grid.length;
        numCol = grid[0].length;
        uf = new UnionFind(numCol * numRow + 1);
    }

    /* Turn the 2-D grid coordinate into 1-D position. */
    private int gridToArray(int row, int col) {
        return numCol * row + col + 1;
    }

    /* Connect an bubble to its orthogonally adjacent bubbles. */
    private void connectToNeighbor(int row, int col) {
        //The top row are automatically connected with the celling.
        if (row == 0) {
            uf.union(gridToArray(row, col), cellingUnit);
        }
        if (grid[row][col] == 1) {
            for (int[] direction: directions) {
                int adjRow = row + direction[0];
                int adjCol = col + direction[1];
                if(adjRow >= 0 && adjRow < numRow && adjCol >= 0 && adjCol < numCol && grid[adjRow][adjCol] == 1) {
                    uf.union(gridToArray(row, col), gridToArray(adjRow, adjCol));
                }
            }
        }
    }
    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int[] result = new int[darts.length];

        //Mark the grid units which are going to be hit and whether there's a bubble.
        for (int[] dart : darts) {
            grid[dart[0]][dart[1]] = grid[dart[0]][dart[1]] == 1 ? -1 : 0;
        }

        //Connect all bubbles remaining, after the hitting of darts.
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                connectToNeighbor(i, j);
            }
        }

        //Move back-forward to find how many bubbles fell after a dart hitting.
        for (int i = darts.length - 1; i >= 0; i--) {
            int prevSize = uf.sizeOf(cellingUnit);
            if (grid[darts[i][0]][darts[i][1]] == -1) {
                grid[darts[i][0]][darts[i][1]] = 1;
                connectToNeighbor(darts[i][0], darts[i][1]);
                result[i] = Math.max(uf.sizeOf(cellingUnit) - prevSize - 1, 0);
            } else {
                result[i] = 0;
            }
        }

        return result;
    }
}
