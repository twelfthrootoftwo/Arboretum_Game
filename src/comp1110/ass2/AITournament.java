package comp1110.ass2;

import comp1110.ass2.game.*;

import java.util.*;

public class AITournament {

    /**
     * Generate a moveString or moveString array for the player whose turn it is.
     * This should combine the functionality of both your chooseDrawLocation and generateMove methods.
     * <p>
     * If this is a draw move, return the chosen draw location/card string.
     * If this is a placement move, instead of returning an array with the placement and discard, concatenate these into a single string.
     * For example: if I am making the placement "a4N01C00" and the discard "b5" I would return the string
     * "a4N01C00b5" as my move.
     * <p>
     * Recall:
     * If the hand size (of the player whose turn it is) is less than 9, this should be a draw move.
     * If the hand size (of the player whose turn it is) is exactly 9, this move should be a placement move. (play a card and discard a card)
     *
     * @param sharedState the shared state
     * @param hand this player's hand
     * @param deckSize the number of cards left in the deck
     * @return a valid move for this player.
     */
    public static String generateAction(String[] sharedState, String hand, int deckSize) {
        //System.out.println("State: "+Arrays.toString(sharedState));
        //System.out.println("Hand: "+hand);
        //System.out.println("Deck size: "+deckSize);

        String activeArbor="";
        String activeDiscard="";
        String otherArbor="";
        String otherDiscard="";
        if(sharedState[0].equals("A")) {
            activeArbor=sharedState[1];
            activeDiscard=sharedState[2];
            otherArbor=sharedState[3];
            otherDiscard=sharedState[4];
        } else {
            activeArbor=sharedState[3];
            activeDiscard=sharedState[4];
            otherArbor=sharedState[1];
            otherDiscard=sharedState[2];
        }
        Player activePlayer=new Player("A",hand,activeArbor,activeDiscard);
        Player otherPlayer=new Player("B","Ba1a1a1a1a1a1a1",otherArbor,otherDiscard);

        //check if draw or placement
        if(hand.length()==19){
            //placement
            String move=tournamentAIMove(activePlayer);
            String discard=tournamentAIDiscard(activePlayer,otherPlayer);
            return move+discard;
        } else {
            //draw
            Boolean forceDiscardDraw=false;
            if(deckSize==0) forceDiscardDraw=true;
            return tournamentAIDraw(activePlayer,otherPlayer,forceDiscardDraw);
        }
    }

    /**
     * Streamlined versions of AI functions for tournament server
     */
    private static String tournamentAIMove(Player player) {
        List<Card> availableCards = player.getHand();
        Arbor arboretum = player.getArboretum();

        HashMap[] moveRecords=findPossibleMoves(arboretum,availableCards);
        HashMap<String,HashMap<String, Integer>> allMoves=moveRecords[0];
        HashMap<String,HashMap<String, Integer>> scoringMoves=moveRecords[1];

        //availableResults2, third level, sort from availableResults
        HashMap<String, Integer> availableResults2 = new LinkedHashMap<>();
        int valueBiggest = 0;
        //find availableResults2/s that have the biggest score
        for (String key : scoringMoves.keySet()) {
            HashMap<String, Integer> value = scoringMoves.get(key);
            if (value != null) {
                String keyBiggest = Collections.max(value.entrySet(), Map.Entry.comparingByValue()).getKey();
                if (value.get(keyBiggest) > valueBiggest) {
                    valueBiggest = value.get(keyBiggest);
                    availableResults2.put(key, valueBiggest);
                }
            }
        }
        String result="";
        //random select if reach the third level
        if (!availableResults2.isEmpty()) {
            result = Collections.max(availableResults2.entrySet(), Map.Entry.comparingByValue()).getKey();
            //System.out.println("AI Move R2 is: " + result);
        } else {
            //random select if reach the second level
            if (!scoringMoves.isEmpty()) {
                List<String> keysAsArray = new ArrayList<>(scoringMoves.keySet());
                Random rand = new Random();
                result = keysAsArray.get(rand.nextInt(keysAsArray.size()));
                //System.out.println("AI Move R1 is: " + result);
            } else {
                //random select
                List<String> keysAsArray = new ArrayList<String>(allMoves.keySet());
                Random rand = new Random();
                result = keysAsArray.get(rand.nextInt(keysAsArray.size()));
                //System.out.println("AI Move R0 is: " + result);
            }
        }
        Card toPlay=new Card(result.substring(0,2));
        player.discard(toPlay);
        //System.out.println("Play: "+result);
        //System.out.println("Hand after play: "+Arrays.toString(player.getHand().toArray()));
        return result;
    }

    private static String tournamentAIDiscard(Player player_turn, Player player_notTurn) {
        List<Card> availableChoices = new ArrayList<>();
        List<Card> player_turn_arbor_cards = player_turn.getArboretum().getArboretumCardList();
        List<Card> player_notTurn_arbor_cards = player_notTurn.getArboretum().getArboretumCardList();

        for (Card card : player_turn.getHand()) {
            //!player_turn_arbor_cards.contains(card) --> 1
            //player_notTurn_arbor_cards.contains(card) --> 2 & 3
            if (!player_turn_arbor_cards.contains(card) && player_notTurn_arbor_cards.contains(card)) {
                availableChoices.add(card);
            }
        }
        Random rand = new Random();
        Card result;
        //if has availableChoices, random choices one, otherwise random choices from hand.
        if (!availableChoices.isEmpty()) {
            result = availableChoices.get(rand.nextInt(availableChoices.size()));
        } else {
            result = player_turn.getHand().get(rand.nextInt(player_turn.getHand().size()));
        }
        player_turn.discard(result);
        //System.out.println("Discard: "+result);
        //System.out.println("Hand after discard: "+Arrays.toString(player_turn.getHand().toArray()));
        return result.toString();
    }

    //this one is modified to return only one card drawn
    private static String tournamentAIDraw(Player player_turn, Player player_notTurn,boolean forceDiscardDraw) {
        //two known available choices from both side discardPile
        List<CardStack> availableChoices = new ArrayList<>();
        HashMap<String, Integer> currentScore = player_turn.getArboretum().currentScore();
        //generate new discard from simulating
        DiscardPile player_turn_discard = player_turn.getDiscardPile();
        DiscardPile player_notTurn_discard = player_notTurn.getDiscardPile();

        //availablePlayers stores players with new discardPile map to discardPiles
        HashMap<CardStack,HashMap<String, Integer>> availableDraws = new HashMap<>();

        if (!player_turn_discard.isEmpty() || !player_notTurn_discard.isEmpty()) {
            if(!player_turn_discard.isEmpty()) availableChoices.add(player_turn_discard);
            if(!player_notTurn_discard.isEmpty()) availableChoices.add(player_notTurn_discard);

            //since we are only drawing one card at a time, consider only the top card of each discard
            for (CardStack stack : availableChoices) {
                if (!stack.isEmpty()) {
                    List<CardStack> valueList = new ArrayList<>();
                    Card drawStack = stack.drawTopCard();
                    if (drawStack != null) {
                        valueList.add(stack);

                        //Streamline original process - store only play strings and resulting scores
                        HashMap<String,HashMap<String, Integer>>allMoves=new HashMap<>();//all possible moves
                        HashMap<String,HashMap<String, Integer>>scoringMoves=new HashMap<>();//only those that increase the score

                        findSingleCardMoves(player_turn.getArboretum(),drawStack,currentScore,allMoves,scoringMoves);
                        if(!scoringMoves.isEmpty()) {

                            String keyBiggest = getHighestScores(scoringMoves);
                            availableDraws.put(stack,scoringMoves.get(keyBiggest));
                        }
                        //for draw result, generate A new player to store it's new discardPile
                    }
                    //add draw card back for continue simulation
                    stack.addTopCard(drawStack);
                }
            }
        }

        if(!availableDraws.isEmpty()) {
            int highScore=0;
            CardStack drawLocation=null;
            for(CardStack stack:availableDraws.keySet()) {
                if(drawLocation==null) drawLocation=stack;
                int scoreToCompare=0;
                for (String species : availableDraws.get(stack).keySet()) {
                    scoreToCompare += availableDraws.get(stack).get(species);
                }
                if(scoreToCompare>highScore) {
                    highScore=scoreToCompare;
                    drawLocation=stack;
                }
            }
            return drawLocation.drawTopCard().toString();
        } else if(forceDiscardDraw) {
            Random rand=new Random();
            CardStack drawLocation=availableChoices.get(rand.nextInt(availableChoices.size()));
            return drawLocation.drawTopCard().toString();
        } else return "D";
    }

    private static HashMap[] findPossibleMoves(Arbor arboretum, List<Card> hand) {
        //store currentScore for later score comparing
        HashMap<String, Integer> currentScore = arboretum.currentScore();

        //Streamline original process - store only play strings and resulting scores
        HashMap<String,HashMap<String, Integer>>allMoves=new HashMap<>();//all possible moves
        HashMap<String,HashMap<String, Integer>>scoringMoves=new HashMap<>();//only those that increase the score

        //for each card in player hand...
        for (Card card : hand) {
            findSingleCardMoves(arboretum,card,currentScore,allMoves,scoringMoves);
        }

        HashMap[] moveRecords=new HashMap[]{allMoves,scoringMoves};
        return moveRecords;
    }

    private static void findSingleCardMoves(Arbor arboretum, Card card, HashMap<String, Integer> currentScore,HashMap<String,HashMap<String, Integer>>allMoves,HashMap<String,HashMap<String, Integer>>scoringMoves) {
        //find all the positions that this card can be placed...
        for (Position pos : arboretum.getAvailablePos()) {
            arboretum.addCard(card, pos);
            HashMap<String, Integer> possibleScore=arboretum.currentScore();

            String moveCode=card.toString()+pos.toArborString();

            //add new arbor and position to availableChoices
            allMoves.put(moveCode,possibleScore);

            //if the move scores better than current, add to the scoring move list
            HashMap<String, Integer> result = compareScores(currentScore, possibleScore);
            if (result != currentScore) {
                scoringMoves.put(moveCode,possibleScore);
            }

            //clear for next test
            arboretum.clearPosition(pos);
        }
    }

    private static String getHighestScores(HashMap<String,HashMap<String, Integer>>scoringMoves) {
        String highestKey="";
        Integer highestScore=0;
        for(String key:scoringMoves.keySet()){
            if(highestScore==0) highestKey=key;
            Integer currentScore=0;
            HashMap<String,Integer> scoreMap=scoringMoves.get(key);
            for(String species:scoreMap.keySet()){
                currentScore+=scoreMap.get(species);
            }
            if(currentScore>highestScore) {
                highestScore=currentScore;
                highestKey=key;
            }
        }
        return highestKey;
    }

    /**
     * Duplicate of compareScores from Game
     * @param scoreOld
     * @param scoreNew
     * @return
     */
    public static HashMap<String, Integer> compareScores(HashMap<String, Integer> scoreOld, HashMap<String, Integer> scoreNew) {
        if (!scoreOld.isEmpty() && !scoreNew.isEmpty()) {
            String keyOld = Collections.max(scoreOld.entrySet(), Map.Entry.comparingByValue()).getKey();
            String keyNew = Collections.max(scoreNew.entrySet(), Map.Entry.comparingByValue()).getKey();
            if (scoreOld.get(keyOld) < scoreNew.get(keyNew)) {
                return scoreNew;
            } else if (scoreOld.get(keyOld) > scoreNew.get(keyNew)) {
                return scoreOld;
            } else {
                int sumNew = scoreNew.values().stream().mapToInt(Integer::intValue).sum();
                int sumOld = scoreOld.values().stream().mapToInt(Integer::intValue).sum();
                if (sumNew > sumOld) {
                    return scoreNew;
                } else {
                    return scoreOld;
                }
            }
        } else {
            return null;
        }


    }

}