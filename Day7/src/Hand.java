import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

// Class to represent the hand read from the input file
public class Hand implements Comparable<Hand> {
    private final String hand;
    private final int bid;

    public Hand(String hand, int bid) {
        this.hand = hand;
        this.bid = bid;
    }

    // Determines the rank of the hand
    public int determineRank() {
        String[] cards = hand.split("");
        HashMap<String, Integer> cardCount = new HashMap<>();

        // Counts the number of unique cards, and uses that to determine rank
        for (String card : cards) {
            if (cardCount.containsKey(card)) {
                cardCount.put(card, cardCount.get(card) + 1);
            } else {
                cardCount.put(card, 1);
            }
        }
        if (cardCount.size() == 1) {
            return 6;
        } else if (cardCount.size() == 2) {
            // Gets the highest card count and uses that to determine the rank in certain cases
            int maxCount = Collections.max(cardCount.values());
            if (maxCount == 4) return 5;
            return 4;
        } else if (cardCount.size() == 3) {
            int maxCount = Collections.max(cardCount.values());
            if (maxCount == 3) return 3;
            return 2;
        } else if (cardCount.size() == 4) {
            return 1;
        } else {
            return 0;
        }
    }

    public int compareHands(Hand o) {
        // Cards and their ranks
        HashMap<String, Integer> cardRanks = new HashMap<String, Integer>() {{
            put("A", 13);
            put("K", 12);
            put("Q", 11);
            put("J", 10);
            put("T", 9);
            put("9", 8);
            put("8", 7);
            put("7", 6);
            put("6", 5);
            put("5", 4);
            put("4", 3);
            put("3", 2);
            put("2", 1);
        }};

        String[] handArray = hand.split("");
        String[] otherHandArray = o.hand.split("");

        // Loops through both hands, and checks each card to see if one beats the other
        for (int i = 0; i < handArray.length; i++) {
            int cardRank = cardRanks.get(handArray[i]);
            int otherCardRank = cardRanks.get(otherHandArray[i]);
            if (cardRank > otherCardRank) {
                return 1;
            } else if (cardRank < otherCardRank) {
                return -1;
            }
        }
        return 0;
    }

    // Returns the bid
    public int getBid() {
        return bid;
    }

    // Formats the hand into an arraylist
    public ArrayList<String> toArrayList() {
        ArrayList<String> handFormat = new ArrayList<>();
        handFormat.add(hand);
        handFormat.add(String.valueOf(bid));
        handFormat.add(String.valueOf(determineRank()));
        return handFormat;
    }

    // Compares the ranks to sort the arraylist holding all the hands
    public int compareTo(Hand o) {

        if (this.determineRank() > o.determineRank()) {
            return 1;
        } else if (this.determineRank() < o.determineRank()) {
            return -1;
            // If both cards are equal rank, compare the hands to find the higher one
        } else {
            return compareHands(o);
        }
    }

    // String format of a hand
    public String toString() {
        return hand + ":" + bid + ":" + determineRank();
    }
}
