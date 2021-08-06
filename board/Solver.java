package board;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solver {
    // find a solution to the initial board (using the A* algorithm)
    private final List<Board> solution = new ArrayList<>();
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("initial board can't be null");
        }
        solve(initial);
    }

    private void solve(Board board) {
        MinPQ<Node> pq = new MinPQ<>(new MComparator());
        MinPQ<Node> pqTwin = new MinPQ<>(new MComparator());

        Node currentNode = new Node(board, null, 0);
        Node twCurrentNode = new Node(board.twin(), null, 0);

        pq.insert(currentNode);
        pqTwin.insert(twCurrentNode);

        while (!currentNode.board.isGoal() && !twCurrentNode.board.isGoal()) {

            currentNode = pq.delMin();
            for (Board b: currentNode.board.neighbors()) {
                if (currentNode.previousNode == null || !currentNode.previousNode.board.equals(b)) {
                    Node tmp = new Node(b, currentNode, currentNode.moves + 1);
                    pq.insert(tmp);
                }
            }

            twCurrentNode = pqTwin.delMin();
            for (Board b: twCurrentNode.board.neighbors()) {
                if (twCurrentNode.previousNode == null || !twCurrentNode.previousNode.board.equals(b)) {
                    Node tmp = new Node(b, twCurrentNode, twCurrentNode.moves + 1);
                    pqTwin.insert(tmp);
                }
            }
        }

        if (currentNode.board.isGoal() && !twCurrentNode.board.isGoal()) {
            while (currentNode != null) {
                solution.add(currentNode.board);
                currentNode = currentNode.previousNode;
            }
            Collections.reverse(solution);
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return !solution.isEmpty();
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable()) {
            return solution.size() - 1;
        } else {
            return -1;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (isSolvable()) {
            return solution;
        } else {
            return null;
        }
    }

    private class Node {
        private int moves;
        private int manhattan;
        private Node previousNode;
        private Board board;

        public Node(Board board, Node previousNode, int moves) {
            this.board = board;
            this.previousNode = previousNode;
            this.moves = moves;
            this.manhattan = board.manhattan() + moves;
        }
    }

    private static class MComparator implements Comparator<Node> {
        @Override
        public int compare(Node n1, Node n2) {
            return n1.manhattan - n2.manhattan;
        }
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
