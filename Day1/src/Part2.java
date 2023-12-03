import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {

  public static void main(String[] args) {
    int sum = 0;
    try {
      File input = new File("./src/input.txt");
      Scanner reader = new Scanner(input);
      while (reader.hasNextLine()) {
        // Convert text representation of number to integer
        String line = reader.nextLine();
        String line1 = line.replaceAll("one", "o1e");
        String line2 = line1.replaceAll("two", "t2o");
        String line3 = line2.replaceAll("three", "t3e");
        String line4 = line3.replaceAll("four", "f4r");
        String line5 = line4.replaceAll("five", "f5e");
        String line6 = line5.replaceAll("six", "s6x");
        String line7 = line6.replaceAll("seven", "s7n");
        String line8 = line7.replaceAll("eight", "e8t");
        String trueLine = line8.replaceAll("nine", "n9e");

        String firstDigit = "";
        String lastDigit = "";

        // Find the first digit
        for (Character ch : trueLine.toCharArray()) {
          if (Character.isDigit(ch)) {
            firstDigit = String.valueOf(ch);
            break;
          }
        }
        // Find last digit by looping backwards
        for (int i = trueLine.length() - 1; i >= 0; i--) {
          if (Character.isDigit(trueLine.charAt(i))) {
            lastDigit = String.valueOf(trueLine.charAt(i));
            break;
          }

        }
        // Combine digits to int and add to sum
        int num = Integer.parseInt(firstDigit + lastDigit);
        sum += num;
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println(sum);
  }
}
