import java.util.*;

public class PrefixTree {

  public static class PrefixTreeNode {
    boolean endOfWord;
    Map<Character, PrefixTreeNode> children;

    PrefixTreeNode() {
      children = new HashMap<>();
    }
  }

  PrefixTreeNode head;

  PrefixTree() {
    head = new PrefixTreeNode();
  }

  void add(String s) {
    PrefixTreeNode n = head;
    for (char c : s.toCharArray()) {
      if (!n.children.containsKey(c)) {
        n.children.put(c, new PrefixTreeNode());
      }
      n = n.children.get(c);
    }
    n.endOfWord = true;
  }

  boolean contains(String s) {
    PrefixTreeNode n = head;
    for (char c : s.toCharArray()) {
      if (!n.children.containsKey(c)) {
        return false;
      }
      n = n.children.get(c);
    }
    return n.endOfWord;
  }

  // Example Usage
  public static void main(String[] args) {
    PrefixTree pt = new PrefixTree();
    assert(!pt.contains("HELLO"));
    assert(!pt.contains("HE"));
    assert(!pt.contains("JELLO"));
    assert(!pt.contains("GEL"));
    pt.add("HELLO");
    pt.add("HE");
    pt.add("JELLO");
    pt.add("GEL");
    assert(pt.contains("HELLO"));
    assert(pt.contains("HE"));
    assert(pt.contains("JELLO"));
    assert(pt.contains("GEL"));
    assert(!pt.contains("GELLO"));
    assert(!pt.contains("HEL"));
  }
}
