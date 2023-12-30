import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part1 {
    public static void main(String[] args) {
        try {
            File input = new File("./src/input.txt");
            Scanner reader = new Scanner(input);
            ArrayList<Hand> handsRanking = new ArrayList<>();
            int answer = 0;

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String hand = line.split(" ")[0];
                String bid = line.split(" ")[1];

                handsRanking.add(new Hand(hand, Integer.parseInt(bid)));
            }

            Collections.sort(handsRanking);

            for (int i = 0; i < handsRanking.size(); i++) {
                Hand currentHand = handsRanking.get(i);
                answer += currentHand.getBid() * (i + 1);
            }

            System.out.println(answer);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
