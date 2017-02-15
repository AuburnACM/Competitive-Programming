import java.util.*;

// CountingStars can be found at:
// https://open.kattis.com/problems/countingstars
//
// This is some starter code that will build the
// graph for you, all you need to do is implement the
// DFS on it!
//
// Our solution will work as follows:
//
// For every test case, we will first read in the data
// and build the graph.
//
// Our goal is to find clusters of '-' symbols. We will build our
// graph by only adding edges between two '-' cells that are up/down
// or left/right from one another.
//
// Then, we will look at every cell in the map. Every time
// we find a '-' cell that has not yet been visited by a depth
// first search, we will count that as a new star (and increase
// the number of stars by one).
//
// Once we find one pixel of the new star, we will visit every other
// cell / pixel in the image that is part of that star, using a
// depth first search (this is the part you have to do).
//
// We will repeat this process until we have checked every pixel
// in the image, at which point we will print out the number
// of stars that we counted.
public class CountingStars {
  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    int caseNumber = 0;

    while(in.hasNext()) {
      caseNumber++;

      // Read in the first line of the test case.
      int numberOfRows = in.nextInt();
      int numberOfColumns = in.nextInt();
      in.nextLine(); // Read trailing '\n' from new line.

      // Read in the "pixels" from the camera.
      Node[][] graph = new Node[numberOfRows][numberOfColumns];

      for (int row = 0; row < numberOfRows; row++) {
        char[] line = in.nextLine().toCharArray();
        for (int col = 0; col < numberOfColumns; col++) {
          graph[row][col] = new Node(line[col]);
        }
      }

      // directions will hold the directions that one cell
      // can be from another to be a neighbor.
      int[][] directions = new int[][] {
        {-1, 0},    // Up     (Back 1 row, same column).
        {1, 0},     // Down   (Forward 1 row, same column).
        {0, -1},    // Left   (Back 1 column, same row).
        {0, 1},     // Right  (Forward 1 column, same row).
      };

      // Actually build the graph.
      for (int row = 0; row < numberOfRows; row++) {
        for (int col = 0; col < numberOfColumns; col++) {
          // Look at every cell. Our graph is really only
          // concerned with '-' pixels, so we will only look at those.
          if (graph[row][col].pixel == '-') {
            // We will then look at the adjacent cell in every
            // direction (Up, Down, Left, Right), using directions.
            for (int[] move : directions) {
              int adjRow = row + move[0];   // Get the row of the adjacent cell.
              int adjCol = col + move[1];   // Get the col of the adjacent cell.

              // Make sure that adjRow and adjCol are in bounds first!
              if (adjRow >= 0 && adjRow < numberOfRows &&
                  adjCol >= 0 && adjCol < numberOfColumns) {

                // If the adjacent cell is also a '-', then they are neighbors.
                if (graph[adjRow][adjCol].pixel == '-') {
                  graph[row][col].neighbors.add(graph[adjRow][adjCol]);
                }
              }
            }
          }
        }
      }

      int numberOfStars = 0;

      // Now that the graph is built, we can start looking for stars!
      // What we will do is look for any '-' pixels that we haven't visited yet.
      // This means that they are part of a star that we haven't counted yet.
      // Then, once we find one, we will increment our counter, and use a
      // depth first search to visit every other pixel that makes up this star.
      // That way, the other pixels in this star will be marked as visited
      // when we examine them later, so we will know they are part of a star
      // that has already been counted.
      for (int row = 0; row < numberOfRows; row++) {
        for (int col = 0; col < numberOfColumns; col++) {

          // Check if this is a new star.
          if (graph[row][col].pixel == '-' && !graph[row][col].visited) {

            // We found a new star! Increment our counter and use a DFS
            // to mark every other pixel in this star.
            numberOfStars++;
            graph[row][col].doDFS();
          }
        }
      }

      // Print the output for this case.
      System.out.println("Case " + caseNumber + ": " + numberOfStars);
    }
  }
}

// Node will represent a vertex in our graph.
// When we build the graph, we will construct a grid
// of nodes.
class Node {
  boolean visited;
  char pixel;
  List<Node> neighbors;

  public Node(char pixel) {
    this.pixel = pixel;
    visited = false;
    neighbors = new ArrayList<>();
  }

  public void doDFS() {
    // /============================\
    // |    WRITE YOUR CODE HERE    |
    // \============================/

    // You will need to implement a DFS in this method.
    // Remember, you just need to recursively visit every
    // unvisited neighbor.
  }
}
