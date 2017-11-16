import java.util.*;
import java.io.*;

public class Security {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] L = br.readLine().split(" ");
    int N = Integer.parseInt(L[0]);
    int M = Integer.parseInt(L[1]);

    L = br.readLine().split(" ");
    int S = Integer.parseInt(L[0])-1;
    int D = Integer.parseInt(L[1])-1;
    Node[] G = new Node[N];

    for (int i = 0; i < N; i++) G[i] = new Node();

    int[] ids = new int[2*M];

    for (int i = 0; i < M; i++) {
      L = br.readLine().split(" ");
      int U = Integer.parseInt(L[0]) - 1;
      int V = Integer.parseInt(L[1]) - 1;
      int lo = Integer.parseInt(L[2]);
      int hi = Integer.parseInt(L[3]);
      ids[2*i] = lo;
      ids[2*i+1] = hi+1;
      G[U].E.add(new Edge(G[V], lo, hi));
    }

    Arrays.sort(ids);

    int tot = 0;

    for (int i = 0; i < ids.length - 1; i++) {
      if (ids[i] == ids[i+1]) continue;
      if (G[S].visit(ids[i], i+1, G[D])) {
        tot += ids[i+1] - ids[i];
      }
    }

    System.out.println(tot);
  }
}

class Node {
  int lastVisited = 0;
  List<Edge> E = new ArrayList<>();

  boolean visit(int id, int visitNum, Node dst) {
    lastVisited = visitNum;
    if (dst == this) return true;
    for (Edge e : E) {
      if (e.to.lastVisited != visitNum && e.fits(id)) {
        e.to.lastVisited = visitNum;
        if (e.to.visit(id, visitNum, dst)) {
          return true;
        }
      }
    }
    return false;
  }
}

class Edge {
  int lo;
  int hi;
  Node to;

  Edge(Node to, int lo, int hi) {
    this.to = to;
    this.lo = lo;
    this.hi = hi;
  }

  public boolean fits(int id) {
    return id >= lo && id <= hi;
  }
}
