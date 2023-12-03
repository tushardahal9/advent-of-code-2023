import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
  public static void main(String[] args) {

    // Count of the colours and max counts
    int red;
    int blue;
    int green;
    int maxRed = 12;
    int maxGreen = 13;
    int maxBlue = 14;
    int answer = 0;

    try {
      File input = new File("./src/input.txt");
      Scanner reader = new Scanner(input);

      while (reader.hasNextLine()) {
        String line = reader.nextLine();
        boolean gamePossible = true;
        // Split line into the game number and the game content
        String[] parts = line.split(":");
        int gameNumber = Integer.parseInt(parts[0].split(" ")[1]);
        String gameContent = parts[1].trim();
        String[] games = gameContent.split(";");

        // Loop through game content
        for (String game : games) {
          red = 0;
          blue = 0;
          green = 0;
          game = game.trim();
          String[] gameSplit = game.split(",");
          // Loop through individual games
          for (String cube : gameSplit) {
            cube = cube.trim();
            String[] cubeSplit = cube.split(" ");
            // Get count and colour
            int count = Integer.parseInt(cubeSplit[0]);
            String colour  = cubeSplit[1];

            if (colour.equals("blue")) {
              blue += count;
            } else if (colour.equals("red")) {
              red += count;
            } else {
              green += count;
            }
          }
          // Check if game is possible
          if (blue > maxBlue || red > maxRed || green > maxGreen) {
            gamePossible = false;
          }
          if (!gamePossible) break;
        }
        if (gamePossible) answer += gameNumber;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println(answer);
  }
}
