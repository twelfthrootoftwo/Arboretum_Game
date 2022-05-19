package comp1110.ass2.gui;

import comp1110.ass2.Arboretum;
import comp1110.ass2.game.Player;
import comp1110.ass2.game.Species;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GUIEnding extends Group {
    public Player winner;

    Rectangle endBox;
    Text endText;
    String content;
    String font_name = Font.getFamilies().get(25);
    int size = 25;
    Font winnerAnnounce = Font.font(font_name, FontWeight.BOLD, FontPosture.REGULAR, size);

    public GUIEnding(Game game, Player player1, Player player2, double x, double y, double boxSizeX, double boxSizeY, String[][] gameState) {
        GUIScore p1ScoreDisplay=new GUIScore(player1,5,5,5);
        GUIScore p2ScoreDisplay=new GUIScore(player2,boxSizeX*0.25,5,5);

        LinkedList<String> p1ScoreSpecies=new LinkedList<String>();
        LinkedList<String> p2ScoreSpecies=new LinkedList<String>();

        for(Species s:Species.values()) {
            String specString=s.toString();
            char specChar=specString.charAt(0);

            //check both players for the right to score
            //if they can score that species, add the species to the list to score
            if(Arboretum.canScore(gameState,player1.getName().charAt(0),specChar)) {
                p1ScoreSpecies.add(specString);
            }
            if(Arboretum.canScore(gameState,player2.getName().charAt(0),specChar)) {
                p2ScoreSpecies.add(specString);
            }
        }
        int p1TotalScore=p1ScoreDisplay.updateSetSpecies(p1ScoreSpecies);
        int p2TotalScore=p2ScoreDisplay.updateSetSpecies(p2ScoreSpecies);

        //assign winner and text for the player with the higher score
        if(p1TotalScore>p2TotalScore) {
            this.winner = player1;
            this.content = "---------GAME OVER---------" +
                    '\n' + " " +'\n' + "                " +  player1.getName() + " Wins!";
        } else if (p1TotalScore<p2TotalScore) {
            this.winner = player2;
            this.content = "---------GAME OVER---------" +
                    '\n' + " " +'\n' + "                " +  player2.getName() + " Wins!";
        } else {
            //tie!
            this.content="---------GAME OVER---------" +
                    '\n'+"It's a tie!\nBoth players plant a tree\nWhoever has the biggest tree in 5 years wins!";
        }

        Button restart   = new Button("New Game");
        restart.setStyle("-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;" +
                "-fx-background-radius: 8; " +
                "-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),#9d4024,#d86e3a,radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);");
        restart.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                game.displayApplication();
            }
        }));
        restart.setLayoutX(boxSizeX*0.37);
        restart.setLayoutY(boxSizeY*0.85);
        endBox = new Rectangle(boxSizeX, boxSizeY);
        endBox.setFill(Color.WHITE);
        endBox.setX(0);
        endBox.setY(0);

        endText = new Text(content);
        endText.setX(0);
        endText.setY(boxSizeY*0.55);
        endText.setFont(winnerAnnounce);

        this.getChildren().addAll(endBox, endText,p1ScoreDisplay,p2ScoreDisplay,restart);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
}
