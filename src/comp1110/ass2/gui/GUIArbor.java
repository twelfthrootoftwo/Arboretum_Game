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
    private int firstColX;
    private int firstRowY;
    private int lastColX;
    private int lastRowY;
    public final int ARBOR_X_SIZE;
    public final int ARBOR_Y_SIZE;
    private double POS_X_SIZE;
    private double POS_Y_SIZE;
    private LinkedList<LinkedList<GUIPosition>> positions;
    GUIPosition highlighted;
    private boolean thisTurn;
    Group root;
    private int margin;
    private final double CARD_RATIO=1.4;

    public GUIArbor(Player player, int xSize,int ySize, int xPos, int yPos,int margin) {
        this.player=player;
        this.arbor=player.getArboretum();
        this.ARBOR_X_SIZE=xSize;
        this.ARBOR_Y_SIZE=ySize;
        this.positions=new LinkedList<LinkedList<GUIPosition>>();
        GUIPosition origin=new GUIPosition(this.arbor,new Position(0,0),this);
        LinkedList<GUIPosition> start=new LinkedList<>();
        start.add(origin);
        this.getChildren().add(origin);
        this.positions.add(start);
        this.margin=margin;

        this.highlighted=null;
        this.setLayoutX(xPos);
        this.setLayoutY(yPos);

        Rectangle borderOutside=new Rectangle(xSize,ySize);
        Rectangle borderInside=new Rectangle(xSize-margin*2,ySize-margin*2);
        borderOutside.setFill(Color.LIGHTGREY);
        borderInside.setFill(Color.WHITE);
        borderInside.setLayoutX(margin);
        borderInside.setLayoutY(margin);
        this.getChildren().addAll(borderOutside,borderInside);
        origin.toFront();

        this.update();
    }

    public void update() {
        this.updateScale();
        this.updatePosDisplay();
        this.layout();
        this.layoutChildren();
    }

    public void add(Node n) {
        this.getChildren().add(n);
    }

    public Boolean isTurn() {return this.thisTurn;}
    public void startTurn() {this.thisTurn=true;}
    public void endTurn() {this.thisTurn=false;}

    private void updatePosArray() {
        int xMax=0;
        int yMax=0;
        int xMin=0;
        int yMin=0;

        Position[] bounds=arbor.getBounds();

        for(Position pos:bounds) {
            if(pos.getX()>xMax) xMax=pos.getX();
            if(pos.getY()>yMax) yMax=pos.getY();
            if(pos.getX()<xMin) xMin=pos.getX();
            if(pos.getX()<yMin) yMax=pos.getY();
        }

        //update left
        if(xMin-1<this.firstColX) {
            while(this.firstColX>xMin-1) {
                this.firstColX--;
                int rowInd=this.firstRowY;
                for(LinkedList<GUIPosition> row:this.positions) {
                    GUIPosition pos=new GUIPosition(this.arbor,new Position(this.firstColX,rowInd),this);
                    this.getChildren().add(pos);
                    pos.toFront();
                    rowInd++;
                    row.add(0,pos);
                }
            }
        }

        //update right
        if(xMax+1>this.lastColX) {
            while(this.lastColX<xMax+1) {
                this.lastColX++;
                int rowInd=this.firstRowY;
                for(LinkedList<GUIPosition> row:this.positions) {
                    GUIPosition pos=new GUIPosition(this.arbor,new Position(this.lastColX,rowInd),this);
                    this.getChildren().add(pos);
                    pos.toFront();
                    rowInd++;
                    row.add(pos);
                }
            }
        }

        //update top
        while(yMin-1<this.firstRowY) {
            this.firstRowY--;
            LinkedList<GUIPosition> newRow=new LinkedList<>();
            for(int colInd=this.firstColX;colInd<=this.lastColX;colInd++) {
                GUIPosition pos=new GUIPosition(this.arbor,new Position(colInd,this.firstRowY),this);
                this.getChildren().add(pos);
                pos.toFront();
                newRow.add(pos);
            }
            this.positions.add(0,newRow);
        }


        //update bottom
        while(yMax+1>this.lastRowY) {
            this.lastRowY++;
            LinkedList<GUIPosition> newRow=new LinkedList<>();
            for(int colInd=this.firstColX;colInd<=this.lastColX;colInd++) {
                GUIPosition pos=new GUIPosition(this.arbor,new Position(colInd,this.lastRowY),this);
                this.getChildren().add(pos);
                pos.toFront();
                newRow.add(pos);
            }
            this.positions.add(newRow);
        }
    }

    public void updateScale() {
        this.updatePosArray();

        double minX=(this.ARBOR_X_SIZE-this.margin*2)/(this.lastColX-this.firstColX+1);
        double yFromX=minX*this.CARD_RATIO;
        double minY=(this.ARBOR_Y_SIZE-this.margin*2)/(this.lastRowY-this.firstRowY+1);
        double xFromY=minY/this.CARD_RATIO;

        this.POS_X_SIZE=Math.min(minX,xFromY);
        this.POS_Y_SIZE=Math.min(minY,yFromX);
    }

    private void updatePosDisplay() {
        //resize all slots for new dimensions
        int xCoord=0;
        int yCoord=0;

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

    public void highlightSlot(double x, double y) {
        if(this.highlighted!=null) highlighted.setFill(Color.LIGHTGREY);
        GUIPosition closest=this.findNearestSlot(x,y);
        if(closest.canPlay()&&this.thisTurn) {
            this.highlighted=closest;
            closest.setFill(Color.GREY);
        }
    }


}
