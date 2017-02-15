import java.util.*;
import java.io.*;

// Coast Length can be found at:
// https://open.kattis.com/problems/coast
//
// This is some starter code that will read in input for
// you and will calculate the coast length once the ocean
// has been identified. That's where you come in!
//
// Our solution will work as follows:
//
// For every test case, we will first read in the data.
// In order to make things easier, we will pretend as though
// the entire map is surrounded by an extra ring of water tiles.
//
// Example:
//
//                    00000000
//  011110            00111100
//  010110            00101100
//  111000      ==>   01110000
//  000010            00000100
//  000000            00000000
//                    00000000
//
// After that, YOU will be responsible for adding edges to the graph.
//
// We will define our graph as follows:
//
//    Add an edge between two cells of the map IF and ONLY IF:
//    1) The two cells are touching (up/down/left/right)
//    2) Both cells are water.
//
// We will then perform a DFS starting from one of the tiles along the outside
// border. A proper DFS will visit every "ocean" tile, and will not visit any
// other tiles (land or lakes).
//
// Once again, YOU will be responsible for writing the DFS.
//
// After that, the program will iterate over every tile. For every land tile,
// it will add the number of adjacent ocean tiles to the total coast length,
// and print out the total.
//
// TLDR: You need to build the graph and perform the DFS.
public class CoastLength {

  static final int[][] directions = new int[][]{
    {-1, 0},  // UP
    {1, 0},   // DOWN
    {0, -1},  // LEFT
    {0, 1}    // RIGHT
  };

  static int numRows;
  static int numCols;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // Get the first line.
    String[] L = br.readLine().split(" ");
    int N = Integer.parseInt(L[0]);
    int M = Integer.parseInt(L[1]);
    numRows = N+2;
    numCols = M+2;
    Node[][] graph = new Node[numRows][numCols];
    char[][] map = new char[N][M];

    // Read in the map.
    for (int i = 0; i < N; i++) {
      char[] line = br.readLine().toCharArray();
      for (int j = 0; j < M; j++) {
        map[i][j] = line[j];
      }
    }

    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        // If this isn't a border cell, put the appropriate map tile there.
        if (row >= 1 && row <= N && col >= 1 && col <= M) {
          // Use - '0' to convert from ascii char to int.
          graph[row][col] = new Node(map[row-1][col-1] - '0');
        } else { // Otherwise, say it is ocean.
          graph[row][col] = new Node(Node.WATER);
        }
      }
    }

    // Here, we need to build the graph.
    // This involves going to every cell in the map, and at every cell,
    // looking in all 4 directions. If the cell in that direction is in bounds,
    // and both the cell we are at and the cell we are looking at are water,
    // we should an edge between them.


    // /============================\
    // |    WRITE YOUR CODE HERE    |
    // \============================/

    // /============================\
    // |    END OF YOUR CODE HERE   |
    // \============================/

    // Here, we call the DFS. Because of the border we added around the
    // outside, this cell is guaranteed to be ocean.
    graph[0][0].doDFS();

    // Now we are tallying up the total number of coast tiles.
    int coastLen = 0;

    // Iterate over all the cells.
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {

        // If it is land:
        if (graph[row][col].type == Node.LAND) {

          // Count the number of adjacent water cells that are
          // in the ocean (visited).
          for (int[] move : directions) {
            int nRow = move[0] + row;
            int nCol = move[1] + col;
            if (inBounds(nRow, nCol)
                && graph[nRow][nCol].type == Node.WATER
                && graph[nRow][nCol].visited) {

              coastLen++;
            }
          }

        }
      }
    }

    // Print out the answer.
    System.out.println(coastLen);
  }

  // inBounds takes a row and column and returns true if and only if
  // they are valid indices of graph.
  public static boolean inBounds(int row, int col) {
    return row >= 0 && row < numRows && col >= 0 && col < numCols;
  }
}

class Node {
  static final int WATER = 0;
  static final int LAND = 1;

  int type;
  boolean visited;
  List<Node> neighbors;

  Node(int type) {
    this.type = type;
    visited = false;
    neighbors = new ArrayList<>();
  }


  public void doDFS() {
    // /============================\
    // |    WRITE YOUR CODE HERE    |
    // \============================/
  }
}
