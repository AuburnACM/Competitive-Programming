import java.util.*;

public class IncreasingSubsequenceFast {

  static int longestIncreasingSubsequence(char[] A) {
    TreeMap<Character, Integer> idx = new TreeMap<>();
    int length = 0;


    for (char c : A) {
      if (idx.higherKey(c) != null) {
        char higher = idx.higherKey(c);
        int len = idx.get(higher);
        idx.remove(higher);
        idx.put(c, len);
      } else {
        idx.put(c, idx.size());
      }
    }
    return idx.size();
  }

  // Sample Usage
  public static void main(String[] args) {
    String test = "aiemckgobjfndlhp";
    assert longestIncreasingSubsequence(test.toCharArray()) == 6;
  }
}
