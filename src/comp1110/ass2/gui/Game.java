package comp1110.ass2.gui;

import comp1110.ass2.Event.Turn;
import comp1110.ass2.game.Card;
import comp1110.ass2.game.Deck;
import comp1110.ass2.game.Player;
import comp1110.ass2.game.Position;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class Game extends Application {
    /* board layout */
    private static final int BOARD_WIDTH = 1200;
    private static final int BOARD_HEIGHT = 700;
    private static final int GRID_SIZE = 50;
    private static final int GRID_DIMENSION = 10;
    private static final int WINDOW_XOFFSET = 10;
    private static final int WINDOW_YOFFSET = 30;
    private static final int TEXTBOX_WIDTH = 120;


    private TextField turnIDTextField;
    private TextField aArboretumTextField;
    private TextField bArboretumTextField;
    private TextField aDiscardTextField;
    private TextField bDiscardTextField;
    private TextField deckTextField;
    private TextField aHandTextField;
    private TextField bHandTextField;

    Image a1 = new Image(new FileInputStream("assets/cards/a_01.png"), 112, 112, true, true);
    Image a2 = new Image(new FileInputStream("assets/cards/a_02.png"), 112, 112, true, true);
    Image a3 = new Image(new FileInputStream("assets/cards/a_03.png"), 112, 112, true, true);
    Image a4 = new Image(new FileInputStream("assets/cards/a_04.png"), 112, 112, true, true);
    Image a5 = new Image(new FileInputStream("assets/cards/a_05.png"), 112, 112, true, true);
    Image a6 = new Image(new FileInputStream("assets/cards/a_06.png"), 112, 112, true, true);
    Image a7 = new Image(new FileInputStream("assets/cards/a_07.png"), 112, 112, true, true);
    Image a8 = new Image(new FileInputStream("assets/cards/a_08.png"), 112, 112, true, true);

    Image b1 = new Image(new FileInputStream("assets/cards/b_01.png"), 112, 112, true, true);
    Image b2 = new Image(new FileInputStream("assets/cards/b_02.png"), 112, 112, true, true);
    Image b3 = new Image(new FileInputStream("assets/cards/b_03.png"), 112, 112, true, true);
    Image b4 = new Image(new FileInputStream("assets/cards/b_04.png"), 112, 112, true, true);
    Image b5 = new Image(new FileInputStream("assets/cards/b_05.png"), 112, 112, true, true);
    Image b6 = new Image(new FileInputStream("assets/cards/b_06.png"), 112, 112, true, true);
    Image b7 = new Image(new FileInputStream("assets/cards/b_07.png"), 112, 112, true, true);
    Image b8 = new Image(new FileInputStream("assets/cards/b_08.png"), 112, 112, true, true);

    Image c1 = new Image(new FileInputStream("assets/cards/c_01.png"), 112, 112, true, true);
    Image c2 = new Image(new FileInputStream("assets/cards/c_02.png"), 112, 112, true, true);
    Image c3 = new Image(new FileInputStream("assets/cards/c_03.png"), 112, 112, true, true);
    Image c4 = new Image(new FileInputStream("assets/cards/c_04.png"), 112, 112, true, true);
    Image c5 = new Image(new FileInputStream("assets/cards/c_05.png"), 112, 112, true, true);
    Image c6 = new Image(new FileInputStream("assets/cards/c_06.png"), 112, 112, true, true);
    Image c7 = new Image(new FileInputStream("assets/cards/c_07.png"), 112, 112, true, true);
    Image c8 = new Image(new FileInputStream("assets/cards/c_08.png"), 112, 112, true, true);

    Image d1 = new Image(new FileInputStream("assets/cards/d_01.png"), 112, 112, true, true);
    Image d2 = new Image(new FileInputStream("assets/cards/d_02.png"), 112, 112, true, true);
    Image d3 = new Image(new FileInputStream("assets/cards/d_03.png"), 112, 112, true, true);
    Image d4 = new Image(new FileInputStream("assets/cards/d_04.png"), 112, 112, true, true);
    Image d5 = new Image(new FileInputStream("assets/cards/d_05.png"), 112, 112, true, true);
    Image d6 = new Image(new FileInputStream("assets/cards/d_06.png"), 112, 112, true, true);
    Image d7 = new Image(new FileInputStream("assets/cards/d_07.png"), 112, 112, true, true);
    Image d8 = new Image(new FileInputStream("assets/cards/d_08.png"), 112, 112, true, true);

    Image j1 = new Image(new FileInputStream("assets/cards/j_01.png"), 112, 112, true, true);
    Image j2 = new Image(new FileInputStream("assets/cards/j_02.png"), 112, 112, true, true);
    Image j3 = new Image(new FileInputStream("assets/cards/j_03.png"), 112, 112, true, true);
    Image j4 = new Image(new FileInputStream("assets/cards/j_04.png"), 112, 112, true, true);
    Image j5 = new Image(new FileInputStream("assets/cards/j_05.png"), 112, 112, true, true);
    Image j6 = new Image(new FileInputStream("assets/cards/j_06.png"), 112, 112, true, true);
    Image j7 = new Image(new FileInputStream("assets/cards/j_07.png"), 112, 112, true, true);
    Image j8 = new Image(new FileInputStream("assets/cards/j_08.png"), 112, 112, true, true);

    Image m1 = new Image(new FileInputStream("assets/cards/m_01.png"), 112, 112, true, true);
    Image m2 = new Image(new FileInputStream("assets/cards/m_02.png"), 112, 112, true, true);
    Image m3 = new Image(new FileInputStream("assets/cards/m_03.png"), 112, 112, true, true);
    Image m4 = new Image(new FileInputStream("assets/cards/m_04.png"), 112, 112, true, true);
    Image m5 = new Image(new FileInputStream("assets/cards/m_05.png"), 112, 112, true, true);
    Image m6 = new Image(new FileInputStream("assets/cards/m_06.png"), 112, 112, true, true);
    Image m7 = new Image(new FileInputStream("assets/cards/m_07.png"), 112, 112, true, true);
    Image m8 = new Image(new FileInputStream("assets/cards/m_08.png"), 112, 112, true, true);

    public Game() throws FileNotFoundException {
    }

    public Image addImage(String img) throws FileNotFoundException {

        return switch (img) {
            case "a1" -> a1;
            case "a2" -> a2;
            case "a3" -> a3;
            case "a4" -> a4;
            case "a5" -> a5;
            case "a6" -> a6;
            case "a7" -> a7;
            case "a8" -> a8;
            case "b1" -> b1;
            case "b2" -> b2;
            case "b3" -> b3;
            case "b4" -> b4;
            case "b5" -> b5;
            case "b6" -> b6;
            case "b7" -> b7;
            case "b8" -> b8;
            case "c1" -> c1;
            case "c2" -> c2;
            case "c3" -> c3;
            case "c4" -> c4;
            case "c5" -> c5;
            case "c6" -> c6;
            case "c7" -> c7;
            case "c8" -> c8;
            case "d1" -> d1;
            case "d2" -> d2;
            case "d3" -> d3;
            case "d4" -> d4;
            case "d5" -> d5;
            case "d6" -> d6;
            case "d7" -> d7;
            case "d8" -> d8;
            case "j1" -> j1;
            case "j2" -> j2;
            case "j3" -> j3;
            case "j4" -> j4;
            case "j5" -> j5;
            case "j6" -> j6;
            case "j7" -> j7;
            case "j8" -> j8;
            case "m1" -> m1;
            case "m2" -> m2;
            case "m3" -> m3;
            case "m4" -> m4;
            case "m5" -> m5;
            case "m6" -> m6;
            case "m7" -> m7;
            case "m8" -> m8;
            default -> null;
        };
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
        Deck deck = new Deck(6);
        Player playerA = new Player("A",6);
        Player playerB = new Player("B",6);

        for (int i = 0; i < 7; i++) {
            playerA.draw(deck);
            playerB.draw(deck);
        }
        playerA.draw(deck);
        playerA.draw(deck);
        Card card1 = playerA.getHand().get(0);
//        System.out.println(playerA.checkPlay(card,new Position(10,0)));
        playerA.play(card1,new Position(10,0));
        playerA.draw(deck);
        Card card2 = playerA.getHand().get(0);
        playerA.play(card2,new Position(0,1));

        System.out.println(Arrays.deepToString(generateGameState(playerA, playerB, deck, "A")));
        update(generateGameState(playerA,playerB,deck,"A"));
        stage.setScene(scene);
        stage.show();

//        while (!isGameEnd()){
//            new Turn(playerA,deck);
//            new Turn(playerB,deck);
//        }


        if (isGameEnd()){
            getWinner();
        }





    }
    private Player getWinner(){
        return null;
    }
    private boolean isGameEnd(){
        return false;
    }
    private String[][] generateGameState(Player playerA,Player playerB,Deck deck,String turn){
        //put all gameState to tree strings
//        String nextTurn = turn; //sharedState[0];
        String arboretumA =  playerA.getArboretumString();//sharedState[1];
        System.out.println("arboretumA: " + arboretumA);
        String discardA = playerA.getDiscardPileString();//sharedState[2];
        System.out.println("discardA: " + discardA);
        String arboretumB = playerB.getArboretumString();//sharedState[3];
//        System.out.println("arboretumB" + arboretumB);
        String discardB = playerB.getDiscardPileString();//sharedState[4];
//        System.out.println("discardB" + discardB);
//        String deck = deck.toString()hiddenState[0];
        String handA = playerA.getHandString();//hiddenState[1];
        String handB = playerB.getHandString();//hiddenState[2];

        String[] sharedState = {turn,arboretumA,discardA,arboretumB,discardB};
        String[] hiddenState = {deck.toString(),handA,handB};

        String[][] gameState = {sharedState,hiddenState};

        return gameState;
    }

    private void update(String[][] gameState) throws FileNotFoundException {

        String[] sharedState = gameState[0];
        String[] hiddenState = gameState[1];

        //put all gameState to tree strings
        String turn = sharedState[0];
        String arboretumA = sharedState[1];
        String discardA = sharedState[2];
        String arboretumB = sharedState[3];
        String discardB = sharedState[4];
        String deck = hiddenState[0];
        String handA = hiddenState[1];
        String handB = hiddenState[2];

        if (!Objects.equals(turn, "") && !Objects.equals(arboretumA, "") && !Objects.equals(discardA, "") && !Objects.equals(arboretumB, "") && !Objects.equals(discardB, "") && !Objects.equals(deck, "") && !Objects.equals(handA, "") && !Objects.equals(handB, "")) {
            ScrollPane scrollPane = new ScrollPane();
            Label arboretum_A = new Label("Player A arboretum: " + arboretumA);
            Label discard_A = new Label("Player A discard: " + discardA);
            Label hand_A = new Label("Player A hand(hidden): " + handA);
            Label arboretum_B = new Label("Player B arboretum: " + arboretumB);
            Label discard_B = new Label("Player B discard: " + discardB);
            Label hand_B = new Label("Player B hand(hidden): " + handB);
            Label deckC = new Label("Deck(hidden): " + deck);
            Label turnP = new Label("turn: " + turn);

            //Player A part
            //player A share area
            HBox sharedA = new HBox();
            sharedA.setBackground(new Background(new BackgroundFill(Color.rgb(185, 230, 160), CornerRadii.EMPTY, Insets.EMPTY)));

            //player A arboretum (left part)
            VBox arboretumABox = new VBox();
            GridPane gridPaneSA = addArboretum(arboretumA);
            arboretumABox.getChildren().addAll(arboretum_A, gridPaneSA);

            //player A discard + hand (right part)
            VBox discardABox = new VBox();

            //player A hand
            VBox hiddenA = new VBox();
            hiddenA.setBackground(new Background(new BackgroundFill(Color.rgb(189, 189, 189), CornerRadii.EMPTY, Insets.EMPTY)));
            GridPane gridPaneHA = addHand(handA);
            hiddenA.getChildren().addAll(hand_A, gridPaneHA);

            //player A discard
            GridPane gridPaneDA = addDiscard(discardA);
            discardABox.getChildren().addAll(discard_A, gridPaneDA, hiddenA);

            sharedA.getChildren().addAll(arboretumABox, discardABox);


            //Player B part
            //player B share area
            HBox sharedB = new HBox();
            sharedB.setBackground(new Background(new BackgroundFill(Color.rgb(185, 230, 160), CornerRadii.EMPTY, Insets.EMPTY)));

            //player B arboretum (left part)
            VBox arboretumBBox = new VBox();
            GridPane gridPaneSB = addArboretum(arboretumB);
            arboretumBBox.getChildren().addAll(arboretum_B, gridPaneSB);

            //player B discard + hand (right part)
            VBox discardBBox = new VBox();

            //player B hand
            VBox hiddenB = new VBox();
            hiddenB.setBackground(new Background(new BackgroundFill(Color.rgb(189, 189, 189), CornerRadii.EMPTY, Insets.EMPTY)));
            GridPane gridPaneHB = addHand(handB);
            hiddenB.getChildren().addAll(hand_B, gridPaneHB);

            //player B discard
            GridPane gridPaneDB = addDiscard(discardB);
            discardBBox.getChildren().addAll(discard_B, gridPaneDB, hiddenB);

            sharedB.getChildren().addAll(arboretumBBox, discardBBox);


            //deck and turn part
            VBox vboxD = new VBox();
            vboxD.setBackground(new Background(new BackgroundFill(Color.rgb(230, 90, 90), CornerRadii.EMPTY, Insets.EMPTY)));
            VBox discardDBox = new VBox();
            discardDBox.setBackground(new Background(new BackgroundFill(Color.rgb(189, 189, 189), CornerRadii.EMPTY, Insets.EMPTY)));
            GridPane gridPaneDD = addDeck(deck);
            discardDBox.getChildren().addAll(deckC, gridPaneDD);

            vboxD.getChildren().addAll(discardDBox, turnP);


            //add scroll
            VBox vboxS = new VBox();
            vboxS.getChildren().addAll(sharedA, sharedB);
            HBox all = new HBox();
            all.getChildren().addAll(vboxS, vboxD);

            scrollPane.setContent(all);
            scrollPane.setPrefSize(1200, 700);
            this.root.getChildren().addAll(new GUICard("a1",addImage("a1")));


        }
    }


    private GridPane addHand(String hand) {
        String newArboretum = hand.substring(1);

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();
        if (!newArboretum.equals("")) {
            String[] trees = newArboretum.split("(?<=\\G.{" + 2 + "})");

            //Setting size for the pane
            gridPane.setMinSize(100, 50);

            //Setting the padding
            gridPane.setPadding(new Insets(10, 10, 10, 10));

            gridPane.setVgap(5);
            gridPane.setHgap(5);

            //Setting the Grid alignment
            gridPane.setAlignment(Pos.CENTER);
            for (int i = 0, treesLength = trees.length; i < treesLength; i++) {
                String tree = trees[i];
                try {
//                    gridPane.add(new ImageView(addImage(tree)), i, 0);
                    gridPane.add(new GUICard(tree,addImage(tree)), i, 0);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }


        }
        return gridPane;
    }

    private GridPane addDeck(String discard) {
        String[] trees = discard.split("(?<=\\G.{" + 2 + "})");

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(100, 50);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);
        for (int i = 0, treesLength = trees.length; i < treesLength; i++) {
            String tree = trees[i];
            try {
                if (i < 8) {
//                    gridPane.add(new ImageView(addImage(tree)), 0, i);
                    gridPane.add(new GUICard(tree,addImage(tree)), 0, i);
                } else if (i < 16) {
//                    gridPane.add(new ImageView(addImage(tree)), 1, i - 8);
                    gridPane.add(new GUICard(tree,addImage(tree)), 1, i - 8);
                }else if (i < 24) {
//                    gridPane.add(new ImageView(addImage(tree)), 2, i - 16);
                    gridPane.add(new GUICard(tree,addImage(tree)), 2, i - 16);
                }else if (i < 32) {
//                    gridPane.add(new ImageView(addImage(tree)), 3, i - 24);
                    gridPane.add(new GUICard(tree,addImage(tree)), 3, i - 24);
                }else if (i < 40) {
//                    gridPane.add(new ImageView(addImage(tree)), 4, i - 32);
                    gridPane.add(new GUICard(tree,addImage(tree)), 4, i - 32);
                }else if (i < 48){
//                    gridPane.add(new ImageView(addImage(tree)), 5, i - 40);
                    gridPane.add(new GUICard(tree,addImage(tree)), 5, i - 40);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        return gridPane;
    }

    private GridPane addDiscard(String discard) {
        String newArboretum = discard.substring(1);

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        if (!newArboretum.equals("")) {
            String[] trees = newArboretum.split("(?<=\\G.{" + 2 + "})");

            //Setting size for the pane
            gridPane.setMinSize(100, 50);

            //Setting the padding
            gridPane.setPadding(new Insets(10, 10, 10, 10));

            gridPane.setVgap(5);
            gridPane.setHgap(5);

            //Setting the Grid alignment
            gridPane.setAlignment(Pos.CENTER);
            for (int i = 0, treesLength = trees.length; i < treesLength; i++) {
                String tree = trees[i];
                try {
                    gridPane.add(new GUICard(tree,addImage(tree)), i, 0);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
            System.out.println(Math.pow(30, 35) % 29);

        }
        return gridPane;
    }


    private GridPane addArboretum(String arboretum) {
        String newArboretum = arboretum.substring(1);

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();
        if (!newArboretum.equals("")) {
            String[] trees = newArboretum.split("(?<=\\G.{" + 8 + "})");

            //Setting size for the pane
            gridPane.setMinSize(100, 50);

            //Setting the padding
            gridPane.setPadding(new Insets(10, 10, 10, 10));

            gridPane.setVgap(5);
            gridPane.setHgap(5);

            //Setting the Grid alignment
            gridPane.setAlignment(Pos.CENTER);
            HashMap<String, int[]> map = new HashMap<>();
            int[] startPos = {0, 0};
            int northest = 0;
            int westest = 0;
            for (String tree : trees) {
                String name = tree.substring(0, 2);
                String directionV = tree.substring(2, 3);
                int stepV = Integer.parseInt(tree.substring(3, 5));
                String directionH = tree.substring(5, 6);
                int stepH = Integer.parseInt(tree.substring(6));

                int newPosV = 0;
                int newPosH = 0;
                if (directionV.equals("C")) {
                    newPosV = startPos[0];
                }
                if (directionV.equals("N")) {
                    newPosV = startPos[0] - stepV;
                    if (northest > newPosV) {
                        northest = newPosV;
                    }
                }
                if (directionV.equals("S")) {
                    newPosV = startPos[0] + stepV;
                }
                if (directionH.equals("C")) {
                    newPosH = startPos[0];
                }
                if (directionH.equals("W")) {
                    newPosH = startPos[0] - stepH;
                    if (westest > newPosH) {
                        westest = newPosH;
                    }
                }
                if (directionH.equals("E")) {
                    newPosH = startPos[0] + stepH;
                }
                int[] newPos = {newPosV, newPosH};
                map.put(name, newPos);

                if (directionV.equals("C") && directionH.equals("C")) {
                    startPos = new int[]{0, 0};
                    map.put(name, startPos);
                }

            }

            for (String key : map.keySet()) {
                int[] value = map.get(key);
                value[0] = value[0] + Math.abs(northest);
                value[1] = value[1] + Math.abs(westest);
                try {
                    gridPane.add(new GUICard(key,addImage(key)), value[1], value[0]);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

        }
        return gridPane;
    }
}
