import java.util.*;
import java.io.*;

public class Rainbow2 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    int N = Integer.parseInt(br.readLine());

    Node[] G = new Node[N];
    for (int i = 0; i < N; i++) G[i] = new Node();

    for (int i = 0; i < N-1; i++) {
      String[] L = br.readLine().split(" ");
      int U = Integer.parseInt(L[0]) - 1;
      int V = Integer.parseInt(L[1]) - 1;
      int C = Integer.parseInt(L[2]) - 1;

      G[U].addEdge(G[V], C);
      G[V].addEdge(G[U], C);
    }

    for (Node node : G) {
      Node[] prev = new Node[N];
      for (Edge edge : node.Edges) {
        if (prev[edge.Color] != null) {
          kill(prev[edge.Color], node);
          kill(edge.Node, node);
        }
        prev[edge.Color] = edge.Node;
      }
    }

    int count = 0;
    for (Node node : G) {
      if (node.Special) count++;
    }

    out.println(count);
    for (int i = 0; i < N; i++) {
      if (G[i].Special) out.println(i+1);
    }
    out.flush();
  }

  static void kill(Node node, Node parent) {

    if (node.Visits >= 2) return;
    node.Visits++;

    Stack<Visit> S = new Stack<>();
    S.push(new Visit(node, parent));

    while(S.size() > 0) {
      Edge edgeToParent = null;
      Visit V = S.pop();

      V.Node.Special = false;

      for (Edge edge : V.Node.Edges) {
        if (edge.Node != V.Parent && !edge.Visited && edge.Node.Visits < 2) {
          edge.Visited = true;
          edge.Node.Visits++;
          S.push(new Visit(edge.Node, V.Node));
        }
      }
    }
  }
}

class Visit {
  Node Node;
  Node Parent;

  Visit(Node Node, Node Parent) {
    this.Node = Node;
    this.Parent = Parent;
  }
}

class Node {
  List<Edge> Edges = new ArrayList<>();
  boolean Special = true;
  int Visits;

  void addEdge(Node neighbor, int color) {
    Edges.add(new Edge(neighbor, color));
    Visits = 0;
  }
}

class Edge {
  Node Node;
  int Color;
  boolean Visited;

  Edge(Node Node, int Color) {
    this.Node = Node;
    this.Color = Color;
    this.Visited = false;
  }
}
