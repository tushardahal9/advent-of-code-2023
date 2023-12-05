import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

  static int rows = 140;
  static int cols = 140;

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

      ArrayList<ArrayList<Integer>> gears = new ArrayList<>();
      ArrayList<Integer> filler = new ArrayList<>();
      filler.add(-100);
      gears.add(filler);
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          StringBuilder numString;
          if (Character.isDigit(schematic[i][j])) {
            int[] startPos = new int[]{i, j};
            numString = new StringBuilder("" + schematic[i][j]);
            while (j < cols - 1 && Character.isDigit(schematic[i][j + 1])) {
              numString.append(schematic[i][j + 1]);
              j++;
            }
            int[] endPos = new int[]{i, j};

            // Check for gear in the row above, and check if row exists
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
                if (schematic[rowAbove][colAbove] == '*') {
                  for (ArrayList<Integer> gear : gears) {
                    if (gear.get(0).equals(rowAbove) && gear.get(1).equals(colAbove)) {
                      gear.add(Integer.parseInt(String.valueOf(numString)));
                    }
                  }
                  ArrayList<Integer> newGear = new ArrayList<>();
                  newGear.add(rowAbove);
                  newGear.add(colAbove);
                  newGear.add(Integer.parseInt(String.valueOf(numString)));
                  gears.add(newGear);
                }
              }
            }
           // Check for gear before and after start and end pos in col
            int colBehind = startPos[1] - 1;
            int colAhead = endPos[1] + 1;
            // Before
            if (colBehind >= 0) {
              if (schematic[i][colBehind] == '*') {
                for (ArrayList<Integer> gear : gears) {
                  if (gear.get(0).equals(i) && gear.get(1).equals(colBehind)) {
                    gear.add(Integer.parseInt(String.valueOf(numString)));
                  }
                }
                ArrayList<Integer> newGear = new ArrayList<>();
                newGear.add(i);
                newGear.add(colBehind);
                newGear.add(Integer.parseInt(String.valueOf(numString)));
                gears.add(newGear);
              }
            }
            // After
            if (colAhead < cols) {
              if (schematic[i][colAhead] == '*') {
                for (ArrayList<Integer> gear : gears) {
                  if (gear.get(0).equals(i) && gear.get(1).equals(colAhead)) {
                    gear.add(Integer.parseInt(String.valueOf(numString)));
                  }
                }
                ArrayList<Integer> newGear = new ArrayList<>();
                newGear.add(i);
                newGear.add(colAhead);
                newGear.add(Integer.parseInt(String.valueOf(numString)));
                gears.add(newGear);
              }
            }
            // Check for gear in the row below, and check if row exists
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
                if (schematic[rowBelow][colBelow] == '*') {
                  for (ArrayList<Integer> gear : gears) {
                    if (gear.get(0).equals(rowBelow) && gear.get(1).equals(colBelow)) {
                      gear.add(Integer.parseInt(String.valueOf(numString)));
                    }
                  }
                  ArrayList<Integer> newGear = new ArrayList<>();
                  newGear.add(rowBelow);
                  newGear.add(colBelow);
                  newGear.add(Integer.parseInt(String.valueOf(numString)));
                  gears.add(newGear);
                }
              }
            }
          }
        }
      }

      for (ArrayList<Integer> i : gears) {
        if (i.size() == 4) {
          System.out.println(i);
          int gearRatio = i.get(2) * i.get(3);
          answer += gearRatio;
        }
      }
      System.out.println(answer);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
