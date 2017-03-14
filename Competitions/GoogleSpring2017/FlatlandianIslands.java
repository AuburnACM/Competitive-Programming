import java.util.*;

//
// Flatlandian Islands
// Auburn Google Mock Competition, Spring 2017
//
//                Description:
// Given the topography of a 2D island, with width and height,
// but no depth, calculate the amount of water that would be
// trapped on the island.
//
// The island will be given as a list of space separated integers,
// representing the height of the island in every column.
//
//  Example: Given an input island of:
//  1 1 4 1 1 1 3 1 2 1
//
//    #                                   #
//    #   #    After flooding ->          #~~~#
//    #   # #    7 tiles of water         #~~~#~#
//  ##########                          ##########
//
//
//                Solution:
// The solution will use two arrays to make computing the amount
// of water much more efficient.
//
// The first array will store the height of the tallest column
// to the left (non-inclusive) of each column.
//
// For the sample input, this would correspond to:
//    {0, 1, 1, 4, 4, 4, 4, 4, 4, 4}
//
// The second array will store the height of the tallest column
// to the right (non-inclusive) of each column.
//
// For the sample input, this would corresond to:
//    {4, 4, 3, 3, 3, 3, 2, 2, 1, 0}
//
// Taking the minimum of these 2 values will reveal the tallest
// height water in this column can rise up to. If it went any
// higher, it would overflow off the shorter side. Note that if
// this value is 3, that means the water and land's combined
// height cannot exceed 3.
//
//    {0, 1, 1, 3, 3, 3, 2, 2, 1, 0}
//
// Now for each cell, we can calculate the amount of water
// by computing the maximum possible height of the column, minus
// the height of the land in that column. In the event that this
// value is negative (this column is taller than the water level
// can rise to without overflowing), we can don't lose water,
// so we will set it to 0.
//
//   {0, 0, 0, 3, 3, 3, 0, 1, 0, 0}
//
// Summing these will reveal the answer: 7

public class FlatlandianIslands {
  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    int numTestCases = in.nextInt();
    for (int caseNum = 0; caseNum < numTestCases; caseNum++) {

      int islandWidth = in.nextInt();

      // Populate heights array.
      int[] heights = new int[islandWidth];
      for (int i = 0; i < islandWidth; i++) {
        heights[i] = in.nextInt();
      }

      // Compute tallest value to the left of each cell.
      int[] tallestToLeft = new int[islandWidth];
      for (int i = 1; i < islandWidth; i++) {
        tallestToLeft[i] = Math.max(tallestToLeft[i-1], heights[i-1]);
      }

      // Compute tallest value to the right of each cell.
      int[] tallestToRight = new int[islandWidth];
      for (int i = islandWidth-2; i >= 0; i--) {
        tallestToRight[i] = Math.max(tallestToRight[i+1], heights[i+1]);
      }

      // Calculate total.
      long total = 0;
      for (int i = 0; i < islandWidth; i++) {
        int maxWaterLevel = Math.min(tallestToLeft[i], tallestToRight[i]);
        total += Math.max(0, maxWaterLevel - heights[i]);
      }
      System.out.println(total);
    }
  }
}
