import java.util.*;

//
// Counting Islands
// Auburn Google Mock Competition, Spring 2017
//
//                Description:
// We are given an RxC grid which serves as a map of some part of the ocean.
// There are two possible elements on the map - land, denoted x, and water,
// denoted o. This values are given in a comma-separated format.
//
// You must number the islands, with island 1 being the 1st island working
// left->right, then top->bottom. An island is a contiguous set of land
// tiles that are connected to one another by moving up, down, left, or right. Ocean tiles should be numbered '0'.
//
//                Example Case:
// 4 6
// o,o,x,o,o,o              0,0,1,0,0,0
// o,o,x,x,o,x        =>    0,0,1,1,0,2
// o,x,o,o,x,o              0,3,0,0,4,0
// o,o,o,o,o,x              0,0,0,0,0,5
//
//                Solution:
// We can simply iterate over the rows and columns of the map, and number any
// contiguous islands we find with their number using a BFS.
public class CountingIslands {

  static final int WATER = 0;
  static final int LAND = -1;

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    int R = in.nextInt();
    int C = in.nextInt();
    in.nextLine();

    int[][] map = new int[R][C];
    for (int row = 0; row < R; row++) {
      String[] inputLine = in.nextLine().split(",");
      for (int column = 0; column < C; column++) {
        map[row][column] = inputLine[column].charAt(0) == 'o' ? WATER : LAND;
      }
    }

    int[][] moves = new int[][] {
      {1, 0},
      {-1, 0},
      {0, 1},
      {0, -1}
    };

    int islandNumber = 1;

    for (int row = 0; row < R; row++) {
      for (int column = 0; column < C; column++) {
        if (map[row][column] == LAND) {

          Queue<Cell> bfsQueue = new LinkedList<>();

          map[row][column] = islandNumber;
          bfsQueue.add(new Cell(row, column));

          while(bfsQueue.size() > 0) {
            Cell currentCell = bfsQueue.poll();

            for (int[] move : moves) {
              int nextRow = currentCell.row + move[0];
              int nextColumn = currentCell.column + move[1];

              if (nextRow >= 0 && nextRow < R && nextColumn >= 0 &&
                  nextColumn < C && map[nextRow][nextColumn] == LAND) {

                map[nextRow][nextColumn] = islandNumber;
                bfsQueue.add(new Cell(nextRow, nextColumn));
              }
           }
          }

          islandNumber++;
        }
      }
    }

    for (int row = 0; row < R; row++) {
      StringBuilder outputLine = new StringBuilder();
      for (int column = 0; column < C; column++) {
        if (column > 0) {
          outputLine.append(',');
        }
        outputLine.append(map[row][column]);
      }
      System.out.println(outputLine);
    }
  }
}

class Cell {
  int row;
  int column;

  Cell (int row, int column) {
    this.row = row;
    this.column = column;
  }
}
