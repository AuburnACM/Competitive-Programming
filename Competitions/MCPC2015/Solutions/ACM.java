import java.util.Scanner;

public class ACM {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    int problemsSolved = 0, totalPenaltyTime = 0;
    boolean[] solved = new boolean[26];
    int[] incorrectSubmits = new int[26];

    while (true) {
      int submissionTime = in.nextInt();

      if (submissionTime < 0) break;

      // Convert A-Z to 1-26.
      int problem = in.next().charAt(0) - 'A';
      boolean correct = in.next().equals("right");

      // Ignore already solved problems.
      if (solved[problem]) continue;

      if (correct) {
        solved[problem] = true;
        problemsSolved++;
        totalPenaltyTime += 20 *
          incorrectSubmits[problem] + submissionTime;
      } else {
        incorrectSubmits[problem]++;
      }
    }

    System.out.println(problemsSolved + " " + totalPenaltyTime);
  }
}
