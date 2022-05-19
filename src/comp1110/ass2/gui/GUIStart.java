package comp1110.ass2.gui;

import comp1110.ass2.game.Arbor;
import comp1110.ass2.game.Player;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class GUIStart extends Group {
    //sizes of arbor
    public final int ARBOR_X_SIZE;
    public final int ARBOR_Y_SIZE;

    GUIStart toRemove;
    Game game;
    FlowPane root;
    VBox border;


    public GUIStart(Game game, int xSizeArbor,int ySizeArbor, int xPosArbor, int yPosArbor) {
        this.ARBOR_X_SIZE=xSizeArbor;
        this.ARBOR_Y_SIZE=ySizeArbor;
        this.game = game;
        this.toRemove = this;
        String font_name = Font.getFamilies().get(25);
        int size = 30;
        Font font = Font.font(font_name, FontWeight.BOLD, FontPosture.REGULAR, size);

        Button button1   = new Button("VS  Human");
        Rectangle space = new Rectangle();
        Button button2   = new Button("VS  Computer");
        button1.setPadding(new Insets(50, 100, 50, 100));
        button2.setPadding(new Insets(50, 100, 50, 100));
        button1.setStyle("-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;" +
                "-fx-background-radius: 8; " +
                "-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),#9d4024,#d86e3a,radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);");
        button1.setFont(font);
        button2.setStyle("-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;" +
                "-fx-background-radius: 8; " +
                "-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),#9d4024,#d86e3a,radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);");
        button2.setFont(font);
        button1.setOnMouseClicked((new EventHandler<MouseEvent>() {

                    public void handle(MouseEvent event) {
                        game.setPlayerAHuman(true);
                        game.setPlayerBHuman(true);
                        game.getRoot().getChildren().remove(toRemove);

                    }
                }));
        button2.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                game.setPlayerAHuman(true);
                game.setPlayerBHuman(false);
                game.getRoot().getChildren().remove(toRemove);

            }
        }));
        space.setHeight(200);
        space.setOpacity(0);
        root = new FlowPane();

        root.setStyle("-fx-background-color: rgba(0,0,0,0.4);");
        root.setLayoutX(xPosArbor);
        root.setLayoutY(yPosArbor);
        root.setMinSize(this.ARBOR_X_SIZE,this.ARBOR_Y_SIZE);
        root.setHgap(250);
        border = new VBox();

        border.setPadding(new Insets(100, 180, 100, 180));

        border.getChildren().addAll(button1,space, button2);
        root.getChildren().addAll(border);


        this.getChildren().add(root);
    }



}

