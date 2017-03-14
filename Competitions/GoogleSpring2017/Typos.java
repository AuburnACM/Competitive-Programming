import java.util.*;

//
// Typos
// Auburn Google Mock Competition, Spring 2017
//
//                Description:
// We are given two strings. The second string is the "correct" string, and
// the first string contains the same string, with extra "typos" inserted
// within the string.
//
// Print out all typos, sorted in ascending ASCII order.
//
//                Example:
// Twittter Bot..
// Twitter Bot.
//
// Expected Output: .t
//
// In this case, the extra characters were a 't' in "Twittter", and an extra
// '.' at the end.
//
//                Solution:
// While there are many solutions, one simple one is to iterate over both
// strings with two pointers. In the event of a mismatch, add the offending
// character to a list, before incrementing only the index in the incorrect
// string. Then, we will add any characters left over at the end of the string
// to the list of typos.
//
//
// For example, with "abbca" vs "abc"
//
//    abbcad      abc     typos: []
//    ^           ^
//      Match! Increment both pointers.
//
//    abbcad      abc     typos: []
//     ^           ^
//      Match! Increment both pointers.
//
//    abbcad      abc     typos: []
//      ^           ^
//      No Match! Add 'b' to typos, and then increment only the 1st pointer.
//
//    abbcad      abc     typos: ['b']
//       ^          ^
//      Match! Increment both pointers, reaching the end of the correct string.
//
//    abbcad      abc     typos: ['b']
//        ^          ^
//      We now all we need to do is add the trailing characters in the
//      incorrect string to typos.
//
//    typos: ['b', 'a', 'd']
//
//    Now, we just need to sort typos => ['a', 'b', 'd'] and concatenate them
//    into the string "abd".
public class Typos {
  public static void main(String[] args) throws Exception {
    // Read in input.
    Scanner in = new Scanner(System.in);

    String incorrectString = in.nextLine();
    String correctedString = in.nextLine();

    int incorrectStrIdx = 0;
    List<Character> typoCharacters = new ArrayList<>();

    // Find mismatches.
    for (int correctStrIdx = 0; correctStrIdx < correctedString.length(); correctStrIdx++) {
      if (correctedString.charAt(correctStrIdx) != incorrectString.charAt(incorrectStrIdx)) {
        typoCharacters.add(incorrectString.charAt(incorrectStrIdx));
        incorrectStrIdx++;
      }
      incorrectStrIdx++;
    }

    // Add trailing characters to the list of typos.
    for (int trailingCharIdx = incorrectStrIdx; trailingCharIdx < incorrectString.length(); trailingCharIdx++) {
      typoCharacters.add(incorrectString.charAt(trailingCharIdx));
    }

    Collections.sort(typoCharacters);
    StringBuilder out = new StringBuilder();

    // Concatenate typos together.
    for (char typo : typoCharacters) {
      out.append(typo);
    }

    System.out.println(out);
  }
}
