package comp1110.ass2;

import comp1110.ass2.game.Card;
import comp1110.ass2.game.Deck;
import comp1110.ass2.game.Player;
import comp1110.ass2.game.Position;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.util.*;

public class Arboretum {

    /**
     * A hiddenState string array is well-formed if it complies with the following rules:
     * <p>
     * hiddenState[0] - Deck
     * A number of 2-character card substrings sorted alphanumerically as defined below
     * For example: "a3a8b5b6c2c7d1d3d7d8m1"
     * <p>
     * hiddenState[1] - Player A's hand
     * 0th character is 'A'
     * Followed by 7, 8 or 9 2-character card substrings sorted alphanumerically.
     * For example a full hand String might look like: "Ab3b4c1j1m2m5m8"
     * <p>
     * hiddenState[2] - Player B's hand
     * 0th character is 'B'
     * Followed by 7, 8 or 9 2-character _card_ substrings
     * For example: "Ba6b7b8c8d2j2j8"
     * <p>
     * Where:
     * "card substring" - A 2-character string that represents a single card.
     * 0th character is 'a', 'b', 'c', 'd', 'j', or 'm' representing the card species.
     * 1st character is a sequential digit from '1' to '8', representing the value of the card.
     * For example: "d7"
     * <p>
     * "alphanumerical(ly)" - This means that cards are sorted first alphabetically and then numerically.
     * For example, "m2" appears before "m5" because 2 < 5
     * Whilst "b4" appears before "c1" because alphabetical ordering takes precedence over
     * numerical ordering.
     * <p>
     * Exceptions:
     * - If the deck is empty, hiddenState[0] will be the empty string ""
     * - If a player's hand is empty, then the corresponding hiddenState[i] will contain only the player's ID.
     * For example: if player A's hand is empty then hiddenState[1] will be "A" with no other characters.
     *
     * @param hiddenState the hidden state array.
     * @return true if the hiddenState array is well-formed, false if it is not well-formed.
     * TASK 3
     */
    public static boolean isHiddenStateWellFormed(String[] hiddenState) {
        // Check every string in the right format
        if (hiddenState.length != 3) {
            return false;
        }
        // For the deck
        if (!hiddenState[0].isEmpty()) {
            if (Character.isUpperCase(hiddenState[0].charAt(0))) {
                return false;
            }
        }
        // For Player A
        if (hiddenState[1].isEmpty()) {
            return false;
        }
        if (hiddenState[1].charAt(0) != 'A' || hiddenState[1].length() % 2 == 0) {
            return false;
        }
        if (hiddenState[1].length() != 1) {
            if (hiddenState[1].length() < 15 || hiddenState[1].length() > 19) {
                return false;
            }
        }
        // For Player B
        if (hiddenState[2].isEmpty()) {
            return false;
        }
        if (hiddenState[2].charAt(0) != 'B' || hiddenState[2].length() % 2 == 0) {
            return false;
        }
        if (hiddenState[2].length() != 1) {
            if (hiddenState[2].length() < 15 || hiddenState[2].length() > 19) {
                return false;
            }
        }
        // Check alphabetic
        for (int i = 0; i < hiddenState[0].length(); i += 2) {
            if (checkSpecies(hiddenState, i, 0)) return false;
        }
        for (int i = 1; i < hiddenState[1].length(); i += 2) {
            if (checkSpecies(hiddenState, i, 1)) return false;
        }
        for (int i = 1; i < hiddenState[2].length(); i += 2) {
            if (checkSpecies(hiddenState, i, 2)) return false;
        }
        // Check numeric
        for (int i = 1; i < hiddenState[0].length(); i += 2) {
            if (hiddenState[0].charAt(i) < '1' || hiddenState[0].charAt(i) > '8') {
                return false;
            }
        }
        for (int i = 2; i < hiddenState[1].length(); i += 2) {
            if (hiddenState[1].charAt(i) < '1' || hiddenState[1].charAt(i) > '8') {
                return false;
            }
        }
        for (int i = 2; i < hiddenState[2].length(); i += 2) {
            if (hiddenState[2].charAt(i) < '1' || hiddenState[2].charAt(i) > '8') {
                return false;
            }
        }
        // Check sorted correctly
        // For deck
        if (!hiddenState[0].isEmpty()) {
            int[] listSorted = new int[hiddenState[0].length() / 2];
            int j = 0;
            for (int i = 0; i < hiddenState[0].length() - 1; i += 2) {
                int x = hiddenState[0].charAt(i);
                int y = hiddenState[0].charAt(i + 1);
                listSorted[j] = toNumber(x, y);
                j += 1;
            }
            int[] list = listSorted.clone();
            Arrays.sort(listSorted);
            if (!Arrays.equals(listSorted, list)) {
                return false;
            }
        }
        // For Player A
        if (hiddenState[1].length() != 1) {
            int[] listSorted2 = new int[(hiddenState[1].length() - 1) / 2];
            int j = 0;
            for (int i = 1; i < hiddenState[1].length() - 1; i += 2) {
                int x = hiddenState[1].charAt(i);
                int y = hiddenState[1].charAt(i + 1);
                listSorted2[j] = toNumber(x, y);
                j += 1;
            }
            int[] list2 = listSorted2.clone();
            Arrays.sort(listSorted2);
            if (!Arrays.equals(listSorted2, list2)) {
                return false;
            }
        }
        // For Player B
        if (hiddenState[2].length() != 1) {
            int[] listSorted3 = new int[(hiddenState[2].length() - 1) / 2];
            int j = 0;
            for (int i = 1; i < hiddenState[2].length() - 1; i += 2) {
                int x = hiddenState[2].charAt(i);
                int y = hiddenState[2].charAt(i + 1);
                listSorted3[j] = toNumber(x, y);
                j += 1;
            }
            int[] list3 = listSorted3.clone();
            Arrays.sort(listSorted3);
            return Arrays.equals(listSorted3, list3);
        }
        // Else return true
        return true;
        //FIXME TASK 3
    }

    /**
     * Change Character and number to a number that can be used to compare
     *
     * @param charA the alphabetic number
     * @param charB the numerical number
     * @return true if the hiddenState array is well-formed, false if it is not well-formed.
     * Helper Function for TASK 3
     */
    private static int toNumber(int charA, int charB) {
        if (charA == 97) {
            return charB - 48;
        } else {
            charA -= 1;
            charB += 10;
            return toNumber(charA, charB);
        }
    }

    /**
     * Change Character and number to a number that can be used to compare
     *
     * @param state the given state to check
     * @param i     character at index
     * @param j     index of which string to check
     * @return true if the species is right, false if the specie does not exist.
     * Helper Function for TASK 3 and TASK 4
     */
    private static boolean checkSpecies(String[] state, int i, int j) {
        return !(state[j].charAt(i) == 'a'
                || state[j].charAt(i) == 'b'
                || state[j].charAt(i) == 'c'
                || state[j].charAt(i) == 'd'
                || state[j].charAt(i) == 'j'
                || state[j].charAt(i) == 'm');
    }

    /**
     * A sharedState string array is well-formed if it complies with the following rules:
     * <p>
     * sharedState[0] - a single character ID string, either "A" or "B" representing whose turn it is.
     * sharedState[1] - Player A's arboretum
     * 0th character is 'A'
     * Followed by a number of 8-character _placement_ substrings as defined below that occur in
     * the order they were played. Note: these are NOT sorted alphanumerically.
     * For example: "Ab1C00C00a4N01C00c3C00E01c6N02C00m7N02W01m4N01E01a5N02E01a2S01E01"
     * <p>
     * sharedState[2] - Player A's discard
     * 0th character is 'A'
     * Followed by a number of 2-character _card_ substrings that occur in the order they were
     * played. Note: these are NOT sorted alphanumerically.
     * For example: "Aa7c4d6"
     * <p>
     * sharedState[3] - Player B's arboretum
     * 0th character is 'B'
     * Followed by a number of 8-character _placement_ substrings that occur in the order they
     * were played. Note: these are NOT sorted alphanumerically.
     * For example: "Bj5C00C00j6S01C00j7S02W01j4C00W01m6C00E01m3C00W02j3N01W01"
     * <p>
     * sharedState[4] - Player B's discard
     * 0th character is 'B'
     * Followed by a number of 2-character _card_ substrings that occur in the order they were
     * played. Note: these are NOT sorted alphanumerically.
     * For example: "Bb2d4c5a1d5"
     * <p>
     * Where: "card substring" and "alphanumerical" ordering are defined above in the javaDoc for
     * isHiddenStateWellFormed and placement substrings are defined as follows:
     * <p>
     * "placement substring" - An 8-character string that represents a card placement in a player's arboretum.
     * - 0th and 1st characters are a 2-character card substring
     * - 2nd character is 'N' for North, 'S' for South, or 'C' for Centre representing the
     * direction of this card relative to the first card played.
     * - 3rd and 4th characters are a 2-digit number from "00" to "99" representing the distance
     * from the first card played to this card, in the direction of the 2nd character.
     * - 5th character is 'E' for East, 'W' for West, or 'C' for Centre representing the
     * direction of this card relative to the first card played.
     * - 6th and 7th characters are a 2-digit number from "00" to "99" representing the distance
     * from the first card played to this card, in the direction of the 3rd character.
     * For example: "a1C00C00b3N01C00" says that card "a1" was played first and card "b3" was played
     * one square north of the first card (which was "a1").
     * <p>
     * Exceptions:
     * If a player's discard or arboretum are empty, (ie: there are no cards in them), then the corresponding string
     * should contain only the player ID.
     * For example:
     * - If Player A's arboretum is empty, then sharedState[1] would be "A" with no other characters.
     * - If Player B's discard is empty, then sharedState[4] would be "B" with no other characters.
     *
     * @param sharedState the shared state array.
     * @return true if the sharedState array is well-formed, false if it is not well-formed.
     * TASK 4
     */
    public static boolean isSharedStateWellFormed(String[] sharedState) {
        // Check turn in the right format
        if (sharedState.length != 5) {
            return false;
        }
        if (sharedState[0].length() != 1 || (sharedState[0].charAt(0) != 'A' && sharedState[0].charAt(0) != 'B')) {
            return false;
        }
        // Check Player A's arboretum
        // Check number of states
        if ((sharedState[1].length() - 1) % 8 != 0) {
            return false;
        }
        // Check format of first letter
        if (sharedState[1].charAt(0) != 'A') {
            return false;
        }
        if (sharedState[1].length() != 1) {
            // Check the species
            for (int i = 1; i < sharedState[1].length() - 7; i += 8) {
                if (checkSpecies(sharedState, i, 1)) return false;
                // Check card number
                if (sharedState[1].charAt(i + 1) < '1' || sharedState[1].charAt(i + 1) > '8') {
                    return false;
                }
                // Check directions
                if (checkDir1(sharedState, i + 2, 1)) return false;
                if (checkDir2(sharedState, i + 5, 1)) return false;
                // Check distances
                if (sharedState[1].charAt(i + 3) < '0' || sharedState[1].charAt(i + 3) > '9') {
                    return false;
                }
                if (sharedState[1].charAt(i + 4) < '0' || sharedState[1].charAt(i + 4) > '9') {
                    return false;
                }
                if (sharedState[1].charAt(i + 6) < '0' || sharedState[1].charAt(i + 6) > '9') {
                    return false;
                }
                if (sharedState[1].charAt(i + 7) < '0' || sharedState[1].charAt(i + 7) > '9') {
                    return false;
                }
            }
        }
        // Check Player B's arboretum
        // Check number of states
        if ((sharedState[3].length() - 1) % 8 != 0) {
            return false;
        }
        // Check format of first letter
        if (sharedState[3].charAt(0) != 'B') {
            return false;
        }
        // Check the species
        if (sharedState[1].length() != 1) {
            for (int i = 1; i < sharedState[3].length() - 7; i += 8) {
                if (checkSpecies(sharedState, i, 3)) return false;
                // Check card number
                if (sharedState[3].charAt(i + 1) < '1' || sharedState[3].charAt(i + 1) > '8') {
                    return false;
                }
                // Check directions
                if (checkDir1(sharedState, i + 2, 3)) return false;
                if (checkDir2(sharedState, i + 5, 3)) return false;
                // Check distances
                if (sharedState[3].charAt(i + 3) < '0' || sharedState[3].charAt(i + 3) > '9') {
                    return false;
                }
                if (sharedState[3].charAt(i + 4) < '0' || sharedState[3].charAt(i + 4) > '9') {
                    return false;
                }
                if (sharedState[3].charAt(i + 6) < '0' || sharedState[3].charAt(i + 6) > '9') {
                    return false;
                }
                if (sharedState[3].charAt(i + 7) < '0' || sharedState[3].charAt(i + 7) > '9') {
                    return false;
                }
            }
        }
        // Check Player A's discard
        // Check alphabetic
        if (sharedState[2].charAt(0) != 'A') {
            return false;
        }
        if ((sharedState[2].length() - 1) % 2 != 0) {
            return false;
        }
        if (sharedState[1].length() != 1) {
            for (int i = 1; i < sharedState[2].length(); i += 2) {
                if (checkSpecies(sharedState, i, 2)) return false;
            }
            // Check numeric
            for (int i = 2; i < sharedState[2].length(); i += 2) {
                if (sharedState[2].charAt(i) < '1' || sharedState[2].charAt(i) > '8') {
                    return false;
                }
            }
        }
        // Check Player B's discard
        // Check alphabetic
        if (sharedState[4].charAt(0) != 'B') {
            return false;
        }
        if ((sharedState[4].length() - 1) % 2 != 0) {
            return false;
        }
        if (sharedState[1].length() != 1) {
            for (int i = 1; i < sharedState[4].length(); i += 2) {
                if (checkSpecies(sharedState, i, 4)) return false;
            }
            // Check numeric
            for (int i = 2; i < sharedState[4].length(); i += 2) {
                if (sharedState[4].charAt(i) < '1' || sharedState[4].charAt(i) > '8') {
                    return false;
                }
            }
        }
        return true;
        //FIXME TASK 4
    }

    /**
     * Change Character and number to a number that can be used to compare
     *
     * @param state the given char to check
     * @param i     character at index
     * @param j     index of which string to check
     * @return true if the 2nd character's direction is right, false if it is wrong.
     * Helper Function for TASK 4
     */
    private static boolean checkDir1(String[] state, int i, int j) {
        return !(state[j].charAt(i) == 'N'
                || state[j].charAt(i) == 'S'
                || state[j].charAt(i) == 'C');
    }

    /**
     * Change Character and number to a number that can be used to compare
     *
     * @param state the given char to check
     * @param i     character at index
     * @param j     index of which string to check
     * @return true if the 5th character's direction is right, false if it is wrong.
     * Helper Function for TASK 4
     */
    private static boolean checkDir2(String[] state, int i, int j) {
        return !(state[j].charAt(i) == 'E'
                || state[j].charAt(i) == 'W'
                || state[j].charAt(i) == 'C');
    }

    /**
     * Given a deck string, draw a random card from the deck.
     * You may assume that the deck string is well-formed.
     *
     * @param deck the deck string.
     * @return a random cardString from the deck. If the deck is empty, return the empty string "".
     * TASK 5
     */
    public static String drawFromDeck(String deck) {
        // FIXME TASK 5

        Deck constructedDeck = new Deck(deck);

        if (constructedDeck.isEmpty()) return "";

        Card drawnCard = constructedDeck.drawTopCard();

        String cardCode = drawnCard.toString();
        return cardCode;
    }

    /**
     * Determine whether this placement is valid for the current player. The "Turn String" determines who is making
     * this placement.
     * <p>
     * A placement is valid if the following conditions are met:
     * <p>
     * - The card is placed adjacent to a card that is already placed, or is placed in the position "C00C00" if this is
     * the first card placed.
     * - The card does not share a location with another card that has been placed by this player.
     * - The card being placed is currently in the hand of this player.
     * - The hand of this player has exactly 9 cards in it.
     * <p>
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array
     * @param placement the placement string of the card to be placed
     * @return false if the placement is valid, false if it is not valid.
     * TASK 7
     */
    public static boolean isPlacementValid(String[][] gameState, String placement) {
        Player player = new Player("player", 48);

        //initialise with appropriate player's information
        if (gameState[0][0] == "A") {
            player = new Player("A", gameState[1][1], gameState[0][1], gameState[0][2]);
        } else {
            player = new Player("B", gameState[1][2], gameState[0][3], gameState[0][4]);
        }

        Card cardToPlay = new Card(placement.substring(0, 2));
        Position posToPlay = new Position(placement.substring(2));

        return player.checkPlay(cardToPlay, posToPlay);
        //FIXME TASK 7
    }

    /**
     * Determine whether the given gameState is valid.
     * A state is valid if the following conditions are met:
     * <p>
     * - There are exactly 48 cards in the game across the deck and each player's hand, arboretum and discard pile.^
     * - There are no duplicates of any cards^
     * - Every card in each player's arboretum is adjacent to at least one card played _before_ it.^
     * - The number of card's in player B's arboretum is equal to, or one less than the number of cards in player
     * A's arboretum. >?
     * - Each player may have 0 cards in hand only if all cards are in the deck.^
     * - Otherwise, a player has exactly 7 cards in their hand if it is not their turn.^
     * - If it is a player's turn, they may have 7, 8, or 9 cards in hard.^
     * - The number of cards in a player's discard pile is less than or equal to the number of cards in their arboretum.
     * <p>
     * You may assume that the gameState array is well-formed.
     *
     * @param gameState the game state array
     * @return true if the gameState is valid, false if it is not valid.
     * TASK 8
     */
    public static boolean isStateValid(String[][] gameState) {
        String[] sharedState = gameState[0];
        String[] hiddenState = gameState[1];


        //turn a string shows who's turn. e.g. "A"  "B"
        String turn = sharedState[0];


        //arboretumA a string shows Player A's arboretum. e.g. "Aa6C00C00d6S01C00"
        //arboretumACardsList an array shows Player A's arboretum. e.g. [a6, d6, b3, d8, j6, d1, a8, m6, j2, d4]
        String arboretumA = sharedState[1];
        String arboretumATrees = arboretumA.substring(1);
        String[] arboretumACardsList = {};
        int numCardInArboretumA = 0;

        //check if arboretum is empty. e.g. "A"
        if (!arboretumATrees.equals("")) {

            //separate arboretum by every 8 bit. e.g. "a6C00C00"
            String[] arboretumATreesList = arboretumATrees.split("(?<=\\G.{" + 8 + "})");
            numCardInArboretumA = arboretumATreesList.length;

            //create an array to store all name of tree on arboretum
            for (String tree : arboretumATreesList) {
                String name = tree.substring(0, 2);
                List<String> list = new ArrayList<>(Arrays.stream(arboretumACardsList).toList());
                list.add(name);
                arboretumACardsList = list.toArray(new String[0]);
            }
        }


        //discardA a string shows Player A's discard. e.g. "Ab5m4j3"
        //discardACards an array shows Player A's discard. e.g. [b5, m4, j3]
        String discardA = sharedState[2];
        String discardACards = discardA.substring(1);
        int numCardInDiscardA = 0;
        String[] discardACardsList = {};

        //check if discard is empty. e.g. "A"
        if (!discardACards.equals("")) {
            discardACardsList = discardACards.split("(?<=\\G.{" + 2 + "})");
            numCardInDiscardA = discardACardsList.length;
        }


        //arboretumB
        String arboretumB = sharedState[3];
        String arboretumBTrees = arboretumB.substring(1);
        String[] arboretumBCardsList = {};
        int numCardInArboretumB = 0;
        if (!arboretumBTrees.equals("")) {
            String[] arboretumBTreesList = arboretumBTrees.split("(?<=\\G.{" + 8 + "})");
            numCardInArboretumB = arboretumBTreesList.length;
            for (String tree : arboretumBTreesList) {
                String name = tree.substring(0, 2);
                List<String> list = new ArrayList<>(Arrays.stream(arboretumBCardsList).toList());
                list.add(name);
                arboretumBCardsList = list.toArray(new String[0]);
            }
        }


        //discardB
        String discardB = sharedState[4];
        String discardBCards = discardB.substring(1);
        int numCardInDiscardB = 0;
        String[] discardBCardsList = {};
        if (!discardBCards.equals("")) {
            discardBCardsList = discardBCards.split("(?<=\\G.{" + 2 + "})");
            numCardInDiscardB = discardBCardsList.length;
        }


        //deck a string shows the deck. e.g. "a1a2a3a4a5a6a7b2b4b5b6b7c1c3c4c5c6c8d1d2d3d5d7j1j3j4j6j7j8m3m4m5m6m7"
        String deck = hiddenState[0];
        int numCardInDeck = 0;
        String[] deckList = {};
        if (!deck.equals("")) {
            deckList = deck.split("(?<=\\G.{" + 2 + "})");
            numCardInDeck = deckList.length;
        }


        //handA a string shows cards in player A's hand. e.g. "Aa8b3c2d6d8j5m2"
        String handA = hiddenState[1];
        String handACards = handA.substring(1);
        int numCardInHandA = 0;
        String[] handACardsList = {};
        if (!handACards.equals("")) {
            handACardsList = handACards.split("(?<=\\G.{" + 2 + "})");
            numCardInHandA = handACardsList.length;
        }


        //handB a string shows cards in player B's hand. e.g. "Aa8b3c2d6d8j5m2"
        String handB = hiddenState[2];
        String handBCards = handB.substring(1);
        int numCardInHandB = 0;
        String[] handBCardsList = {};
        if (!handBCards.equals("")) {
            handBCardsList = handBCards.split("(?<=\\G.{" + 2 + "})");
            numCardInHandB = handBCardsList.length;
        }

        // put all cards in gameState[] to a list.
        List<String> allCards = new ArrayList<>();
        allCards.addAll(Arrays.stream(arboretumACardsList).toList());
        allCards.addAll(Arrays.stream(discardACardsList).toList());
        allCards.addAll(Arrays.stream(arboretumBCardsList).toList());
        allCards.addAll(Arrays.stream(discardBCardsList).toList());
        allCards.addAll(Arrays.stream(deckList).toList());
        allCards.addAll(Arrays.stream(handACardsList).toList());
        allCards.addAll(Arrays.stream(handBCardsList).toList());
        //remove empty string ""
        allCards.removeIf(card -> card.equals(""));


        //starting filter

        //There are exactly 48 cards in the game across the deck and each player's hand, arboretum and discard pile.
        if (allCards.size() != 48) {
            return false;
        }

        //There are no duplicates of any cards.
        Set<String> duplicates = new HashSet<>();
        for (String card : allCards) {
            if (!duplicates.add(card)) {
                return false;
            }
        }

        //Every card in each player's arboretum is adjacent to at least one card played _before_ it.^
        if (!checkArboretum(arboretumATrees)) {
            return false;
        }
        if (!checkArboretum(arboretumBTrees)) {
            return false;
        }

        //The number of card's in player B's arboretum is equal to, or one less than the number of cards in player A's
        if (!(numCardInArboretumB == numCardInArboretumA || numCardInArboretumB == numCardInArboretumA - 1)) {
            return false;
        }

        // Each player may have 0 cards in hand only if all cards are in the deck.
        if (numCardInHandA == 0 && numCardInDeck != 48) {
            return false;
        }
        if (numCardInHandB == 0 && numCardInDeck != 48) {
            return false;
        }

        //Otherwise, a player has exactly 7 cards in their hand if it is not their turn.
        if (turn.equals("A") && numCardInHandB != 7) {
            if (numCardInDeck != 48) {
                return false;
            }
        }
        if (turn.equals("B") && numCardInHandA != 7) {
            if (numCardInDeck != 48) {
                return false;
            }
        }

        //If it is a player's turn, they may have 7, 8, or 9 cards in hard.
        if (turn.equals("A") && !(numCardInHandA == 7 || numCardInHandA == 8 || numCardInHandA == 9)) {
            if (numCardInDeck != 48) {
                return false;
            }
        }
        if (turn.equals("B") && !(numCardInHandB == 7 || numCardInHandB == 8 || numCardInHandB == 9)) {
            if (numCardInDeck != 48) {
                return false;
            }
        }

        //The number of cards in a player's discard pile is less than or equal to the number of cards in their arboretum.
        if (numCardInDiscardA > numCardInArboretumA) {
            return false;
        }
        if (numCardInDiscardB > numCardInArboretumB) {
            return false;
        }
        return true;
        // FIXME TASK 8
    }

    /**
     * A Helper function to determine whether the given arboretum is valid.
     *
     * @param arboretum the arboretum String
     * @return true if the arboretum is valid, false if it is not valid.
     */
    private static boolean checkArboretum(String arboretum) {
        //check if arboretum is empty. e.g. "A"
        if (!arboretum.equals("")) {
            //a map that store all trees by order in arboretum.
            HashMap<String, int[]> map = new HashMap<>();
            //another map that try to add valid trees by order from last map.
            // If these two map's length doesn't match, return false.
            HashMap<String, int[]> hasNext = new HashMap<>();
            String[] arboretumBTreesList = arboretum.split("(?<=\\G.{" + 8 + "})");
            int[] startPos = {0, 0};

            //put all trees to an array by order. e.g. [a6C00C00, d6S01C00, b3C00W01, d8N01W01]
            for (String tree : arboretumBTreesList) {
                String name = tree.substring(0, 2);

                //get tree absolute location to start position(0,0). S & E positive; N & W negative.
                // e.g. d6 is on [1,0], b3 is on [0, -1], d8 is on [-1, -1]
                String directionV = tree.substring(2, 3);
                int stepV = Integer.parseInt(tree.substring(3, 5));
                String directionH = tree.substring(5, 6);
                int stepH = Integer.parseInt(tree.substring(6));

                int newPosV = 0;
                int newPosH = 0;
                if (directionV.equals("C") && directionH.equals("C")) {
                    startPos = new int[]{0, 0};
                    map.put(name, startPos);
                    hasNext.put(name, startPos);
                }

                if (directionV.equals("C")) {
                    newPosV = startPos[0];
                }
                if (directionV.equals("N")) {
                    newPosV = startPos[0] - stepV;
                }
                if (directionV.equals("S")) {
                    newPosV = startPos[0] + stepV;
                }
                if (directionH.equals("C")) {
                    newPosH = startPos[0];
                }
                if (directionH.equals("W")) {
                    newPosH = startPos[0] - stepH;
                }
                if (directionH.equals("E")) {
                    newPosH = startPos[0] + stepH;
                }
                int[] newPos = {newPosV, newPosH};

                //check if next tree by given order can be put.
                for (int[] pos : map.values()) {
                    if ((pos[0] == newPosV + 1 || pos[0] == newPosV - 1) && pos[1] == newPosH) {
                        hasNext.put(name, newPos);
                    }
                    if ((pos[1] == newPosH + 1 || pos[1] == newPosH - 1) && pos[0] == newPosV) {
                        hasNext.put(name, newPos);
                    }
                }
                //normally add trees to map
                map.put(name, newPos);

            }

            if (map.keySet().size() != hasNext.keySet().size()) {
                return false;
            }


        } else {
            return true;
        }
        return true;
    }

    /**
     * Determine whether the given player has the right to score the given species. Note: This does not check whether
     * a viable path exists. You may gain the right to score a species that you do not have a viable scoring path for.
     * See "Gaining the Right to Score" in `README.md`.
     * You may assume that the given gameState array is valid.
     * <p>
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array.
     * @param player    the player attempting to score.
     * @param species   the species that is being scored.
     * @return true if the given player has the right to score this species, false if they do not have the right.
     * TASK 9
     */
    public static boolean canScore(String[][] gameState, char player, char species) {
        return false;
        // FIXME TASK 9
    }

    /**
     * Find all the valid placements for the given card for the player whose turn it is.
     * A placement is valid if it satisfies the following conditions:
     * 1. The card is horizontally or vertically adjacent to at least one other placed card.
     * 2. The card does not overlap with an already-placed card.
     * <p>
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array
     * @param card      the card to play
     * @return a set of valid card placement strings for the current player.
     * TASK 10
     */
    public static Set<String> getAllValidPlacements(String[][] gameState, String card) {
        return null;
        //FIXME TASK 10
    }

    /**
     * Find all viable scoring paths for the given player and the given species if this player has the right to
     * score this species.
     * <p>
     * A "scoring path" is a non-zero number of card substrings in order from starting card to ending card.
     * For example: "a1a3b6c7a8" is a path of length 5 starting at "a1" and ending at "a8".
     * <p>
     * A path is viable if the following conditions are met:
     * 1. The player has the right to score the given species.
     * 2. Each card along the path is orthogonally adjacent to the previous card.
     * 3. Each card has value greater than the previous card.
     * 4. The path begins with the specified species.
     * 5. The path ends with the specified species.
     * <p>
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array
     * @param player    the given player
     * @param species   the species the path must start and end with.
     * @return a set of all viable scoring paths if this player has the right to score this species, or null if this
     * player does not have the right to score this species. If the player has no viable scoring paths (but has the
     * right to score this species), return the empty Set.
     * TASK 12
     */
    public static Set<String> getAllViablePaths(String[][] gameState, char player, char species) {
        return null;
        // FIXME TASK 12
    }

    /**
     * Find the highest score of the viable paths for the given player and species.
     * <p>
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array
     * @param player    the given player
     * @param species   the species to score
     * @return the score of the highest scoring viable path for this player and species.
     * If this player does not have the right to score this species, return -1.
     * If this player has the right to score this species but there is no viable scoring path, return 0.
     * TASK 13
     */
    public static int getHighestViablePathScore(String[][] gameState, char player, char species) {
        return Integer.MIN_VALUE;
        // FIXME TASK 13
    }

    /**
     * AI Part 1:
     * Decide whether to draw a card from the deck or a discard pile.
     * Note: This method only returns the choice, it does not update the game state.
     * If you wish to draw a card from the deck, return "D".
     * If you wish to draw a card from a discard pile, return the cardstring of the (top) card you wish to draw.
     * You may count the number of cards in a players' hand to determine whether this is their first or final draw
     * for the turn.
     * <p>
     * Note: You may only draw the top card of a players discard pile. ie: The last card that was added to that pile.
     * Note: There must be cards in the deck (or alternatively discard) to draw from the deck (or discard) respectively.
     * <p>
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array
     * @return "D" if you wish to draw from the deck, or the cardstring of the card you wish to draw from a discard
     * pile.
     * TASK 14
     */
    public static String chooseDrawLocation(String[][] gameState) {
        return null;
        // FIXME TASK 14
    }


    /**
     * AI Part 2:
     * Generate a moveString array for the player whose turn it is.
     * <p>
     * A moveString array consists of two parts;
     * moveString[0] is the valid card _placement_ string for the card you wish to place.
     * moveString[1] is the cardstring of the card you wish to discard.
     * <p>
     * For example: If I want to play card "a1" in location "N01E02" and discard card "b4" then my moveString[] would
     * be as follows:
     * moveString[0] = "a1N01E02"
     * moveString[1] = "b4"
     * <p>
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array
     * @return a valid move for this player.
     * TASK 15
     */
    public static String[] generateMove(String[][] gameState) {
        return null;
        // FIXME TASK 15
    }

    /**
     * Main method for testing purposes
     */
    public static void main(String[] args) {
        String[][] validState = new String[][]{new String[]{"B",
                "Ab1C00C00a4N01C00c3C00E01c6N02C00m7N02W01m4N01E01a5N02E01a2S01E01", "A",
                "Bj5C00C00j6S01C00j7S02C00j4S01W01m6C00E01m3C00W02j3N01W01", "B"},
                new String[]{"a7a8b2b5b6c2c4c5c7d1d3d4d6d7d8m1", "Ab3b4c1j1m2m5m8", "Ba1a3a6b7b8c8d2j2j8"}};
        String[] placements = {"N01W02", "N02W01", "N01C00", "N01E01", "C00E02", "S01E01", "S02W01",
                "S01W02", "C00W03"};
        String[] cards = {"a1", "a3", "a6", "b7", "b8", "c8", "d2", "j2", "j8"};

        for (int i = 0; i < placements.length; i++) {
            for (int j = 0; j < cards.length; j++) {
                String placementString = cards[j] + placements[i];
                isPlacementValid(validState, placementString);
            }
        }

        Position test1 = new Position("C00C00");
        Position test2 = new Position(0, 0);
        System.out.println(test1.hashCode());
        System.out.println(test2.hashCode());
    }
}
