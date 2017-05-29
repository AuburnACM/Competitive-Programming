import java.util.*;

public class WordCloudsRevisited {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt(), W = in.nextInt();

    Map<Integer, Size> memo = new HashMap<>();
    memo.put(0, new Size(0, 0));

    // Iterate over all text boxes.
    for (int i = 0; i < N; i++) {
      int w = in.nextInt(), h = in.nextInt();
      // Will hold the best values after inserting
      // this text box.
      Map<Integer, Size> nMemo = new HashMap<>();
      // Iterate over all trailing widths for the
      // previous insertion.
      for (int width: memo.keySet()) {
        Size prevSize = memo.get(width);
        // If we can insert this into the current
        // row, do so.
        if (width + w <= W) {
          int nCurrentRowHeight = Math.max(h,
              prevSize.currentRowHeight);
          Size nSize = new Size(nCurrentRowHeight,
            prevSize.previousRowsHeight);
          insert(nMemo, width + w, nSize);
        }
        // Always try creating a new row.
        Size nSize = new Size(h,
          prevSize.totalHeight);
        insert(nMemo, w, nSize);
      }
      // Copy over memoization.
      memo = nMemo;
    }
    // Find best at end.
    int best = Integer.MAX_VALUE;
    for (int width: memo.keySet()) {
      best = Math.min(best,
        memo.get(width).totalHeight);
    }
    System.out.println(best);
  }

  /**
   * Optimal Substucture - We want to store the
   * smallest total height (current row's current
   * height + height of any previous rows) attainable
   * for every possible width of the current row.
   */
  static void insert(Map<Integer, Size> m, int w,
      Size s) {

    if (!m.containsKey(w) || m.get(w).totalHeight >
        s.totalHeight) {
      m.put(w, s);
    }
  }
}

/**
 * Size represents the height of the word cloud
 * after a number of insertions.
 */
class Size {
  int currentRowHeight;
  int previousRowsHeight;
  int totalHeight;

  /**
   * Constructor for size.
   */
  public Size (int currentRowHeight,
    int previousRowsHeight) {

    this.currentRowHeight = currentRowHeight;
    this.previousRowsHeight = previousRowsHeight;
    totalHeight = currentRowHeight+previousRowsHeight;
  }
}
