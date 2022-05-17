package comp1110.ass2.game;

import java.util.*;

public class Arbor {
    public HashMap<Position, Card> arbor;
    private List<String> arboretumList;
    private List<Card> arboretumCardList;
    private HashMap<Position, ArrayList<Position>> scoringMap;
    private int numCards;
    private final int limit;
    private final int numSpecies;
    private Position nBound;
    private Position eBound;
    private Position sBound;
    private Position wBound;
    private String originalArbor;


    /**
     * Contribution: Natasha
     * Initialisation constructor, starting off empty
     *
     * @param numSpecies - the number of species in play
     */
    public Arbor(int numSpecies) {
        this.arbor = new HashMap<Position, Card>();
        this.arboretumList = new ArrayList<>();
        this.arboretumCardList = new ArrayList<>();
        this.numCards = 0;
        this.limit = 8*numSpecies - 1;//8 card values per species
        this.scoringMap = new HashMap<Position, ArrayList<Position>>();
        this.numSpecies=numSpecies;

        for (int i = -this.limit; i <= this.limit; i++) {
            for (int j = -this.limit + 1; j <= this.limit; j++) {
                Position newPos = new Position(i, j);
                this.arbor.put(newPos, null);
            }
        }

        //set starting range to (0,0)
        this.nBound=new Position(0,0);
        this.eBound=this.nBound;
        this.sBound=this.nBound;
        this.wBound=this.nBound;
    }

    /**
     * Contribution: Natasha, Hongzhe
     * Constructor for use with assignment specification strings
     *
     * @param arborCode - the string representing the arboretum, in assignment specification format
     */
    public Arbor(String arborCode) {
        this.arbor = new HashMap<Position, Card>();
        //the original arbor String given to construct Arbor
        this.originalArbor = arborCode;
        //assume we're working with the 2-player variant
        this.numSpecies=6;
        this.limit = 8 * this.numSpecies - 1;
        this.arboretumList = new ArrayList<>();
        this.arboretumCardList = new ArrayList<>();
//        //truncate first character, since this is to represent the player
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
            this.arboretumList.add(playedCard.toString() + playedPosition.toArborString());
            this.arboretumCardList.add(playedCard);
            this.arbor.put(playedPosition, playedCard);
        }

        //set starting range to (0,0)
        this.nBound=new Position(0,0);
        this.eBound=this.nBound;
        this.sBound=this.nBound;
        this.wBound=this.nBound;

        //update bounds
        this.findRange();
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
        this.arboretumCardList.add(card);
        this.arbor.put(pos, card);

        //update range & card count
        this.numCards++;
        this.checkRange(pos);
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
        if (this.numCards == 0 && pos.getX() == 0 & pos.getY() == 0) {
            return true;
        }

        //from chen: the above should be: if there are no other cards, the input pos can be anypos.
        // but in player.play, this anypos should be reset to 0,0
        //NP: Switched this back to forcing only (0,0) for the first move (otherwise we'd need to reassign positions to displayed GUIPositions)
//        if (this.numCards == 0) {
//            return true;
//        }

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
    public List<Position> getAvailablePos(){

        if (this.numCards > 0 ){
            List<Position> positions = new LinkedList<>();
            for (Position card:this.arbor.keySet()) {
                Position[] adjacentPos = getAdjacentPos(card);
                for (int i = 0; i < adjacentPos.length; i++) {
                    if (isPosCanPlace(adjacentPos[i])) {
                        positions.add(adjacentPos[i]);
                    }
                }
            }
            Set<Position> set = new HashSet<>(positions);
            positions.clear();
            positions.addAll(set);
            return positions;
        }else {
            List<Position> positions = new LinkedList<>();
            positions.add(new Position(0,0));
            return positions;
        }

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
     * Determines the scores from this Arboretum for each species
     * @return a HashMap containing scores for all species, using Species enum as keys
     */
    public HashMap<Species,Integer> score() {
        HashMap<Species,Integer> finalScores=new HashMap<Species,Integer>();

        //initialise scores for all species to 0
        for(Species species:Species.values()) {
            finalScores.put(species,0);
        }

        //get scoring paths
        LinkedList<LinkedList<Card>> scorePaths=this.findScoringPaths();

        //go through each path
        for(LinkedList<Card> path:scorePaths) {
            int score=findScore(path);
            Species species=path.get(0).getSpecies();

            //compare to existing score for this species, and replace if it's higher
            if(score>finalScores.get(species)) finalScores.put(species,score);
        }

        return finalScores;
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

    /**
     * Contribution: Natasha
     * Given a List<Card> that forms a valid scoring path, determines the score of the path
     * Does not check for scoring validity
     * @param path - a List<Card> of the scoring path, in order from first (lowest) to last (highest)
     * @return the score of this path
     */
    public static int findScore(List<Card> path) {
        //one point per card in path
        int sizeBonus=path.size();

        //if all cards are the same species, and path is 4 or more cards, score a bonus point for each card
        int speciesBonus=0;
        if(path.size()>3) {
            Boolean sameSpecies=true;
            int i=1;
            while(sameSpecies&&i<path.size()) {
                Species species1=path.get(i-1).getSpecies();
                Species species2=path.get(i).getSpecies();
                if(species1!=species2)sameSpecies=false;
                i++;
            }
            if(sameSpecies) speciesBonus=path.size();
        }

        //if first card is 1, bonus 1 point
        int startBonus=0;
        if(path.get(0).getNumber()==1) startBonus=1;

        //if last card is 8, bonus 2 points
        int endBonus=0;
        if(path.get(path.size()-1).getNumber()==8) endBonus=2;

        //add all score elements
        int finalScore=sizeBonus+speciesBonus+startBonus+endBonus;
        return finalScore;
    }

    /**
     * Contribution: Natasha
     * Determines the occupied positions at the furthest extremes of the Arbor
     * For use with prebuilt arbors (eg assignment spec strings)
     */
    private void findRange() {
        //check each occupied position
        for(Position pos:this.arbor.keySet()) {
            this.checkRange(pos);
        }
    }

    /**
     * Contribution: Natasha
     * Updates the recorded range bounds if the input position is outside the range
     * Checks if the position contains a card first
     *
     * @param pos
     */
    private void checkRange(Position pos) {
        if(this.arbor.get(pos)!=null) {
            if (pos.getY() > this.nBound.getY()) this.nBound = pos;
            if (pos.getX() > this.eBound.getX()) this.eBound = pos;
            if (pos.getY() < this.sBound.getY()) this.sBound = pos;
            if (pos.getX() < this.wBound.getX()) this.wBound = pos;
        }
    }

    /**
     * Contribution: Natasha
     * Gets the bounding positions of this Arbor
     * @return a Position[] of the most extreme occupied positions, in [N,E,S,W] order
     */
    public Position[] getBounds() {
        Position[] bounds=new Position[]{this.nBound,this.eBound,this.sBound,this.wBound};
        return bounds;
    }

    /**
     * Contribution: Hongzhe
     * List all the existing placements without the card specie and value.
     *
     * @return a set that separates all the exists placements
     */
    public Set<String> separateCards() {
        Set<String> output = new HashSet<>();
        for (int i = 3; i < originalArbor.length() - 5; i += 8) {
            output.add(Character.toString(originalArbor.charAt(i))
                    + originalArbor.charAt(i + 1)
                    + originalArbor.charAt(i + 2)
                    + originalArbor.charAt(i + 3)
                    + originalArbor.charAt(i + 4)
                    + originalArbor.charAt(i + 5));
        }
        return output;
    }
    /** Contribution: Hongzhe
     * Generate a hashmap containing the current score of existing species of the arboretum for each player.
     * @return a hashmap with its key of species and the current score of the species.
     */
    public HashMap<String, Integer> currentScore() {
//        System.out.println(arboretum);
        HashMap<String, Integer> output = new HashMap<>();
        for (String species : new String[]{"a", "b", "c", "d", "j", "m"}){
            // Check if the species do exist in the arboretum
            if (this.toString().contains(species)){
                output.put(species, getHighestScore(species.charAt(0)));

            }
        }
        return output;
    }

    public int getHighestScore(char species) {
        HashMap<Species, Integer> scores = this.score();

        //get score for target species
        Species toScore = Species.valueOf(String.valueOf(species));
        return scores.get(toScore);
    }

    public List<Card> getArboretumCardList() {
        return arboretumCardList;
    }
}

