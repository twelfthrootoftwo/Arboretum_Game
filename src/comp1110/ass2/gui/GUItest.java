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
    public GUItest() throws FileNotFoundException {}

//    public static void main(String[] args) {
//        launch(args);
//    }

    @Override
    public void start(Stage stage) {
        this.root = new Group();
        this.scene = new Scene(root, 1200, 700);
        this.deck = new Deck(6);
        this.playerA = new Player("A",6);
        this.playerB = new Player("B",6);

        GUIArbor arborA=new GUIArbor(playerA,550,500,10,10,5);
        GUIArbor arborB=new GUIArbor(playerB,550,500,640,10,5);
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
