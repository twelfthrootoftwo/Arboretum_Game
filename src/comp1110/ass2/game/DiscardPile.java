package comp1110.ass2.game;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile extends CardStack{
    List<Card> cards;

    public DiscardPile(){
        this.cards = new ArrayList<>();
    }

    public Card peekTopCard(){
        return new Card("testCard",Species.A,1);
    }
}
