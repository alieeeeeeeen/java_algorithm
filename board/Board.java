package board;

public class Board {
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private final int dimension;
    private final int[][] tiles;

    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new NullPointerException();
        }

        this.tiles = tiles.clone();
        dimension = this.tiles.length;
    }

    // string representation of this board
    public String toString() {

    }

    // board dimension n
    public int dimension() {
        return dimension;
    }

    // number of tiles out of place
    public int hamming() {
        int move = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] != i * dimension + j + 1) {
                    int goalRow = tiles[i][j] / dimension;
                    int goalCol = tiles[i][j] % dimension;
                    move += goalCol - j + goalRow - i;
                } else {
                    continue;
                }
            }
        }
        return move;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan()

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        Board that = (Board) y;

        if (this.dimension != that.dimension) {
            return false;
        }

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                 if (tiles[i][j] != that.tiles[i][j]) {
                     return false;
                 }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors()

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {

    }
}
