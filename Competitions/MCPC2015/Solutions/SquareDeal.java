import java.util.*;

public class SquareDeal {
  public static void main(String[] args) {
    Rect[] R = new Rect[3];
    Scanner in = new Scanner(System.in);
    // Read in input.
    for (int i = 0; i < 3; i++) {
      R[i] = new Rect(in.nextInt(), in.nextInt());
    }
    System.out.println(square(R) ? "YES" : "NO");
  }

  static boolean square(Rect[] R) {
    // Try all 8 combinations of rotations.
    for (int i = 0; i < 8; i++) {
      // T will hold a combination.
      Rect[] T = new Rect[3];
      for (int j = 0; j < 3; j++) {
        // Base the orientation of the jth triangle
        // on the jth bit of i.
        T[j] = R[j].rotate(i & (1 << j));
      }
      // Test if all 3 can be lined up.
      if (T[0].W + T[1].W + T[2].W == T[0].H &&
          T[0].H == T[1].H && T[1].H == T[2].H) {
        return true;
      }
      // Test if you can put 2 together and the
      // other can go across them.
      for (int j = 0; j < 3; j++) {
        // Wrap around.
        int r1 = j;
        int r2 = (j+1) % 3;
        int r3 = (j+2) % 3;

        if (T[r1].H == T[r2].H &&
            T[r1].W + T[r2].W == T[r3].H &&
            T[r1].H + T[r3].W == T[r3].H) {
          return true;
        }
      }
    }
    // Nothing worked. It's impossible.
    return false;
  }
}

class Rect {
  int W;
  int H;

  Rect(int W, int H) {
    this.W = W;
    this.H = H;
  }

  /**
   * Rotate this rectangle. If the given direction
   * is 0, it will return the original direction.
   * Otherwise, it will rotate it 90 degrees.
   */
  public Rect rotate(int direction) {
    if (direction == 0) {
      return this;
    } else {
      return new Rect(H, W);
    }
  }
}
