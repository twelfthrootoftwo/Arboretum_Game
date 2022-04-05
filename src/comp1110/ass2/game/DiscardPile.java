package comp1110.ass2.game;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile extends CardStack{
    List<Card> cards;

    public DiscardPile(){
        this.cards = new ArrayList<>();
    }

    /**
     * Get the card at the top of the discard
     * We need this so that the top card (and only the top card) is displayed
     * @return the top (last) card in this.cards;
     */
    public Card peekTopCard(){
        return this.cards.get(this.cards.size()-1);
    }
}
