public class EggDrop {
  // Example Usage
  public static void main(String[] args) {
    assert eggDrop(10, 2) == 4;
    assert eggDrop(36, 2) == 8;
  }

  static int eggDrop(int N, int K) {
    int[][] memo = new int[N+1][K+1];
    for (int i = 1; i <= K; i++) {
      memo[1][i] = 1;
      memo[0][i] = 0;
    }

    for (int j = 1; j <= N; j++) {
      memo[j][1] = j;
    }

    for (int eggs = 2; eggs <= K; eggs++) {
      for (int floor = 2; floor <= N; floor++) {
        memo[floor][eggs] = Integer.MAX_VALUE;
        for (int firstDrop = 1; firstDrop <= floor; firstDrop++) {
          memo[floor][eggs] = Math.min(memo[floor][eggs], 1 +
            Math.max(memo[firstDrop-1][eggs-1], memo[floor-firstDrop][eggs]));
        }
      }
    }
    return memo[N][K];
  }
}
