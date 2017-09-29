import java.util.*;

public class MailboxIterative {
  public static void main(String[] args) {

    // memo[i][j][k] holds the answer to bestCost(i, j, k).
    // The number of mailboxes ranges from 0 to 10 (size 11).
    // The number of possible firecrackers ranges from 0 to 101 (we say
    // that knowing a mailbox can only fit 100 firecrackers is the same
    // as knowing 101 makes it explode).
    int[][][] memo = new int[11][102][102];

    // We know that if mailboxes = 1, for highestSafe < lowestExploded,
    // then the worst-case cost must be the sum of all integers between
    // highestSafe and lowestExploded.
    for (int highestSafe = 0; highestSafe <= 100; highestSafe++) {
      for (int lowestExploded = highestSafe + 1; lowestExploded <= 101; lowestExploded++) {
        int cost = 0;
        for (int f = highestSafe + 1; f < lowestExploded; f++) {
          cost += f;
        }
        memo[1][highestSafe][lowestExploded] = cost;
      }
    }

    // We know that if highestSafe + 1 = lowestExploded, our cost is 0, regardless
    // of the number of mailboxes.
    for (int mailboxes = 2; mailboxes <= 10; mailboxes++) {
      for (int highestSafe = 0; highestSafe < 101; highestSafe++) {
        memo[mailboxes][highestSafe][highestSafe+1] = 0;
      }
    }

    // We need to fill in the memoization array for every mailbox number
    // greater than 2, for every highestSafe from 0 to 100, and for every
    // lowestExploded from highestSafe + 2 to 101.
    //
    // We know bestCost(i, j, k) = min for f (j < f < k) of:
    // f + max(bestCost(i-1, j, f), bestCost(i, f, k))
    // Because this means we depend on smaller values of i and k, but higher
    // values of j, we need to iterate upwards on i and k, and backwards on j.
    for (int mailboxes = 2; mailboxes <= 10; mailboxes++) {
      for (int highestSafe = 100; highestSafe >= 0; highestSafe--) {
        for (int lowestExploded = highestSafe + 2; lowestExploded <= 101; lowestExploded++) {
          int best = Integer.MAX_VALUE;

          for (int fireworks = highestSafe + 1; fireworks < lowestExploded; fireworks++) {
            int cost = fireworks + Math.max(
              memo[mailboxes-1][highestSafe][fireworks],    // Mailbox explodes.
              memo[mailboxes][fireworks][lowestExploded]);  // Mailbox does not explode.

            best = Math.min(best, cost);
          }

          // Store our solution.
          memo[mailboxes][highestSafe][lowestExploded] = best;
        }
      }
    }

    // Now that we have all the answers, time to read in the input!
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    for (int i = 0; i < N; i++) {
      int M = in.nextInt();
      int K = in.nextInt();
      // We have m mailboxes. We know 0 fireworks is safe. If our mailbox can
      // fit up to K fireworks, that means it can't fit K+1, which is the same
      // as already knowing that K+1 fireworks would make the mailbox explode.
      System.out.println(memo[M][0][K+1]);
    }
  }
}
