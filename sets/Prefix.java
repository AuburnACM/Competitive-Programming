import java.util.*;

class Prefix {

  /**
   * isPrefix returns true iff x is the prefix to some word in the dictionary in
   * O(log(n) + k) time where n is the size of the dictionary and k is the
   * longest word in the dictionary.
   */
  public static boolean isPrefix(String x, TreeSet<String> dictionary) {
    String val = dictionary.ceiling(x);
    if (val == null) {
      return false;
    }
    return val.startsWith(x);
  }

  public static void main(String[] args) {
    TreeSet<String> dictionary = new TreeSet<>();
    dictionary.add("aa");
    dictionary.add("ab");
    dictionary.add("ac");
    dictionary.add("ad");
    System.out.println(isPrefix("a", dictionary));
    System.out.println(isPrefix("aa", dictionary));
    System.out.println(isPrefix("ae", dictionary));
  }

}
