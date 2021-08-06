package board;

import java.util.ArrayList;
import java.util.List;

public class Board {
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private final int dimension;
    private final int[][] tiles;
    private int blankRow;
    private int blankCol;

    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new NullPointerException();
        }

        this.tiles = tiles.clone();
        dimension = this.tiles.length;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] == 0) {
                    blankCol = j;
                    blankRow = i;
                }
            }
        }
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
        int count = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] != i * dimension + j + 1) {
                    count++;
                } else {
                    continue;
                }
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int move = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] != i * dimension + j + 1) {
                    int goalRow = tiles[i][j] / dimension;
                    int goalCol = tiles[i][j] % dimension;
                    move += Math.abs(goalCol - j) + Math.abs(goalRow - i);
                } else {
                    continue;
                }
            }
        }
        return move;
    }

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

        if (this.blankRow != that.blankRow) {
            return false;
        }

        if (this.blankCol != that.blankCol) {
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
    public Iterable<Board> neighbors() {
        List<Board> neighbours = new ArrayList<>();
        if (blankRow > 0) {
            int [][] north = tiles.clone();
            swap(north, blankRow, blankCol, blankRow - 1, blankCol);
            neighbours.add(new Board(north));
        }

        if (blankRow < dimension - 1) {
            int [][] south = tiles.clone();
            swap(south, blankRow, blankCol, blankRow + 1, blankCol);
            neighbours.add(new Board(south));
        }

        if (blankCol > 0) {
            int [][] east = tiles.clone();
            swap(east, blankRow, blankCol, blankRow, blankCol - 1);
            neighbours.add(new Board(east));
        }

        if (blankCol < dimension - 1) {
            int [][] west = tiles.clone();
            swap(west, blankRow, blankCol, blankRow, blankCol + 1);
            neighbours.add(new Board(west));
        }

        return neighbours;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {

    }

    private void swap (int [][] v, int rowA, int colA, int rowB, int colB) {
        int tmp = v[rowA][colA];
        v[rowA][colA] = v[rowB][colB];
        v[rowB][colB] = tmp;
    }
}
