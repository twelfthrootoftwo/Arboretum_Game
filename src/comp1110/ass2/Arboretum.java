package comp1110.ass2;

import java.util.Set;

public class Arboretum {

    /**
     * A hiddenState string array is well-formed if it complies with the following rules:
     *
     * hiddenState[0] - Deck
     *                      A number of 2-character card substrings sorted alphanumerically as defined below
     *                      For example: "a3a8b5b6c2c7d1d3d7d8m1"
     *
     * hiddenState[1] - Player A's hand
     *                      0th character is 'A'
     *                      Followed by 7, 8 or 9 2-character card substrings sorted alphanumerically.
     *                      For example a full hand String might look like: "Ab3b4c1j1m2m5m8"
     *
     * hiddenState[2] - Player B's hand
     *                      0th character is 'B'
     *                      Followed by 7, 8 or 9 2-character _card_ substrings
     *                      For example: "Ba6b7b8c8d2j2j8"
     *
     * Where:
     * "card substring" - A 2-character string that represents a single card.
     *                     0th character is 'a', 'b', 'c', 'd', 'j', or 'm' representing the card species.
     *                     1st character is a sequential digit from '1' to '8', representing the value of the card.
     *                     For example: "d7"
     *
     * "alphanumerical(ly)" - This means that cards are sorted first alphabetically and then numerically.
     *                     For example, "m2" appears before "m5" because 2 < 5
     *                     Whilst "b4" appears before "c1" because alphabetical ordering takes precedence over
     *                     numerical ordering.
     *
     * Exceptions:
     *      - If the deck is empty, hiddenState[0] will be the empty string ""
     *      - If a player's hand is empty, then the corresponding hiddenState[i] will contain only the player's ID.
     *      For example: if player A's hand is empty then hiddenState[1] will be "A" with no other characters.
     *
     * @param hiddenState the hidden state array.
     * @return true if the hiddenState array is well-formed, false if it is not well-formed.
     * TASK 3
     */
    public static boolean isHiddenStateWellFormed(String[] hiddenState) {
        return false;
        //FIXME TASK 3
    }


    /**
     * A sharedState string array is well-formed if it complies with the following rules:
     *
     * sharedState[0] - a single character ID string, either "A" or "B" representing whose turn it is.
     * sharedState[1] - Player A's arboretum
     *                     0th character is 'A'
     *                     Followed by a number of 8-character _placement_ substrings as defined below that occur in
     *                       the order they were played. Note: these are NOT sorted alphanumerically.
     *                     For example: "Ab1C00C00a4N01C00c3C00E01c6N02C00m7N02W01m4N01E01a5N02E01a2S01E01"
     *
     * sharedState[2] - Player A's discard
     *                    0th character is 'A'
     *                    Followed by a number of 2-character _card_ substrings that occur in the order they were
     *                    played. Note: these are NOT sorted alphanumerically.
     *                    For example: "Aa7c4d6"
     *
     * sharedState[3] - Player B's arboretum
     *                    0th character is 'B'
     *                    Followed by a number of 8-character _placement_ substrings that occur in the order they
     *                    were played. Note: these are NOT sorted alphanumerically.
     *                    For example: "Bj5C00C00j6S01C00j7S02W01j4C00W01m6C00E01m3C00W02j3N01W01"
     *
     * sharedState[4] - Player B's discard
     *                    0th character is 'B'
     *                    Followed by a number of 2-character _card_ substrings that occur in the order they were
     *                    played. Note: these are NOT sorted alphanumerically.
     *                    For example: "Bb2d4c5a1d5"
     *
     * Where: "card substring" and "alphanumerical" ordering are defined above in the javaDoc for
     * isHiddenStateWellFormed and placement substrings are defined as follows:
     *
     * "placement substring" - An 8-character string that represents a card placement in a player's arboretum.
     *                  - 0th and 1st characters are a 2-character card substring
     *                  - 2nd character is 'N' for North, 'S' for South, or 'C' for Centre representing the
     *                    direction of this card relative to the first card played.
     *                  - 3rd and 4th characters are a 2-digit number from "00" to "99" representing the distance
     *                    from the first card played to this card, in the direction of the 2nd character.
     *                  - 5th character is 'E' for East, 'W' for West, or 'C' for Centre representing the
     *                    direction of this card relative to the first card played.
     *                  - 6th and 7th characters are a 2-digit number from "00" to "99" representing the distance
     *                    from the first card played to this card, in the direction of the 3rd character.
     *                  For example: "a1C00C00b3N01C00" says that card "a1" was played first and card "b3" was played
     *                  one square north of the first card (which was "a1").
     *
     * Exceptions:
     * If a player's discard or arboretum are empty, (ie: there are no cards in them), then the corresponding string
     * should contain only the player ID.
     * For example:
     *  - If Player A's arboretum is empty, then sharedState[1] would be "A" with no other characters.
     *  - If Player B's discard is empty, then sharedState[4] would be "B" with no other characters.
     *
     * @param sharedState the shared state array.
     * @return true if the sharedState array is well-formed, false if it is not well-formed.
     * TASK 4
     */
    public static boolean isSharedStateWellFormed(String[] sharedState) {
                                                                      return false; //FIXME TASK 4
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
                                                 return null; // FIXME TASK 5
                                                                             }

    /**
     * Determine whether this placement is valid for the current player. The "Turn String" determines who is making
     * this placement.
     *
     * A placement is valid if the following conditions are met:
     *
     * - The card is placed adjacent to a card that is already placed, or is placed in the position "C00C00" if this is
     * the first card placed.
     * - The card does not share a location with another card that has been placed by this player.
     * - The card being placed is currently in the hand of this player.
     * - The hand of this player has exactly 9 cards in it.
     *
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array
     * @param placement the placement string of the card to be placed
     * @return false if the placement is valid, false if it is not valid.
     * TASK 7
     */
    public static boolean isPlacementValid(String[][] gameState, String placement) {
        return false;
        //FIXME TASK 7
    }

    /**
     * Determine whether the given gameState is valid.
     * A state is valid if the following conditions are met:
     *
     * - There are exactly 48 cards in the game across the deck and each player's hand, arboretum and discard pile.
     * - There are no duplicates of any cards
     * - Every card in each player's arboretum is adjacent to at least one card played _before_ it.
     * - The number of card's in player B's arboretum is equal to, or one less than the number of cards in player
     * A's arboretum.
     * - Each player may have 0 cards in hand only if all cards are in the deck.
     * - Otherwise, a player has exactly 7 cards in their hand if it is not their turn.
     * - If it is a player's turn, they may have 7, 8, or 9 cards in hard.
     * - The number of cards in a player's discard pile is less than or equal to the number of cards in their arboretum.
     *
     * You may assume that the gameState array is well-formed.
     *
     * @param gameState the game state array
     * @return true if the gameState is valid, false if it is not valid.
     * TASK 8
     */
    public static boolean isStateValid(String[][] gameState) { return false;
        // FIXME TASK 8
    }

    /**
     * Determine whether the given player has the right to score the given species. Note: This does not check whether
     * a viable path exists. You may gain the right to score a species that you do not have a viable scoring path for.
     * See "Gaining the Right to Score" in `README.md`.
     * You may assume that the given gameState array is valid.
     *
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array.
     * @param player the player attempting to score.
     * @param species the species that is being scored.
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
     *
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array
     * @param card the card to play
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
     *
     * A "scoring path" is a non-zero number of card substrings in order from starting card to ending card.
     * For example: "a1a3b6c7a8" is a path of length 5 starting at "a1" and ending at "a8".
     *
     * A path is viable if the following conditions are met:
     * 1. The player has the right to score the given species.
     * 2. Each card along the path is orthogonally adjacent to the previous card.
     * 3. Each card has value greater than the previous card.
     * 4. The path begins with the specified species.
     * 5. The path ends with the specified species.
     *
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array
     * @param player the given player
     * @param species the species the path must start and end with.
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
     *
     * You may assume that the inputs to this method are valid and/or well-formed.
     *
     * @param gameState the game state array
     * @param player the given player
     * @param species the species to score
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
     *
     * Note: You may only draw the top card of a players discard pile. ie: The last card that was added to that pile.
     * Note: There must be cards in the deck (or alternatively discard) to draw from the deck (or discard) respectively.
     *
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
     *
     * A moveString array consists of two parts;
     * moveString[0] is the valid card _placement_ string for the card you wish to place.
     * moveString[1] is the cardstring of the card you wish to discard.
     *
     * For example: If I want to play card "a1" in location "N01E02" and discard card "b4" then my moveString[] would
     * be as follows:
     * moveString[0] = "a1N01E02"
     * moveString[1] = "b4"
     *
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
}