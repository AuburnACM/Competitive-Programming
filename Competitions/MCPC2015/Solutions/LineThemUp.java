import java.util.Scanner;

public class LineThemUp {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    int N = in.nextInt();
    String[] L = new String[N];
    for (int i = 0; i < N; i++) L[i] = in.next();

    int res = getOrder(L);

    if (res < 0) System.out.println("DECREASING");
    else if (res > 0) System.out.println("INCREASING");
    else System.out.println("NEITHER");
  }

  static int getOrder(String[] L) {
    int order = L[1].compareTo(L[0]);

    for (int i = 2; i < L.length; i++) {
      int comparison = L[i].compareTo(L[i-1]);

      // If they aren't both positive or both negative:
      if (comparison * order < 0) {
        return 0;
      }
    }

    return order;
  }
}
