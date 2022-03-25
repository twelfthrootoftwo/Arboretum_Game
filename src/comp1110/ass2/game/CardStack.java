package comp1110.ass2.game;

import java.util.ArrayList;
import java.util.List;

public abstract class CardStack {
    List<Card> cards = new ArrayList<>();

    public void randomise(){

    }
    public Card drawTopCard(){
        return new Card("testCard",Species.A,1);
    }
    public void addTopCard(Card card){

    }
    public boolean isEmpty(){
        return false;
    }
}
