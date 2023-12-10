import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

  public static void main(String[] args) {
    int answer = 0;
    ArrayList<Integer> copies = new ArrayList<>();
    ArrayList<Integer> count = new ArrayList<>();
    try {
      File input = new File("./src/input.txt");
      Scanner reader = new Scanner(input);
      while (reader.hasNextLine()) {
        // Splitting line into winning numbers and your numbers
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

        // Adding number of copies of each card
        int countNum = 0;
        for (int num : myNumbers) {
          for (int winNum : winningNumbers) {
            if (num == winNum) {
              countNum++;
            }
          }
        }
        copies.add(countNum);
      }

      // Initializing count of each card
      for (int i = 0; i < 204; i++) {
        count.add(1);
      }

      // Calculates the total number of cards
      for (int i = 0; i < copies.size(); i++) {
        int j = i + 1;
        int endLoop = j + copies.get(i) - 1;
        for (; j <= endLoop; j++) {
          count.set(j, count.get(j) + count.get(i));
        }
      }

      for (int num : count) {
        answer += num;
      }
      System.out.println(answer);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
