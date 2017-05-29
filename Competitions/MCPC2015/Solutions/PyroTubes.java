import java.util.*;
import java.io.*;

public class PyroTubes {
  public static void main(String[] args)
    throws Exception {

    BufferedReader br = new BufferedReader(
      new InputStreamReader(System.in));

    PrintWriter out = new PrintWriter(
        new BufferedWriter(
        new OutputStreamWriter(System.out)));

    // Store all values here.
    List<Integer> nums = new ArrayList<>();
    Set<Integer> nSet = new HashSet<>();

    // Read in the input.
    int L = Integer.parseInt(br.readLine());
    while(L > 0) {
      nums.add(L);
      nSet.add(L);
      L = Integer.parseInt(br.readLine());
    }

    // Iterate over values.
    for (int l : nums) {

      int count = 0;

      // First bit to swap.
      for (int i = 0; i < 18; i++) {
        int nl = l ^ (1 << i);
        if (nl > l && nSet.contains(nl)) count++;

        // Second bit to swap.
        for (int j = i+1; j < 18; j++) {
          int nnl = nl ^ (1 << j);
          if (nnl > l && nSet.contains(nnl)) count++;
        }
      }
      // Output result.
      out.println(l + ":" + count);
    }
    out.flush();
  }
}
