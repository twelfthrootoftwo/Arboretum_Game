package comp1110.ass2.gui;

import comp1110.ass2.Arboretum;
import comp1110.ass2.Event.Turn;
import comp1110.ass2.game.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

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


    private static final int ARBOR_X = 540;
    private static final int ARBOR_Y = 500;
    private static final int leftArborX = 10;
    private static final int leftArborY = 10;
    private static final int rightArborX = 645;
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
    private static final int leftDiscardY = (int) Math.floor(BOARD_HEIGHT - BASE_CARD_HEIGHT * 1.5);
    private static final int rightDiscardX = BOARD_WIDTH - CARD_STACK_WIDTH - 10;
    private static final int rightDiscardY = (int) Math.floor(BOARD_HEIGHT - BASE_CARD_HEIGHT * 1.5);

//    private TextField turnIDTextField;
//    private TextField aArboretumTextField;
//    private TextField bArboretumTextField;
//    private TextField aDiscardTextField;
//    private TextField bDiscardTextField;
//    private TextField deckTextField;
//    private TextField aHandTextField;
//    private TextField bHandTextField;

    private Player playerA;
    private Player playerB;
    private Deck deck;
    private String activeTurn;
    public int cardsDrawn;
    private LinkedList<GUICard> displayedHand;

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


        //setup
        this.deck = new Deck(6);
        this.playerA = new Player("A", 6);
        this.playerB = new Player("B", 6);
//        this.deck = new Deck("a3a8c2c7d1d3d7d8m1");
//        this.playerA = new Player("A","Ab3b4c1j1m2m5m8","Ab1C00C00a4N01C00c3C00E01c6N02C00m7N02W01m4N01E01a5N02E01a2S01E01","Aa7c4d6");
//        this.playerB = new Player("B","Ba6b5b6b7b8c8d2j2j8","Bj5C00C00j6S01C00j7S02C00j4C00W01m6C00E01m3C00W02j3N01W01", "Bb2d4c5a1d5");

        for (int i = 0; i < 7; i++) {
            playerA.draw(deck);
            playerB.draw(deck);
        }
        this.activeTurn = "A";

        //add arbor, current score, cards, decks to display
        GUIArbor displayArborA = new GUIArbor(playerA, this, ARBOR_X, ARBOR_Y, leftArborX, leftArborY, arborMargin);
        GUIArbor displayArborB = new GUIArbor(playerB, this, ARBOR_X, ARBOR_Y, rightArborX, rightArborY, arborMargin);
        Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn));
        Button playButton = new Button("Next");
        playButton.setLayoutX(575);
        playButton.setLayoutY(180);
        Button drawButton = new Button("Draw from Deck");
        drawButton.setLayoutX(545);
        drawButton.setLayoutY(220);
        root.getChildren().addAll(displayArborA, displayArborB, playButton, drawButton);

        //prepare backing for hand area, for visual niceness
        Rectangle handBacking = new Rectangle(handBackingWidth, handBackingHeight);
        handBacking.setLayoutX(handBackingX);
        handBacking.setLayoutY(handBackingY);
        handBacking.setFill(Color.LIGHTGREY);
        root.getChildren().add(handBacking);

        //add deck & discard display
        GUICardStack displayDeck = new GUICardStack(this.deck, this, this.root, deckX, deckY);
        GUICardStack discardA = new GUICardStack(this.playerA.getDiscardPile(), this, this.root, leftDiscardX, leftDiscardY);
        GUICardStack discardB = new GUICardStack(this.playerB.getDiscardPile(), this, this.root, rightDiscardX, rightDiscardY);
        root.getChildren().addAll(displayDeck, discardA, discardB);

        //TODO - fix initial turn start
//        startTurn(playerA);


        //turns
        //TODO - actually cycle turns

//        startTurn(playerB);
        playButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
//                System.out.println(activeTurn);
//                System.out.println(activeTurn.equals("A"));
//                System.out.println(activeTurn.equals("B"));
                if (activeTurn.equals("A")) {
                    AIMove(playerA);
                    playerB.getDisplayArbor().endTurn();
                    startTurn(playerA);
                    System.out.println("A");
                    System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));
                    activeTurn = "B";
                } else if (activeTurn.equals("B")) {
                    AIMove(playerB);
                    playerA.getDisplayArbor().endTurn();
                    startTurn(playerB);
                    System.out.println("B");
                    System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, activeTurn)));
                    activeTurn = "A";

                }
            }
        }));
        drawButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (activeTurn.equals("B")) {
                    playerA.draw(deck);
                    updateHand(playerA);
                }else if (activeTurn.equals("A")){
                    playerB.draw(deck);
                    updateHand(playerB);
                }

            }

        }));
//        while (!isGameEnd()){
//            if (isGameEnd()){
//                getWinner();
//                break;
//            }
////            if(this.activeTurn == playerA){
////                startTurn(playerA);
////                this.activeTurn = playerB;
////            }
////            if(this.activeTurn == playerB){
////                startTurn(playerB);
////                this.activeTurn = playerA;
////            }
////            startTurn(playerA);
//        }


//        System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, "A")));
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Contribution: Natasha
     * Setup to start the turn of a player, including changing GUIArbor display, drawing cards, and showing hand
     *
     * @param player - start the turn of this player
     */
    private void startTurn(Player player) {
//        this.activeTurn=player;
        player.getDisplayArbor().startTurn();

        this.cardsDrawn = 0;
        this.updateHand(player);
    }

    /**
     * Contribution: Natasha
     * Displays the hand of the current player
     *
     * @param player - the player who is currently taking their turn
     */
    public void updateHand(Player player) {
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
            GUICard guiCard = new GUICard(card, root);
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
        if (this.activeTurn.equals("A")) return playerA;
        else if (this.activeTurn.equals("B")) return playerB;
        else return null;
    }

    public List<GUICard> getDisplayHand() {
        return this.displayedHand;
    }

    private Player getWinner() {
        return null;
    }

    private boolean isGameEnd() {
        return false;
    }

    private String[][] generateGameState(Player playerA, Player playerB, Deck deck, String turn) {


        //put all gameState to tree strings
//        String nextTurn = turn; //sharedState[0];
        String arboretumA = playerA.getArboretumString();//sharedState[1];
//        System.out.println("arboretumA: " + arboretumA);
        String discardA = playerA.getDiscardPileString();//sharedState[2];
//        System.out.println("discardA: " + discardA);
        String arboretumB = playerB.getArboretumString();//sharedState[3];
//        System.out.println("arboretumB" + arboretumB);
        String discardB = playerB.getDiscardPileString();//sharedState[4];
//        System.out.println("discardB" + discardB);
//        String deck = deck.toString()hiddenState[0];
        String handA = playerA.getHandString();//hiddenState[1];
        String handB = playerB.getHandString();//hiddenState[2];

        String[] sharedState = {turn, arboretumA, discardA, arboretumB, discardB};
        String[] hiddenState = {deck.getDeckString(), handA, handB};

        String[][] gameState = {sharedState, hiddenState};

        return gameState;
    }

    private Arbor AIMove(Player player) {

        if (player.getName().equals("A")) {
            HashMap<Arbor, Position> availableChoices = new LinkedHashMap<>();
            Arbor arboretum_A = player.getArboretum();
            HashMap<String, Integer> currentScore = arboretum_A.currentScore();
//            System.out.println(currentScore);
            List<Card> availableCards = player.getHand();
//            System.out.println(player.getArboretum().getAvailablePos());
            for (Card card:availableCards) {
//                System.out.println("card: " + card );
                for (Position pos:player.getArboretum().getAvailablePos()) {
//                    System.out.println("pos: " +  pos);
                    Arbor newArbor = new Arbor(this.playerA.getName() + this.playerA.getArboretum().toString());
//                    System.out.println(this.playerA.getName() + this.playerA.getArboretum().toString());
//                    System.out.println("old: "+ newArbor.arboretumList);
                    newArbor.addCard(card,pos);
//                    System.out.println("new: "+ newArbor.arboretumList);
                    availableChoices.put(newArbor, pos);

                }
            }
//            System.out.println("availableChoices: " + availableChoices);
            HashMap<Arbor, HashMap<String, Integer>> availableResults = new LinkedHashMap<>();
            for (Arbor key:availableChoices.keySet()) {
//                System.out.println("compare "+ currentScore + " and " + key.currentScore());
                HashMap<String, Integer> result = compareScores(currentScore,key.currentScore());
                if (result != currentScore){
                    availableResults.put(key, result);
//                    System.out.println("result: " + result);
                }

            }
//            System.out.println("availableResults: " + availableResults);
            HashMap<Arbor, Integer> availableResults2 = new LinkedHashMap<>();
            int valueBiggest = 0;
            for (Arbor key:availableResults.keySet()) {
                HashMap<String, Integer> value = availableResults.get(key);
                if (value != null){
                    String keyBiggest = Collections.max(value.entrySet(), Map.Entry.comparingByValue()).getKey();
                    if (value.get(keyBiggest) > valueBiggest){
                        valueBiggest = value.get(keyBiggest);
                        availableResults2.put(key, valueBiggest);
                    }
                }
            }
            if (!availableResults2.isEmpty()){
                System.out.println("availableResults2: " + availableResults2);
                Arbor result = Collections.max(availableResults2.entrySet(), Map.Entry.comparingByValue()).getKey();
                System.out.println("AI Move is: " + result);
                return result;
            }else {
                if (!availableResults.isEmpty()){
                    System.out.println("availableResults1: " + availableResults);
                    List<Arbor> keysAsArray = new ArrayList<Arbor>(availableResults.keySet());
                    Random rand = new Random();
                    Arbor result = keysAsArray.get(rand.nextInt(keysAsArray.size()));
                    System.out.println("AI Move is: " + result);
                    return result;
                }else {
                    System.out.println("availableChoices: " + availableChoices);
                    List<Arbor> keysAsArray = new ArrayList<Arbor>(availableChoices.keySet());
                    Random rand = new Random();
                    Arbor result = keysAsArray.get(rand.nextInt(keysAsArray.size()));
                    System.out.println("AI Move is: " + result);
                    return result;
                }


            }


        }
        if (player.getName().equals("B")) {
            HashMap<Arbor, Position> availableChoices = new LinkedHashMap<>();
            Arbor arboretum_B = player.getArboretum();
            HashMap<String, Integer> currentScore = arboretum_B.currentScore();
//            System.out.println(currentScore);
            List<Card> availableCards = player.getHand();
//            System.out.println(player.getArboretum().getAvailablePos());
            for (Card card:availableCards) {
//                System.out.println("card: " + card );
                for (Position pos:player.getArboretum().getAvailablePos()) {
//                    System.out.println("pos: " +  pos);
                    Arbor newArbor = new Arbor(this.playerB.getName() + this.playerB.getArboretum().toString());
//                    System.out.println(this.playerA.getName() + this.playerA.getArboretum().toString());
//                    System.out.println("old: "+ newArbor.arboretumList);
                    newArbor.addCard(card,pos);
//                    System.out.println("new: "+ newArbor.arboretumList);
                    availableChoices.put(newArbor, pos);

                }
            }
//            System.out.println("availableChoices: " + availableChoices);
            HashMap<Arbor, HashMap<String, Integer>> availableResults = new LinkedHashMap<>();
            for (Arbor key:availableChoices.keySet()) {
//                System.out.println("compare "+ currentScore + " and " + key.currentScore());
                HashMap<String, Integer> result = compareScores(currentScore,key.currentScore());
                if (result != currentScore){
                    availableResults.put(key, result);
//                    System.out.println("result: " + result);
                }

            }
//            System.out.println("availableResults: " + availableResults);
            HashMap<Arbor, Integer> availableResults2 = new LinkedHashMap<>();
            int valueBiggest = 0;
            for (Arbor key:availableResults.keySet()) {
                HashMap<String, Integer> value = availableResults.get(key);
                if (value != null){
                    String keyBiggest = Collections.max(value.entrySet(), Map.Entry.comparingByValue()).getKey();
                    if (value.get(keyBiggest) > valueBiggest){
                        valueBiggest = value.get(keyBiggest);
                        availableResults2.put(key, valueBiggest);
                    }
                }
            }
            if (!availableResults2.isEmpty()){
                System.out.println("availableResults2: " + availableResults2);
                Arbor result = Collections.max(availableResults2.entrySet(), Map.Entry.comparingByValue()).getKey();
                System.out.println("AI Move is: " + result);
                return result;
            }else {
                if (!availableResults.isEmpty()){
                    System.out.println("availableResults1: " + availableResults);
                    List<Arbor> keysAsArray = new ArrayList<Arbor>(availableResults.keySet());
                    Random rand = new Random();
                    Arbor result = keysAsArray.get(rand.nextInt(keysAsArray.size()));
                    System.out.println("AI Move is: " + result);
                    return result;
                }else {
                    System.out.println("availableChoices: " + availableChoices);
                    List<Arbor> keysAsArray = new ArrayList<Arbor>(availableChoices.keySet());
                    Random rand = new Random();
                    Arbor result = keysAsArray.get(rand.nextInt(keysAsArray.size()));
                    System.out.println("AI Move is: " + result);
                    return result;
                }

            }

        }

        return null;
    }
    private HashMap<String, Integer> compareScores(HashMap<String, Integer> scoreOld, HashMap<String, Integer> scoreNew){
        if (!scoreOld.isEmpty() && !scoreNew.isEmpty()){
            String keyOld = Collections.max(scoreOld.entrySet(), Map.Entry.comparingByValue()).getKey();
            String keyNew = Collections.max(scoreNew.entrySet(), Map.Entry.comparingByValue()).getKey();
            if (scoreOld.get(keyOld) < scoreNew.get(keyNew)){
                return scoreNew;
            }else if (scoreOld.get(keyOld) > scoreNew.get(keyNew)){
                return scoreOld;
            }else {
                int sumNew = scoreNew.values().stream().mapToInt(Integer::intValue).sum();
                int sumOld= scoreOld.values().stream().mapToInt(Integer::intValue).sum();
                if (sumNew > sumOld){
                    return scoreNew;
                }else {
                    return scoreOld;
                }
            }
        }else {
            return null;
        }


    }
//
//    private void update(String[][] gameState) throws FileNotFoundException {
//
//        String[] sharedState = gameState[0];
//        String[] hiddenState = gameState[1];
//
//        //put all gameState to tree strings
//        String turn = sharedState[0];
//        String arboretumA = sharedState[1];
//        String discardA = sharedState[2];
//        String arboretumB = sharedState[3];
//        String discardB = sharedState[4];
//        String deck = hiddenState[0];
//        String handA = hiddenState[1];
//        String handB = hiddenState[2];
//
//        if (!Objects.equals(turn, "") && !Objects.equals(arboretumA, "") && !Objects.equals(discardA, "") && !Objects.equals(arboretumB, "") && !Objects.equals(discardB, "") && !Objects.equals(deck, "") && !Objects.equals(handA, "") && !Objects.equals(handB, "")) {
//            ScrollPane scrollPane = new ScrollPane();
//            Label arboretum_A = new Label("Player A arboretum: " + arboretumA);
//            Label discard_A = new Label("Player A discard: " + discardA);
//            Label hand_A = new Label("Player A hand(hidden): " + handA);
//            Label arboretum_B = new Label("Player B arboretum: " + arboretumB);
//            Label discard_B = new Label("Player B discard: " + discardB);
//            Label hand_B = new Label("Player B hand(hidden): " + handB);
//            Label deckC = new Label("Deck(hidden): " + deck);
//            Label turnP = new Label("turn: " + turn);
//
//            //Player A part
//            //player A share area
//            HBox sharedA = new HBox();
//            sharedA.setBackground(new Background(new BackgroundFill(Color.rgb(185, 230, 160), CornerRadii.EMPTY, Insets.EMPTY)));
//
//            //player A arboretum (left part)
//            VBox arboretumABox = new VBox();
//            GridPane gridPaneSA = addArboretum(arboretumA);
//            arboretumABox.getChildren().addAll(arboretum_A, gridPaneSA);
//
//            //player A discard + hand (right part)
//            VBox discardABox = new VBox();
//
//            //player A hand
//            VBox hiddenA = new VBox();
//            hiddenA.setBackground(new Background(new BackgroundFill(Color.rgb(189, 189, 189), CornerRadii.EMPTY, Insets.EMPTY)));
//            GridPane gridPaneHA = addHand(handA);
//            hiddenA.getChildren().addAll(hand_A, gridPaneHA);
//
//            //player A discard
//            GridPane gridPaneDA = addDiscard(discardA);
//            discardABox.getChildren().addAll(discard_A, gridPaneDA, hiddenA);
//
//            sharedA.getChildren().addAll(arboretumABox, discardABox);
//
//
//            //Player B part
//            //player B share area
//            HBox sharedB = new HBox();
//            sharedB.setBackground(new Background(new BackgroundFill(Color.rgb(185, 230, 160), CornerRadii.EMPTY, Insets.EMPTY)));
//
//            //player B arboretum (left part)
//            VBox arboretumBBox = new VBox();
//            GridPane gridPaneSB = addArboretum(arboretumB);
//            arboretumBBox.getChildren().addAll(arboretum_B, gridPaneSB);
//
//            //player B discard + hand (right part)
//            VBox discardBBox = new VBox();
//
//            //player B hand
//            VBox hiddenB = new VBox();
//            hiddenB.setBackground(new Background(new BackgroundFill(Color.rgb(189, 189, 189), CornerRadii.EMPTY, Insets.EMPTY)));
//            GridPane gridPaneHB = addHand(handB);
//            hiddenB.getChildren().addAll(hand_B, gridPaneHB);
//
//            //player B discard
//            GridPane gridPaneDB = addDiscard(discardB);
//            discardBBox.getChildren().addAll(discard_B, gridPaneDB, hiddenB);
//
//            sharedB.getChildren().addAll(arboretumBBox, discardBBox);
//
//
//            //deck and turn part
//            VBox vboxD = new VBox();
//            vboxD.setBackground(new Background(new BackgroundFill(Color.rgb(230, 90, 90), CornerRadii.EMPTY, Insets.EMPTY)));
//            VBox discardDBox = new VBox();
//            discardDBox.setBackground(new Background(new BackgroundFill(Color.rgb(189, 189, 189), CornerRadii.EMPTY, Insets.EMPTY)));
//            GridPane gridPaneDD = addDeck(deck);
//            discardDBox.getChildren().addAll(deckC, gridPaneDD);
//
//            vboxD.getChildren().addAll(discardDBox, turnP);
//
//
//            //add scroll
//            VBox vboxS = new VBox();
//            vboxS.getChildren().addAll(sharedA, sharedB);
//            HBox all = new HBox();
//            all.getChildren().addAll(vboxS, vboxD);
//
//            scrollPane.setContent(all);
//            scrollPane.setPrefSize(1200, 700);
//            this.root.getChildren().addAll(scrollPane);
////            this.root.getChildren().addAll(new GUICard("a1",addImage("a1")));
//
//
//        }
//    }
//
//
//    private GridPane addHand(String hand) {
//        String newArboretum = hand.substring(1);
//
//        //Creating a Grid Pane
//        GridPane gridPane = new GridPane();
//        if (!newArboretum.equals("")) {
//            String[] trees = newArboretum.split("(?<=\\G.{" + 2 + "})");
//
//            //Setting size for the pane
//            gridPane.setMinSize(100, 50);
//
//            //Setting the padding
//            gridPane.setPadding(new Insets(10, 10, 10, 10));
//
//            gridPane.setVgap(5);
//            gridPane.setHgap(5);
//
//            //Setting the Grid alignment
//            gridPane.setAlignment(Pos.CENTER);
//            for (int i = 0, treesLength = trees.length; i < treesLength; i++) {
//                String tree = trees[i];
//                try {
////                    gridPane.add(new ImageView(addImage(tree)), i, 0);
//                    gridPane.add(new GUICard(tree,addImage(tree)), i, 0);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//
//        }
//        return gridPane;
//    }
//
//    private GridPane addDeck(String discard) {
//        String[] trees = discard.split("(?<=\\G.{" + 2 + "})");
//
//        //Creating a Grid Pane
//        GridPane gridPane = new GridPane();
//
//        //Setting size for the pane
//        gridPane.setMinSize(100, 50);
//
//        //Setting the padding
//        gridPane.setPadding(new Insets(10, 10, 10, 10));
//
//        gridPane.setVgap(5);
//        gridPane.setHgap(5);
//
//        //Setting the Grid alignment
//        gridPane.setAlignment(Pos.CENTER);
//        for (int i = 0, treesLength = trees.length; i < treesLength; i++) {
//            String tree = trees[i];
//            try {
//                if (i < 8) {
////                    gridPane.add(new ImageView(addImage(tree)), 0, i);
//                    gridPane.add(new GUICard(tree,addImage(tree)), 0, i);
//                } else if (i < 16) {
////                    gridPane.add(new ImageView(addImage(tree)), 1, i - 8);
//                    gridPane.add(new GUICard(tree,addImage(tree)), 1, i - 8);
//                }else if (i < 24) {
////                    gridPane.add(new ImageView(addImage(tree)), 2, i - 16);
//                    gridPane.add(new GUICard(tree,addImage(tree)), 2, i - 16);
//                }else if (i < 32) {
////                    gridPane.add(new ImageView(addImage(tree)), 3, i - 24);
//                    gridPane.add(new GUICard(tree,addImage(tree)), 3, i - 24);
//                }else if (i < 40) {
////                    gridPane.add(new ImageView(addImage(tree)), 4, i - 32);
//                    gridPane.add(new GUICard(tree,addImage(tree)), 4, i - 32);
//                }else if (i < 48){
////                    gridPane.add(new ImageView(addImage(tree)), 5, i - 40);
//                    gridPane.add(new GUICard(tree,addImage(tree)), 5, i - 40);
//                }
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        return gridPane;
//    }
//
//    private GridPane addDiscard(String discard) {
//        String newArboretum = discard.substring(1);
//
//        //Creating a Grid Pane
//        GridPane gridPane = new GridPane();
//
//        if (!newArboretum.equals("")) {
//            String[] trees = newArboretum.split("(?<=\\G.{" + 2 + "})");
//
//            //Setting size for the pane
//            gridPane.setMinSize(100, 50);
//
//            //Setting the padding
//            gridPane.setPadding(new Insets(10, 10, 10, 10));
//
//            gridPane.setVgap(5);
//            gridPane.setHgap(5);
//
//            //Setting the Grid alignment
//            gridPane.setAlignment(Pos.CENTER);
//            for (int i = 0, treesLength = trees.length; i < treesLength; i++) {
//                String tree = trees[i];
//                try {
//                    gridPane.add(new GUICard(tree,addImage(tree)), i, 0);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            }
//            System.out.println(Math.pow(30, 35) % 29);
//
//        }
//        return gridPane;
//    }
//
//
//    private GridPane addArboretum(String arboretum) {
//        String newArboretum = arboretum.substring(1);
//
//        //Creating a Grid Pane
//        GridPane gridPane = new GridPane();
//        if (!newArboretum.equals("")) {
//            String[] trees = newArboretum.split("(?<=\\G.{" + 8 + "})");
//
//            //Setting size for the pane
//            gridPane.setMinSize(100, 50);
//
//            //Setting the padding
//            gridPane.setPadding(new Insets(10, 10, 10, 10));
//
//            gridPane.setVgap(5);
//            gridPane.setHgap(5);
//
//            //Setting the Grid alignment
//            gridPane.setAlignment(Pos.CENTER);
//            HashMap<String, int[]> map = new HashMap<>();
//            int[] startPos = {0, 0};
//            int northest = 0;
//            int westest = 0;
//            for (String tree : trees) {
//                String name = tree.substring(0, 2);
//                String directionV = tree.substring(2, 3);
//                int stepV = Integer.parseInt(tree.substring(3, 5));
//                String directionH = tree.substring(5, 6);
//                int stepH = Integer.parseInt(tree.substring(6));
//
//                int newPosV = 0;
//                int newPosH = 0;
//                if (directionV.equals("C")) {
//                    newPosV = startPos[0];
//                }
//                if (directionV.equals("N")) {
//                    newPosV = startPos[0] - stepV;
//                    if (northest > newPosV) {
//                        northest = newPosV;
//                    }
//                }
//                if (directionV.equals("S")) {
//                    newPosV = startPos[0] + stepV;
//                }
//                if (directionH.equals("C")) {
//                    newPosH = startPos[0];
//                }
//                if (directionH.equals("W")) {
//                    newPosH = startPos[0] - stepH;
//                    if (westest > newPosH) {
//                        westest = newPosH;
//                    }
//                }
//                if (directionH.equals("E")) {
//                    newPosH = startPos[0] + stepH;
//                }
//                int[] newPos = {newPosV, newPosH};
//                map.put(name, newPos);
//
//                if (directionV.equals("C") && directionH.equals("C")) {
//                    startPos = new int[]{0, 0};
//                    map.put(name, startPos);
//                }
//
//            }
//
//            for (String key : map.keySet()) {
//                int[] value = map.get(key);
//                value[0] = value[0] + Math.abs(northest);
//                value[1] = value[1] + Math.abs(westest);
//                try {
//                    gridPane.add(new GUICard(key,addImage(key)), value[1], value[0]);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//        }
//        return gridPane;
//    }
}
