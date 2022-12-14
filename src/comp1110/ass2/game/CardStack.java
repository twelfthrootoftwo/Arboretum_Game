package comp1110.ass2.game;

import comp1110.ass2.Arboretum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class CardStack {
    List<Card> cards = new ArrayList<>();// maybe try use stack?


    /**
     * Contribution: Natasha
     * Randomise the order of this.cards (i.e. "shuffle the deck")
     * This should be independent of the number of cards actually in this.cards, but in actual gameplay will only be used on a full deck prior to starting the game
     */
    public void randomise() {
        List<Card> newOrder = new ArrayList<>();
        Random random = new Random();

        //pick members at random from cards and add them to newOrder
        //randomisation happens at the point of picking from the existing card list; JC: I think one random maybe enough?
        while (cards.size() > 0) {
            int nextCardIndex = random.nextInt(0, cards.size());
            newOrder.add(cards.get(nextCardIndex));
            cards.remove(nextCardIndex);
        }

        //replace empty card list with randomised version
        this.cards = newOrder;
    }

    /**
     * Contribution: Natasha
     * Draws the top card (last card in the list)
     * Drawn card is removed from the list
     * In game, this is used when drawing from the deck or from a player's discard
     *
     * @return the card that was drawn, or null if there are no cards in the stack
     */
    public Card drawTopCard() {
        //return null if empty
        if (this.cards.size() == 0) return null;

        Card drawnCard = this.cards.get(this.cards.size() - 1);
        this.cards.remove(this.cards.size() - 1);
        return drawnCard;
    }

    /**
     * Contribution: Natasha
     * Adds a card to the top of the stack
     *
     * @param card - the card to be added
     */
    public void addTopCard(Card card) {
        if (card!=null){
            this.cards.add(card);
        }

    }

    /**
     * Contribution: Natasha
     * Check whether the stack is empty
     *
     * @return True if there are no cards in the stack, False otherwise
     */
    public boolean isEmpty() {
        if (this.cards.size() == 0) return true;
        else return false;
    }

    /**
     * Contribution: Natasha
     * toString method, returning a string in an unsorted version of the assignment specification format
     * Decks need to produce sorted string codes, so they have a different version of this function
     *
     * @return a deck code, in string form (eg m1a2c3j4d5)
     */
    @Override
    public String toString() {
        return Arboretum.cardListToString(cards);
    }

}
