import java.util.*;
import java.text.*;

public class Move {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    int N = in.nextInt();
    Circle[] C = new Circle[N];

    for (int i = 0; i < N; i++) {
      C[i] = new Circle(in.nextInt(), in.nextInt(), in.nextInt());
    }

    List<Point> candidates = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      candidates.add(C[i].furthest());

      for (int j = i + 1; j < N; j++) {
        for (Point p : C[i].intersections(C[j])) {
          candidates.add(p);
        }
      }
    }

    double best = 0.0;
    Point O = new Point(0, 0);
    for (Point p : candidates) {
      boolean valid = true;
      for (Circle c : C) {
        if (!c.contains(p)) {
          valid = false;
          break;
        }
      }

      if (valid) {
        best = Math.max(best, O.dist(p));
      }
    }

    DecimalFormat fmt = new DecimalFormat("0.000");
    System.out.println(fmt.format(best));
  }
}

class Circle {
  static double EPSILON = 1e-9;

  Point Center;
  double R;

  Circle(double X, double Y, double R) {
    this.Center = new Point(X, Y);
    this.R = R;
  }

  Point[] intersections(Circle o) {
    double dist = Center.dist(o.Center);
    if (dist + o.R + EPSILON < R) return new Point[]{};
    double theta = Math.acos((R*R + dist * dist - o.R * o.R) / (2 * R * dist));
    double angle = Math.atan2(o.Center.Y - Center.Y, o.Center.X - Center.X);

    Point p1 = new Point(Center.X + Math.cos(theta + angle) * R, Center.Y + Math.sin(theta + angle) * R);
    Point p2 = new Point(Center.X + Math.cos(angle - theta) * R, Center.Y + Math.sin(angle - theta) * R);

    return new Point[]{p1, p2};
  }

  Point furthest() {
    double theta = Math.atan2(Center.Y, Center.X);
    return new Point(Center.X + R * Math.cos(theta), Center.Y + R * Math.sin(theta));
  }

  boolean contains(Point p) {
    double dist = Center.dist(p);
    return dist < R + EPSILON;
  }
}

class Point {
  double X;
  double Y;

  Point(double X, double Y) {
    this.X = X;
    this.Y = Y;
  }

  double dist(Point o) {
    double dX = X - o.X;
    double dY = Y - o.Y;
    return Math.sqrt(dX * dX + dY * dY);
  }
}
