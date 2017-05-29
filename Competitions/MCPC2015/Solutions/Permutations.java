/**
 * The Permutations class contains utility functions for
 * brute forcing every permutation of a set of numbers.
 */
public class Permutations {

  /**
   * The PermutationWorker interface should be
   * implemented by the contestant, and should perform
   * some action in the permutationWork method, which
   * will be called by runPermutations, passing in all
   * necessary permutations in the process.
   */
  public interface PermutationWorker {
    public void permutationWork(int[] permutation);
  }

  /**
   * runPermutations will take a PermutationWorker and an
   * integer N and will repeatedly call the
   * PermutationWorker's permutationWork method, passing
   * in all permutations of [0...N] in the process. It is
   * the contestant's job to implement the
   * PermutationWorker's permutationWork method.
   */
  public static void runPermutations(PermutationWorker w, int N) {

    int[] o = new int[N];
    for (int i = 0; i < N; i++) o[i] = i;
    runPermutations(o, 0, w);
  }

  /**
   * Helper method used in brute-forcing all permutations
   */
  private static void runPermutations(int[] p, int d,  PermutationWorker w) {

    if (d == p.length) {
      w.permutationWork(p);
    } else {
      for (int i = d; i < p.length; i++) {
        swap(p, d, i);
        runPermutations(p, d+1, w);
        swap(p, d, i);
      }
    }
  }

  /**
   * Swap two variables in an array.
   */
  private static void swap(int[] A, int i, int j) {
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
  }

}

