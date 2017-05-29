import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * UCS implements basic functionality for
 * performing Uniform Cost Searches, given an
 * implementation of the UCSState interface.
 */
public class UCS {

  /**
   * A contestant's implementation fo UCSState
   * can be used to perform a Uniform Cost Search.
   * It represents a state in the search's state
   * space, along with how far this search is from
   * the initial state, and if it is a goal state.
   */
  public interface UCSState {

    /**
     * distance should return the distance this
     * state is from the initial state.
     */
    public int distance();

    /**
     * neighbors should return a list of all
     * states reachable from the given state.
     * It does not need to keep track of whether
     * those states have been visited, provided
     * hashCode and equals are properly implemented.
     */
    public List<UCSState> neighbors();

    /**
     * atDestination should return true iff the
     * given state is a goal state.
     */
    public boolean atDestination();

    /**
     * equals should return true if the object
     * passed is an equivalent state to this
     * state. Note that this equivalence should
     * be independent of the distance the two
     * states have been reached at.
     *
     * For example, if s1 represents reaching
     * node 5 after 7 steps, and s2 represents
     * reaching node 5 after 9 steps, this should
     * still return true, because we only care
     * about what node we are at (the state),
     * not the distance.
     */
    public boolean equals(Object o);

    /**
     * s1.hashCode() must equal s2.hashCode() if
     * s1.equals(s2) is true. Otherwise, the less
     * occurrences where s1.equals(s2) is false, but
     * s1.hashCode() = s2.hashCode(), the faster
     * the search will be.
     */
    public int hashCode();
  }

  /**
   * UCSStateComparator is used to define a
   * comparison for the UCS's priority queue
   * between 2 states.
   */
  private static class UCSStateComparator
    implements Comparator<UCSState> {

    public int compare(UCSState s1, UCSState s2) {
      return s1.distance() - s2.distance();
    }
  }

  /**
   * doSearch will take an initial UCSState and
   * will return either the minimum distance
   * to a goal state or -1 if no path could be
   * found.
   */
  public static int doSearch(UCSState initial) {
    Map<UCSState, Integer> best = new HashMap<>();
    PriorityQueue<UCSState> Q = new PriorityQueue<>(128,
      new UCSStateComparator());

    Q.add(initial);
    best.put(initial, initial.distance());

    while(Q.size() > 0) {
      UCSState s = Q.poll();
      if (best.get(s) < s.distance()) continue;
      if (s.atDestination()) return s.distance();

      List<UCSState> neighbors = s.neighbors();
      for (UCSState neighbor : neighbors) {
        if (!best.containsKey(neighbor) ||
          best.get(neighbor) > neighbor.distance()) {

          best.put(neighbor, neighbor.distance());
          Q.add(neighbor);
        }
      }
    }

    return -1;
  }
}
