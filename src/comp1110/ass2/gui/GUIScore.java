package comp1110.ass2.gui;

import comp1110.ass2.game.Arbor;
import comp1110.ass2.game.Player;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GUIScore extends Group {
    public Player player;
    private Arbor arbor;

    //sizes of arbor
    public final int ARBOR_X_SIZE;
    public final int ARBOR_Y_SIZE;

    Text scoreText;

    //constant used
    String newLine = "\n";
    String heading;
    String score;

    public GUIScore(Player player, int xSizeArbor, int ySizeArbor, int xPosArbor, int yPosArbor, boolean inverse) {
        this.player=player;
        this.arbor=player.getArboretum();
        this.ARBOR_X_SIZE=xSizeArbor;
        this.ARBOR_Y_SIZE=ySizeArbor;
        this.setLayoutX(xPosArbor);
        this.setLayoutY(yPosArbor);
        this.heading = player +
                " Score: " + newLine;

        scoreText = new Text(heading);
        if (inverse) scoreText.setX(xPosArbor-ARBOR_X_SIZE*1.85);
        else scoreText.setX(xPosArbor+ARBOR_X_SIZE);
        scoreText.setY(yPosArbor);
        String font_name = Font.getFamilies().get(25);
        int size = 13;
        Font font = Font.font(font_name, FontWeight.BOLD, FontPosture.REGULAR, size);
        scoreText.setFont(font);
        this.getChildren().add(scoreText);
    }

    public void update(){
        this.score = "";
        for (String species : arbor.currentScore().keySet()){
            this.score += speciesFullName(species) + " : " + arbor.currentScore().get(species);
            this.score += newLine;
        }
        this.getChildren().remove(scoreText);
        scoreText.setText(heading+score);
        this.getChildren().add(scoreText);
    }

    /** Contribution: Hongzhe
     * Convert the short name to the full name of a species.
     * @param shortName the short name of the species
     * @return the full name of the species
     */
    public String speciesFullName(String shortName){
        if (shortName == "a") return "Cassia";
        else if (shortName == "b") return "BlueSpruce";
        else if (shortName == "c") return "Cherry";
        else if (shortName == "d") return "Dogwood";
        else if (shortName == "j") return "Jacaranda";
        else return "Maple";
    }
}

