import java.util.*;

public class AddingWords {

  static HashMap<String, Integer> dict = new HashMap<>();
  static HashMap<Integer, String> lookup = new HashMap<>();

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    while(in.hasNext()) {
      String line = in.nextLine() + " ";
      int firstSpace = line.indexOf(" ");
      String op = line.substring(0, firstSpace);
      String data = line.substring(firstSpace + 1);
      if(op.equals("def")) {
        Scanner parser = new Scanner(data);
        String keyword = parser.next();
        int value = parser.nextInt();
        lookup.remove(dict.get(keyword));
        dict.put(keyword, value);
        lookup.put(value, keyword);
      } else if (op.equals("calc")) {
        String output = eval(data);
        System.out.println(data + output);
      } else {
        dict = new HashMap<>();
        lookup = new HashMap<>();
      }
    }
  }

  public static String eval(String exp) {
    String[] expArray = exp.split(" ");
    int val = 0;
    if(dict.containsKey(expArray[0]))
      val = dict.get(expArray[0]);
    else
      return "unknown";

    for(int i = 1; i < expArray.length - 1; i += 2) {
      if(expArray[i].equals("+")) {
        String term = expArray[i+1];
        if(dict.containsKey(term))
          val += dict.get(term);
        else
          return "unknown";
      } else {
        String term = expArray[i+1];
        if(dict.containsKey(term))
          val -= dict.get(term);
        else
          return "unknown";
      }
    }

    if(lookup.containsKey(val)) {
      return lookup.get(val);
    } else {
      return "unknown";
    }


  }
}
