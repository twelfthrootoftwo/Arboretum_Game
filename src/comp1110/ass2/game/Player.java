package comp1110.ass2.game;

import comp1110.ass2.Arboretum;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    Arbor arboretum;
    CardStack discardPile;
    List<Card> hand;

    public Player(String name, int size){
        this.name = name;
        this.arboretum = new Arbor(size);
        this.discardPile = new DiscardPile();
        this.hand = new ArrayList<>();
    }

    /**
     * Given a set of assignment specification codes, construct a Player in the given game state.
     * These values are as specified in comp1110.ass2.Arboretum.isHiddenStateWellFormed and isSharedStateWellFormed
     * @param name - A or B
     * @param handCode - a set of card codes
     * @param arborCode - code for the player's arboretum (Arbor object)
     * @param discardCode - code for the player's discard
     */
    public Player(String name, String handCode, String arborCode,String discardCode) {
        this.name=name;
        this.arboretum=new Arbor(arborCode);
        this.discardPile=new DiscardPile(discardCode);
        this.hand=constructHandFromString(handCode);
    }

    public String getName() {
        return name;
    }

    public Arbor getArboretum() {
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

    /**
     * Generate the hand of the player from assignment specification format
     * @param handCode - a string representing the player's hand, in assignment specification format
     * @return a List<card> suitable for addition to the "hand" field of this class
     */
    private List<Card> constructHandFromString(String handCode) {
        //remove player code
        handCode=handCode.substring(1);
        List<Card> cards=new ArrayList<>();

        //split the code into card subsections, get the corresponding card, and add to list
        int numCards=handCode.length()/2;
        for(int i=0;i<numCards;i++) {
            Card nextCard=new Card(handCode.substring(i*2,i*2+2));
            cards.add(nextCard);
        }

        return cards;
    }
}
