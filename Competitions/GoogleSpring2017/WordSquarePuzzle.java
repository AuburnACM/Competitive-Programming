import java.util.*;

//
// Word Square Puzzle
// Auburn Google Mock Competition, Spring 2017
//
//                Description:
// A word square is a square of characters, such that the words forming
// the rows are the same as the rows forming the columns.
//
//
// Given a list of words, determine if writing them in a square would form
// a valid word square.
//
//                Example:
//
//                      bit
// bit ice ten      =>  ice       => valid word square.
//                      ten
//
//                Solution:
// For each word square, we will do the following: check that every word has
// length N, where N is the number of words.
//
// After that, we can iterate over the rows and columns, and just ensure that
// WordSquare[i][j] == WordSquare[j][i] for all i, j from 0 to N-1.
public class WordSquarePuzzle {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    int numTestCases = in.nextInt();
    in.nextLine();

    for (int testCase = 0; testCase < numTestCases; testCase++) {
      String[] line = in.nextLine().split(" ");
      System.out.println(validWordSquare(line) ? "True" : "False");
    }
  }

  public static boolean validWordSquare(String[] line) {
    // Special case [""], the results of an empty line.
    if (line.length == 1 && line[0].length() == 0) return true;

    // Ensure all strings are the correct length.
    for (String s : line) {
      if (s.length() != line.length) return false;
    }

    int N = line.length;

    // Build word square from the input.
    char[][] wordSquare = new char[N][N];
    for (int i = 0; i < N; i++) {
      wordSquare[i] = line[i].toCharArray();
    }

    // Verify square is symmetric across the diagonal.
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (wordSquare[i][j] != wordSquare[j][i]) return false;
      }
    }

    // If we made it this far, we are good to go.
    return true;
  }
}
