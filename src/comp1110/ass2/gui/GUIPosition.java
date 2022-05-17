package comp1110.ass2.gui;

import comp1110.ass2.game.Arbor;
import comp1110.ass2.game.Position;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GUIPosition extends Rectangle {
    Arbor arbor;
    GUIArbor guiArbor;

    Position position;
    GUICard playedCard;

    private final double ARBOR_X;
    private final double ARBOR_Y;
    double xCoord;
    double yCoord;

    int margin=2;


    public GUIPosition(Arbor arbor, Position pos,GUIArbor gui) {
        super();
        this.position=pos;
        this.arbor=arbor;
        this.setFill(Color.LIGHTGREY);
        this.guiArbor=gui;
        this.playedCard=null;

        this.ARBOR_X=gui.getLayoutX();
        this.ARBOR_Y=gui.getLayoutY();

        //initialise starting positions - these will be overridden before display
        this.updateCoord(0,0);
        this.updateScale(50,50);
    }

    /**
     * Contribution: Natasha
     * Move this GUIPosition to a new location within its GUIArbor
     * @param x - the new x coordinate
     * @param y - the new y coordinate
     */
    public void updateCoord(double x, double y) {
        //position this at the specified coordinates, with a margin applied
        this.setLayoutX(x+margin);
        this.setLayoutY(y+margin);

        //set the stored coordinates for this position to the centre (rather than the top left)
        //this is so that the formula for finding nearby positions calculates distance the centre
        this.xCoord=x+(this.getWidth()/2)+this.ARBOR_X;
        this.yCoord=y+(this.getHeight()/2)+this.ARBOR_Y;

        //if there's a card here, also move the card
        if(this.playedCard!=null) this.playedCard.updateCoord(x+ARBOR_X+margin,y+ARBOR_Y+margin);
    }

    /**
     * Contribution: Natasha
     * Changes the display size of this GUIPosition
     * @param xSize - the new display width
     * @param ySize - the new display height
     */
    public void updateScale(double xSize,double ySize) {
        //change size of this slot, incorporating a margin
        this.setWidth(xSize-margin*2);
        this.setHeight(ySize-margin*2);

        //if there's a card here, also change its scale
        if(this.playedCard!=null) this.playedCard.updateScale(xSize,ySize);
    }

    /**
     * Contribution: Natasha
     * Finds the distance from specified coordinates to the centre of this slot
     * Used for identifying the closest position
     * @param x - x coord to check
     * @param y - y coord to check
     * @return a double representing the distance between the input coord and the centre of this GUIPosition
     */
    public double distance(double x, double y) {
        double xDiff=x-this.xCoord;
        double yDiff=y-this.yCoord;

        double dist=Math.sqrt(xDiff*xDiff+yDiff*yDiff);
        return dist;
    }

    /**
     * Contribution: Natasha
     * Checks if the Position associated with this slot can be played to
     * @return True if this is a legal play position, False otherwise
     */
    public boolean canPlayArbor() {
        return this.arbor.isPosCanPlace(this.position);
    }

    /**
     * Contribution: Natasha
     * Play a card to this position
     * Associates the GUICard with this slot, and updates the backend board to mark the contents of the associated Position
     * @param card - a GUICard to play here
     * @return True if the play was legal and has been processed, False otherwise
     */
    public boolean playHere(GUICard card) {
        //first check the play is legal
        if(this.canPlayArbor()) {
            //register the played card to the arbor
            this.guiArbor.player.play(card.getCard(),this.position);

            //store the GUICard here
            card.setLayoutX(this.getLayoutX());
            card.setLayoutY(this.getLayoutY());
            this.playedCard=card;

            //update display
            this.guiArbor.update();

            return true;
        }
        return false;
    }
}
