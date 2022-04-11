package comp1110.ass2.game;

import java.util.HashMap;

public class Arbor {
    private HashMap<Position, Card> arbor;
    private int numCards;
    private int limit;

    //Initialisation constructor, starting off empty
    public Arbor(int size) {
        this.arbor=new HashMap<Position,Card>();
        this.numCards=0;
        this.limit=size-1;

        for(int i=-size+1;i<size;i++) {
            for(int j=-size+1;j<size;j++) {
                Position newPos=new Position(i,j);
                this.arbor.put(newPos, null);

            }
        }
    }

    //Constructor for use with the specification testing strings
    public Arbor(String arborCode) {
        this.arbor=new HashMap<Position,Card>();

        //assume we're working with the 2-player variant
        //TODO -  allow this to be variable if there are more suits in play?
        this.limit=8*6-1;

        //truncate first character, since this is to represent the player
        arborCode=arborCode.substring(1);

        //each move is 8 characters long
        //build arrays to hold played cards and positions
        this.numCards=arborCode.length()/8;

        for(int i=0;i<this.numCards;i++) {
            String thisMove=arborCode.substring(8*i,8*i+8);
            String cardCode=thisMove.substring(0,2);
            String posCode=thisMove.substring(2);

            Card playedCard=new Card(cardCode);
            Position playedPosition=new Position(posCode);
            this.arbor.put(playedPosition,playedCard);
        }
    }

    /**
     * Add the specified card to the specified position.
     * This assumes the position has already been checked to make sure it's valid (see isPosCanPlace)
     * @param card - the card to add
     * @param pos - the position to add the card to
     */
    public void addCard(Card card, Position pos) {
        this.arbor.put(pos,card);
        this.numCards++;
    }

    /**
     * Get the card at the input position
     * @param pos - the position to check
     * @return the card at this position (or null if there is no card there currently)
     */
    public Card getCard(Position pos) { return this.arbor.get(pos); }

    /**
     * Check a position to see if it's a valid place to add a card
     * A position is valid if it's the first card going into (0,0), or if the position is currently empty and adjacent to a filled spot.
     * @param pos - the position to check
     * @return True if a card can be placed here, or False otherwise
     */
    public Boolean isPosCanPlace(Position pos) {

        //if there are no other cards and the target position is (0,0), place here
        if(this.numCards==0&&pos.getX()==0&pos.getY()==0) {
            return true;
        }

        //if there are other cards, need no card in this position
        if(this.arbor.get(pos)==null) {
            //this position is open, now look for an adjacent card
            Position[] adjacentPos=getAdjacentPos(pos);

            //if any of the adjacent positions have cards, this is a valid position
            for(int i=0;i<adjacentPos.length;i++) {
                if(this.arbor.get(adjacentPos[i])!=null) {
                    return true;
                }
            }
            return false;

        } else return false;//there's already a card here
    }

    /**
     * Returns an array of positions adjacent to a given position
     * @param startPos - the position to check around
     * @return a length 2, 3, or 4 array of positions adjacent to startPos in order N,E,S,W
     */
    private Position[] getAdjacentPos(Position startPos) {
        //first get number of adjacent positions by looking at edges
        int numPairs=4;
        if(Math.abs(startPos.getX())==this.limit) numPairs--;
        if(Math.abs(startPos.getY())==this.limit) numPairs--;

        Position[] adjacentPos=new Position[numPairs];
        int i=0;

        if(startPos.getY()+1<=this.limit) {
            adjacentPos[i]=new Position(startPos.getX(),startPos.getY()+1);
            i++;
        }
        if(startPos.getX()+1<=this.limit) {
            adjacentPos[i]=new Position(startPos.getX()+1,startPos.getY());
            i++;
        }
        if(startPos.getY()-1>=-this.limit) {
            adjacentPos[i]=new Position(startPos.getX(),startPos.getY()-1);
            i++;
        }
        if(startPos.getX()-1<=this.limit) {
            adjacentPos[i]=new Position(startPos.getX()-1,startPos.getY());
            i++;
        }

        return adjacentPos;
    }

}
