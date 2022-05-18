package comp1110.ass2.gui;

import comp1110.ass2.game.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Thread.sleep;

public class Game extends Application {
    /* board layout */
    private static final int BOARD_WIDTH = 1200;
    private static final int BOARD_HEIGHT = 700;
    private static final int GRID_SIZE = 50;
    private static final int GRID_DIMENSION = 10;
    private static final int WINDOW_XOFFSET = 10;
    private static final int WINDOW_YOFFSET = 30;
    private static final int TEXTBOX_WIDTH = 120;
    private static final int BASE_CARD_WIDTH = 80;
    private static final int BASE_CARD_HEIGHT = 112;


    private static final int SCORE_X = 450;
    private static final int SCORE_Y = 480;
    private static final int ARBOR_X = 450;
    private static final int ARBOR_Y = 480;
    private static final int leftArborX = 10;
    private static final int leftArborY = 10;
    private static final int rightArborX = BOARD_WIDTH - leftArborX - ARBOR_X;
    private static final int rightArborY = 10;
    private static final int arborMargin = 5;

    private static final int handBackingWidth = BASE_CARD_WIDTH * 10;
    private static final int handBackingHeight = (int) Math.floor(BASE_CARD_HEIGHT * 1.2);
    private static final int handBackingX = (BOARD_WIDTH - handBackingWidth) / 2;
    private static final int handBackingY = BOARD_HEIGHT - handBackingHeight;

    private static final int CARD_STACK_WIDTH = BASE_CARD_WIDTH + 10;
    private static final int CARD_STACK_HEIGHT = BASE_CARD_HEIGHT + 10;
    private static final int deckX = (BOARD_WIDTH - CARD_STACK_WIDTH) / 2;
    private static final int deckY = 10;
    private static final int leftDiscardX = 10;
    private static final int leftDiscardY = (int) Math.floor(BOARD_HEIGHT - BASE_CARD_HEIGHT * 1.2);
    private static final int rightDiscardX = BOARD_WIDTH - CARD_STACK_WIDTH - 10;
    private static final int rightDiscardY = (int) Math.floor(BOARD_HEIGHT - BASE_CARD_HEIGHT * 1.2);

//    private TextField turnIDTextField;
//    private TextField aArboretumTextField;
//    private TextField bArboretumTextField;
//    private TextField aDiscardTextField;
//    private TextField bDiscardTextField;
//    private TextField deckTextField;
//    private TextField aHandTextField;
//    private TextField bHandTextField;

    //game turn parameters
    private TurnState turnState;
    private Player playerA;
    private Player playerB;
    private Deck deck;
    private String activeTurn;
    private boolean gameEnd;

    //display elements
    private LinkedList<GUICard> displayedHand;
    private GUIArbor displayArborA;
    private GUIArbor displayArborB;
    private GUICardStack displayDeck;
    private GUICardStack displayDiscardA;
    private GUICardStack displayDiscardB;


    public Game() throws FileNotFoundException {
    }

    private final Group root = new Group();
    private final Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);


    @Override
    public void start(Stage stage) throws Exception {
        // FIXME Task 11: Implement a basic playable comp1110.ass2.Arboretum game in JavaFX that only allows cards to be placed in
        //   valid places
        // FIXME Task 16: Implement a computer opponent so that a human can play your game against the computer.
        // FIXME Task 18: Implement variant(s).
        stage.setTitle("comp1110.ass2.Arboretum");


//        //setup
        this.deck = new Deck(6);
        this.playerA = new Player("A", 6);
        this.playerB = new Player("B", 6);

        for (int i = 0; i < 7; i++) {
            playerA.draw(deck);
            playerB.draw(deck);
        }
        this.activeTurn = "A";
        this.turnState = TurnState.end;

        //add arbor, current score, cards, decks to display
        this.displayArborA = new GUIArbor(playerA, this, ARBOR_X, ARBOR_Y, leftArborX, leftArborY, arborMargin, false);
        this.displayArborB = new GUIArbor(playerB, this, ARBOR_X, ARBOR_Y, rightArborX, rightArborY, arborMargin, true);
        GUIScore GUIScoreA = new GUIScore(playerA, SCORE_X, SCORE_Y, leftArborX, leftArborY, false);
        GUIScore GUIScoreB = new GUIScore(playerB, SCORE_X, SCORE_Y, rightArborX, rightArborY, true);
        Button playButton = new Button("Next");
        playButton.setLayoutX(575);
        playButton.setLayoutY(180);
        root.getChildren().addAll(displayArborA, displayArborB, playButton, GUIScoreA, GUIScoreB);

        //displayArborA.updateFromBackend();
        //displayArborB.updateFromBackend();

        //prepare backing for hand area, for visual niceness
        Rectangle handBacking = new Rectangle(handBackingWidth, handBackingHeight);
        handBacking.setLayoutX(handBackingX);
        handBacking.setLayoutY(handBackingY);
        handBacking.setFill(Color.LIGHTGREY);
        root.getChildren().add(handBacking);

        //add deck & discard display
        this.displayDeck = new GUICardStack(this.deck, this, this.root, deckX, deckY);
        this.displayDiscardA = new GUICardStack(this.playerA.getDiscardPile(), this, this.root, leftDiscardX, leftDiscardY);
        this.displayDiscardB = new GUICardStack(this.playerB.getDiscardPile(), this, this.root, rightDiscardX, rightDiscardY);
        root.getChildren().addAll(displayDeck, displayDiscardA, displayDiscardB);

        //player settings
        boolean playerAHuman = false;
        boolean playerBHuman = false;

        //await game start on button press
        playButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                //if the game should end, don't progress - instead show game end screen
                if (gameEnd) {
                    //displayGameEnd();
                    return;
                }

                if (turnState == TurnState.end) {
                    turnState = TurnState.begin;

                    if (activeTurn.equals("A")) {
                        playerB.getDisplayArbor().endTurn();
                        startTurn(playerA, playerAHuman);
                        System.out.println("End B's turn, start A's turn");
                        System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));
                        //run AI if this player is computer controlled
                        if (!playerAHuman) {
                            //move logic
                            AIDraw(playerA, playerB);
                            System.out.println("AI draw finish");
                            System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));
                            AIMove(playerA);
                            displayArborA.updateFromBackend();
                            System.out.println("AI Move finish");
                            System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));
                            AIDiscard(playerA, playerB);
                            updateDisplayHand(playerA);
                            System.out.println("AI Discard finish");
                            System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));
                            System.out.println("End A's turn, start B's turn");
                            //update display
                            displayDiscardA.updateTopCard();
                        }
                        activeTurn = "B";
                        // update score text
                        root.getChildren().remove(GUIScoreB);
                        GUIScoreB.update();
                        root.getChildren().add(GUIScoreB);

                    } else if (activeTurn.equals("B")) {
                        playerA.getDisplayArbor().endTurn();
                        startTurn(playerB, playerBHuman);
                        System.out.println("End A's turn, start B's turn");
                        System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));
                        //run AI if this player is computer controlled
                        if (!playerBHuman) {
                            //move logic
                            AIDraw(playerB, playerA);
                            System.out.println("AI draw finish");
                            System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));
                            AIMove(playerB);
                            displayArborB.updateFromBackend();
                            System.out.println("AI Move finish");
                            System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));
                            AIDiscard(playerB, playerA);
                            updateDisplayHand(playerB);
                            System.out.println("AI Discard finish");
                            System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));

                            //update display
                            displayDiscardB.updateTopCard();
                        }
                        activeTurn = "A";
                        // update score text
                        root.getChildren().remove(GUIScoreA);
                        GUIScoreA.update();
                        root.getChildren().add(GUIScoreA);
                    }
                }

            }
        }));
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Contribution: Natasha
     * Setup to start the turn of a player, including changing GUIArbor display, drawing cards, and showing hand
     *
     * @param player - start the turn of this player
     */
    private void startTurn(Player player, boolean human) {
        System.out.println("Starting turn of player " + player.getName());
        player.getDisplayArbor().startTurn();
        this.turnState = TurnState.firstDraw;

        //only change displayed hand if it's a human player
        if (human) {
            this.updateDisplayHand(player);
        }
    }

    /**
     * Contribution: Natasha
     * Displays the hand of the current player
     *
     * @param player - the player who is currently taking their turn
     */
    public void updateDisplayHand(Player player) {
//        remove currently displayed hand
        if (this.displayedHand != null) {
            for (GUICard card : this.displayedHand) {
                root.getChildren().remove(card);
            }
        }
        this.displayedHand = new LinkedList<GUICard>();

        //add the cards currently in the player's hand
        int i = 0;
        double spacer = (handBackingWidth - 10) / player.getHand().size();
        for (Card card : player.getHand()) {
            GUICard guiCard = new GUICard(card, root, this);
            guiCard.updateCoord(handBackingX + 5 + i * spacer, handBackingY + (handBackingHeight - BASE_CARD_HEIGHT) / 2);
//            System.out.println(guiCard.name);
            root.getChildren().add(guiCard);
            this.displayedHand.add(guiCard);
            guiCard.toFront();
            i++;
        }
    }

    /**
     * Contribution: Natasha
     *
     * @return the Player object that is currently taking their turn
     */
    public Player getActivePlayer() {
        if (this.activeTurn.equals("A")) return playerB;
        else if (this.activeTurn.equals("B")) return playerA;
        else return null;
    }

    public List<GUICard> getDisplayHand() {
        return this.displayedHand;
    }

    private Player getWinner() {
        return null;
    }

    /**
     * Contribution: Natasha
     * Checks if the deck is empty, meaning the game is over
     *
     * @return True if the game should end, False otherwise
     */
    private boolean isGameEnd() {
        this.gameEnd = this.deck.isEmpty();
        return this.gameEnd;
    }

    private String[][] generateGameState(Player playerA, Player playerB, Deck deck, String turn) {
        String arboretumA = playerA.getArboretumString();
        String discardA = playerA.getDiscardPileString();
        String arboretumB = playerB.getArboretumString();
        String discardB = playerB.getDiscardPileString();
        String handA = playerA.getHandString();
        String handB = playerB.getHandString();

        String[] sharedState = {turn, arboretumA, discardA, arboretumB, discardB};
        String[] hiddenState = {deck.getDeckString(), handA, handB};

        String[][] gameState = {sharedState, hiddenState};

        return gameState;
    }

    private void AIDraw(Player player_turn, Player player_notTurn) {
        System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));
        List<CardStack> availableChoices = new ArrayList<>();
        HashMap<String, Integer> currentScore = player_turn.getArboretum().currentScore();
        DiscardPile player_turn_discard = new DiscardPile(player_turn.getName() + player_turn.getDiscardPile().toString());
        DiscardPile player_notTurn_discard = new DiscardPile(player_notTurn.getName() + player_notTurn.getDiscardPile().toString());
        HashMap<Player, List<CardStack>> availablePlayers = new HashMap<>();
        HashMap<List<CardStack>, Integer> availablePlayers2 = new HashMap<>();
        if (!player_turn_discard.isEmpty() && !player_notTurn_discard.isEmpty()) {
            availableChoices.add(player_turn_discard);
            availableChoices.add(player_notTurn_discard);
            for (CardStack stack : availableChoices) {
                for (CardStack stack2 : availableChoices) {
                    if (!stack.isEmpty() && !stack2.isEmpty()) {
                        List<CardStack> valueList = new ArrayList<>();
                        Card drawStack1 = stack.drawTopCard();
                        Card drawStack2 = stack2.drawTopCard();
                        if (drawStack1 != null && drawStack2 != null) {
                            valueList.add(stack);
                            valueList.add(stack2);
                            availablePlayers.put(new Player(player_turn.getName(), player_turn.getHandString() + drawStack1.toString() + drawStack2.toString(), player_turn.getArboretumString(), player_turn.getDiscardPileString()), valueList);
                        }
                        stack.addTopCard(drawStack2);
                        stack2.addTopCard(drawStack1);
                    }
                }
            }
            for (Player player : availablePlayers.keySet()) {
                Arbor playerArbor = AIMove(player);
                if (playerArbor != null) {
                    int valueBiggest = 0;
                    HashMap<String, Integer> playerScore = playerArbor.currentScore();
                    if (playerScore != null) {
                        if (compareScores(currentScore, playerScore) == playerScore) {
                            String keyBiggest = Collections.max(playerScore.entrySet(), Map.Entry.comparingByValue()).getKey();
                            if (playerScore.get(keyBiggest) > valueBiggest) {
                                valueBiggest = playerScore.get(keyBiggest);
                                availablePlayers2.put(availablePlayers.get(player), valueBiggest);
                            }
                        }
                    }
                }
            }
        }
        if (!availablePlayers2.isEmpty()) {
            List<CardStack> resultList = Collections.max(availablePlayers2.entrySet(), Map.Entry.comparingByValue()).getKey();
            for (CardStack discard : resultList) {
                if (discard.toString().equals(playerA.getDiscardPile().toString())) {
                    System.out.println("ai draw from playerA: " + discard);
                    player_turn.draw(playerA.getDiscardPile());
                    discard.drawTopCard();
                }
                if (discard.toString().equals(playerB.getDiscardPile().toString())) {
                    System.out.println("ai draw from playerB: " + discard);
                    player_turn.draw(playerB.getDiscardPile());
                    discard.drawTopCard();
                }
            }
            this.turnState = TurnState.play;
        } else {
            System.out.println("ai draw from: deck");
            player_turn.draw(this.deck);
            System.out.println("ai draw from: deck");
            player_turn.draw(this.deck);
            this.turnState = TurnState.play;
        }
//        System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));
    }


    private void AIDiscard(Player player_turn, Player player_notTurn) {
//        System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));
        List<Card> availableChoices = new ArrayList<>();
        List<Card> player_turn_arbor_cards = player_turn.getArboretum().getArboretumCardList();
        List<Card> player_notTurn_arbor_cards = player_notTurn.getArboretum().getArboretumCardList();
        for (Card card : player_turn.getHand()) {
            if (!player_turn_arbor_cards.contains(card) && player_notTurn_arbor_cards.contains(card)) {
                availableChoices.add(card);
            }
        }
        Random rand = new Random();
        Card result;
        if (!availableChoices.isEmpty()) {
            result = availableChoices.get(rand.nextInt(availableChoices.size()));

        } else {
            result = player_turn.getHand().get(rand.nextInt(player_turn.getHand().size()));
        }
        if (player_turn.getName().equals("A")) {
            System.out.println("PlayerA discard: " + result);
            playerA.discard(result);
            this.turnState = TurnState.end;
        }
        if (player_turn.getName().equals("B")) {
            System.out.println("PlayerB discard: " + result);
            playerB.discard(result);
            this.turnState = TurnState.end;
        }

    }

    public Arbor AIMove(Player player) {
        HashMap<Arbor, Position> availableChoices = new LinkedHashMap<>();
        HashMap<String, Integer> currentScore = new HashMap<>();
        List<Card> availableCards = player.getHand();
        if (player.getName().equals("A")) {
            Arbor arboretum_A = player.getArboretum();
            currentScore = arboretum_A.currentScore();
            for (Card card : availableCards) {
                for (Position pos : player.getArboretum().getAvailablePos()) {
                    Arbor newArbor = new Arbor(player.getName() + arboretum_A.toString());
                    newArbor.addCard(card, pos);
                    availableChoices.put(newArbor, pos);
                }
            }
        } else if (player.getName().equals("B")) {
            Arbor arboretum_B = player.getArboretum();
            currentScore = arboretum_B.currentScore();
            for (Card card : availableCards) {
                for (Position pos : player.getArboretum().getAvailablePos()) {
                    Arbor newArbor = new Arbor(player.getName() + arboretum_B.toString());
                    newArbor.addCard(card, pos);
                    availableChoices.put(newArbor, pos);
                }
            }
        }
        HashMap<Arbor, HashMap<String, Integer>> availableResults = new LinkedHashMap<>();
        for (Arbor key : availableChoices.keySet()) {
            HashMap<String, Integer> result = compareScores(currentScore, key.currentScore());
            if (result != currentScore) {
                availableResults.put(key, result);
            }
        }
        HashMap<Arbor, Integer> availableResults2 = new LinkedHashMap<>();
        int valueBiggest = 0;
        for (Arbor key : availableResults.keySet()) {
            HashMap<String, Integer> value = availableResults.get(key);
            if (value != null) {
                String keyBiggest = Collections.max(value.entrySet(), Map.Entry.comparingByValue()).getKey();
                if (value.get(keyBiggest) > valueBiggest) {
                    valueBiggest = value.get(keyBiggest);
                    availableResults2.put(key, valueBiggest);
                }
            }
        }
        if (!availableResults2.isEmpty()) {
            Arbor result = Collections.max(availableResults2.entrySet(), Map.Entry.comparingByValue()).getKey();
            System.out.println("AI Move R2 is: " + result);
            Pair<Card, Position> pair = findCardToPlay(result);
            player.play(pair.getKey(), pair.getValue());
            this.turnState = TurnState.discard;
            return result;
        } else {
            if (!availableResults.isEmpty()) {
                List<Arbor> keysAsArray = new ArrayList<>(availableResults.keySet());
                Random rand = new Random();
                Arbor result = keysAsArray.get(rand.nextInt(keysAsArray.size()));
                System.out.println("AI Move R1 is: " + result);
                Pair<Card, Position> pair = findCardToPlay(result);
                player.play(pair.getKey(), pair.getValue());
                this.turnState = TurnState.discard;
                return result;
            } else {
                List<Arbor> keysAsArray = new ArrayList<Arbor>(availableChoices.keySet());
                Random rand = new Random();
                Arbor result = keysAsArray.get(rand.nextInt(keysAsArray.size()));
                System.out.println("AI Move R0 is: " + result);
                Pair<Card, Position> pair = findCardToPlay(result);
                player.play(pair.getKey(), pair.getValue());
                this.turnState = TurnState.discard;
                return result;
            }
        }
    }

    private Pair<Card, Position> findCardToPlay(Arbor arbor) {
        if (arbor != null) {
            String cardAndPos = arbor.toString().substring(arbor.toString().length() - 8);
            String card = cardAndPos.substring(0, 2);
            String pos = cardAndPos.substring(2, 8);
            return new Pair<>(new Card(card), new Position(pos));

        }
        return null;
    }

    private HashMap<String, Integer> compareScores(HashMap<String, Integer> scoreOld, HashMap<String, Integer> scoreNew) {
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

    /**
     * Contribution: Natasha
     * update trackers for whether cards have been drawn, played, or discarded
     *
     * @return True if a new action was recorded, False if this action has previously been recorded this turn or requires other actions to be performed first
     */
    public boolean trackDraw() {
        if (this.turnState == TurnState.firstDraw) {
            this.turnState = TurnState.secondDraw;
            return true;
        } else if (this.turnState == TurnState.secondDraw) {
            this.turnState = TurnState.play;
            return true;
        } else return false;
    }

    public boolean trackCardPlayed() {
        if (this.turnState == TurnState.play) {
            this.turnState = TurnState.discard;
            return true;
        }
        return false;
    }

    public boolean trackCardDiscarded() {
        if (this.turnState == TurnState.discard) {
            this.turnState = TurnState.end;
            this.isGameEnd();
            return true;
        }
        return false;
    }

    /**
     * Contribution: Natasha
     * Check if the turn is in the relevant phase for a specific action
     *
     * @return True if the turn is in this phase, False otherwise
     */
    public boolean waitingForDraw() {
        return this.turnState == TurnState.firstDraw || this.turnState == TurnState.secondDraw;
    }

    public boolean waitingForDiscard() {
        return this.turnState == TurnState.discard;
    }

    public boolean waitingForPlay() {
        return this.turnState == TurnState.play;
    }

    //TODO - fix GUICard implementation so this isn't needed
    public Group getRoot() {
        return this.root;
    }
}
