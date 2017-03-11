import java.util.*;
import java.io.*;

//
// Count More Islands
// Auburn Google Mock Competition, Spring 2017
//
//                Description:
// There is an expanse of ocean, represented as a grid, of tiles that can
// be either land or water. All tiles start as water. We must process a list
// of events / queries, all of which are of one of 3 types.
//
// 1) Volcano (V <X> <Y>): A volcano has occurred at (X, Y). This will cause
// the tile located at (X, Y) to be land.
//
// 2) Earthquake (E <X> <Y>): An earthquake as occurred at (X, Y). This will
// cause the tile located at (X, Y) to be water.
//
// 3) Count the number of islands (C): You must count the number of islands
// and print out the count. An island is a contiguous set of land tiles, which
// may be connected up, down, left, or right from one another.
//
//                Example Case:
//
// 4 6 11
// V 0 3
// C                  1
// V 3 5
// V 0 2
// C                  2
// E 0 3    =>
// V 1 2
// V 1 3
// C                  2
// E 1 2
// C                  3
//
//                Solution:
// The basic approach is to have a set of points that can be modified when
// volcanoes (add a point) or earthquakes (remove a point) occur. In the
// event of a query, we can simply either:
// 1) Perform a DFS / BFS on every yet-unvisited land tile, incrementing a
// counter for each one we find.
// 2) Use a Union-Find to determine which land tiles share an island. While
// increasing the time complexity by a factor of lg(N), this is very easy
// to implement.
public class CountMoreIslands {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] L = br.readLine().split(" ");
    int numberOfQueries = Integer.parseInt(L[2]);

    Set<Point> landTiles = new HashSet<>();

    int[][] moves = new int[][] {
      {0, -1},
      {0, 1},
      {1, 0},
      {-1, 0}
    };

    for (int query = 0; query < numberOfQueries; query++) {

      L = br.readLine().split(" ");
      // We have a "count" query.
      if (L.length == 1) {

        // Holds the total # of islands.
        int count = landTiles.size();
        UnionFind uf = new UnionFind(landTiles);

        // For every piece of land,
        for (Point p : landTiles) {
          // Look up, down, left, right.
          for (int[] move : moves) {
            Point newPoint = new Point(p.x + move[0], p.y + move[1]);

            // If that point is land, and we haven't marked them as being
            // part of the same island, decrease the number of unique islands,
            // and then union the two, joining the islands together.
            if (landTiles.contains(newPoint) && uf.find(p) != uf.find(newPoint)) {
              count--;
              uf.union(p, newPoint);
            }
          }
        }

        System.out.println(count);

      // We have a "volcano" query.
      } else if (L[0].charAt(0) == 'V') {
        int x = Integer.parseInt(L[1]);
        int y = Integer.parseInt(L[2]);
        // Add a point to the set of land tiles.
        landTiles.add(new Point(x, y));
      // We have an "earthquake" query.
      } else {
        int x = Integer.parseInt(L[1]);
        int y = Integer.parseInt(L[2]);
        // Remove a point from the set of land tiles.
        landTiles.remove(new Point(x, y));
      }
    }
  }

}

class Point {
  int x;
  int y;

  Point (int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Point) {
      Point otherPoint = (Point) other;
      return otherPoint.x == x && otherPoint.y == y;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return x * 17 + y;
  }
}

class UnionFind {
  int[] parent;
  Map<Point, Integer> ids = new HashMap<>();

  UnionFind (Set<Point> points) {
    parent = new int[points.size()];
    int idx = 0;
    for (Point p : points) {
      ids.put(p, idx);
      parent[idx] = idx;
      idx++;
    }
  }

  int find(Point p) {
    return findHelper(ids.get(p));
  }

  int findHelper(int a) {
    if (parent[a] == a) return a;
    parent[a] = findHelper(parent[a]);
    return parent[a];
  }

  void union(Point p1, Point p2) {
    parent[find(p1)] = find(p2);
  }
}
