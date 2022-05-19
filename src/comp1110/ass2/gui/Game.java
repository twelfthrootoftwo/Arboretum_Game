package comp1110.ass2.gui;

import comp1110.ass2.game.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
    private static final int HINT_X = 20;
    private static final int HINT_Y = 20;
    private static final int ARBOR_X = 450;
    private static final int ARBOR_Y = 480;
    private static final int hintx = 480;
    private static final int hinty = 380;
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
    private GUIHint guiHint;
    private Button playButton;
    private GUIEnding endScreen;

    private boolean playerAHuman = true;
    private boolean playerBHuman = false;


    public Game() throws FileNotFoundException {
    }

    private  Group root = new Group();
    private  Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

    public void displayApplication() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    root = new Group();
                    scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
                    gameEnd = false;
                    start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        // FIXME Task 11: Implement a basic playable comp1110.ass2.Arboretum game in JavaFX that only allows cards to be placed in
        //   valid places
        // FIXME Task 16: Implement a computer opponent so that a human can play your game against the computer.
        // FIXME Task 18: Implement variant(s).
        stage.setTitle("comp1110.ass2.Arboretum");
        Platform.setImplicitExit(false);

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
        this.guiHint = new GUIHint(this, HINT_X, HINT_Y, hintx, hinty);
        String font_name = Font.getFamilies().get(25);
        int size = 30;
        Font font = Font.font(font_name, FontWeight.BOLD, FontPosture.REGULAR, size);
        playButton = new Button("Next");
        playButton.setFont(font);
        playButton.setLayoutX(555);
        playButton.setLayoutY(180);
        playButton.setPadding(new Insets(8, 15, 15, 15));
        playButton.setStyle("-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;" +
                "-fx-background-radius: 8; " +
                "-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),#9d4024,#d86e3a,radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);");

        scene.setFill(new LinearGradient(
                0, 0, 1, 1, true,                      //sizing
                CycleMethod.NO_CYCLE,                  //cycling
                new Stop(0, Color.web("#EEEEEE")),     //colors
                new Stop(1, Color.web("#FCFCFC")))
        );
        root.getChildren().addAll(guiHint, displayArborA, displayArborB, playButton, GUIScoreA, GUIScoreB);

        //displayArborA.updateFromBackend();
        //displayArborB.updateFromBackend();

        //prepare backing for hand area, for visual niceness
        Rectangle handBacking = new Rectangle(handBackingWidth, handBackingHeight);
        handBacking.setLayoutX(handBackingX);
        handBacking.setLayoutY(handBackingY);
        handBacking.setStyle("-fx-stroke: #898787; -fx-stroke-width: 5;");
        handBacking.setFill(new LinearGradient(
                0, 0, 1, 1, true,                      //sizing
                CycleMethod.NO_CYCLE,                  //cycling
                new Stop(0, Color.web("#99EAFA")),     //colors
                new Stop(1, Color.web("#A6ECFA")))
        );
        root.getChildren().add(handBacking);

        //add deck & discard display
        this.displayDeck = new GUICardStack(this.deck, this, this.root, deckX, deckY);
        this.displayDiscardA = new GUICardStack(this.playerA.getDiscardPile(), this, this.root, leftDiscardX, leftDiscardY);
        this.displayDiscardB = new GUICardStack(this.playerB.getDiscardPile(), this, this.root, rightDiscardX, rightDiscardY);
        root.getChildren().addAll(displayDeck, displayDiscardA, displayDiscardB);
        GUIStart startScreen = new GUIStart(this, 700, 700, 250, 0);
        startScreen.toFront();
        //player settings
        root.getChildren().add(startScreen);

        //await game start on button press
        playButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                //if the game should end, don't progress - instead show game end screen
                if (gameEnd) {
                    System.out.println("---------------GAME OVER---------------");
                    displayGameEnd();
                    return;
                }
                if (turnState == TurnState.end) {
                    playButton.setDisable(true);
                }
                //TurnState.end to achieve loop and state control
                if (turnState == TurnState.end) {

                    turnState = TurnState.begin;
                    guiHintUpdate();
                    //player A's turn
                    if (activeTurn.equals("A")) {

                        playerB.getDisplayArbor().endTurn();
                        startTurn(playerA, playerAHuman);
                        System.out.println("End B's turn, start A's turn");
                        System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));
                        updateDisplayHand(playerA);
                        //run AI if this player is computer controlled
                        if (!playerAHuman) {
                            //AI draw cards
                            AIDraw(playerA, playerB);
                            //update discard GUI In case AI draw from player's discard
                            updateDisplayHand(playerA);
                            displayDiscardA.updateTopCard();
                            displayDiscardB.updateTopCard();
                            displayDeck.updateTopCard();
                            System.out.println("AI draw finish");
                            //AI make a move
                            AIMove(playerA);
                            //update Arbor GUI
                            displayArborA.updateFromBackend();
                            updateDisplayHand(playerA);
                            System.out.println("AI Move finish");
                            System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));
                            //AI discard a card
                            AIDiscard(playerA, playerB);
                            //update hand and discard GUI after discard
                            //updateDisplayHand(playerA);
                            displayDiscardA.updateTopCard();
                            updateDisplayHand(playerA);
                            System.out.println("AI Discard finish");
                            System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));

                        }
                        updateDisplayHand(playerA);
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
                        updateDisplayHand(playerB);
                        //run AI if this player is computer controlled
                        if (!playerBHuman) {
                            //AI draw cards
                            AIDraw(playerB, playerA);
                            //update discard GUI In case AI draw from player's discard
                            updateDisplayHand(playerB);
                            displayDiscardA.updateTopCard();
                            displayDiscardB.updateTopCard();
                            displayDeck.updateTopCard();

                            System.out.println("AI draw finish");
                            System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));
                            //AI make a move
                            AIMove(playerB);
                            //update Arbor GUI
                            displayArborB.updateFromBackend();
                            updateDisplayHand(playerB);
                            System.out.println("AI Move finish");
                            System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));
                            //AI discard a card
                            AIDiscard(playerB, playerA);
                            //update hand and discard GUI after discard
                            //updateDisplayHand(playerB);
                            displayDiscardB.updateTopCard();
                            updateDisplayHand(playerB);
                            System.out.println("AI Discard finish");
                            System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));


                        }
                        updateDisplayHand(playerB);
                        activeTurn = "A";
                        // update score text
                        root.getChildren().remove(GUIScoreA);
                        GUIScoreA.update();
                        root.getChildren().add(GUIScoreA);
                    }

                    isGameEnd();
                }
            }
        }));
        stage.setScene(scene);
        stage.show();

    }

    public void guiHintUpdate() {
        root.getChildren().remove(guiHint);
        guiHint.update();
        root.getChildren().add(guiHint);
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

    /**
     * Contribution: Natasha
     * Checks if the deck is empty, meaning the game is over
     *
     * @return True if the game should end, False otherwise
     */
    private boolean isGameEnd() {
        this.gameEnd = this.deck.isEmpty();

        System.out.println("Tracking game end possibility: " + this.gameEnd);
        return this.gameEnd;
    }

    /**
     * Contribution: Junxian
     * Generate current GameState for calculate and display
     *
     * @param playerA current player A
     * @param playerB current player B
     * @param deck    current deck
     * @param turn    the name of current player to play
     * @return String[sharedState][hiddenState] as task
     */
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

    /**
     * Contribution: Junxian
     * perform an AI draw action, draw two cards by AI decided. The only known information is the top cards on both side
     * discardPile, by checking if draw these card will bring advantage to player_turn or disadvantage to player_notTurn
     * which :
     * 1. if using these cards from discardPile can increase the score of player_turn
     * 2. if taking these cards from discardPile can reduce the possibility that player_notTurn get right to it's
     * score
     * after draw one card, the status should update to get the info under that draw card.
     * otherwise, AI will draw card from deck
     *
     * @param player_turn    the player in turn
     * @param player_notTurn the player not in turn
     */
    private void AIDraw(Player player_turn, Player player_notTurn) {
        //two known available choices from both side discardPile
        List<CardStack> availableChoices = new ArrayList<>();
        HashMap<String, Integer> currentScore = player_turn.getArboretum().currentScore();
        //generate new discard from simulating
        DiscardPile player_turn_discard = new DiscardPile(player_turn.getName() + player_turn.getDiscardPile().toString());
        DiscardPile player_notTurn_discard = new DiscardPile(player_notTurn.getName() + player_notTurn.getDiscardPile().toString());
        //availablePlayers stores players with new discardPile map to discardPiles
        HashMap<Player, List<CardStack>> availablePlayers = new HashMap<>();
        //availablePlayers2 stores result discardPiles map to the score get by that players best move
        HashMap<List<CardStack>, Integer> availablePlayers2 = new HashMap<>();

        if (!player_turn_discard.isEmpty() && !player_notTurn_discard.isEmpty()) {
            availableChoices.add(player_turn_discard);
            availableChoices.add(player_notTurn_discard);
            //for two discardpiles simulate the three conditions which:
            // draw(A),draw(A); draw(B),draw(B); draw(A),draw(B)
            for (CardStack stack : availableChoices) {
                for (CardStack stack2 : availableChoices) {
                    if (!stack.isEmpty() && !stack2.isEmpty()) {
                        List<CardStack> valueList = new ArrayList<>();
                        Card drawStack1 = stack.drawTopCard();
                        Card drawStack2 = stack2.drawTopCard();
                        if (drawStack1 != null && drawStack2 != null) {
                            valueList.add(stack);
                            valueList.add(stack2);
                            //for draw result, generate A new player to store it's new discardPile
                            availablePlayers.put(new Player(player_turn.getName(), player_turn.getHandString() + drawStack1.toString() + drawStack2.toString(), player_turn.getArboretumString(), player_turn.getDiscardPileString()), valueList);
                        }
                        //add draw card back for continue simulation
                        stack.addTopCard(drawStack2);
                        stack2.addTopCard(drawStack1);
                    }
                }
            }
            //for each new player, generate it's best move with AIMove and get the best score.
            for (Player player : availablePlayers.keySet()) {
                //best score arbor.
                Arbor playerArbor = AIMove(player);
                //find the biggest score, update availablePlayers2
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
        // if availablePlayers2 is not empty, then both discardPiles is not empty --> can be draw
        if (!availablePlayers2.isEmpty()) {
            List<CardStack> resultList = Collections.max(availablePlayers2.entrySet(), Map.Entry.comparingByValue()).getKey();
            int count = 0;
            //draw two cards
            for (CardStack discard : resultList) {
                if (count < 2 && discard.toString().equals(playerA.getDiscardPile().toString())) {
                    System.out.println("ai draw from playerA: " + discard);
                    player_turn.draw(playerA.getDiscardPile());
                    discard.drawTopCard();
                    count++;
                }
                if (count < 2 && discard.toString().equals(playerB.getDiscardPile().toString())) {
                    System.out.println("ai draw from playerB: " + discard);
                    player_turn.draw(playerB.getDiscardPile());
                    discard.drawTopCard();
                    count++;
                }
            }
            this.turnState = TurnState.play;
            guiHintUpdate();
        } else {
            //if availablePlayers2 is empty, then both discardPiles are empty, just draw from deck.
            System.out.println("ai draw from: deck");
            player_turn.draw(this.deck);
            System.out.println("ai draw from: deck");
            player_turn.draw(this.deck);
            this.turnState = TurnState.play;
            guiHintUpdate();
        }
    }


    /**
     * Contribution: Junxian
     * discard A card for player_turn, the card to discard should:
     * 1. cannot reduce the possibility for player_turn to get right to score
     * 2. cannot increase the possibility for player_notTurn to get higher score
     * 3. cannot increase the possibility for player_notTurn to get right to score
     * otherwise, just random discard.
     *
     * @param player_turn    the player in turn
     * @param player_notTurn the player not in turn
     */
    private void AIDiscard(Player player_turn, Player player_notTurn) {
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
        //perform discard
        if (player_turn.getName().equals("A")) {
            System.out.println("PlayerA discard: " + result);
            playerA.discard(result);
            this.turnState = TurnState.end;
            guiHintUpdate();
            this.playButton.setDisable(false);
        }
        if (player_turn.getName().equals("B")) {
            System.out.println("PlayerB discard: " + result);
            playerB.discard(result);
            this.turnState = TurnState.end;
            guiHintUpdate();
            this.playButton.setDisable(false);
        }
    }

    /**
     * Contribution: Junxian
     * AI make an action to get best score, by put all possible cards to all possibility position, due the size of
     * current Game, the calculation time is acceptable.
     *
     * @param player the player to perform action
     * @return an Arbor with best score
     */
    public Arbor AIMove(Player player) {
        //availableChoices, first level.
        HashMap<Arbor, Position> availableChoices = new LinkedHashMap<>();
        //store currentScore for later score comparing
        HashMap<String, Integer> currentScore = new HashMap<>();
        List<Card> availableCards = player.getHand();
        //availableChoices is map contains all new arbor map to the pos to create new arbor.
        if (player.getName().equals("A")) {
            Arbor arboretum_A = player.getArboretum();
            currentScore = arboretum_A.currentScore();
            //for each card in player hand...
            for (Card card : availableCards) {
                //find all the positions that this card can be placed...
                for (Position pos : player.getArboretum().getAvailablePos()) {
                    //generate a new arbor with the card put to the position...
                    Arbor newArbor = new Arbor(player.getName() + arboretum_A.toString());
                    newArbor.addCard(card, pos);
                    //add new arbor and position to availableChoices
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
        //availableResults, second level, sort from availableChoices
        HashMap<Arbor, HashMap<String, Integer>> availableResults = new LinkedHashMap<>();
        //compare new arbor to origin arbor, keep the new arbor if it has higher score.
        for (Arbor key : availableChoices.keySet()) {
            HashMap<String, Integer> result = compareScores(currentScore, key.currentScore());
            if (result != currentScore) {
                availableResults.put(key, result);
            }
        }
        //availableResults2, third level, sort from availableResults
        HashMap<Arbor, Integer> availableResults2 = new LinkedHashMap<>();
        int valueBiggest = 0;
        //find availableResults2/s that have the biggest score
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
        //random select if reach the third level
        if (!availableResults2.isEmpty()) {
            Arbor result = Collections.max(availableResults2.entrySet(), Map.Entry.comparingByValue()).getKey();
            System.out.println("AI Move R2 is: " + result);
            Pair<Card, Position> pair = findCardToPlay(result);
            player.play(pair.getKey(), pair.getValue());
            this.turnState = TurnState.discard;
            guiHintUpdate();
            return result;
        } else {
            //random select if reach the second level
            if (!availableResults.isEmpty()) {
                List<Arbor> keysAsArray = new ArrayList<>(availableResults.keySet());
                Random rand = new Random();
                Arbor result = keysAsArray.get(rand.nextInt(keysAsArray.size()));
                System.out.println("AI Move R1 is: " + result);
                Pair<Card, Position> pair = findCardToPlay(result);
                player.play(pair.getKey(), pair.getValue());
                this.turnState = TurnState.discard;
                guiHintUpdate();
                return result;
            } else {
                //random select
                List<Arbor> keysAsArray = new ArrayList<Arbor>(availableChoices.keySet());
                Random rand = new Random();
                Arbor result = keysAsArray.get(rand.nextInt(keysAsArray.size()));
                System.out.println("AI Move R0 is: " + result);
                Pair<Card, Position> pair = findCardToPlay(result);
                player.play(pair.getKey(), pair.getValue());
                this.turnState = TurnState.discard;
                guiHintUpdate();
                return result;
            }
        }
    }

    /**
     * Contribution: Junxian
     * helper method to get the latest card in an arbor
     *
     * @param arbor arbor
     * @return pair of (Card, Position) to place
     */
    private Pair<Card, Position> findCardToPlay(Arbor arbor) {
        if (arbor != null) {
            String cardAndPos = arbor.toString().substring(arbor.toString().length() - 8);
            String card = cardAndPos.substring(0, 2);
            String pos = cardAndPos.substring(2, 8);
            return new Pair<>(new Card(card), new Position(pos));
        }
        return null;
    }

    /**
     * Contribution: Junxian
     * helper method to compare two arbors and return the one has bigger score.
     *
     * @param scoreOld scoreOld
     * @param scoreNew scoreNew
     * @return an arbor that has bigger score
     */
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
            guiHintUpdate();
            this.turnState = TurnState.secondDraw;
            return true;
        } else if (this.turnState == TurnState.secondDraw) {
            guiHintUpdate();
            this.turnState = TurnState.play;
            return true;
        } else return false;
    }

    public boolean trackCardPlayed() {
        if (this.turnState == TurnState.play) {
            guiHintUpdate();
            this.turnState = TurnState.discard;
            return true;
        }
        return false;
    }

    public boolean trackCardDiscarded() {
        if (this.turnState == TurnState.discard) {
            guiHintUpdate();
            this.turnState = TurnState.end;
            this.playButton.setDisable(false);
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

    public boolean isPlayerAHuman() {
        return playerAHuman;
    }

    public void setPlayerAHuman(boolean playerAHuman) {
        this.playerAHuman = playerAHuman;
    }

    public boolean isPlayerBHuman() {
        return playerBHuman;
    }

    public void setPlayerBHuman(boolean playerBHuman) {
        this.playerBHuman = playerBHuman;
    }

    public TurnState getTurnState() {
        return this.turnState;
    }

    /**
     * Contribution: Natasha
     * Shows the end game screen
     */
    public void displayGameEnd() {
        endScreen = new GUIEnding(this, playerA, playerB, ARBOR_X + 2 * leftArborX, playButton.getLayoutY(), BOARD_WIDTH - 2 * (ARBOR_X + 2 * leftArborX), 300, generateGameState(playerA, playerB, deck, activeTurn));
        root.getChildren().add(endScreen);
    }
}
