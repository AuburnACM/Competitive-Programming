import java.util.*;

//
// Vowel Prankster
// Auburn Google Mock Competition, Spring 2017
//
//                Description:
// Given a string, print out the string, but with the order of the
// vowels in the string reversed. Vowels are only a, e, i, o, and u.
//
//                Example:
// "auburn university" -> "iebirn unuvursaty"
//
//                Solution:
// This is a case where a stack comes in handy. First things first, though,
// we need some way of determining whether a character is a vowel. One
// way to do this is to add all vowels to a set, and then test if a character
// is in the set of vowels.
//
// Once that is done, we can iterate twice over the string. On the first
// iteration, we will look at each character. If it is a consonant, we will
// leave it alone. If it is a vowel, however, we can push it onto the stack.
//
// On the second iteration, if a character is a consonant, we will simply append
// it to our output. If it is a vowel, however, we can pop the next character
// off the stack, ensuring that the order is reversed, before appending that
// to our output.
public class VowelPrankster {
  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    char[] input = in.nextLine().toCharArray();

    Stack<Character> vowelsInString = new Stack<>();
    Set<Character> vowels = new HashSet<>();

    StringBuilder out = new StringBuilder();

    // Fill vowels with appropriate values.
    for (char vowel : new char[]{'a', 'e', 'i', 'o', 'u'}) {
      vowels.add(vowel);
    }

    // First pass. Push any vowels onto the stack.
    for (char ch : L) {
      if (vowels.contains(ch)) {
        vowelsInString.push(ch);
      }
    }

    // Second pass. Output consonants unchanged, use the stack to get the
    // appropriate vowel.
    for (char ch : L) {
      if (vowels.contains(ch)) {
        out.append(s.pop());
      } else {
        out.append(ch);
      }
    }

    System.out.println(out);
  }
}
