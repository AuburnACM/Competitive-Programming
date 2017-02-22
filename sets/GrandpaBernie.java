import java.util.*;

public class GrandpaBernie {

  public static void main(String[] args) {
    Map<String, List<Integer>> trips = new HashMap<>();
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    for (int i = 0; i < n; i++) {
      String country = in.next();
      int year = in.nextInt();
      // TODO: Add country to trips if it doesn't exist.
      // TODO: Add year to country's year list.
    }
    // TODO: Sort all the year lists.
    int q = in.nextInt();
    for (int i = 0; i < q; i++) {
      String country = in.next();
      int year = in.nextInt();
      // TODO: Print the right year.
    }
  }

}
