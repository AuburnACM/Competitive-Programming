import java.util.*;

// Wet Tiles can be found at:
// https://open.kattis.com/problems/wettiles
//
// This starter program will read in the input.
// The rest is up to you!
public class WetTiles {

  static final int FLOOR = 0;
  static final int WALL = 1;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    while (true) {
      int X = in.nextInt();
      if (X < 0) return;

      int Y = in.nextInt();
      int T = in.nextInt();
      int L = in.nextInt();
      int W = in.nextInt();

      int[][] map = new int[Y][X];

      List<Cell> leaks = new ArrayList<>();

      for (int i = 0; i < L; i++) {
        int col = in.nextInt();
        int row = in.nextInt();
        leaks.add(new Cell(row, col));
      }

      for (int i = 0; i < W; i++) {
        int startCol = in.nextInt();
        int startRow = in.nextInt();
        int endCol = in.nextInt();
        int endRow = in.nextInt();
        int dCol = endCol - startCol;
        int dRow = endRow - startRow;
        int row = startRow;
        int col = startCol;

        while (row != endRow && col != endCol) {
          map[row][col] = WALL;
          row += dRow;
          col += dCol;
        }
      }

    }
  }
}

class Cell {
  int row;
  int col;

  Cell(int row, int col) {
    this.row = row;
    this.col = col;
  }
}
