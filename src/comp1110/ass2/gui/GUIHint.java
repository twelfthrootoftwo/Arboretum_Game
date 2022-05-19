package comp1110.ass2.gui;

import comp1110.ass2.game.Arbor;
import comp1110.ass2.game.Player;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GUIHint extends Group {
    Game game;

    //sizes of arbor
    public final int ARBOR_X_SIZE;
    public final int ARBOR_Y_SIZE;

    Text HintText;

    //constant used
    String newLine = "\n";
    String heading;
    String test;

    public GUIHint(Game game, int xSizeArbor, int ySizeArbor, int xPosArbor, int yPosArbor) {
        this.game=game;
        this.ARBOR_X_SIZE=xSizeArbor;
        this.ARBOR_Y_SIZE=ySizeArbor;
        this.setLayoutX(xPosArbor);
        this.setLayoutY(yPosArbor);
        heading = "Hint: ";

        HintText = new Text(heading);

        String font_name = Font.getFamilies().get(25);
        int size = 20;
        Font font = Font.font(font_name, FontPosture.REGULAR, size);
        HintText.setFont(font);
        this.getChildren().add(HintText);
    }

    public void update(){
        if (game.getTurnState() == TurnState.firstDraw  || game.getTurnState() == TurnState.begin){
            this.test = "Draw Two cards from deck or discard.";
        }else if ( game.getTurnState() == TurnState.secondDraw){
            this.test = "    Play one card to your Arboretum.";
        }else if (game.getTurnState() == TurnState.play){
            this.test = "  Drop one card to your DiscardPile.";
        }else if (game.getTurnState() == TurnState.end){
            this.test = "        Click Next to start.";
        }else if (game.getTurnState() == TurnState.discard){
            this.test = "       Click Next to continue.";
        }else {
            this.test = "";
        }

        this.getChildren().remove(HintText);
        HintText.setText(heading+newLine+test);
        this.getChildren().add(HintText);
    }

}

