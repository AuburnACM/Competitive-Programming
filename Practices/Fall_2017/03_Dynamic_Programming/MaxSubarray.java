public class MaxSubarray {

  // Sample usage
  public static void main(String[] args) {
    assert maxSum(new int[]{ -2, 1, -3, 4, -1, 2, 1, -5, 4}) == 6;
  }

  static int maxSum(int[] A) {
    int runningTotal = 0, max = 0;

    for (int i = 0; i < A.length; i++) {
      runningTotal = Math.max(A[i], runningTotal + A[i]);
      max = Math.max(max, runningTotal);
    }

    return max;
  }
}
