import java.util.*;
import java.io.*;

public class Zoo {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int test = 0;
    while(true) {
      int N = Integer.parseInt(br.readLine());
      if (N == 0) return;

      TreeMap<String, Integer> zoo = new TreeMap<>();
      test++;


      for (int i = 0; i < N; i++) {
        String[] L = br.readLine().split(" ");
        String a = L[L.length-1].toLowerCase();
        if (!zoo.containsKey(a)) {
          zoo.put(a, 0);
        }
        zoo.put(a, zoo.get(a) + 1);
      }
      System.out.println("List " + (test) + ":");
      for (String a : zoo.keySet()) {
        System.out.println(a + " | " + zoo.get(a));
      }
    }
  }
}
