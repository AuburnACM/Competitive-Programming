import java.util.*;

public class Stars {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int S = in.nextInt();

    for (int F = 2; F + F - 1 <= S; F++) {
      if (S % (2 * F - 1) == 0 || (S + F - 1) % (2 * F - 1) == 0)
        System.out.println(F + " " + (F-1));
      if (S % F == 0)
        System.out.println(F + " " + F);
    }
  }
}
