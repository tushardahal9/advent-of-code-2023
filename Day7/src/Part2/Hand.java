package Part2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

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

        // Gets the most common card in the hand
        String highestCard = "";
        int count = 0;
        for (String card : cardCount.keySet()) {
            if ((cardCount.get(card) > count) & !card.equals("J")) {
                highestCard = card;
                count = cardCount.get(card);
            }
        }

        // If there are any Joker cards and the whole hand isn't all Jokers,
        // replace them with the most common card
        if (cardCount.get("J") != null && !(cardCount.size() == 1)) {
            int jokerCount = cardCount.get("J");
            cardCount.remove("J");
            cardCount.put(highestCard, cardCount.get(highestCard) + jokerCount);
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
        HashMap<String, Integer> cardRanks = new HashMap<>() {{
            put("A", 13);
            put("K", 12);
            put("Q", 11);
            put("T", 10);
            put("9", 9);
            put("8", 8);
            put("7", 7);
            put("6", 6);
            put("5", 5);
            put("4", 4);
            put("3", 3);
            put("2", 2);
            put("J", 1);
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

    // Compares the ranks of 2 hands of cards
    public int compareTo(Hand o) {

        if (this.determineRank() > o.determineRank()) {
            return 1;
        } else if (this.determineRank() < o.determineRank()) {
            return -1;
            // If both cards are equal rank, compare the individual cards to find the higher one
        } else {
            return compareHands(o);
        }
    }

    // String format of a hand
    public String toString() {
        return hand + ":" + bid + ":" + determineRank();
    }
}
