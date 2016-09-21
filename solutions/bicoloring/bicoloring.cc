/**
 * Problem URL: https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=37&page=show_problem&problem=945.
 *
 * Tags: graphs, bipartite, coloring, dfs
 *
 * Problem Description:
 *
 * This problem asks you to check if a graph can be bicolored. The solution
 * involves noticing that a graph can be bicolored iff it is bipartite. This is
 * made easier becuse the graphs are said to be simple and connected.  A graph
 * can be checked to be bipartite by coloring some random node n in the graph a
 * certain color, performing a depth-first search from n, and coloring all
 * children the opposite color of their parent.
 *
 * If at the end of the search, no edges have vertices of the same color, the
 * graph is bipartite and therefore can be bicolored.
 */

#include <iostream>
#include <vector>

using namespace std;

/** RED color is the first possible color. */
#define RED 0
/** BLACK color is the second possible color. */
#define BLACK 1

/**
 * color is represented by a numeric value.
 *
 * Can take the above values RED or BLUE.
 */
typedef int color;
/**
 * vertex is represented by a numeric value since the vertex labels are said to
 * be contigous in the range of [1, n).
 */
typedef int vertex;
/**
 * vertex_list is a list of vertices.
 */
typedef vector<vertex> vertex_list;
/**
 * adjacency_list associates a vertex to a position, and that position to a list
 * of vertices adjacent to the initial one.
 */
typedef vector<vertex_list> adjacency_list;
/**
 * color_list associates a vertex to a position, and that position to the color
 * of the vertex at that position.
 */
typedef vector<color> color_list;

/** complement returns the opposite color of the given color.  */
color complement(color c) {
  if (c == RED) {
    return BLACK;
  }
  return RED;
}

/**
 * two_color all vertices in the graph represented by the adjacency_list.
 *
 * The coloring is stored in color_list, the current color and vertex are also
 * given.
 */
void two_color(adjacency_list& list, color_list& coloring, color c, vertex v) {
  // This is a simple DFS avoiding cycles which colors vertices at each step.
  //  Set the vertex's color to the current color.
  coloring[v] = c;
  // Flip the color to the opposite value.
  //
  // If it is currently RED, make it BLACK. If it is BLACK, make it RED.
  c = complement(c);
  // For all vertices adjacent to the given vertex.
  for (int neighbor : list[v]) {
    // A vertex hasn't been seen iff it hasn't been colored, meaning its
    // coloring is 0.
    if (coloring[neighbor] == 0) {
      // Perform the two_coloring on the adjacent vertex recursively.
      two_color(list, coloring, c, neighbor);
    }
  }
}

/**
 * good_to_coloring returns true iff the provided color_list represents a valid
 * two coloring of the graph represented by the adjacency_list.
 *
 * This is true iff there are no two vertices connected by an edge which share
 * the same color.
 */
bool good_two_coloring(adjacency_list& list, color_list& coloring) {
  // All edges can be found by iterating over the adjacency list, where the
  // index is the edge's starting vertex and the list associated with that
  // position represents the end vertices of all edges connected to the starting
  // vertex.
  //
  // For all starting vertices in the adjacency list.
  for (vertex i = 0; i < list.size(); i++) {
    // Find all ending vertices in the starting vertices incident edges.
    for (vertex j : list[i]) {
      // i <-> j is an edge in the graph.
      //
      // If i, j have the same color, the graph doesn't have a good bicoloring.
      if (coloring[i] == coloring[j]) {
        return false;
      }
    }
  }
  // Here, all edges have been checked, so the bicoloring is good.
  return true;
}

/**
 * main reads input until a 0 value is received, and checks if the graphs
 * represented by the given vertices and edges are bicolorable.
 */
int main() {
  while (true) {
    int vertexCount, edgeCount;
    cin >> vertexCount;
    // This signals end of input, so stop.
    if (vertexCount == 0) {
      break;
    }
    cin >> edgeCount;
    // Make an adjacency list with room for n vertices.
    adjacency_list list(vertexCount);
    for (int i = 0; i < vertexCount; i++) {
      list.push_back(vertex_list());
    }
    int start, end;
    for (int i = 0; i < edgeCount; i++) {
      cin >> start >> end;
      // Store the edge in both ends of the adjacency list.
      list[start].push_back(end);
      list[end].push_back(start);
    }
    // Make a color_list with room for n colorings.
    color_list coloring(vertexCount);
    // Two color the graph represented by list starting by coloring vertex 0
    // RED. The result is stored in coloring.
    two_color(list, coloring, RED, 0);
    // If the two coloring is good, the graph is bicolorable.
    if (good_two_coloring(list, coloring)) {
      cout << "BICOLORABLE." << endl;
    } else {
      cout << "NOT BICOLORABLE." << endl;
    }
  }
}
