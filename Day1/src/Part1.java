import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

  public static void main(String[] args) {
    int sum = 0;
    try {
      File input = new File("./src/input.txt");
      Scanner reader = new Scanner(input);
      while (reader.hasNextLine()) {
        // Get current line to work on
        String line = reader.nextLine();
        String firstDigit = "";
        String lastDigit = "";

        // Find first digit
        for (Character ch : line.toCharArray()) {
          if (Character.isDigit(ch)) {
            firstDigit = String.valueOf(ch);
            break;
          }
        }
        // Find last digit by looping backwards
        for (int i = line.length() - 1; i >= 0; i--) {
          if (Character.isDigit(line.charAt(i))) {
            lastDigit = String.valueOf(line.charAt(i));
            break;
          }

        }
        // Combine digits to int and add to sum
        int num = Integer.parseInt(firstDigit + lastDigit);
        sum += num;
        }
      reader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println(sum);
  }
}