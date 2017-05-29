import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// From our library, include the following:
// search/UCS.java

public class KitchenMeasurements {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    // Read input.
    int N = in.nextInt();
    int[] sizes = new int[N];
    for (int i = 0; i < N; i++) {
      sizes[i] = in.nextInt();
    }
    int V = in.nextInt();

    // Make input accessible from state class.
    State.size = sizes;
    State.goal = V;

    // Build initial state.
    int[] startWater = new int[N];
    startWater[0] = sizes[0];
    State initial = new State(startWater, 0);

    // Perform UCS. doSearch will return -1
    // in the event of a failure.
    int res = UCS.doSearch(initial);
    System.out.println(res < 0 ? "impossible" : res);
  }
}

class State implements UCS.UCSState {

  // The capacity of each cup.
  static int[] size;
  // The target volume.
  static int goal;

  // The amount of water in each cup.
  int[] water;
  // The total distance this is from start.
  int dist;

  /**
   * Constructor for State.
   */
  State(int[] water, int dist) {
    this.water = water;
    this.dist = dist;
  }

  @Override
  /**
   * hashCode must be overwritten to allow
   * for the State to be used in HashMaps.
   * This is a necessary method of UCSState.
   */
  public int hashCode() {
    int out = 0;
    for (int w: water) {
      out *= 64;
      out += w;
    }
    return out;
  }

  @Override
  /**
   * equals returns true iff this state is
   * equal to the given object.
   * This is a necessary method of UCSState.
   */
  public boolean equals(Object o) {
    if (o instanceof State) {
      State s = (State) o;
      if (s.water.length != water.length) return false;
      for (int i = 0; i < water.length; i++)
        if (water[i] != s.water[i])
          return false;
      return true;
    }
    return false;
  }

  @Override
  /**
   * distance returns the total distance this
   * state is from the initial state.
   * This is a necessary method of UCSState.
   */
  public int distance() {
    return dist;
  }

  @Override
  /**
   * neighbors will return a list of all
   * UCSStates reachable from this state.
   * This is a necessary method of UCSState.
   */
  public List<UCS.UCSState> neighbors() {
    List<UCS.UCSState> out = new ArrayList<>();
    for (int src = 0; src < water.length; src++) {
      for (int dst = 0; dst < water.length; dst++) {
        if (src == dst) continue;
        int pour = Math.min(water[src], size[dst] - water[dst]);
        if (pour == 0) continue;
        int nDist = dist + pour;
        int[] nWater = Arrays.copyOf(water, water.length);
        nWater[src] -= pour;
        nWater[dst] += pour;
        out.add(new State(nWater, nDist));
      }
    }
    return out;
  }

  @Override
  /**
   * atDestination returns true iff this
   * state is a goal state.
   * This is a necessary method of UCSState.
   */
  public boolean atDestination() {
    return water[0] == goal;
  }
}
