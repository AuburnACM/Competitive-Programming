import java.util.*;

public class IncreasingSubsequence {

  static int longestIncreasingSubsequence(char[] A) {
    char[] smallest = new char[A.length];
    Arrays.fill(smallest, (char) 255);
    int length = 0;

    for (int i = 0; i < A.length; i++) {
      int insertAt = 0;
      for (int j = 0; j < A.length; j++) {
        if (smallest[j] >= A[i]) {
          insertAt = j;
          break;
        }
      }
      smallest[insertAt] = A[i];
      length = Math.max(length, insertAt + 1);
    }
    return length;
  }

  // Sample Usage
  public static void main(String[] args) {
    String test = "aiemckgobjfndlhp";
    assert longestIncreasingSubsequence(test.toCharArray()) == 6;
  }
}
