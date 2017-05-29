import java.util.Scanner;

public class HiddenPassword {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    char[] P = in.next().toCharArray();
    char[] S = in.next().toCharArray();

    System.out.println(valid(P, S) ? "PASS" : "FAIL");
  }

  static boolean valid(char[] password, char[] message) {
    int passwordIdx = 0;

    for (char c : message) {
      if (c == password[passwordIdx]) {
        passwordIdx++;
        if (passwordIdx == password.length) {
          return true;
        }
      } else {
        for (int i = passwordIdx + 1; i < password.length; i++) {
          if (c == password[i]) {
            return false;
          }
        }
      }
    }

    return false;
  }
}
