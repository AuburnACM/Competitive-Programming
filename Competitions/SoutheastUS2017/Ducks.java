import java.util.*;
import java.io.*;

public class Ducks {

  static final int INF = 1000000000;
  static final int NOT_IN_CACHE = -1;

  static char[] L;
  static int N;
  static int K;
  static int M;
  static int[][][][] memo;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] LL = br.readLine().split(" ");
    N = Integer.parseInt(LL[0]);
    K = Integer.parseInt(LL[1]);
    L = br.readLine().toCharArray();
    M = L.length;

    if (K * N + K - 1 > M) {
      System.out.println(-1);
    } else {
      memo = new int[2][K+1][N+1][M];
      for (int i = 0; i < 2; i++)
        for (int j = 0; j <= K; j++)
          for (int k = 0; k <= N; k++)
            Arrays.fill(memo[i][j][k], NOT_IN_CACHE);

      System.out.println(solve(false, K, N, 0));
    }
  }

  static int solve(boolean casting, int seqsLeft, int ducksLeft, int pos) {

    if (seqsLeft == 0) return 0;
    if (pos == M) return INF;

    int dLeft = Math.max(0, ducksLeft);
    int castInt = casting ? 1 : 0;
    if (memo[castInt][seqsLeft][dLeft][pos] != NOT_IN_CACHE)
      return memo[castInt][seqsLeft][dLeft][pos];

    boolean isDuck = ((L[pos] == 'D') != casting);

    int best = INF;
    int newDucksLeft = ducksLeft - 1;
    int newSeqsLeft = (newDucksLeft == 0 ? seqsLeft - 1 : seqsLeft);

    if (isDuck)
      best = Math.min(best, solve(casting, newSeqsLeft, newDucksLeft, pos + 1));
    else
      best = Math.min(best, solve(casting, seqsLeft, N, pos + 1));

    if (!isDuck)
      best = Math.min(best, 1-castInt + solve(!casting, newSeqsLeft, newDucksLeft, pos + 1));
    else
      best = Math.min(best, 1-castInt + solve(!casting, seqsLeft, N, pos + 1));
    memo[castInt][seqsLeft][dLeft][pos] = best;
    return best;
  }
}
