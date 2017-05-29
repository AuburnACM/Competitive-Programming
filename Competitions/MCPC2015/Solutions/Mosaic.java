import java.util.*;
import java.io.*;
public class Mosaic {
  static final int WHITE = 0; // Completely black.
  static final int BLACK = 1; // Completely white.
  static final int TRITL = 2; // Top Left half is black.
  static final int TRITR = 3; // Top Right half is black.
  static final int TRIBR = 4; // Bottom Right half is black.
  static final int TRIBL = 5; // Bottom Left half is black.
  static final int UNSET = 6; // Un-determined tile.

  static int R; // Number of rows.
  static int C; // Number of columns.
  static int numTriangles = 0; // Count number of triangles.

  static final int TOP = 0;
  static final int RIGHT = 1;
  static final int BOTTOM = 2;
  static final int LEFT = 3;

  // Arbitary large number, the count of triangles adjacent
  // adjacent to a tile with an unknown count.
  static final int UNKNOWN_COUNT = 100;

  // Hardcoded listing of which sides are black.
  static int[] sides = new int[] {
    0b0000, 0b1111, 0b1001, 0b0011, 0b0110, 0b1100
  };

  // The locations of the top-left of all 2x2 tiles
  // containing a given tile, relative to that tile.
  static int[][] sqStart = new int[][] {
    {0, 0}, {-1, 0}, {0, -1}, {-1, -1}
  };

  // Up, Down, Left, and Right.
  static int[][] dirs = new int[][] {
    {0, -1}, {0, 1}, {-1, 0}, {1, 0}
  };

  // Will hold precomputed results of each 2x2 tile working.
  static boolean[] valid = new boolean[1<<12];

  public static void main(String[] args) throws Exception {

    // Precompute valid 2x2 tiles.
    computeValid();

    // Read in the input, surrounding it with black
    // tiles.
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] L = br.readLine().split(" ");
    C = Integer.parseInt(L[0]);
    R = Integer.parseInt(L[1]);

    int[][] G = new int[R+2][C+2];
    int[][] count = new int[R+2][C+2];

    for (int i = 0; i < G.length; i++) {
      Arrays.fill(G[i], BLACK);
      Arrays.fill(count[i], UNKNOWN_COUNT);
    }

    for (int i = 0; i < R; i++) {
      char[] input = br.readLine().toCharArray();
      for (int j = 0; j < C; j++) {
        char c = input[j];
        if (c == '.') {
          G[i+1][j+1] = UNSET;
        } else if (c != '*') {
          count[i+1][j+1] = c - '0';
        }
      }
    }

    // Fill in the board and print result.
    solve(G, count, 1, 1);
    System.out.println(numTriangles);
  }

  /**
   * solve will recursively attempt to fill g will a
   * set of tiles, working left to right, then top down
   * starting at (r, c).
   */
  static boolean solve(int[][] g, int[][] count, int r, int c) {

    // Passed all rows. We did it!
    if (r > R) return true;

    // Compute what the next row and column will be.
    int nextRow = r;
    int nextCol = c + 1;

    if (nextCol > C) {
      nextCol = 1;
      nextRow++;
    }

    // If it is already determined to be black, move to
    // the next case.
    if (g[r][c] == BLACK) {
      return solve(g, count, nextRow, nextCol);
    } else {
      // Otherwise, try all non-black tiles.
      for (int t = WHITE; t < UNSET; t++) {
        if (t == BLACK) continue;

        // Set this tile.
        g[r][c] = t;
        if (t != WHITE) numTriangles++;

        // If it is invalid, recurse to keep filling.
        if (validPlacement(g, count, r, c)) {
          if (solve(g, count, nextRow, nextCol)) return true;
        }

        // If we reached this, this was not the
        // correct guess. Clear it.
        g[r][c] = UNSET;
        if (t != WHITE) numTriangles--;
      }
    }

    // Nothing we tried worked. Something before this
    // must be at fault.
    return false;
  }


  /**
   * validPlacement returns true if the tile at (r, c) in
   * the mosaic g, with the given counts is valid.
   */
  static boolean validPlacement(int[][] g, int[][] count,
    int r, int c) {

    // Verify that all 2x2 squares containing (r, c)
    // either contain unset tiles or are valid.
    for (int[] sq : sqStart) {
      int h = hash(g, r + sq[0], c + sq[1]);
      if (h >= 0 && !valid[h]) {
        return false;
      }
    }

    // Verify that if there are any adjacent tiles with a
    // tile count requirement, that the requirement is
    // still satisfied.
    for (int[] move : dirs) {
      int nr = r + move[0];
      int nc = c + move[1];
      // For all adjacent tiles, if there is a requirement:
      if (count[nr][nc] != UNKNOWN_COUNT) {
        int adj = numTrianglesAdjacent(g, nr, nc);
        // Are there too many triangles adjacent to this tile?
        if (adj > count[nr][nc]) {
          return false;
        }

        // Are there not enough, even if all unset adjacent
        // to this become triangles?
        if (adj + numUnsetAdjacent(g, nr, nc) < count[nr][nc]) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * numTrianglesAdjacent will return the number of triangle
   * tiles adjacent to (r, c) in g.
   */
  static int numTrianglesAdjacent(int[][] g, int r, int c) {
    int count = 0;
    for (int[] move : dirs) {
      int v = g[r + move[0]][c + move[1]];
      if (v >= TRITL && v <= TRIBL) count++;
    }
    return count;
  }

  /**
   * numUnknownAdjacent will return the number of unset
   * tiles adjacent to (r, c) in g.
   */
  static int numUnsetAdjacent(int[][] g, int r, int c) {
    int count = 0;
    for (int[] move : dirs) {
      int v = g[r + move[0]][c + move[1]];
      if (v == UNSET) count++;
    }
    return count;
  }

  /**
   * hash returns a number uniquely identifying the 2x2 set
   * of tiles in g starting at r, c if there are no unset
   * tiles. If there are any unset tiles, it will return a
   * negative number.
   */
  static int hash(int[][] g, int r, int c) {
    int out = 0;
    for (int i = 0; i < 4; i++) {
      out = out << 3;
      int v = g[r + (i&1)][c + (i >> 1)];
      if (v == UNSET) return -1;
      out += v;
    }
    return out;
  }

  /**
   * computeValid will pre-compute which 2x2 tiles are valid.
   */
  static void computeValid() {

    // Brute-force all combinations of 4 tiles.
    for (int q1 = 0; q1 < 6; q1++) {
      for (int q2 = 0; q2 < 6; q2++) {
        for (int q3 = 0; q3 < 6; q3++) {
          for (int q4 = 0; q4 < 6; q4++) {

            // Build a 2x2 grid from these 4 tiles.
            int[][] quad = new int[][] {
              {q1, q2}, {q3, q4}
            };

            // If it is valid, set the corresponding entry.
            if (quadIsValid(quad)) {
              valid[hash(quad, 0, 0)] = true;
            }
          }
        }
      }
    }
  }

  /**
   * whiteSide returns true if the given type of tile
   * is white on the given side.
   */
  static boolean whiteSide(int tile, int side) {
    return (sides[tile] & (1 << side)) == 0;
  }

  /**
   * quadIsValid returns true if the given 2x2 tile
   * arrangement is valid. Note that this may say
   * that the given arrangement is valid, when it
   * could contain an error, but that error is
   * guaranteed to be caught, due to an overlapping
   * 2x2 pattern being invalid.
   */
  static boolean quadIsValid(int[][] q) {

    boolean[] isWhite = new boolean[16];
    // Look at all 8 interior sides of the 2x2 group,
    // moving clockwise. (each side is looked at
    // once for both neighboring tiles).
    isWhite[0] = whiteSide(q[0][0], BOTTOM);
    isWhite[1] = whiteSide(q[0][0], RIGHT);
    isWhite[2] = whiteSide(q[0][1], LEFT);
    isWhite[3] = whiteSide(q[0][1], BOTTOM);
    isWhite[4] = whiteSide(q[1][1], TOP);
    isWhite[5] = whiteSide(q[1][1], LEFT);
    isWhite[6] = whiteSide(q[1][0], RIGHT);
    isWhite[7] = whiteSide(q[1][0], TOP);

    // Duplicate those 8, just to catch any issues
    // near the start/end of the pattern.
    for (int i = 8; i < 16; i++)
      isWhite[i] = isWhite[i-8];

    int wCount = 0, bCount = 0;
    // In the event of <black> <odd # white> <black> sides
    // we have an angle that is not 90/180 degrees.
    // In the event of <black> <6 white> <black> sides,
    // we have white components that form an 'L'.
    // Return false in either case.
    for (int i = 0; i < 16; i++) {
      if (isWhite[i]) {
        wCount++;
      } else {
        if (bCount > 0 && (wCount % 2 == 1 || wCount == 6))
          return false;
        bCount++;
        wCount = 0;
      }
    }
    // We found no other issues.
    return true;
  }
}
