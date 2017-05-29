import java.util.*;

public class Agglomerator {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    double time = 0;
    // Read input.
    int N = in.nextInt();
    List<Drop> D = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      D.add(new Drop(in.nextInt(), in.nextInt(),
        in.nextInt(), in.nextInt(), in.nextInt(), 0));
    }
    while(true) { // Repeat until done.
      // The drops to merge, how long until merging.
      int d1 = -1, d2 = -1;
      double nextTime = Integer.MAX_VALUE;
      // Look at all pairs of drops for next merge.
      for (int i = 0; i < D.size(); i++) {
        for (int j = i+1; j < D.size(); j++) {
          double dt = Drop.collisionTime(D.get(i),
              D.get(j));
          if (dt > 0 && dt < nextTime) {
            nextTime = dt; d1 = i; d2 = j;
          }
        }
      }
      // No more merges. We are done.
      if (d1 < 0) break;
      // Update the list of drops.
      List<Drop> newD = new ArrayList<>();
      newD.add(Drop.merge(
          D.get(d1).afterTime(nextTime),
          D.get(d2).afterTime(nextTime)));
      time += nextTime;
      for (int i = 0; i < D.size(); i++) {
        // Skip the drops to be merged.
        if (i == d1 || i == d2) continue;
        newD.add(D.get(i).afterTime(nextTime));
      }
      D = newD;
    }
    System.out.format("%d %.3f\n", D.size(), time);
  }
}

/**
 * Drop represents a droplet.
 */
class Drop {
  double x;   // X Coordinate
  double y;   // Y Coordinate
  double vx;  // X Velocity
  double vy;  // Y Velocity
  double r;   // Radius
  double t;   // Time at this postion.

  /**
   * Constructor for drop.
   */
  Drop(double x, double y, double vx, double vy,
      double r, double t) {

    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
    this.r = r;
    this.t = t;
  }

  /**
   * Combine 2 drops together, creating
   * a new drop.
   */
  static Drop merge(Drop a, Drop b) {
    double a1 = a.r * a.r;
    double a2 = b.r * b.r;
    double totalArea = a1 + a2;
    double nr = Math.sqrt(totalArea);
    double nx = (a.x * a1 + b.x * a2) / totalArea;
    double ny = (a.y * a1 + b.y * a2) / totalArea;
    double nvx = (a.vx * a1 + b.vx * a2) / totalArea;
    double nvy = (a.vy * a1 + b.vy * a2) / totalArea;
    return new Drop(nx, ny, nvx, nvy, nr, a.t);
  }

  /**
   * Produce a new drop, representing this drop
   * after dt seconds.
   */
  Drop afterTime(double dt) {
    return new Drop(x + vx * dt, y + vy * dt,
      vx, vy, r, t + dt);
  }

  /**
   * Given 2 drops, returns the first time at which
   * they will collide, or a negative value if they
   * won't collide.
   */
  static double collisionTime(Drop a, Drop b) {
    double dx = a.x - b.x;
    double dy = a.y - b.y;
    double dvx = a.vx - b.vx;
    double dvy = a.vy - b.vy;
    double sr = a.r + b.r;
    double A = dvx * dvx + dvy * dvy;
    double B = 2 * (dx * dvx + dy * dvy);
    double C = dx * dx + dy * dy - sr * sr;
    // Quadratic formula.
    double det = B * B - 4 * A * C;
    if (det < 0) return -1;
    // a is positive, so the smaller solution is the
    // one with the determinant subtracted.
    // Per the problem statement, both solutions will
    // either be positive or negative.
    return (-1 * B - Math.sqrt(det)) / (2 * A);
  }
}
