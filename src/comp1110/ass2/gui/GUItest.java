//package comp1110.ass2.gui;
//
//import comp1110.ass2.game.*;
//import javafx.application.Application;
//import javafx.event.EventHandler;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;
//
//import java.io.FileNotFoundException;
//import java.util.*;
//
//public class GUITest extends Application {
//    /* board layout */
//    private static final int BOARD_WIDTH = 1200;
//    private static final int BOARD_HEIGHT = 700;
//    private static final int GRID_SIZE = 50;
//    private static final int GRID_DIMENSION = 10;
//    private static final int WINDOW_XOFFSET = 10;
//    private static final int WINDOW_YOFFSET = 30;
//    private static final int TEXTBOX_WIDTH = 120;
//    private static final int BASE_CARD_WIDTH = 80;
//    private static final int BASE_CARD_HEIGHT = 112;
//
//
//    private static final int SCORE_X = 450;
//    private static final int SCORE_Y = 480;
//    private static final int ARBOR_X = 450;
//    private static final int ARBOR_Y = 480;
//    private static final int leftArborX = 10;
//    private static final int leftArborY = 10;
//    private static final int rightArborX = BOARD_WIDTH - leftArborX - ARBOR_X;
//    private static final int rightArborY = 10;
//    private static final int arborMargin = 5;
//
//    private static final int handBackingWidth = BASE_CARD_WIDTH * 10;
//    private static final int handBackingHeight = (int) Math.floor(BASE_CARD_HEIGHT * 1.2);
//    private static final int handBackingX = (BOARD_WIDTH - handBackingWidth) / 2;
//    private static final int handBackingY = BOARD_HEIGHT - handBackingHeight;
//
//    private static final int CARD_STACK_WIDTH = BASE_CARD_WIDTH + 10;
//    private static final int CARD_STACK_HEIGHT = BASE_CARD_HEIGHT + 10;
//    private static final int deckX = (BOARD_WIDTH - CARD_STACK_WIDTH) / 2;
//    private static final int deckY = 10;
//    private static final int leftDiscardX = 10;
//    private static final int leftDiscardY = (int) Math.floor(BOARD_HEIGHT - BASE_CARD_HEIGHT * 1.2);
//    private static final int rightDiscardX = BOARD_WIDTH - CARD_STACK_WIDTH - 10;
//    private static final int rightDiscardY = (int) Math.floor(BOARD_HEIGHT - BASE_CARD_HEIGHT * 1.2);
//
////    private TextField turnIDTextField;
////    private TextField aArboretumTextField;
////    private TextField bArboretumTextField;
////    private TextField aDiscardTextField;
////    private TextField bDiscardTextField;
////    private TextField deckTextField;
////    private TextField aHandTextField;
////    private TextField bHandTextField;
//
//    //tracking turn parameters
//    private TurnState turnState;
////    public int cardsDrawn;
////    private boolean cardPlayed;
////    private boolean cardDiscarded;
//
//    private Player playerA;
//    private Player playerB;
//    private Deck deck;
//    private String activeTurn;
//    private LinkedList<GUICard> displayedHand;
//
//    public void GUITest() throws FileNotFoundException {
//    }
//
//    private final Group root = new Group();
//    private final Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
//
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        // FIXME Task 11: Implement a basic playable comp1110.ass2.Arboretum game in JavaFX that only allows cards to be placed in
//        //   valid places
//        // FIXME Task 16: Implement a computer opponent so that a human can play your game against the computer.
//        // FIXME Task 18: Implement variant(s).
//        stage.setTitle("comp1110.ass2.Arboretum");
//
//
//        //setup
//        this.deck = new Deck(6);
//        this.playerA = new Player("A", 6);
//        this.playerB = new Player("B", 6);
//        this.deck = new Deck("a3a8c2c7d1d3d7d8m1");
//        this.playerA = new Player("A","Ab3b4c1j1m2m5m8","Ab1C00C00a4N01C00c3C00E01c6N02C00m7N02W01m4N01E01a5N02E01a2S01E01","Aa7c4d6");
//        this.playerB = new Player("B","Ba6b5b6b7b8c8d2j2j8","Bj5C00C00j6S01C00j7S02C00j4C00W01m6C00E01m3C00W02j3N01W01", "Bb2d4c5a1d5");
//
//        for (int i = 0; i < 7; i++) {
//            playerA.draw(deck);
//            playerB.draw(deck);
//        }
//        this.activeTurn = "A";
//        this.turnState=TurnState.end;
//
//        //add arbor, current score, cards, decks to display
//        GUIArbor displayArborA = new GUIArbor(playerA, this, ARBOR_X, ARBOR_Y, leftArborX, leftArborY, arborMargin, false);
//        GUIArbor displayArborB = new GUIArbor(playerB, this, ARBOR_X, ARBOR_Y, rightArborX, rightArborY, arborMargin, true);
//        GUIScore GUIScoreA = new GUIScore(playerA, SCORE_X, SCORE_Y, leftArborX, leftArborY, false);
//        GUIScore GUIScoreB = new GUIScore(playerB, SCORE_X, SCORE_Y, rightArborX, rightArborY, true);
//        Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn));
//        Button playButton = new Button("Next");
//        playButton.setLayoutX(575);
//        playButton.setLayoutY(180);
//        Button drawButton = new Button("Draw from Deck");
//        drawButton.setLayoutX(545);
//        drawButton.setLayoutY(220);
//        root.getChildren().addAll(displayArborA, displayArborB, playButton, drawButton, GUIScoreA, GUIScoreB);
//
//        //prepare backing for hand area, for visual niceness
//        Rectangle handBacking = new Rectangle(handBackingWidth, handBackingHeight);
//        handBacking.setLayoutX(handBackingX);
//        handBacking.setLayoutY(handBackingY);
//        handBacking.setFill(Color.LIGHTGREY);
//        root.getChildren().add(handBacking);
//
//        //add deck & discard display
//        GUICardStack displayDeck = new GUICardStack(this.deck, this, this.root, deckX, deckY);
//        GUICardStack discardA = new GUICardStack(this.playerA.getDiscardPile(), this, this.root, leftDiscardX, leftDiscardY);
//        GUICardStack discardB = new GUICardStack(this.playerB.getDiscardPile(), this, this.root, rightDiscardX, rightDiscardY);
//        root.getChildren().addAll(displayDeck, discardA, discardB);
//
//        stage.setScene(scene);
//        stage.show();
//
//    }
////
////    /**
////     * Contribution: Natasha
////     * Setup to start the turn of a player, including changing GUIArbor display, drawing cards, and showing hand
////     *
////     * @param player - start the turn of this player
////     */
////    private void startTurn(Player player) {
//////        this.activeTurn=player;
////        System.out.println("Starting turn of player "+player.getName());
////        player.getDisplayArbor().startTurn();
////        this.turnState=TurnState.firstDraw;
////
////        this.updateHand(player);
////    }
////
////    /**
////     * Contribution: Natasha
////     * Displays the hand of the current player
////     *
////     * @param player - the player who is currently taking their turn
////     */
////    public void updateHand(Player player) {
//////        remove currently displayed hand
////        if (this.displayedHand != null) {
////            for (GUICard card : this.displayedHand) {
////                root.getChildren().remove(card);
////            }
////        }
////        this.displayedHand = new LinkedList<GUICard>();
////
////        //add the cards currently in the player's hand
////        int i = 0;
////        double spacer = (handBackingWidth - 10) / player.getHand().size();
////        for (Card card : player.getHand()) {
////            GUICard guiCard = new GUICard(card, root, this);
////            guiCard.updateCoord(handBackingX + 5 + i * spacer, handBackingY + (handBackingHeight - BASE_CARD_HEIGHT) / 2);
//////            System.out.println(guiCard.name);
////            root.getChildren().add(guiCard);
////            this.displayedHand.add(guiCard);
////            guiCard.toFront();
////            i++;
////        }
////    }
////
////    /**
////     * Contribution: Natasha
////     *
////     * @return the Player object that is currently taking their turn
////     */
////    public Player getActivePlayer() {
////        if (this.activeTurn.equals("A")) return playerB;
////        else if (this.activeTurn.equals("B")) return playerA;
////        else return null;
////    }
////
////    public List<GUICard> getDisplayHand() {
////        return this.displayedHand;
////    }
////
////    private Player getWinner() {
////        return null;
////    }
////
////    private boolean isGameEnd() {
////        return false;
////    }
////
//    private String[][] generateGameState(Player playerA, Player playerB, Deck deck, String turn) {
//
//
//        //put all gameState to tree strings
////        String nextTurn = turn; //sharedState[0];
//        String arboretumA = playerA.getArboretumString();//sharedState[1];
////        System.out.println("arboretumA: " + arboretumA);
//        String discardA = playerA.getDiscardPileString();//sharedState[2];
////        System.out.println("discardA: " + discardA);
//        String arboretumB = playerB.getArboretumString();//sharedState[3];
////        System.out.println("arboretumB" + arboretumB);
//        String discardB = playerB.getDiscardPileString();//sharedState[4];
////        System.out.println("discardB" + discardB);
////        String deck = deck.toString()hiddenState[0];
//        String handA = playerA.getHandString();//hiddenState[1];
//        String handB = playerB.getHandString();//hiddenState[2];
//
//        String[] sharedState = {turn, arboretumA, discardA, arboretumB, discardB};
//        String[] hiddenState = {deck.getDeckString(), handA, handB};
//
//        String[][] gameState = {sharedState, hiddenState};
//
//        return gameState;
//    }
////
////    private void AIDraw(Player player_turn, Player player_notTurn) {
////        List<CardStack> availableChoices = new ArrayList<>();
////        HashMap<String, Integer> currentScore = player_turn.getArboretum().currentScore();
////        DiscardPile player_turn_discard = new DiscardPile(player_turn.getName() + player_turn.getDiscardPile().toString());
////        DiscardPile player_notTurn_discard = new DiscardPile(player_notTurn.getName() + player_notTurn.getDiscardPile().toString());
//////        System.out.println(player_turn_discard);
//////        System.out.println(player_notTurn_discard);
////        availableChoices.add(player_turn_discard);
////        availableChoices.add(player_notTurn_discard);
////        HashMap<Player,List<CardStack>> availablePlayers = new HashMap<>();
////        for (CardStack stack : availableChoices) {
////            for (CardStack stack2 : availableChoices) {
////                if (!stack.isEmpty() && !stack2.isEmpty()) {
////                    List<CardStack> valueList = new ArrayList<>();
////                    valueList.add(stack);
////                    valueList.add(stack2);
////                    availablePlayers.put(new Player(player_turn.getName(), player_turn.getHandString() + stack.drawTopCard().toString() + stack2.drawTopCard().toString(), player_turn.getArboretumString(), player_turn.getDiscardPileString()),valueList);
////                }
////
////            }
////        }
////        System.out.println(availablePlayers);
////        HashMap<List<CardStack>,Integer> availablePlayers2 = new HashMap<>();
////        for (Player player:availablePlayers.keySet()) {
////            Arbor playerArbor = AIMove(player);
////            if (playerArbor != null){
////                int valueBiggest = 0;
////                HashMap<String, Integer> playerScore = playerArbor.currentScore();
////                if (playerScore != null){
////                    if (compareScores(currentScore,playerScore) == playerScore){
////                        String keyBiggest = Collections.max(playerScore.entrySet(), Map.Entry.comparingByValue()).getKey();
////                        if (playerScore.get(keyBiggest) > valueBiggest) {
////                            valueBiggest = playerScore.get(keyBiggest);
////                            availablePlayers2.put(availablePlayers.get(player),valueBiggest);
////                        }
////
////                    }
////                }
////            }
////        }
////        if (!availablePlayers2.isEmpty()){
////            List<CardStack> resultList = Collections.max(availablePlayers2.entrySet(), Map.Entry.comparingByValue()).getKey();
////            for (CardStack discard: resultList){
////                player_turn.draw(discard);
////            }
////            updateHand(player_turn);
////        }else {
////            player_turn.draw(this.deck);
////            player_turn.draw(this.deck);
////            updateHand(player_turn);
////        }
////    }
////
////
////    private void AIDiscard(){
////
////    }
////
////    private Arbor AIMove(Player player) {
////
////        if (player.getName().equals("A")) {
////            HashMap<Arbor, Position> availableChoices = new LinkedHashMap<>();
////            Arbor arboretum_A = player.getArboretum();
////            HashMap<String, Integer> currentScore = arboretum_A.currentScore();
//////            System.out.println(currentScore);
////            List<Card> availableCards = player.getHand();
//////            System.out.println(player.getArboretum().getAvailablePos());
////            for (Card card : availableCards) {
//////                System.out.println("card: " + card );
////                for (Position pos : player.getArboretum().getAvailablePos()) {
//////                    System.out.println("pos: " +  pos);
////                    Arbor newArbor = new Arbor(this.playerA.getName() + this.playerA.getArboretum().toString());
//////                    System.out.println(this.playerA.getName() + this.playerA.getArboretum().toString());
//////                    System.out.println("old: "+ newArbor.arboretumList);
////                    newArbor.addCard(card, pos);
//////                    System.out.println("new: "+ newArbor.arboretumList);
////                    availableChoices.put(newArbor, pos);
////
////                }
////            }
//////            System.out.println("availableChoices: " + availableChoices);
////            HashMap<Arbor, HashMap<String, Integer>> availableResults = new LinkedHashMap<>();
////            for (Arbor key : availableChoices.keySet()) {
//////                System.out.println("compare "+ currentScore + " and " + key.currentScore());
////                HashMap<String, Integer> result = compareScores(currentScore, key.currentScore());
////                if (result != currentScore) {
////                    availableResults.put(key, result);
//////                    System.out.println("result: " + result);
////                }
////
////            }
//////            System.out.println("availableResults: " + availableResults);
////            HashMap<Arbor, Integer> availableResults2 = new LinkedHashMap<>();
////            int valueBiggest = 0;
////            for (Arbor key : availableResults.keySet()) {
////                HashMap<String, Integer> value = availableResults.get(key);
////                if (value != null) {
////                    String keyBiggest = Collections.max(value.entrySet(), Map.Entry.comparingByValue()).getKey();
////                    if (value.get(keyBiggest) > valueBiggest) {
////                        valueBiggest = value.get(keyBiggest);
////                        availableResults2.put(key, valueBiggest);
////                    }
////                }
////            }
////            if (!availableResults2.isEmpty()) {
//////                System.out.println("availableResults2: " + availableResults2);
////                Arbor result = Collections.max(availableResults2.entrySet(), Map.Entry.comparingByValue()).getKey();
////                System.out.println("AI Move is: " + result);
////                return result;
////            } else {
////                if (!availableResults.isEmpty()) {
//////                    System.out.println("availableResults1: " + availableResults);
////                    List<Arbor> keysAsArray = new ArrayList<Arbor>(availableResults.keySet());
////                    Random rand = new Random();
////                    Arbor result = keysAsArray.get(rand.nextInt(keysAsArray.size()));
////                    System.out.println("AI Move is: " + result);
////                    return result;
////                } else {
//////                    System.out.println("availableChoices: " + availableChoices);
////                    List<Arbor> keysAsArray = new ArrayList<Arbor>(availableChoices.keySet());
////                    Random rand = new Random();
////                    Arbor result = keysAsArray.get(rand.nextInt(keysAsArray.size()));
////                    System.out.println("AI Move is: " + result);
////                    return result;
////                }
////
////
////            }
////
////
////        }
////        if (player.getName().equals("B")) {
////            HashMap<Arbor, Position> availableChoices = new LinkedHashMap<>();
////            Arbor arboretum_B = player.getArboretum();
////            HashMap<String, Integer> currentScore = arboretum_B.currentScore();
//////            System.out.println(currentScore);
////            List<Card> availableCards = player.getHand();
//////            System.out.println(player.getArboretum().getAvailablePos());
////            for (Card card : availableCards) {
//////                System.out.println("card: " + card );
////                for (Position pos : player.getArboretum().getAvailablePos()) {
//////                    System.out.println("pos: " +  pos);
////                    Arbor newArbor = new Arbor(this.playerB.getName() + this.playerB.getArboretum().toString());
//////                    System.out.println(this.playerA.getName() + this.playerA.getArboretum().toString());
//////                    System.out.println("old: "+ newArbor.arboretumList);
////                    newArbor.addCard(card, pos);
//////                    System.out.println("new: "+ newArbor.arboretumList);
////                    availableChoices.put(newArbor, pos);
////
////                }
////            }
//////            System.out.println("availableChoices: " + availableChoices);
////            HashMap<Arbor, HashMap<String, Integer>> availableResults = new LinkedHashMap<>();
////            for (Arbor key : availableChoices.keySet()) {
//////                System.out.println("compare "+ currentScore + " and " + key.currentScore());
////                HashMap<String, Integer> result = compareScores(currentScore, key.currentScore());
////                if (result != currentScore) {
////                    availableResults.put(key, result);
//////                    System.out.println("result: " + result);
////                }
////
////            }
//////            System.out.println("availableResults: " + availableResults);
////            HashMap<Arbor, Integer> availableResults2 = new LinkedHashMap<>();
////            int valueBiggest = 0;
////            for (Arbor key : availableResults.keySet()) {
////                HashMap<String, Integer> value = availableResults.get(key);
////                if (value != null) {
////                    String keyBiggest = Collections.max(value.entrySet(), Map.Entry.comparingByValue()).getKey();
////                    if (value.get(keyBiggest) > valueBiggest) {
////                        valueBiggest = value.get(keyBiggest);
////                        availableResults2.put(key, valueBiggest);
////                    }
////                }
////            }
////            if (!availableResults2.isEmpty()) {
//////                System.out.println("availableResults2: " + availableResults2);
////                Arbor result = Collections.max(availableResults2.entrySet(), Map.Entry.comparingByValue()).getKey();
////                System.out.println("AI Move is: " + result);
////                return result;
////            } else {
////                if (!availableResults.isEmpty()) {
//////                    System.out.println("availableResults1: " + availableResults);
////                    List<Arbor> keysAsArray = new ArrayList<Arbor>(availableResults.keySet());
////                    Random rand = new Random();
////                    Arbor result = keysAsArray.get(rand.nextInt(keysAsArray.size()));
////                    System.out.println("AI Move is: " + result);
////                    return result;
////                } else {
//////                    System.out.println("availableChoices: " + availableChoices);
////                    List<Arbor> keysAsArray = new ArrayList<Arbor>(availableChoices.keySet());
////                    Random rand = new Random();
////                    Arbor result = keysAsArray.get(rand.nextInt(keysAsArray.size()));
////                    System.out.println("AI Move is: " + result);
////                    return result;
////                }
////
////            }
////
////        }
////
////        return null;
////    }
////
////    private HashMap<String, Integer> compareScores(HashMap<String, Integer> scoreOld, HashMap<String, Integer> scoreNew) {
////        if (!scoreOld.isEmpty() && !scoreNew.isEmpty()) {
////            String keyOld = Collections.max(scoreOld.entrySet(), Map.Entry.comparingByValue()).getKey();
////            String keyNew = Collections.max(scoreNew.entrySet(), Map.Entry.comparingByValue()).getKey();
////            if (scoreOld.get(keyOld) < scoreNew.get(keyNew)) {
////                return scoreNew;
////            } else if (scoreOld.get(keyOld) > scoreNew.get(keyNew)) {
////                return scoreOld;
////            } else {
////                int sumNew = scoreNew.values().stream().mapToInt(Integer::intValue).sum();
////                int sumOld = scoreOld.values().stream().mapToInt(Integer::intValue).sum();
////                if (sumNew > sumOld) {
////                    return scoreNew;
////                } else {
////                    return scoreOld;
////                }
////            }
////        } else {
////            return null;
////        }
////
////
////    }
////
////    /**
////     * Contribution: Natasha
////     * update trackers for whether cards have been drawn, played, or discarded
////     * @return True if a new action was recorded, False if this action has previously been recorded this turn or requires other actions to be performed first
////     */
////    public boolean trackDraw() {
////        if(this.turnState==TurnState.firstDraw) {
////            this.turnState=TurnState.secondDraw;
////            return true;
////        } else if (this.turnState==TurnState.secondDraw) {
////            this.turnState=TurnState.play;
////            return true;
////        }
////        else return false;
////    }
////    public boolean trackCardPlayed() {
////        if(this.turnState==TurnState.play) {
////            this.turnState=TurnState.discard;
////            return true;
////        }
////        return false;
////    }
////    public boolean trackCardDiscarded() {
////        if(this.turnState==TurnState.discard) {
////            this.turnState=TurnState.end;
////            return true;
////        }
////        return false;
////    }
////
////    /**
////     * Contribution: Natasha
////     * Check if the turn is in the relevant phase for a specific action
////     * @return True if the turn is in this phase, False otherwise
////     */
////    public boolean waitingForDraw() {return this.turnState==TurnState.firstDraw||this.turnState==TurnState.secondDraw;}
////    public boolean waitingForDiscard() {
////        return this.turnState==TurnState.discard;
////    }
////    public boolean waitingForPlay() {
////        return this.turnState==TurnState.play;
////    }
////
////    //TODO - fix GUICard implementation so this isn't needed
////    public Group getRoot() {return this.root;}
//}
//
