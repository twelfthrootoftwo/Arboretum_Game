package comp1110.ass2.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    Arboretum arboretum;
    CardStack discardPile;
    List<Card> hand;

    public Player(String name, int size){
        this.name = name;
        this.arboretum = new Arboretum(size);
        this.discardPile = new DiscardPile();
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Arboretum getArboretum() {
        return arboretum;
    }

    public CardStack getDiscardPile() {
        return discardPile;
    }

    public List<Card> getHand() {
        return hand;
    }
    public void discard(Card card){

    }
    public void draw(CardStack location){
        Card card = location.drawTopCard();
        this.hand.add(card);
    }
    public boolean play(Card card, Position position){
        if (this.hand.contains(card)){
            if (this.arboretum.isPosCanPlace(position)){
                this.arboretum.addCard(card,position);
                this.hand.remove(card);
                return true;
            }
        }
        return false;


    }
}
