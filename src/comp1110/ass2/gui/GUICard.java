package comp1110.ass2.gui;

import comp1110.ass2.game.Position;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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


    String name;
    double homeX, homeY;
    double mouseX, mouseY;
    Position loc;

    public GUICard(String name, Image image) throws FileNotFoundException {
        this.name = name;
        setImage(image);
//        setFitHeight(SQUARE_SIZE - 4);
//        setFitWidth(SQUARE_SIZE - 4);
//        this.homeX = (loc.getX()) * SQUARE_SIZE + PLAY_AREA_X;
//        this.homeY = (loc.getY()) * SQUARE_SIZE + PLAY_AREA_Y;
//        setLayoutX(this.homeX);
//        setLayoutY(this.homeY);

        setOnMouseReleased(event -> {

        });



        setOnMouseDragged(event -> {
            double diffX = event.getSceneX() - mouseX;
            double diffY = event.getSceneY() - mouseY;
            this.setLayoutX(this.getLayoutX() + diffX);
            this.setLayoutY(this.getLayoutY() + diffY);
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
        });
    }
}
