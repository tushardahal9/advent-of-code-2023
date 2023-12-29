import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Part2 {

    // Reads time and distances from the text file
    public static ArrayList<Long> getTimesAndDistances() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./src/input.txt"));

            String[] timeString = reader.readLine().split(":")[1].trim().split(" {5}");
            String[] distanceString = reader.readLine().split(":")[1].trim().split(" {3}");

            return getValues(timeString, distanceString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Converts the time and distances into longs
    private static ArrayList<Long> getValues(String[] timeString, String[] distanceString) {
        ArrayList<Long> values = new ArrayList<>();
        StringBuilder combinedTime = new StringBuilder();
        StringBuilder combinedDistance = new StringBuilder();

        for (String time : timeString) {
            combinedTime.append(time);
        }
        for (String distance : distanceString) {
            combinedDistance.append(distance);
        }

        values.add(Long.parseLong(combinedTime.toString()));
        values.add(Long.parseLong(combinedDistance.toString()));
        return values;
    }

    public static void main(String[] args) {
        ArrayList<Long> values = getTimesAndDistances();

        long time = values.getFirst();
        long distance = values.getLast();
        int answer = 0;

        // Loop through time and calculate distance
        for (int i = 0; i <= time; i++) {
            long distanceTravelled = i * (time - i);
            if (distanceTravelled > distance) answer++;
        }
        System.out.println(answer);
    }
}