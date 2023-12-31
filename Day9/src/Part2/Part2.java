package Part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) {
        try {
            File input = new File("./src/input.txt");
            Scanner reader = new Scanner(input);
            long answer = 0;

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                answer += new Report(line).getHistoryValue();
            }
            System.out.println(answer);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
