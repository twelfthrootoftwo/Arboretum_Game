package comp1110.ass2.gui;

import comp1110.ass2.game.Card;
import comp1110.ass2.game.Deck;
import comp1110.ass2.game.Player;
import comp1110.ass2.game.Position;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GUItest extends Application {
    Group root;
    Scene scene;
    Deck deck;
    Player playerA;
    Player playerB;
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
    public GUItest() throws FileNotFoundException {}

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.root = new Group();
        this.scene = new Scene(root, 1200, 700);
        this.deck = new Deck(6);
        this.playerA = new Player("A",6);
        this.playerB = new Player("B",6);

        GUIArbor arborA=new GUIArbor(playerA,550,500,10,10,5);
        GUIArbor arborB=new GUIArbor(playerB,550,500,590,10,5);
        arborA.startTurn();
        arborB.startTurn();

        root.getChildren().add(arborA);
        root.getChildren().add(arborB);


        GUICard[] cards=new GUICard[4];
        cards[0]=new GUICard(new Card("a1"), this.root);
        cards[1]=new GUICard(new Card("b1"), this.root);
        cards[2]=new GUICard(new Card("c1"), this.root);
        cards[3]=new GUICard(new Card("d1"), this.root);
        int[] xCoords=new int[]{100,300,500,700};
        int yCoord=550;
        for(int i=0;i<cards.length;i++) {
            cards[i].setLayoutX(xCoords[i]);
            cards[i].setLayoutY(yCoord);
            root.getChildren().add(cards[i]);
        }

        stage.setScene(scene);
        stage.show();
    }
}
