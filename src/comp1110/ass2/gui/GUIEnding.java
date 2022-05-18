package comp1110.ass2.gui;


import comp1110.ass2.game.Arbor;
import comp1110.ass2.game.Player;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GUIEnding extends Group {
    public Player winner;
    private Arbor arbor;

    Rectangle endBox;
    Text endText;
    String content;

    public GUIEnding(Player winner, int x, int y, int boxSizeX, int boxSizeY) {
        this.winner=winner;
        this.arbor=winner.getArboretum();
        this.content = winner + " Wins!";

        endBox = new Rectangle(boxSizeX, boxSizeY);
        endBox.setFill(Color.WHITE);
        endBox.setX(x);
        endBox.setY(y);
        endText = new Text(content);
        endText.setX(x);
        endText.setY(y);
        this.getChildren().addAll(endBox, endText);
    }
}
