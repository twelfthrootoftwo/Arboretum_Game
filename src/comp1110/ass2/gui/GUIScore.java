package comp1110.ass2.gui;

import comp1110.ass2.Arboretum;
import comp1110.ass2.game.Arbor;
import comp1110.ass2.game.Player;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.List;

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

    /**
     * Contribution: Natasha, Hongzhe
     * Alternate constructor that just specifies x and y position
     * @param player - the Player to track
     * @param x - the x position
     * @param y - the y position
     * @param margin - offset between x and y positions and the text display
     */
    public GUIScore(Player player, double x, double y, int margin) {
        this.player=player;
        this.arbor=player.getArboretum();
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.heading = player +
                " Score: " + newLine;
        this.ARBOR_X_SIZE=0;
        this.ARBOR_Y_SIZE=0;

        scoreText = new Text(heading);
        scoreText.setX(x+margin);
        scoreText.setY(y+margin);
        String font_name = Font.getFamilies().get(25);
        int size = 13;
        Font font = Font.font(font_name, FontWeight.BOLD, FontPosture.REGULAR, size);
        scoreText.setFont(font);
        this.getChildren().add(scoreText);
    }
    /**
     * Contribution: Hongzhe
     * Updates the score with the latest information
     */
    public void update(){
        this.score = "";
        for (String species : arbor.currentScore().keySet()){
            this.score += Arboretum.speciesFullName(species) + " : " + arbor.currentScore().get(species);
            this.score += newLine;
        }
        this.getChildren().remove(scoreText);
        scoreText.setText(heading+score);
        this.getChildren().add(scoreText);
    }

    /**
     * Contribution: Natasha
     * Variant update that only displays certain species according to input
     * Used for the post-game display screen
     * @param speciesList - a list of strings that should represent species one-letter codes
     * @return an int as the total score for this set of species
     */
    public int updateSetSpecies(List<String> speciesList){
        int scoreVal=0;
        this.score = "";

        //for each species in list, add a line to the display and increase the total score
        for (String species : speciesList){
            if(arbor.currentScore().get(species)!=null) {
                int speciesScore = arbor.currentScore().get(species);
                this.score += Arboretum.speciesFullName(species) + " : " + speciesScore;
                this.score += newLine;
                scoreVal += speciesScore;
            }
        }
        this.score+="Total score: "+scoreVal;

        //change text
        this.getChildren().remove(scoreText);
        scoreText.setText(heading+score);
        this.getChildren().add(scoreText);

        return scoreVal;
    }


}

