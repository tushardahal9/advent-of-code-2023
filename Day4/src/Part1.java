import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {
  public static void main(String[] args) {
    int answer = 0;
    try {
      File input = new File("./src/input.txt");
      Scanner reader = new Scanner(input);
      while (reader.hasNextLine()) {
        String line = reader.nextLine();
        String[] winningNum = line.split("\\|")[0].split(":")[1].trim().split(" ");
        String[] myNum = line.split("\\|")[1].split(" ");
        ArrayList<Integer> winningNumbers = new ArrayList<>();
        ArrayList<Integer> myNumbers = new ArrayList<>();

        for (String numbers : winningNum) {
          if (!numbers.trim().isEmpty()) {
            winningNumbers.add(Integer.parseInt(numbers));
          }
        }
        for (String numbers : myNum) {
          if (!numbers.trim().isEmpty()) {
            myNumbers.add(Integer.parseInt(numbers));
          }
        }

        int count = 0;
        for (int num : myNumbers) {
          for (int winNum : winningNumbers) {
            if (num == winNum) {
              count++;
            }
          }
        }
        answer += (int) Math.pow(2, count - 1);
      }
      System.out.println(answer);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
