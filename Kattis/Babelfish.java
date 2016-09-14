import java.util.*;
import java.io.*;

public class Babelfish {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		Map<String, String> dictionary = new HashMap<>();

		String line = in.readLine();

		while (!line.equals("")) {
			StringTokenizer st = new StringTokenizer(line);

			String englishWord = st.nextToken();
			String foreignWord = st.nextToken();

			dictionary.put(foreignWord, englishWord);
			line = in.readLine();
		}

		while (in.ready()) {
			String word = in.readLine();
			if (!dictionary.containsKey(word)) {
				System.out.println("eh");
			} else {
				System.out.println(dictionary.get(word));
			}
		}
	}
}
