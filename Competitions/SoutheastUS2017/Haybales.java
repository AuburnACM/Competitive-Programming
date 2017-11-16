import java.util.*;
import java.io.*;

public class Haybales {

  static int INF = 1000000;

  public static void main(String[] args) throws Exception {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] L = br.readLine().split(" ");
    int N = Integer.parseInt(L[0]);
    int K = Integer.parseInt(L[1]);

    char[][] grid = new char[N][0];

    @SuppressWarnings("unchecked")
    PriorityQueue<Best>[] cols = new PriorityQueue[N];
    for (int i = 0; i < N; i++) {
      grid[i] = br.readLine().toCharArray();
      cols[i] = new PriorityQueue<Best>(K);
    }


    int best = 0;

    cols[0].add(new Best(0, 0));
    for (int r = 0; r < N; r++) {

      PriorityQueue<Best> row = new PriorityQueue<>(K);
      if (r == 0) row.add(new Best(0, 0));

      for (int c = 0; c < N; c++) {
        if (grid[r][c] == '#') continue;
        if (r == 0 && c == 0) continue;

        int rowBest = findBest(row, c-K);
        int colBest = findBest(cols[c], r-K);

        best = Math.min(rowBest, colBest);
        if (best != INF) {
          row.add(new Best(best, c));
          cols[c].add(new Best(best, r));
        }

      }
    }

    System.out.println(best == INF ? -1 : best);
  }

  static int findBest(PriorityQueue<Best> pq, int min) {
    while(pq.size() > 0) {
      Best prev = pq.peek();
      if (prev.idx >= min) return prev.jumps + 1;
      pq.poll();
    }
    return INF;
  }
}

class Best implements Comparable<Best> {
  int jumps;
  int idx;

  Best(int jumps, int idx) {
    this.jumps = jumps;
    this.idx = idx;
  }

  public int compareTo(Best other) {
    if (jumps != other.jumps)
      return jumps - other.jumps;
    return other.idx - idx;
  }
}
