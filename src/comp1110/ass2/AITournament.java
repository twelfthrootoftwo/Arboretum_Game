package comp1110.ass2;

public class AITournament {

    /**
     * Generate a moveString or moveString array for the player whose turn it is.
     * This should combine the functionality of both your chooseDrawLocation and generateMove methods.
     * <p>
     * If this is a draw move, return the chosen draw location/card string.
     * If this is a placement move, instead of returning an array with the placement and discard, concatenate these into a single string.
     * For example: if I am making the placement "a4N01C00" and the discard "b5" I would return the string
     * "a4N01C00b5" as my move.
     * <p>
     * Recall:
     * If the hand size (of the player whose turn it is) is less than 9, this should be a draw move.
     * If the hand size (of the player whose turn it is) is exactly 9, this move should be a placement move. (play a card and discard a card)
     *
     * @param sharedState the shared state
     * @param hand this player's hand
     * @param deckSize the number of cards left in the deck
     * @return a valid move for this player.
     */
    public static String generateAction(String[] sharedState, String hand, int deckSize) {
        return "";
    }
}