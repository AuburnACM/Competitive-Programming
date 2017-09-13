import java.util.*;
import java.io.*;

public class SynchronizingLists {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int c = 0;
		while (true) {
			int n = Integer.parseInt(in.readLine());
			if (n == 0) return;
			if (c > 0) System.out.println();
			c++;
			TreeMap<Integer, Integer> nums = new TreeMap<>();
			for (int i = 0; i < n; i++) {
				nums.put(Integer.parseInt(in.readLine()), i);
			}
			int[] order = new int[n];
			int idx = 0;
			for (int i : nums.keySet()) {
				order[nums.get(i)] = idx;
				idx++;
			}
			int[] l = new int[n];
			for (int i = 0; i < n; i++) {
				l[i] = Integer.parseInt(in.readLine());
			}
			Arrays.sort(l);

			for (int i = 0; i < n; i++) {
				System.out.println(l[order[i]]);
			}
		}
	}
}
