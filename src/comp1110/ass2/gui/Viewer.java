package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1200;//1200
    private static final int VIEWER_HEIGHT = 700;//700
    private static final int GRID_SIZE = 50;
    private static final int GRID_DIMENSION = 10;
    private static final int WINDOW_XOFFSET = 10;
    private static final int WINDOW_YOFFSET = 30;
    private static final int TEXTBOX_WIDTH = 120;

    private final Group root = new Group();
    private final Group controls = new Group();

    private TextField turnIDTextField;
    private TextField aArboretumTextField;
    private TextField bArboretumTextField;
    private TextField aDiscardTextField;
    private TextField bDiscardTextField;
    private TextField deckTextField;
    private TextField aHandTextField;
    private TextField bHandTextField;

    Image a1 = new Image(new FileInputStream("assets/cards/a_01.png"), 91, 91, true, true);
    Image a2 = new Image(new FileInputStream("assets/cards/a_02.png"), 91, 91, true, true);
    Image a3 = new Image(new FileInputStream("assets/cards/a_03.png"), 91, 91, true, true);
    Image a4 = new Image(new FileInputStream("assets/cards/a_04.png"), 91, 91, true, true);
    Image a5 = new Image(new FileInputStream("assets/cards/a_05.png"), 91, 91, true, true);
    Image a6 = new Image(new FileInputStream("assets/cards/a_06.png"), 91, 91, true, true);
    Image a7 = new Image(new FileInputStream("assets/cards/a_07.png"), 91, 91, true, true);
    Image a8 = new Image(new FileInputStream("assets/cards/a_08.png"), 91, 91, true, true);

    Image b1 = new Image(new FileInputStream("assets/cards/b_01.png"), 91, 91, true, true);
    Image b2 = new Image(new FileInputStream("assets/cards/b_02.png"), 91, 91, true, true);
    Image b3 = new Image(new FileInputStream("assets/cards/b_03.png"), 91, 91, true, true);
    Image b4 = new Image(new FileInputStream("assets/cards/b_04.png"), 91, 91, true, true);
    Image b5 = new Image(new FileInputStream("assets/cards/b_05.png"), 91, 91, true, true);
    Image b6 = new Image(new FileInputStream("assets/cards/b_06.png"), 91, 91, true, true);
    Image b7 = new Image(new FileInputStream("assets/cards/b_07.png"), 91, 91, true, true);
    Image b8 = new Image(new FileInputStream("assets/cards/b_08.png"), 91, 91, true, true);

    Image c1 = new Image(new FileInputStream("assets/cards/c_01.png"), 91, 91, true, true);
    Image c2 = new Image(new FileInputStream("assets/cards/c_02.png"), 91, 91, true, true);
    Image c3 = new Image(new FileInputStream("assets/cards/c_03.png"), 91, 91, true, true);
    Image c4 = new Image(new FileInputStream("assets/cards/c_04.png"), 91, 91, true, true);
    Image c5 = new Image(new FileInputStream("assets/cards/c_05.png"), 91, 91, true, true);
    Image c6 = new Image(new FileInputStream("assets/cards/c_06.png"), 91, 91, true, true);
    Image c7 = new Image(new FileInputStream("assets/cards/c_07.png"), 91, 91, true, true);
    Image c8 = new Image(new FileInputStream("assets/cards/c_08.png"), 91, 91, true, true);

    Image d1 = new Image(new FileInputStream("assets/cards/d_01.png"), 91, 91, true, true);
    Image d2 = new Image(new FileInputStream("assets/cards/d_02.png"), 91, 91, true, true);
    Image d3 = new Image(new FileInputStream("assets/cards/d_03.png"), 91, 91, true, true);
    Image d4 = new Image(new FileInputStream("assets/cards/d_04.png"), 91, 91, true, true);
    Image d5 = new Image(new FileInputStream("assets/cards/d_05.png"), 91, 91, true, true);
    Image d6 = new Image(new FileInputStream("assets/cards/d_06.png"), 91, 91, true, true);
    Image d7 = new Image(new FileInputStream("assets/cards/d_07.png"), 91, 91, true, true);
    Image d8 = new Image(new FileInputStream("assets/cards/d_08.png"), 91, 91, true, true);

    Image j1 = new Image(new FileInputStream("assets/cards/j_01.png"), 91, 91, true, true);
    Image j2 = new Image(new FileInputStream("assets/cards/j_02.png"), 91, 91, true, true);
    Image j3 = new Image(new FileInputStream("assets/cards/j_03.png"), 91, 91, true, true);
    Image j4 = new Image(new FileInputStream("assets/cards/j_04.png"), 91, 91, true, true);
    Image j5 = new Image(new FileInputStream("assets/cards/j_05.png"), 91, 91, true, true);
    Image j6 = new Image(new FileInputStream("assets/cards/j_06.png"), 91, 91, true, true);
    Image j7 = new Image(new FileInputStream("assets/cards/j_07.png"), 91, 91, true, true);
    Image j8 = new Image(new FileInputStream("assets/cards/j_08.png"), 91, 91, true, true);

    Image m1 = new Image(new FileInputStream("assets/cards/m_01.png"), 91, 91, true, true);
    Image m2 = new Image(new FileInputStream("assets/cards/m_02.png"), 91, 91, true, true);
    Image m3 = new Image(new FileInputStream("assets/cards/m_03.png"), 91, 91, true, true);
    Image m4 = new Image(new FileInputStream("assets/cards/m_04.png"), 91, 91, true, true);
    Image m5 = new Image(new FileInputStream("assets/cards/m_05.png"), 91, 91, true, true);
    Image m6 = new Image(new FileInputStream("assets/cards/m_06.png"), 91, 91, true, true);
    Image m7 = new Image(new FileInputStream("assets/cards/m_07.png"), 91, 91, true, true);
    Image m8 = new Image(new FileInputStream("assets/cards/m_08.png"), 91, 91, true, true);

    public Viewer() throws FileNotFoundException {
    }

    /**
     * Draw a placement in the window, removing any previously drawn placements
     *
     * @param gameState TASK 6
     */
    void displayState(String[][] gameState) {

//        System.out.println(Arrays.deepToString(gameState));
        String[] sharedState = gameState[0];
        String[] hiddenState = gameState[1];
//        System.out.println(Arrays.toString(sharedState));
//        System.out.println(Arrays.toString(hiddenState));

        String turn = sharedState[0];
        String arboretumA = sharedState[1];
        String discardA = sharedState[2];
        String arboretumB = sharedState[3];
        String discardB = sharedState[4];
        String deck = hiddenState[0];
        String handA = hiddenState[1];
        String handB = hiddenState[2];
        if (!Objects.equals(turn, "") && !Objects.equals(arboretumA, "") && !Objects.equals(discardA, "") && !Objects.equals(arboretumB, "") && !Objects.equals(discardB, "") && !Objects.equals(deck, "") && !Objects.equals(handA, "") && !Objects.equals(handB, "")) {

            Label arboretum_A = new Label("Player A arboretum: " + arboretumA);
            Label discard_A = new Label("Player A discard: " + discardA);
            Label hand_A = new Label("Player A hand(hidden): " + handA);
            Label arboretum_B = new Label("Player B arboretum: " + arboretumB);
            Label discard_B = new Label("Player B discard: " + discardB);
            Label hand_B = new Label("Player B hand(hidden): " + handB);
            Label deckC = new Label("Deck(hidden): " + deck);
            Label turnP = new Label("turn: " + turn);


            HBox sharedA = new HBox();

            sharedA.setBackground(new Background(new BackgroundFill(Color.rgb(185, 230, 160), CornerRadii.EMPTY, Insets.EMPTY)));

            VBox arboretumABox = new VBox();
            GridPane gridPaneSA = addArboretum(arboretumA);
            arboretumABox.getChildren().addAll(arboretum_A, gridPaneSA);

            VBox discardABox = new VBox();

            VBox hiddenA = new VBox();
            hiddenA.setBackground(new Background(new BackgroundFill(Color.rgb(189, 189, 189), CornerRadii.EMPTY, Insets.EMPTY)));
            GridPane gridPaneHA = addHand(handA);
            hiddenA.getChildren().addAll(hand_A, gridPaneHA);

            GridPane gridPaneDA = addDiscard(discardA);
            discardABox.getChildren().addAll(discard_A, gridPaneDA, hiddenA);

            sharedA.getChildren().addAll(arboretumABox, discardABox);

//        HBox hiddenA = new HBox();
//        GridPane gridPaneHA = addHand(handA);
//        hiddenA.getChildren().addAll(hand_A,gridPaneHA);


            HBox sharedB = new HBox();

            sharedB.setBackground(new Background(new BackgroundFill(Color.rgb(185, 230, 160), CornerRadii.EMPTY, Insets.EMPTY)));

            VBox arboretumBBox = new VBox();
            GridPane gridPaneSB = addArboretum(arboretumB);
            arboretumBBox.getChildren().addAll(arboretum_B, gridPaneSB);

            VBox discardBBox = new VBox();

            VBox hiddenB = new VBox();
            hiddenB.setBackground(new Background(new BackgroundFill(Color.rgb(189, 189, 189), CornerRadii.EMPTY, Insets.EMPTY)));
            GridPane gridPaneHB = addHand(handB);
            hiddenB.getChildren().addAll(hand_B, gridPaneHB);

            GridPane gridPaneDB = addDiscard(discardB);
            discardBBox.getChildren().addAll(discard_B, gridPaneDB, hiddenB);


            sharedB.getChildren().addAll(arboretumBBox, discardBBox);

//        HBox hiddenB = new HBox();
//        GridPane gridPaneHB = addHand(handB);
//        hiddenB.getChildren().addAll(hand_B,gridPaneHB);


            VBox vboxD = new VBox();
            vboxD.setBackground(new Background(new BackgroundFill(Color.rgb(230, 90, 90), CornerRadii.EMPTY, Insets.EMPTY)));
            VBox discardDBox = new VBox();
            discardDBox.setBackground(new Background(new BackgroundFill(Color.rgb(189, 189, 189), CornerRadii.EMPTY, Insets.EMPTY)));
            GridPane gridPaneDD = addDeck(deck);
            discardDBox.getChildren().addAll(deckC, gridPaneDD);

            vboxD.getChildren().addAll(discardDBox, turnP);


            VBox vboxS = new VBox();
            vboxS.getChildren().addAll(sharedA, sharedB);

//        VBox vboxH = new VBox();
//        vboxH.getChildren().addAll(sharedA,sharedB);

            HBox all = new HBox();
            all.getChildren().addAll(vboxS, vboxD);

            root.getChildren().addAll(all);

        }else {
            System.out.println("some inputs are missing...");
        }
        // FIXME Task 6: implement the simple state viewer
    }

    private Image addImage(String img) throws FileNotFoundException {

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

    private GridPane addHand(String hand) {

        String newArboretum = hand.substring(1);

        String[] trees = newArboretum.split("(?<=\\G.{" + 2 + "})");
//        System.out.println(Arrays.toString(chunks));

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(200, 100);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);
        for (int i = 0, treesLength = trees.length; i < treesLength; i++) {
            String tree = trees[i];
            try {
                gridPane.add(new ImageView(addImage(tree)), i, 0);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        return gridPane;
    }

    private GridPane addDeck(String discard) {


        String[] trees = discard.split("(?<=\\G.{" + 2 + "})");
//        System.out.println(Arrays.toString(chunks));

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(200, 100);

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
                    gridPane.add(new ImageView(addImage(tree)), 0, i);
                } else {
                    gridPane.add(new ImageView(addImage(tree)), 1, i - 8);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        return gridPane;
    }

    private GridPane addDiscard(String discard) {

        String newArboretum = discard.substring(1);

        String[] trees = newArboretum.split("(?<=\\G.{" + 2 + "})");
//        System.out.println(Arrays.toString(chunks));

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(200, 100);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);
        for (int i = 0, treesLength = trees.length; i < treesLength; i++) {
            String tree = trees[i];
            try {
                gridPane.add(new ImageView(addImage(tree)), i, 0);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        return gridPane;
    }

    private GridPane addArboretum(String arboretum) {

        String newArboretum = arboretum.substring(1);

        String[] trees = newArboretum.split("(?<=\\G.{" + 8 + "})");
//        System.out.println(Arrays.toString(chunks));

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(400, 200);

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
//            System.out.println(name + " " + directionV + " " + stepV+ " " + directionH + " " + stepH);

            int newPosV = 0;
            int newPosH = 0;
            if (directionV.equals("C")) {
                newPosV = startPos[0];
            }
            if (directionV.equals("N")) {
                newPosV = startPos[0] - stepV;
//                System.out.println(newPosV);
//                System.out.println(northest);
                if (northest > newPosV) {
                    northest = newPosV;
                }
//                System.out.println(northest);
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
//            System.out.println(Arrays.toString(newPos));

            if (directionV.equals("C") && directionH.equals("C")) {

                startPos = new int[]{0, 0};
                map.put(name, startPos);
//                gridPane.add(new Button(name),0,0);
            }

        }

//        gridPane.add(text1, 0, 0);
//        gridPane.add(textField1, 1, 0);
//        gridPane.add(text2, 0, 1);
//        gridPane.add(textField2, 1, 1);
//        gridPane.add(button1, 0, 2);
//        gridPane.add(button2, 1, 2);
//        System.out.println(map.values());
//        System.out.println(northest+ "  " +westest);

        for (String key : map.keySet()) {
            int[] value = map.get(key);
            value[0] = value[0] + Math.abs(northest);
            value[1] = value[1] + Math.abs(westest);
//            System.out.println(key+ "  " +Arrays.toString(value));
            try {
                gridPane.add(new ImageView(addImage(key)), value[1], value[0]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        return gridPane;
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label boardLabel = new Label("Player Turn ID");
        turnIDTextField = new TextField();
        turnIDTextField.setPrefWidth(TEXTBOX_WIDTH);
//        Label aArboretum = new Label("Player A comp1100.ass2.Arboretum:");
        Label aArboretum = new Label("Player A Arboretum:");
        aArboretumTextField = new TextField();
        aArboretumTextField.setPrefWidth(TEXTBOX_WIDTH);
        Label aDiscard = new Label("Player A Discard:");
        aDiscardTextField = new TextField();
        aDiscardTextField.setPrefWidth(TEXTBOX_WIDTH);
//        Label bArboretum = new Label("Player B comp1100.ass2.Arboretum:");
        Label bArboretum = new Label("Player B Arboretum:");
        bArboretumTextField = new TextField();
        bArboretumTextField.setPrefWidth(TEXTBOX_WIDTH);
        Label bDiscard = new Label("Player B Discard:");
        bDiscardTextField = new TextField();
        bDiscardTextField.setPrefWidth(TEXTBOX_WIDTH);
        Label deck = new Label("Deck:");
        deckTextField = new TextField();
        deckTextField.setPrefWidth(TEXTBOX_WIDTH);
        Label aHand = new Label("Player A Hand:");
        aHandTextField = new TextField();
        aHandTextField.setPrefWidth(TEXTBOX_WIDTH);
        Label bHand = new Label("Player B Discard:");
        bHandTextField = new TextField();
        bHandTextField = new TextField();
        bHandTextField.setPrefWidth(TEXTBOX_WIDTH);

        Button displayState = new Button("Display State");
        displayState.setOnAction(e -> {
            String[] sharedState = {turnIDTextField.getText(), aArboretumTextField.getText(),
                    aDiscardTextField.getText(), bArboretumTextField.getText(), bDiscardTextField.getText()};
//            String[] sharedState = {"A", "Ab1C00C00a4N01C00c3C00E01c6N02C00m7N02W01m4N01E01a5N02E01a2S01E01", "Aa7c4d6", "Bj5C00C00j6S01C00j7S02W01j4C00W01m6C00E01m3C00W02j3N01W01", "Bb2d4c5a1d5"};
            String[] hiddenState = {deckTextField.getText(), aHandTextField.getText(), bHandTextField.getText()};
//            String[] hiddenState = {"a3a8b5b6c2c7d1d3d7d8m1", "Ab3b4c1j1m2m5m8", "Ba6b7b8c8d2j2j8"};
            displayState(new String[][]{sharedState, hiddenState});//[[A, Ab1C00C00a4N01C00c3C00E01c6N02C00m7N02W01m4N01E01a5N02E01a2S01E01, Aa7c4d6, Bj5C00C00j6S01C00j7S02W01j4C00W01m6C00E01m3C00W02j3N01W01, Bb2d4c5a1d5], [a3a8b5b6c2c7d1d3d7d8m1, Ab3b4c1j1m2m5m8, Ba6b7b8c8d2j2j8]]

        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(boardLabel, turnIDTextField, aArboretum, aArboretumTextField, aDiscard,
                aDiscardTextField, bArboretum, bArboretumTextField, bDiscard, bDiscardTextField, deck, deckTextField,
                aHand, aHandTextField, bHand, bHandTextField, displayState);
        vbox.setSpacing(10);
        vbox.setLayoutX(10.4 * (GRID_SIZE) + (2 * WINDOW_XOFFSET) + (GRID_DIMENSION * GRID_SIZE) + (0.5 * GRID_SIZE));
        vbox.setLayoutY(WINDOW_YOFFSET);

        controls.getChildren().add(vbox);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("comp1110.ass2.Arboretum Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

