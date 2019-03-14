package rbhatt23.lab06;

import javax.smartcardio.Card;
import java.util.Arrays;
import java.util.Scanner;

public class rbhatt23_lab06_q3 {

    public static int[] pickedCards = new int[52];

    public static void main(String[] args) {


        printHeader(6, 3, "Rishab", "Bhatt", "Simple card game using arrays");

        Scanner scan = new Scanner(System.in);

        String[] deck = new String[52];
        String[] suits = {"H", "C", "D", "S"};


        populateDeck(deck, suits);

        // array with cards from highest to lowest ranking
//        int[] orderedDeck = new int[52];
//        System.out.println(orderedDeck.length);
        //                orderedDeck[0] = 40;
//        orderedDeck[1] = 27;
//        orderedDeck[2] = 14;
//        orderedDeck[3] = 1;

//        orderedDeck[4] = 52;
//        orderedDeck[5] = 39;
//        orderedDeck[6] = 26;
//        orderedDeck[7] = 13;
//
//        orderedDeck[8] = 51;
//        orderedDeck[9] = 38;
//        orderedDeck[10] = 25;
//        orderedDeck[11] = 12;
//
//        orderedDeck[12] = 50;
//        orderedDeck[13] = 37;
//        orderedDeck[14] = 24;
//        orderedDeck[15] = 11;
//
//        orderedDeck[16] = 49;
//        orderedDeck[16] = 36;
//        orderedDeck[16] = 23;
//        orderedDeck[16] = 10;

//        int originalNum = 52;
//        int num = 52;
//        for (int i = 4; i < 52; i++) {
//            orderedDeck[i] = num;
//            num = num - 13;
//            if (i % 4 == 0) {
//                num = --originalNum;
//            }
//        }


//        for (int i = 0; i < 52; i++) {
////            System.out.print(orderedDeck[i] + " ");
////            System.out.print(i+",,,");
//            System.out.print(i + "-" + showCard(deck[orderedDeck[i] -1]) + "     ");
//        }
////        System.out.println(Arrays.toString(orderedDeck));

//        if (true) {
//            return;
//        }
        // [1H, 2H, 3H, 4H, 5H, 6H, 7H, 8H, 9H, 10H, 11H, 12H, 13H, 13
        // 1C, 2C, 3C, 4C, 5C, 6C, 7C, 8C, 9C, 10C, 11C, 12C, 13C,14
        // 1D, 2D, 3D, 4D, 5D, 6D, 7D, 8D, 9D, 10D, 11D, 12D, 13D,27
        // 1S, 2S, 3S, 4S, 5S, 6S, 7S, 8S, 9S, 10S, 11S, 12S, 13S]40

        // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13
        // 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26
        // 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39
        // 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52

        while (true) {
            int firstCardIndex = drawCardAsIndex();
            int secondCardIndex = drawCardAsIndex();
            int higherCardIndex = -1;

            // If the cards are the same... draw the second card again
            // But since higherCard starts off with -1, loop runs anyway...
            while (higherCardIndex == -1) {
                secondCardIndex = drawCardAsIndex();
                higherCardIndex = higherCard(firstCardIndex, secondCardIndex);
//                higherCardIndex = higherCard(firstCardIndex + 1, secondCardIndex + 1) - 1;
            }

            String firstCard = showCard(deck[firstCardIndex]);
            String secondCard = showCard(deck[secondCardIndex]);
            String higherCard = showCard(deck[higherCardIndex]);

            System.out.printf("FIRST: %d / %s  \nSECOND: %d / %s  \nHIGHER: %d / %s", firstCardIndex, firstCard, secondCardIndex, secondCard, higherCardIndex, higherCard);
            System.out.println("\nThe first card drawn was " + showCard(deck[firstCardIndex]));

            String secondCardDrawnMessage = String.format("The second card drawn was %s", showCard(deck[secondCardIndex]));

            System.out.println("Will the next card be higher? (y/n)");
            String input = scan.nextLine();
            System.out.println();

            if (higherCardIndex == firstCardIndex) {
                if (input.contains("y")) {
                    System.out.println(secondCardDrawnMessage);
                    System.out.println("Too bad!");
                } else {
                    System.out.println(secondCardDrawnMessage);
                    System.out.println("You were correct!");
                }
            } else {
                if (input.contains("y")) {
                    System.out.println(secondCardDrawnMessage);
                    System.out.println("You were correct!");
                } else {
                    System.out.println(secondCardDrawnMessage);
                    System.out.println("Too bad!");
                }
            }


            System.out.println("\nWould you like to play again? (y/n)");
            input = scan.nextLine();
            if (input.equals("n")) {
                break;
            }
        }

        System.out.println("Goodbye!");

    }

    private static void populateDeck(String[] deck, String[] suits) {

        // Count from 0-51 to fill in the cards array
        int start = 0;
        for (int i = 1; i <= suits.length; i++) {
            for (int j = 1; j <= 13; j++) {
                deck[start] = j + suits[i - 1];
                start++;
            }
        }


    }

    private static String showCard(String cardValue) {
        cardValue = cardValue.replace("11", "J");
        cardValue = cardValue.replace("12", "Q");
        cardValue = cardValue.replace("13", "K");
        cardValue = cardValue.replace("\\b1H\\b", "AH"); // escape the entire word not just one occurrence of 1
        cardValue = cardValue.replace("\\b1C\\b", "AC"); // escape the entire word not just one occurrence of 1
        cardValue = cardValue.replace("\\b1D\\b", "AD"); // escape the entire word not just one occurrence of 1
        cardValue = cardValue.replace("\\b1S\\b", "AS"); // escape the entire word not just one occurrence of 1
        return cardValue;
    }

    private static int drawCardAsIndex() {
        int index = (int) (Math.random() * ((52 - 1) + 1));
        // If the card has already been picked, draw another one...
        while (pickedCards[index] == 1) {
            index = (int) (Math.random() * ((52 - 1) + 1));
        }
        return index;
    }

    private static int higherCard(int firstCardIndex, int secondCardIndex) {
        // Ordered deck ranked by highest card to lowest.. Ace of Spades being the highest...
        int[] rankedDeck = {40, 27, 14, 1, 52, 39, 26, 13, 51, 38, 25, 12, 50, 37, 24, 11, 49, 36, 23, 10, 48, 35, 22, 9, 47, 34, 21, 8, 46, 33, 20, 7, 45, 32, 19, 6, 44, 31, 18, 5, 43, 30, 17, 4, 42, 29, 16, 3, 41, 28, 15, 2};
//        int[] sortedDeck = rankedDeck;
//        Arrays.sort(sortedDeck);
        int firstPosition = -1, secondPosition = -1;
        for (int i = 0; i < 52; i++) {
            if (rankedDeck[i] == firstCardIndex) {
                firstPosition = i;
            }

            if (rankedDeck[i] == secondCardIndex) {
                secondPosition = i;
            }
        }

        if (firstPosition < secondPosition) {
            return firstCardIndex;
        }
        return secondCardIndex;

    }

    /**
     * Compare which of the two given cards is higher
     *
     * @param firstCardIndex  first card index
     * @param secondCardIndex second card index
     * @return index of higher card
     */
    private static int _higherCard(int firstCardIndex, int secondCardIndex) {

        int firstCard = firstCardIndex % 13;
        int secondCard = secondCardIndex % 13;

        if (firstCard == 0) {
            firstCard = 13;
        }
        if (secondCard == 0) {
            secondCard = 13;
        }

        // Check if the cards are the same
        if (firstCardIndex == secondCardIndex) {
            System.out.println("Checking if cards are the same...");
            return -1;
        }

        // Ace of spaces, the highest card
        if (firstCardIndex == 40) {
            System.out.println("checking if card is ace of spade");
            return firstCardIndex;
        }

        // If both are Aces, the one with the lower index is lower
        if (firstCard == 1 && secondCard == 1) {
            System.out.println("Checking if both cards are aces");
            return firstCardIndex < secondCardIndex ? secondCardIndex : firstCardIndex;
        }

        // If one is an ace and the other isn't, then it's automatically higher
        if (firstCard == 1 || secondCard == 1) {
            System.out.println("Checking if either one of them is an ace");
            if (firstCardIndex % 13 == 1) {
                return firstCardIndex;
            }
            return secondCardIndex;
        }

        // If it's the same type of card aka the same card.. the highest suit is greater
        if (firstCard == secondCard) {
            System.out.println("checking if first card and second card are the same number but diff suit");
            // Since they are the same card, check which card has a lower index.. because the highest cards (e.g. spades) are at the end
            if (firstCardIndex > secondCardIndex) {
                return firstCardIndex;
            }
            return secondCardIndex;
        }

        // If we get this far... we know that it's not gonna be an ace.. or the same card
        // So now all that's left is checking which card is higher!
        if (firstCard > secondCard) {
            System.out.println("checking if first card > second card");
            if (firstCardIndex > secondCardIndex) {
                return firstCardIndex;
            }
            return secondCardIndex;
        } else {
            System.out.println("checking if first card < second card");
            if (firstCardIndex > secondCardIndex) {
                System.out.println("f<s");
                return secondCardIndex;
            }
            System.out.println("f>s");
            return firstCardIndex;
        }

    }

    /**
     * Print the header
     *
     * @param labNum      lab number
     * @param questionNum question number
     * @param fName       first name
     * @param lName       last name
     * @param mission     mission
     */
    private static void printHeader(int labNum, int questionNum, String fName, String lName, String mission) {

        String lab = "Lab " + labNum;
        String question = "Question " + questionNum;
        String name = String.format("Name: %s %s", fName, lName);

        // Get the highest length from the many different strings since we need to have the border that's the length of the longest string

        int[] lengths = {lab.length(), question.length(), name.length(), mission.length()};

        int highestLength = 0;
        for (int length : lengths) {
            if (length > highestLength) {
                highestLength = length;
            }
        }

        // Repeat * until it hits the highestLength
        String borders = new String(new char[highestLength]).replace("\0", "*");


        System.out.println(borders);
        System.out.println(mission);
        System.out.println(borders);
        System.out.println(lab);
        System.out.println(question);
        System.out.println(name);
        System.out.println(borders);
    }


}
