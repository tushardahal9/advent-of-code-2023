import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) {
        try {
            ArrayList<ArrayList<Long>> answerSeeds = getArrayLists();

            File input = new File("./src/input.txt");
            Scanner reader = new Scanner(input);
            ArrayList<ArrayList<Long>> newAnswerSeeds = new ArrayList<>();

            reader.nextLine();
            reader.nextLine();

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (!line.isEmpty() && !line.split(" ")[1].equals("map:")) {
                    String[] map = line.split(" ");
                    long destination = Long.parseLong(map[0]);
                    long source = Long.parseLong(map[1]);
                    long range = Long.parseLong(map[2]);
                    for (Iterator<ArrayList<Long>> iterator = answerSeeds.iterator(); iterator.hasNext();) {
                        if (answerSeeds.isEmpty()) break;
                        ArrayList<Long> currentSeedRange = iterator.next();
                        long seedStart = currentSeedRange.getFirst();
                        long seedEnd = currentSeedRange.getLast();
                        // If start and end in range
                        if (seedStart >= source && seedEnd <= source + range - 1) {
                            ArrayList<Long> newSeedRange = new ArrayList<>();
                            newSeedRange.add(destination + (seedStart - source));
                            newSeedRange.add(destination + (seedEnd - source));
                            newAnswerSeeds.remove(currentSeedRange);
                            newAnswerSeeds.add(newSeedRange);
                            iterator.remove();
                            // If start in range, and end out of range
                        } else if ((source <= seedStart && seedStart <= source + range - 1)
                                && seedEnd > source + range - 1) {
                            ArrayList<Long> newSeedRange1 = new ArrayList<>();
                            ArrayList<Long> newSeedRange2 = new ArrayList<>();
                            newSeedRange1.add(destination + (seedStart - source));
                            newSeedRange1.add(destination + (range - 1));
                            newSeedRange2.add(source + range);
                            newSeedRange2.add(seedEnd);
                            newAnswerSeeds.remove(currentSeedRange);
                            newAnswerSeeds.add(newSeedRange1);
                            newAnswerSeeds.add(newSeedRange2);
                            iterator.remove();
                            // If start out of range, and end in range
                        } else if (seedStart < source && (source <= seedEnd && seedEnd <= source + range - 1)) {
                            ArrayList<Long> newSeedRange1 = new ArrayList<>();
                            ArrayList<Long> newSeedRange2 = new ArrayList<>();
                            newSeedRange1.add(seedStart);
                            newSeedRange1.add(source - 1);
                            newSeedRange2.add(destination);
                            newSeedRange2.add(destination + (seedEnd - source));
                            newAnswerSeeds.remove(currentSeedRange);
                            newAnswerSeeds.add(newSeedRange1);
                            newAnswerSeeds.add(newSeedRange2);
                            iterator.remove();
                            // If start and end both out of range
                        } else if (seedStart < source && seedEnd > source + range - 1) {
                            ArrayList<Long> newSeedRange1 = new ArrayList<>();
                            ArrayList<Long> newSeedRange2 = new ArrayList<>();
                            ArrayList<Long> newSeedRange3 = new ArrayList<>();
                            newSeedRange1.add(seedStart);
                            newSeedRange1.add(source - 1);
                            newSeedRange2.add(destination);
                            newSeedRange2.add(destination + range - 1);
                            newSeedRange3.add(source + range);
                            newSeedRange3.add(seedEnd);
                            newAnswerSeeds.remove(currentSeedRange);
                            newAnswerSeeds.add(newSeedRange1);
                            newAnswerSeeds.add(newSeedRange2);
                            newAnswerSeeds.add(newSeedRange3);
                            iterator.remove();
                            // Add original seed range to new seed range
                        } else {
                            if (!newAnswerSeeds.contains(currentSeedRange)) newAnswerSeeds.add(currentSeedRange);
                        }
                    }
                } else if (line.isEmpty()) {
                    answerSeeds = new ArrayList<>(newAnswerSeeds);
                    newAnswerSeeds.clear();
                }
            }
            answerSeeds = new ArrayList<>(newAnswerSeeds);
            newAnswerSeeds.clear();

            long answer = Integer.MAX_VALUE;
            for (ArrayList<Long> seedRange : answerSeeds) {
                if (seedRange.getFirst() < answer) answer = seedRange.getFirst();
            }
            System.out.println(answer);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<ArrayList<Long>> getArrayLists() throws IOException {
        FileReader firstLineInput = new FileReader("./src/input.txt");
        BufferedReader firstLineReader = new BufferedReader(firstLineInput);
        String[] seeds = firstLineReader.readLine().split(":")[1].trim().split(" ");
        ArrayList<ArrayList<Long>> answerSeeds = new ArrayList<>();

        for (int i = 0; i < seeds.length; i += 2) {
            long seedStart = Long.parseLong(seeds[i]);
            long seedEnd = seedStart + Long.parseLong(seeds[i + 1]) - 1;
            ArrayList<Long> seedRange = new ArrayList<>();
            seedRange.add(seedStart);
            seedRange.add(seedEnd);
            answerSeeds.add(seedRange);
        }
        return answerSeeds;
    }
}
