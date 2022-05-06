package comp1110.ass2.gui;

import comp1110.ass2.game.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.LinkedList;

public class GUIArbor extends Group {
    private Player player;
    private Arbor arbor;
    Group root;
    private boolean thisTurn;//whether it's the turn of this arbor's player

    //coordinate reference limits for positions
    private int firstColX;
    private int firstRowY;
    private int lastColX;
    private int lastRowY;

    //sizes of arbor and position slots
    public final int ARBOR_X_SIZE;
    public final int ARBOR_Y_SIZE;
    private double POS_X_SIZE;
    private double POS_Y_SIZE;


    private LinkedList<LinkedList<GUIPosition>> positions;
    GUIPosition highlighted;
    private int margin;//width of border
    private final double CARD_RATIO=1.4;//height to width ratio of cards and position slots

    //display elements
    Rectangle borderOutside;
    Rectangle borderInside;

    public GUIArbor(Player player, int xSize,int ySize, int xPos, int yPos,int margin) {
        this.player=player;
        this.arbor=player.getArboretum();
        this.ARBOR_X_SIZE=xSize;
        this.ARBOR_Y_SIZE=ySize;
        this.margin=margin;
        this.highlighted=null;
        this.setLayoutX(xPos);
        this.setLayoutY(yPos);

        //link player to this display
        player.setDisplayArbor(this);

        //create a border to mark the visual bounds of the arbor
        //TODO - make the border colour/width change to indicate whether the turn is active
        borderOutside=new Rectangle(xSize,ySize);
        borderInside=new Rectangle(xSize-margin*2,ySize-margin*2);
        borderOutside.setFill(Color.LIGHTGREY);
        borderInside.setFill(Color.WHITE);
        borderInside.setLayoutX(margin);
        borderInside.setLayoutY(margin);
        this.getChildren().addAll(borderOutside,borderInside);

        //establish a starting position at (0,0) - this will be expanded into a 3x3 grid before initial display
        this.positions=new LinkedList<LinkedList<GUIPosition>>();
        GUIPosition origin=addNewPosition(0,0);
        LinkedList<GUIPosition> start=new LinkedList<>();
        start.add(origin);
        this.positions.add(start);

        //prepare display
        this.update();
    }

    /**
     * Contribution: Natasha
     * Updates the position display for the new game state
     */
    public void update() {
        this.updateScale();//find the new position slot sizes
        this.updatePosDisplay();//change the position display with new numbers and sizes
        this.layout();//?
        this.layoutChildren();//?
    }

    /**
     * contribution: Natasha
     * Adds something to the arbor as per getChildren.add()
     * @param n - a Node to add to the display
     */
    public void add(Node n) {
        this.getChildren().add(n);
    }

    //Turn boolean handlers
    public Boolean isTurn() {return this.thisTurn;}
    public void endTurn() {
        this.thisTurn=false;
        this.borderOutside.setFill(Color.LIGHTGREY);
        this.borderInside.setLayoutX(margin);
        this.borderInside.setLayoutY(margin);
        this.borderInside.setWidth(ARBOR_X_SIZE-margin*2);
        this.borderInside.setHeight(ARBOR_Y_SIZE-margin*2);
        update();
    }
    public void startTurn() {
        this.thisTurn=true;
        this.borderOutside.setFill(Color.AQUAMARINE);
        this.borderInside.setLayoutX(margin+1);
        this.borderInside.setLayoutY(margin+1);
        this.borderInside.setWidth(ARBOR_X_SIZE-margin*2-2);
        this.borderInside.setHeight(ARBOR_Y_SIZE-margin*2-2);
        update();
    }

    /**
     * Contribution: Natasha
     * Adds new GUIPositions as needed
     * This function will always keep an extra empty row/column around the outside, so that all playable positions are displayed
     * TODO - refactor?
     */
    private void updatePosArray() {
        int xMax=0;
        int yMax=0;
        int xMin=0;
        int yMin=0;

        //Get the furthest extent of filled positions in the arbor
        //Note that these conditions aren't exclusive - one occupied position can alter both x and y limits
        Position[] bounds=arbor.getBounds();
        for(Position pos:bounds) {
            if(pos.getX()>xMax) xMax=pos.getX();
            if(pos.getY()>yMax) yMax=pos.getY();
            if(pos.getX()<xMin) xMin=pos.getX();
            if(pos.getY()<yMin) yMin=pos.getY();
        }

        //update left column/s
        if(xMin-1<this.firstColX) {
            while(this.firstColX>xMin-1) {
                this.firstColX--;
                int rowInd=this.firstRowY;
                for(LinkedList<GUIPosition> row:this.positions) {
                    GUIPosition pos=addNewPosition(this.firstColX,rowInd);
                    rowInd++;
                    row.add(0,pos);
                }
            }
        }

        //update right column/s
        if(xMax+1>this.lastColX) {
            while(this.lastColX<xMax+1) {
                this.lastColX++;
                int rowInd=this.firstRowY;
                for(LinkedList<GUIPosition> row:this.positions) {
                    GUIPosition pos=addNewPosition(this.lastColX,rowInd);
                    rowInd++;
                    row.add(pos);
                }
            }
        }

        //update top row/s
        while(yMin-1<this.firstRowY) {
            this.firstRowY--;
            LinkedList<GUIPosition> newRow=new LinkedList<>();
            for(int colInd=this.firstColX;colInd<=this.lastColX;colInd++) {
                GUIPosition pos=addNewPosition(colInd,this.firstRowY);
                newRow.add(pos);
            }
            this.positions.add(0,newRow);
        }


        //update bottom row/s
        while(yMax+1>this.lastRowY) {
            this.lastRowY++;
            LinkedList<GUIPosition> newRow=new LinkedList<>();
            for(int colInd=this.firstColX;colInd<=this.lastColX;colInd++) {
                GUIPosition pos=addNewPosition(colInd,this.lastRowY);
                newRow.add(pos);
            }
            this.positions.add(newRow);
        }
    }

    /**
     * Contributon: Natasha
     * Sets the dimensions of GUIPosition slots, based on the 2-dimensional position array required
     */
    public void updateScale() {
        this.updatePosArray();//make sure the number of positions is up to date

        //Since the position slots are fixed ratio, check whether the x or y direction is the limiting factor and ensure the other direction's dimension is scaled accordingly
        double minX=(this.ARBOR_X_SIZE-this.margin*2)/(this.lastColX-this.firstColX+1);
        double yFromX=minX*this.CARD_RATIO;
        double minY=(this.ARBOR_Y_SIZE-this.margin*2)/(this.lastRowY-this.firstRowY+1);
        double xFromY=minY/this.CARD_RATIO;

        //the minimum corresponds to the direction which is more constrained (smaller slots)
        this.POS_X_SIZE=Math.min(minX,xFromY);
        this.POS_Y_SIZE=Math.min(minY,yFromX);
    }

    /**
     * Contribution: Natasha
     * Apply changes to GUIPosition scale and location
     */
    private void updatePosDisplay() {
        int xCoord=0;
        int yCoord=0;

        //track display x- and y-coordinates, since the GUIPosition's position within the arbor is based on them
        //Top left is (0,0), row and column increase going down and right
        for(LinkedList<GUIPosition> row:this.positions) {
            for(GUIPosition pos:row) {
                pos.updateCoord(xCoord*this.POS_X_SIZE+this.margin,yCoord*this.POS_Y_SIZE+this.margin);
                pos.updateScale(this.POS_X_SIZE,this.POS_Y_SIZE);

                xCoord++;
            }
            yCoord++;
            xCoord=0;
        }
    }

    /**
     * Contribution: Natasha
     * Finds the GUIPosition closest to the provided coordinates
     * This is used for identifying the position a card will be played to
     * @param x - x coord to search from
     * @param y - y coord to search from
     * @return the GUIPosition in this Arbor which is closest to the provided (x,y)
     */
    public GUIPosition findNearestSlot(double x,double y) {
        GUIPosition closest=this.positions.get(0).get(0);
        double smallDist=closest.distance(x,y);

        for(LinkedList<GUIPosition> row:this.positions) {
            for(GUIPosition pos:row) {
                double thisDist=pos.distance(x,y);
                if(thisDist<smallDist) {
                    smallDist=thisDist;
                    closest=pos;
                }
            }
        }

        return closest;
    }

    /**
     * Contribution: Natasha
     * Highlights the GUIPosition closest to a coordinate, if the slot is available to play a card into
     * @param x - x coord to search from
     * @param y - y coord to search from
     */
    public void highlightSlot(double x, double y) {
        //reset current highlighted slot back to normal
        if(this.highlighted!=null) highlighted.setFill(Color.LIGHTGREY);

        //find the nearest slot
        GUIPosition closest=this.findNearestSlot(x,y);

        //if this is a valid place to play - i.e. a legal position and this arbor belongs to the current player - highlight the nearest slot
        if(closest.canPlay()&&this.thisTurn) {
            this.highlighted=closest;
            closest.setFill(Color.GREY);
        }
    }

    /**
     * Contribution: Natasha
     * Checks if a given coordinate is inside this GUIArbor
     * @param x - the x coordinate to check
     * @param y - the y coordinate to check
     * @return True if the coord is inside this Arbor, false otherwise
     */
    public Boolean coordsInside(double x, double y) {
        Boolean result=true;

        //check x
        if(this.getLayoutX()>x) result=false;
        else if(this.getLayoutX()+this.ARBOR_X_SIZE<x) result=false;

        //check y
        if(this.getLayoutY()>y) result=false;
        else if(this.getLayoutY()+this.ARBOR_Y_SIZE<y) result=false;

        return result;
    }

    /**
     * Contribution: Natasha
     * Creates a new GUIPosition with the specified position coordinates
     * Does not add to this.positions! (yet) TODO-move this.positions logic in here
     * @param xInd - the x coord of the associated Position
     * @param yInd - the y coord of the associated Position
     * @return a reference to the new GUIPosition
     */
    private GUIPosition addNewPosition(int xInd, int yInd) {
        GUIPosition pos=new GUIPosition(this.arbor,new Position(xInd,yInd),this);
        this.getChildren().add(pos);
        pos.toFront();
        return pos;
    }

}
