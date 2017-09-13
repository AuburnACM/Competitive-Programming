import java.util.*;

public class Gnomes1 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    System.out.println("Gnomes:");
    while(N --> 0) {
      int a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
      System.out.println((a < b) == (b < c) ? "Ordered" : "Unordered");
    }
  }
}
