import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

  static int rows = 140;
  static int cols = 140;

  // Check for special characters
  public static boolean checkSpecialChar(int row, int col, char[][] schematic) {
    char specialChar = schematic[row][col];
    return (!Character.isDigit(specialChar) && !Character.isLetter(specialChar)
        && !Character.isSpaceChar(specialChar) && specialChar != '.') || specialChar == '-';
  }

  public static void main(String[] args) {
    int answer = 0;
    try {
      char[][] schematic = new char[rows][cols];
      File input = new File("./src/input.txt");
      Scanner reader = new Scanner(input);

      // Put input into 2d array
      for (int row = 0; reader.hasNextLine() && row < rows; row++) {
        char[] chars = reader.nextLine().toCharArray();
        for (int i = 0; i < cols && i < chars.length; i++) {
          schematic[row][i] = chars[i];
        }
      }

      // Loop through grid
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          // Get start and end pos of num
          StringBuilder numString;
          if (Character.isDigit(schematic[i][j])) {
            int[] startPos = new int[]{i, j};
            numString = new StringBuilder("" + schematic[i][j]);
            while (j < cols - 1 && Character.isDigit(schematic[i][j + 1])) {
              numString.append(schematic[i][j + 1]);
              j++;
            }
            int[] endPos = new int[]{i, j};

            // Check for special char above the number, and check if row exists above
            int rowAbove = startPos[0] - 1;
            int topLeft;
            int topRight;

            topLeft = Math.max(startPos[1] - 1, 0);
            if (endPos[1] + 1 >= cols) {
              topRight = cols - 1;
            } else {
              topRight = endPos[1] + 1;
            }

            if (rowAbove >= 0 && rowAbove < rows) {
              for (int colAbove = topLeft; colAbove <= topRight; colAbove++) {
                if (checkSpecialChar(rowAbove, colAbove, schematic)) {
                  answer += Integer.parseInt(numString.toString());
                  break;
                }
              }
            }
            // Check for special char before and after start and end pos in col
            int colBehind = startPos[1] - 1;
            int colAhead = endPos[1] + 1;
            // Before
            if (colBehind >= 0) {
              if (checkSpecialChar(i, colBehind, schematic)) {
                answer += Integer.parseInt(numString.toString());
              }
            }
            // After
            if (colAhead < cols) {
              if (checkSpecialChar(i, colAhead, schematic)) {
                answer += Integer.parseInt(numString.toString());
              }
            }
            // Check for special char below the number, and check if row exists below
            int rowBelow = startPos[0] + 1;
            int bottomLeft;
            int bottomRight;

            bottomLeft = Math.max(startPos[1] - 1, 0);
            if (endPos[1] + 1 >= cols) {
              bottomRight = cols - 1;
            } else {
              bottomRight = endPos[1] + 1;
            }

            if (rowBelow >= 0 && rowBelow < rows) {
              for (int colBelow = bottomLeft; colBelow <= bottomRight; colBelow++) {
                if (checkSpecialChar(rowBelow, colBelow, schematic)) {
                  answer += Integer.parseInt(numString.toString());
                  break;
                }
              }
            }
          }
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println(answer);
  }
}