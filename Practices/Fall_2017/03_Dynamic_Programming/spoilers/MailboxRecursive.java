import java.util.*;

public class MailboxRecursive {

  static int[][][] memo = new int[11][102][102];

  static int UNKNOWN = -1;

  public static void main(String[] args) {
    for (int i = 0; i <= 10; i++)
      for (int j = 0; j <= 101; j++)
        for (int k = 0; k <= 101; k++)
          memo[i][j][k] = UNKNOWN;

    Scanner in = new Scanner(System.in);
    int N = in.nextInt();

    for (int i = 0; i < N; i++) {
      int M = in.nextInt(), K = in.nextInt();
      // We have M mailboxes.
      // We know 0 firecrackers is safe.
      // Knowing that we can only fit K firecrackers is the same as knowing
      // that K+1 firecrackers will make the mailbox explode.
      System.out.println(bestCost(M, 0, K+1));
    }
  }

  static int bestCost(int mailboxes, int highestSafe, int lowestExploded) {

    // If we know K fireworks isn't too many, and K+1 fireworks is too many,
    // we know K is the correct answer, without needing to test anything else.
    if (lowestExploded == highestSafe + 1) {
      return 0;
    }

    // If we already computed the answer and stored it in the memo array,
    // then we can just return that answer and be done.
    if (memo[mailboxes][highestSafe][lowestExploded] != UNKNOWN)
      return memo[mailboxes][highestSafe][lowestExploded];

    // If we have exactly 1 mailbox remaining, then in the worstCase, we have
    // to try every mailbox from highestSafe+1 to lowestExploded-1.
    if (mailboxes == 1) {
      int res = 0;
      for (int i = highestSafe + 1; i < lowestExploded; i++) {
        res += i;
      }

      // Store the result before returning it.
      memo[mailboxes][highestSafe][lowestExploded] = res;
      return res;
    }

    // Otherwise, try every possible # of fireworks to insert into the mailbox
    // that is greater than highestSafe and less than lowestExploded, determine
    // how bad it is in the worst case, and pick the optimal strategy.
    int best = Integer.MAX_VALUE;
    for (int numFireworks = highestSafe + 1; numFireworks < lowestExploded; numFireworks++) {
      int cost = numFireworks + Math.max(
        bestCost(mailboxes-1, highestSafe, numFireworks),
        bestCost(mailboxes, numFireworks, lowestExploded));
      best = Math.min(best, cost);
    }

    // Store the result before returning it.
    memo[mailboxes][highestSafe][lowestExploded] = best;
    return best;
  }
}
