package comp1110.ass2.gui;

import comp1110.ass2.game.Card;
import comp1110.ass2.game.Position;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

class GUICard extends ImageView {


    private static final int SQUARE_SIZE = 80;
    private static final int MARGIN_X = 60;
    private static final int MARGIN_Y = 30;
    private static final int BOARD_WIDTH = 450;
    private static final int BOARD_HEIGHT = 450;
    private static final int BOARD_MARGIN = 25;
    private static final int BOARD_Y = MARGIN_Y;
    private static final int BOARD_X = MARGIN_X;
    private static final int PLAY_AREA_Y = BOARD_Y + BOARD_MARGIN;
    private static final int PLAY_AREA_X = BOARD_X + BOARD_MARGIN;
    private static final int GAME_WIDTH = BOARD_X + BOARD_WIDTH + MARGIN_X + 280;
    private static final int GAME_HEIGHT = BOARD_Y + BOARD_HEIGHT + MARGIN_Y + 100;
    private final Group selectionPieces = new Group();
    private final Position[] highlightedPositions = new Position[4];
    private boolean draggable;


    Card card;
    String name;
    double homeX, homeY;
    double mouseX, mouseY;
    Position loc;
    Group root;

    public GUICard(String name, Image image) throws FileNotFoundException {
        this.name = name;
        this.draggable=true;
        setImage(image);
//        setFitHeight(SQUARE_SIZE - 4);
//        setFitWidth(SQUARE_SIZE - 4);
//        this.homeX = (loc.getX()) * SQUARE_SIZE + PLAY_AREA_X;
//        this.homeY = (loc.getY()) * SQUARE_SIZE + PLAY_AREA_Y;
//        setLayoutX(this.homeX);
//        setLayoutY(this.homeY);

        setOnMousePressed(event -> {
            if(draggable) mousePress(event);
        });

        setOnMouseReleased(event -> {
            if(draggable) mouseRelease(event);
        });

        setOnMouseDragged(event -> {
            if(draggable) mouseDrag(event);
        });
    }

    public GUICard(Card card, Group root, Image image) {
        this.name=card.toString();
        this.card=card;
        this.root=root;
        this.draggable=true;
        setImage(image);


        setOnMousePressed(event -> {
            if(draggable) mousePress(event);
        });

        setOnMouseReleased(event -> {
            if(draggable) mouseRelease(event);
        });

        setOnMouseDragged(event -> {
            if(draggable) mouseDrag(event);
        });
    }

    public String getName() {return this.name;}
    public Card getCard() {return this.card;}

    public void updateImage(Image image) {
        this.setImage(image);
    }

    private GUIArbor findArbor() {
        ObservableList<Node> nodes=root.getChildren();
        for(Node node:nodes) {
            if(node instanceof GUIArbor) {
                GUIArbor gArb=(GUIArbor) node;
                if(gArb.isTurn()&&gArb.contains(mouseX,mouseY)) {
                    return gArb;
                }
            }
        }
        return null;
    }

    private void mouseDrag(MouseEvent event) {
        double diffX = event.getSceneX() - mouseX;
        double diffY = event.getSceneY() - mouseY;
        this.setLayoutX(this.getLayoutX() + diffX);
        this.setLayoutY(this.getLayoutY() + diffY);
        mouseX = event.getSceneX();
        mouseY = event.getSceneY();

        GUIArbor localArbor=findArbor();
        if(localArbor!=null) {
            localArbor.highlightSlot(mouseX,mouseY);
        }
    }

    private void mouseRelease(MouseEvent event) {
        GUIArbor localArbor=findArbor();
        mouseX = event.getSceneX();
        mouseY = event.getSceneY();
        boolean played=false;
        if(localArbor!=null) {
            GUIPosition localPos=localArbor.findNearestSlot(mouseX,mouseY);
            if(localPos.canPlay()) {
                localPos.playHere(this);
                //this.removeFromHand();
                played=true;
                this.draggable=false;
            }
        }
        if(!played) {
            this.setLayoutX(this.homeX);
            this.setLayoutY(this.homeY);
        }
    }

    private void mousePress(MouseEvent event) {
        this.homeX=this.getLayoutX();
        this.homeY=this.getLayoutY();
        this.mouseX=event.getSceneX();
        this.mouseY=event.getSceneY();
        this.toFront();
    }

    public void updateScale(double width,double height) {
        this.setFitHeight(height);
        this.setFitWidth(width);
    }

    public void updateCoord(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
}
