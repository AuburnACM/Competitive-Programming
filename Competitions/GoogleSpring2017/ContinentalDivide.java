import java.util.*;

//
// Continental Divide
// Auburn Google Mock Competition, Spring 2017
//
//                Description:
// We are given an NxN grid indicating the elevation of every point on a square
// continent. To the north and west of the continent lies the Pacific Ocean;
// the Atlantic ocean is on the south and east borders of the continent.
//
// Water in a given tile will flow to any tiles immediately North, South, East,
// or West, but only if the destination tile's elevation is less than or equal
// to the elevation of the source tile.
//
// A continental divide is a collection of tiles, such that all water on one
// side of the divide will flow ONLY to the Pacific Ocean, and all water on
// the other side will flow ONLY to the Atlantic Ocean.
//
// Given the continent's topography, print the coordinates (Row, Column) of all
// cells in the continental divide, sorted by row, then by column.
//
//                Example Case:
//
// 5                (1, 5)
// 12235            (2, 4)
// 32344            (2, 5)
// 24531    =>      (3, 3)
// 67145            (4, 1)
// 51124            (4, 2)
//                  (5, 1)
//
//                Solution:
// One potential O(N^2) solution is as follows: Perform a DFS going UPHILL (and
// between tiles of the same height) starting from the Pacific. Then, do the
// same, starting from the Atlantic. The continental divide is the set of cells
// that were visited from both the Atlantic and Pacific.
public class ContinentalDivide {

  static Cell[][] map;
  static int N;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    N = in.nextInt();
    map = new Cell[N][N];

    // Build the graph.
    for (int row = 0; row < N; row++) {
      char[] elevations = in.next().toCharArray();
      for (int column = 0; column < N; column++) {
        map[row][column] = new Cell(row, column, elevations[column]);
      }
    }

    // Launch DFS's.
    for (int i = 0; i < N; i++) {
      map[i][0].doDfs(Cell.PACIFIC);
      map[0][i].doDfs(Cell.PACIFIC);
      map[i][N-1].doDfs(Cell.ATLANTIC);
      map[N-1][i].doDfs(Cell.ATLANTIC);
    }

    // If a cell is visited from both oceans, print it out.
    for (int r = 0; r < N; r++) {
      for (int c = 0; c < N; c++) {
        if (map[r][c].visited[Cell.PACIFIC] && map[r][c].visited[Cell.ATLANTIC]) {
          System.out.println(map[r][c]);
        }
      }
    }
  }

  // Returns true iff the given cell is inside the map.
  public static boolean inRange(int val) {
    return val >= 0 && val < N;
  }

}

class Cell {
  static final int ATLANTIC = 0;
  static final int PACIFIC = 1;
  static final int NUM_OCEANS = 2;

  // The directions we can go.
  static final int[][] moves = new int[][] {
    {1, 0},
    {-1, 0},
    {0, 1},
    {0, -1}
   };

  // The height of this part of the map.
  int height;

  // What row (0 - indexed) it is in.
  int row;

  // What column (0 - indexed) it is in.
  int column;

  // Stores whehter this cell was visited from each column.
  boolean[] visited;

  Cell (int row, int column, char heightChar) {
    this.row = row;
    this.column = column;
    this.height = heightChar - '0';
    this.visited = new boolean[NUM_OCEANS];
  }

  void doDfs(int ocean) {
    // If we have already visited this cell from the given ocean, we can stop
    // now.
    if (visited[ocean]) return;

    // Otherwise, mark this cell as visited by that ocean.
    visited[ocean] = true;
    for (int[] move : moves) {
      int newRow = this.row + move[0];
      int newColumn = this.column + move[1];
      // If the adjacent cell is in the map and taller than ours, visit it.
      if (ContinentalDivide.inRange(newRow) &&
          ContinentalDivide.inRange(newColumn) &&
          ContinentalDivide.map[newRow][newColumn].height >= this.height) {
        ContinentalDivide.map[newRow][newColumn].doDfs(ocean);
      }
    }
  }

  public String toString() {
    return "(" + (row + 1) + ", " + (column + 1) + ")";
  }
}
