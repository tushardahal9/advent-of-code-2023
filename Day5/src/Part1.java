import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Part1 {
  public static void main(String[] args) {
    HashMap<Long, Boolean> checkSeedChange = new HashMap<>();
    ArrayList<Long> seeds = new ArrayList<>();
    try {
      File input = new File("./src/input.txt");
      Scanner reader = new Scanner(input);
      while(reader.hasNextLine()) {
        String line = reader.nextLine();
        // Get seeds and set up HashMap
        if (line.split(":")[0].equals("seeds")) {
          String[] seedsArr = line.split(":")[1].trim().split(" ");
          for (String seed : seedsArr) {
            checkSeedChange.put(Long.parseLong(seed), false);
            seeds.add(Long.parseLong(seed));
          }
        } else if (!line.isEmpty() && !line.split(" ")[1].equals("map:")) {
          String[] map = line.split(" ");
          // Gets map and checks if seed is in range
          for (int i = 0; i < seeds.size(); i++) {
            Long seed = seeds.get(i);
            Long destination = Long.parseLong(map[0]);
            Long source = Long.parseLong(map[1]);
            Long range = Long.parseLong(map[2]);
            // If in range, update seed and update the HashMap, so no further updates are done
            if (seed >= source && seed <= source + range - 1 && !checkSeedChange.get(seed)) {
              seeds.set(i, destination + (seed - source));
              checkSeedChange.put(destination + (seed - source), true);
            }
          }
          // After each map (e.g. new line) reset the HashMap for the next map
        } else if (line.isEmpty()) {
          checkSeedChange.replaceAll((s, v) -> false);
        }

      }
      System.out.println(Collections.min(seeds));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
