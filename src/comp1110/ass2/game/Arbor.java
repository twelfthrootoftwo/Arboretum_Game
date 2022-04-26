package comp1110.ass2.game;

import java.util.*;

public class Arbor {
    private HashMap<Position, Card> arbor;
    private List<String> arboretumList;
    private HashMap<Position, ArrayList<Position>> scoringMap;
    private int numCards;
    private int limit;


    /**
     * Contribution: Natasha
     * Initialisation constructor, starting off empty
     *
     * @param size - the maximum number of cards a player can play
     */
    public Arbor(int size) {
        this.arbor = new HashMap<Position, Card>();
        this.arboretumList = new ArrayList<>();
        this.numCards = 0;
        this.limit = size - 1;
        this.scoringMap = new HashMap<Position, ArrayList<Position>>();

        for (int i = -size + 1; i < size; i++) {
            for (int j = -size + 1; j < size; j++) {
                Position newPos = new Position(i, j);
                this.arbor.put(newPos, null);

            }
        }
    }

    /**
     * Contribution: Natasha
     * Constructor for use with assignment specification strings
     *
     * @param arborCode - the string representing the arboretum, in assignment specification format
     */
    public Arbor(String arborCode) {
        this.arbor = new HashMap<Position, Card>();

        //assume we're working with the 2-player variant
        //TODO -  allow this to be variable if there are more suits in play?
        this.limit = 8 * 6 - 1;

        //truncate first character, since this is to represent the player
        arborCode = arborCode.substring(1);

        //each move is 8 characters long
        //build arrays to hold played cards and positions
        this.numCards = arborCode.length() / 8;

        for (int i = 0; i < this.numCards; i++) {
            String thisMove = arborCode.substring(8 * i, 8 * i + 8);
            String cardCode = thisMove.substring(0, 2);
            String posCode = thisMove.substring(2);

            Card playedCard = new Card(cardCode);
            Position playedPosition = new Position(posCode);
            this.arbor.put(playedPosition, playedCard);
        }
    }

    /**
     * Contribution: Natasha
     * Add the specified card to the specified position.
     * This assumes the position has already been checked to make sure it's valid (see isPosCanPlace)
     *
     * @param card - the card to add
     * @param pos  - the position to add the card to
     */
    public void addCard(Card card, Position pos) {
        String cardName = card.toString() + pos.toArborString();
        this.arboretumList.add(cardName);

        this.arbor.put(pos, card);
        this.numCards++;
    }

    /**
     * Contribution: Natasha
     * Get the card at the input position
     *
     * @param pos - the position to check
     * @return the card at this position (or null if there is no card there currently)
     */
    public Card getCard(Position pos) {
        return this.arbor.get(pos);
    }

    /**
     * Contribution: Natasha
     * Modified: Junxian
     * Check a position to see if it's a valid place to add a card
     * A position is valid if it's the first card going into (0,0), or if the position is currently empty and adjacent to a filled spot.
     *
     * @param pos - the position to check
     * @return True if a card can be placed here, or False otherwise
     */
    public Boolean isPosCanPlace(Position pos) {

//        //if there are no other cards and the target position is (0,0), place here
//        if (this.numCards == 0 && pos.getX() == 0 & pos.getY() == 0) {
//            return true;
//        }

        //from chen: the above should be: if there are no other cards, the input pos can be anypos.
        // but in player.play, this anypos should be reset to 0,0
        if (this.numCards == 0) {
            return true;
        }

        //if there are other cards, need no card in this position
        if (this.arbor.get(pos) == null) {
            //this position is open, now look for an adjacent card
            Position[] adjacentPos = getAdjacentPos(pos);

            //if any of the adjacent positions have cards, this is a valid position
            for (int i = 0; i < adjacentPos.length; i++) {
                if (this.arbor.get(adjacentPos[i]) != null) {
                    return true;
                }
            }
            return false;

        } else return false;//there's already a card here
    }

    /**
     * Contribution: Natasha
     * Returns an array of positions adjacent to a given position
     *
     * @param startPos - the position to check around
     * @return a length 2, 3, or 4 array of positions adjacent to startPos in order N,E,S,W
     */
    private Position[] getAdjacentPos(Position startPos) {
        //first get number of adjacent positions by looking at edges
        int numPairs = 4;
        if (Math.abs(startPos.getX()) == this.limit) numPairs--;
        if (Math.abs(startPos.getY()) == this.limit) numPairs--;

        Position[] adjacentPos = new Position[numPairs];
        int i = 0;

        if (startPos.getY() + 1 <= this.limit) {
            adjacentPos[i] = new Position(startPos.getX(), startPos.getY() + 1);
            i++;
        }
        if (startPos.getX() + 1 <= this.limit) {
            adjacentPos[i] = new Position(startPos.getX() + 1, startPos.getY());
            i++;
        }
        if (startPos.getY() - 1 >= -this.limit) {
            adjacentPos[i] = new Position(startPos.getX(), startPos.getY() - 1);
            i++;
        }
        if (startPos.getX() - 1 >= -this.limit) {
            adjacentPos[i] = new Position(startPos.getX() - 1, startPos.getY());
            i++;
        }

        return adjacentPos;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (String card : this.arboretumList) {
            output.append(card);
        }
        return output.toString();
    }

    public int getNumCards() {
        return numCards;
    }

    /**
     * Contribution: Natasha
     * Fills in the HashMap of all scoring steps on this arbor.
     * Resets the scoring map to null first, so any existing map will be overwritten.
     * This assumes that the arbor can't be modified, only expanded (in which case all the old information will be recalculated).
     */
    public void findScoringMap() {
        //reset score map, in case it's been calculated previously
        this.scoringMap = new HashMap<>();

        //fill in all positions and find their scoring paths
        for (int i = -this.limit; i <= this.limit; i++) {
            for (int j = -this.limit; j <= this.limit; j++) {
                Position newPos = new Position(i, j);
                this.scoringMap.put(newPos, getScoringSteps(newPos));
            }
        }
    }

    /**
     * Contribution: Natasha
     * Given a position in this arbor, return all (adjacent) positions that form valid scoring routes, i.e. contain cards with numerical values higher than that of the card in this position
     *
     * @param pos - the starting position
     * @return an ArrayList of positions that are valid scoring steps
     */
    public ArrayList<Position> getScoringSteps(Position pos) {
        Position[] adjacentPos = getAdjacentPos(pos);
        ArrayList<Position> scoringDirs = new ArrayList<>();
        Card thisCard = arbor.get(pos);

        //check if there's a card here
        if (thisCard == null) {
            return scoringDirs;
        }

        //check for cards in each of the surrounding positions
        for (Position testPos : adjacentPos) {
            Card testCard = arbor.get(testPos);
            if (testCard != null) {
                //if there is a card, check if its value is greater than the card here (i.e. it's a valid scoring step)
                if (testCard.getNumber() > thisCard.getNumber()) {
                    scoringDirs.add(testPos);
                }
            }
        }

        return scoringDirs;
    }

    /**
     * Contribution: Natasha
     * Generates the scoring map and then finds all possible scoring sequences.
     * This considers both valid steps (low -> high number) and ends (start and end card must be same species)
     *
     * @return a HashSet of LinkedList<Card>s that form valid scoring sequences for this arboretum
     */
    public LinkedList<LinkedList<Card>> findScoringPaths() {
        LinkedList<LinkedList<Card>> scoringSeqs=new LinkedList<>();

        //first generate the scoring map
        this.findScoringMap();

        //look over all positions in arbor
        for (int i = -this.limit; i <= this.limit; i++) {
            for (int j = -this.limit; j <= this.limit; j++) {
                Position thisPos = new Position(i, j);
                LinkedList<Card> sequence=new LinkedList<>();

                //if there's a card here, look for scoring paths starting from it
                if(this.arbor.get(thisPos)!=null) {
                    sequence.add(this.arbor.get(thisPos));
                    findPath(sequence,thisPos,scoringSeqs);
                }
            }
        }

        return scoringSeqs;
    }

    /**
     * Contribution: Natasha
     * Recursive function to propagate scoring paths from a given starting position
     *
     * @param sequence - a LinkedList<Card> for all cards in this scoring path
     * @param thisPos - the position to be checked
     * @param scoringSeqs - a HashSet<LinkedList<Card>> containing all valid scoring paths discovered so far
     */
    public void findPath(LinkedList<Card> sequence, Position thisPos, LinkedList<LinkedList<Card>>scoringSeqs) {
        ArrayList<Position> scoringSteps=this.scoringMap.get(thisPos);
        if(scoringSteps.size()==0) return;//no scoring steps from this position
        else {
            for(Position nextPos:scoringSteps) {
                //we already know there's a card here, since the step was included in the scoring map
                Card nextCard=this.arbor.get(nextPos);
                sequence.add(nextCard);

                //only add to scoring sequences if the start and end are the same species
                if(nextCard.getSpecies()==sequence.get(0).getSpecies()) {
                    scoringSeqs.add((LinkedList<Card>) sequence.clone());
                }

                //recurse to find paths from this position
                findPath(sequence,nextPos,scoringSeqs);

                //clear the checked position from sequence (since the same instance of sequence is used for all recursive calls)
                sequence.remove(nextCard);
            }
        }
    }
}
