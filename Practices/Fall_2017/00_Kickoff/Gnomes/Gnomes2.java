import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gnomes2 {

  static class TestCase {
    int a;
    int b;
    int c;

    TestCase(int a, int b, int c) {
      this.a = a;
      this.b = b;
      this.c = c;
    }

    boolean ordered() {
      if (a < b && b < c) {
        return true;
      } else if (a > b && b > c) {
        return true;
      } else {
        return false;
      }
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    List<TestCase> input = new ArrayList<>();

    int nCases = in.nextInt();
    for (int i = 0; i < nCases; i++) {
      input.add(new TestCase(in.nextInt(), in.nextInt(), in.nextInt()));
    }

    System.out.println("Gnomes:");
    for (TestCase c : input) {
      if (c.ordered()) {
        System.out.println("Ordered");
      } else {
        System.out.println("Unordered");
      }
    }
  }
}

