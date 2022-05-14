package comp1110.ass2.game;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile extends CardStack {
    List<Card> cards;

    public DiscardPile() {
        this.cards = new ArrayList<>();
    }

    /**
     * Contribution: Natasha
     * Given a string code as per the assignment specification, construct an equivalent Deck
     * (see comments for comp1110.ass2.Arboretum.isSharedStateWellFormed for string code details)
     *
     * @param discardCode - a string representing the discard sequence
     */
    public DiscardPile(String discardCode) {
        this.cards = new ArrayList<>();
//        System.out.println(discardCode);
        //Remove the player code
        discardCode = discardCode.substring(1);

        if (discardCode.length() > 0) {
            int numCards = discardCode.length() / 2;

            //add card to discard, using card constructor built for assignment spec strings
            for (int i = 0; i < numCards; i++) {
                Card nextCard = new Card(discardCode.substring(i * 2, i * 2 + 2));
                this.cards.add(nextCard);
            }
        }
    }

    /**
     * Contribution: Natasha
     * Get the card at the top of the discard
     * We need this so that the top card (and only the top card) is displayed
     *
     * @return the top (last) card in this.cards;
     */
    public Card peekTopCard() {
        if(this.cards.size()>0) return this.cards.get(this.cards.size() - 1);
        else return new Card();
    }
}
