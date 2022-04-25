package comp1110.ass2.game;

import comp1110.ass2.Arboretum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public String getArboretumString() {
        String output = this.getName() + this.getArboretum().toString();
        return output;
    }

    public CardStack getDiscardPile() {
        return discardPile;
    }

    public String getDiscardPileString(){
        StringBuilder output = new StringBuilder(this.getName());
        for (Card card:this.getDiscardPile().cards) {
            output.append(card.toString());
        }
        return output.toString();
    }
    public List<Card> getHand() {
        return hand;
    }

    public String getHandString() {
        StringBuilder output = new StringBuilder(this.getName());
        for (Card card:this.hand) {
            output.append(card.toString());
        }
        return output.toString();
    }


    public void discard(Card card){
        this.discardPile.addTopCard(card);
    }

    public void draw(CardStack location){
        Card card = location.drawTopCard();
        this.hand.add(card);
    }

    /**
     * Checks if the chosen card can be played a thte chosen position; if it can, play it there
     * @param card - card to play
     * @param position - position to play card into
     * @return True if the move was legal and has been processed, False otherwise
     */
    public boolean play(Card card, Position position){
        if (checkPlay(card,position)) {
            if (this.arboretum.getNumCards() == 0){
                this.arboretum.addCard(card, new Position(0,0));
            }else {
                this.arboretum.addCard(card, position);
            }
            this.hand.remove(card);
            return true;


        }
        return false;
    }

    /**
     * Checks whether a play is legal
     * Considers hand size, cards in hand, and position validity
     * @param card - card to play
     * @param position - position to play card into
     * @return True if the play is legal, false otherwise
     */
    public boolean checkPlay(Card card, Position position) {
        System.out.println(0);
        if(this.hand.size()==9) {
            System.out.println(1);
            if (checkCardInHand(card)) {
                System.out.println(2);
                if (this.arboretum.isPosCanPlace(position)) {
                    System.out.println(3);
                    return true;
                }
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

    /**
     * Checks if a specific card is in the player's hand
     * @param cardToCheck - card to look for
     * @return True if it's in hand, False otherwise
     */
    private boolean checkCardInHand(Card cardToCheck) {
        Boolean result=false;
        for (Card card : this.hand) {
            if(cardToCheck.isEqual(card)) {
                result=true;
            }
        }
        return result;
    }
}
