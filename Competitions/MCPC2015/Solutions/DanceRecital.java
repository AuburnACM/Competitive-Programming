import java.util.Scanner;

// From our libary, include the following:
// brute_force/Permutations.java

public class DanceRecital {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();

    // We will record which dancers are present in
    // each dance.
    boolean[][] present = new boolean[N][26];
    for (int i = 0; i < N; i++) {
      for (char c : in.next().toCharArray()) {
        present[i][c-'A'] = true;
      }
    }

    // quickChanges[i][j] will hold the number of
    // quick changes that need to occur between
    // dance i and dance j.
    int[][] quickChanges = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        // For each possible dancer:
        for (int dancer = 0; dancer < 26; dancer++) {
          // If the given dancer is present in
          // both dances, that means they must
          // perform a quick change.
          if (present[i][dancer] && present[j][dancer]) {
            quickChanges[i][j]++;
          }
        }
      }
    }

    // Create a PermutationWorker.
    Worker w = new Worker(quickChanges);
    // Run the permutation worker, performing our
    // computation for every dancer permutation.
    Permutations.runPermutations(w, N);
    System.out.println(w.best);
  }
}

class Worker implements Permutations.PermutationWorker {
  int[][] D;
  int best;

  Worker(int[][] D) {
    this.D = D;
    best = Integer.MAX_VALUE;
  }

  // The permutations library will call this
  // function for each permutation of [0...N].
  // Each time, the associated permutation will be
  // passed in as p.
  public void permutationWork(int[] p) {
    // Calculate the total # of quick changes.
    int cost = 0;
    for (int i = 1; i < p.length; i++) {
      cost += D[p[i]][p[i-1]];
    }
    // Update our best if necessary.
    best = Math.min(best, cost);
  }
}
