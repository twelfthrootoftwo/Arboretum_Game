package comp1110.ass2.gui;

import comp1110.ass2.game.Arbor;
import comp1110.ass2.game.Position;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GUIPosition extends Rectangle {
    Position position;
    double xCoord;
    double yCoord;
    Arbor arbor;
    GUIArbor guiArbor;
    int margin=2;
    GUICard playedCard;

    public GUIPosition(Arbor arbor, Position pos,GUIArbor gui) {
        super();
        this.updateCoord(0,0);
        this.updateScale(50,50);
        this.position=pos;
        this.arbor=arbor;
        this.setFill(Color.LIGHTGREY);
        this.guiArbor=gui;
        this.playedCard=null;
    }


    public void updateCoord(double x, double y) {
        this.setLayoutX(x+margin);
        this.xCoord=x+(this.getWidth()/2);
        this.setLayoutY(y+margin);
        this.yCoord=y+(this.getHeight()/2);
        if(this.playedCard!=null) this.playedCard.updateCoord(x+margin,y+margin);
    }

    public void updateScale(double xSize,double ySize) {
        this.setWidth(xSize-margin*2);
        this.setHeight(ySize-margin*2);
        if(this.playedCard!=null) this.playedCard.updateScale(xSize,ySize);
    }

    public double distance(double x, double y) {
        double xDiff=x-this.xCoord;
        double yDiff=y-this.yCoord;

        double dist=Math.sqrt(xDiff*xDiff+yDiff*yDiff);
        return dist;
    }

    public boolean canPlay() {
        return this.arbor.isPosCanPlace(this.position);
    }

    public boolean playHere(GUICard card) {
        if(this.canPlay()) {
            this.arbor.addCard(card.getCard(),this.position);
            card.setLayoutX(this.getLayoutX());
            card.setLayoutY(this.getLayoutY());
            this.playedCard=card;
            this.guiArbor.update();
            return true;
        }
        return false;
    }
}
