import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {
  public static void main(String[] args) {

    // Count of the colours and max counts
    int maxRed;
    int maxGreen;
    int maxBlue;
    int answer = 0;

    try {
      File input = new File("./src/input.txt");
      Scanner reader = new Scanner(input);

      while (reader.hasNextLine()) {
        String line = reader.nextLine();
        maxRed = 0;
        maxGreen = 0;
        maxBlue = 0;
        // Get game content
        String[] parts = line.split(":");
        String gameContent = parts[1].trim();
        String[] games = gameContent.split(";");

        // Loop through game content
        for (String game : games) {
          game = game.trim();
          String[] gameSplit = game.split(",");
          // Loop through individual games
          for (String cube : gameSplit) {
            cube = cube.trim();
            String[] cubeSplit = cube.split(" ");
            // Get count and colour
            int count = Integer.parseInt(cubeSplit[0]);
            String colour  = cubeSplit[1];
            // Update max num if required
            if (colour.equals("blue")) {
              if (count > maxBlue) maxBlue = count;
            } else if (colour.equals("red")) {
              if (count > maxRed) maxRed = count;
            } else {
              if (count > maxGreen) maxGreen = count;
            }
          }
        }
        answer += maxBlue * maxRed * maxGreen;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println(answer);
  }
}
