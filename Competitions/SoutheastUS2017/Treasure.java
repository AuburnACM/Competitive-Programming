import java.util.*;
import java.io.*;

public class Treasure {

  static int[] G;
  static int[] D;
  static List<List<Edge>> E;
  static int[][] memo;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] L = br.readLine().split(" ");
    int N = Integer.parseInt(L[0]);
    int M = Integer.parseInt(L[1]);
    G = new int[N];
    D = new int[N];
    E = new ArrayList<>(N);
    for (int i = 0; i < N; i++) {
      E.add(new ArrayList<>());
    }

    for (int i = 0; i < N; i++) {
      L = br.readLine().split(" ");
      G[i] = Integer.parseInt(L[0]);
      D[i] = Integer.parseInt(L[1]);
    }

    for (int i = 0; i < M; i++) {
      L = br.readLine().split(" ");
      int a = Integer.parseInt(L[0]) - 1;
      int b = Integer.parseInt(L[1]) - 1;
      int t = Integer.parseInt(L[2]);
      E.get(a).add(new Edge(b, t));
      E.get(b).add(new Edge(a, t));
    }

    memo = new int[N][1001];
    for (int i = 0; i < N; i++) Arrays.fill(memo[i], -1);
    System.out.println(best(0, 0));
  }

  static int value(int mine, int day) {
    return Math.max(0, G[mine] - D[mine] * day);
  }

  static int best(int mine, int day) {
    if (day >= 1000) return 0;
    if (memo[mine][day] >= 0) return memo[mine][day];

    int solution = 0;
    for (Edge e : E.get(mine)) {
      solution = Math.max(solution, best(e.N, day + e.T));
    }
    solution += value(mine, day);
    memo[mine][day] = solution;
    return solution;
  }
}

class Edge {
  int N;
  int T;

  Edge(int N, int T) {
    this.N = N;
    this.T = T;
  }
}
