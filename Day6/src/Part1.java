import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Part1 {

    // Reads the time and distances from the text file
    public static ArrayList<ArrayList<Integer>> getTimesAndDistances() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./src/input.txt"));

            String[] timeString = reader.readLine().split(":")[1].trim().split(" {5}");
            String[] distanceString = reader.readLine().split(":")[1].trim().split(" {3}");

            return getValues(timeString, distanceString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Converts the times and distances into int arrays
    private static ArrayList<ArrayList<Integer>> getValues(String[] timeString, String[] distanceString) {
        ArrayList<Integer> times = new ArrayList<>();
        ArrayList<Integer> distances = new ArrayList<>();
        ArrayList<ArrayList<Integer>> values = new ArrayList<>();

        for (String time : timeString) {
            times.add(Integer.parseInt(time.trim()));
        }
        for (String distance : distanceString) {
            distances.add(Integer.parseInt(distance.trim()));
        }

        values.add(times);
        values.add(distances);
        return values;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> values = getTimesAndDistances();
        int answer = 1;

        // Loop through all times
        for (int i = 0; i < 4; i++) {
            int count = 0;
            int time = values.getFirst().get(i);
            int distance = values.getLast().get(i);
            // Calculate the distance travelled for each time
            for (int j = 0; j <= time; j++) {
                int distanceTravelled = j * (time - j);
                if (distanceTravelled > distance) count++;
            }
            answer *= count;
        }
        System.out.println(answer);
    }
}