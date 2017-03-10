import java.util.*;

//
// Counting Islands
// Auburn Google Mock Competition, Spring 2017
//
//                Description:
// In Bomberman, there is an NxN grid of cells containing 1 of 3 things:
// 1) An empty cell, denoted 'x'.
// 2) An enemy, denoted 'E'.
// 3) A wall, denoted 'W'.
//
// You can place a bomb on any empty cell. The blast will travel along the
// bomb's row and column, destroying all enemies it encounters until blocked
// by a wall.
//
// Given one bomb, what is the most enemies you can destroy.
//
//                Example:
// 5
// Exxxx
// Exxxx
// xxEWE          =>          3
// Wxxxx
// Exxxx
//
//                Solution:
// We can look at the rows and columns separately. In each case, we can simply
// work along the row or column, counting how many enemies we see. Once we
// find a wall, we will backfill all elements before the previous wall with
// the count. This will produce the following arrays:
//
// ROWS       COLUMNS
// 11111      20101
// 11111      20101
// 11101      20101
// 00000      00101
// 11111      10101
//
// After that, we just need to find an empty cell such that the row + column
// value is maximized.
public class Bomberman {

  static final char EMPTY = 'x';
  static final char ENEMY = 'E';
  static final char WALL = 'W';

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    in.nextLine();

    char[][] grid = new char[N][N];
    for (int row = 0; row < N; row++) {
      grid[row] = in.nextLine().toCharArray();
    }

    // Calculate result of horizontal part of blast.
    int[][] leftAndRight = new int[N][N];
    for (int row = 0; row < N; row++) {
      int lastObstacle = -1;
      int count = 0;
      for (int col = 0; col < N; col++) {
        if (grid[row][col] == ENEMY) {
          count++;
        } else if (grid[row][col] == WALL) {
          for (int i = col-1; i > lastObstacle; i--) {
            leftAndRight[row][i] = count;
          }
          count = 0;
          lastObstacle = col;
        }
      }
      for (int i = N-1; i > lastObstacle; i--) {
        leftAndRight[row][i] = count;
      }
    }

    // Calculate result of vertical part of blast.
    int[][] upAndDown = new int[N][N];
    for (int col = 0; col < N; col++) {
      int lastObstacle = -1;
      int count = 0;
      for (int row = 0; row < N; row++) {
        if (grid[row][col] == ENEMY) {
          count++;
        } else if (grid[row][col] == WALL) {
          for (int i = row-1; i > lastObstacle; i--) {
            upAndDown[i][col] = count;
          }
          count = 0;
          lastObstacle = row;
        }
      }
      for (int i = N-1; i > lastObstacle; i--) {
        upAndDown[i][col] = count;
      }
    }

    // Find the maximum.
    int max = 0;
    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        if (grid[row][col] == EMPTY) {
          max = Math.max(max, leftAndRight[row][col] + upAndDown[row][col]);
        }
      }
    }

    System.out.println(max);
  }
}
