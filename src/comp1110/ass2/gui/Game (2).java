package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {
    /* board layout */
    private static final int BOARD_WIDTH = 1200;
    private static final int BOARD_HEIGHT = 700;

    @Override
    public void start(Stage stage) throws Exception {
        // FIXME Task 11: Implement a basic playable Arboretum game in JavaFX that only allows cards to be placed in
        //   valid places
        // FIXME Task 16: Implement a computer opponent so that a human can play your game against the computer.
        // FIXME Task 18: Implement variant(s).
        stage.setTitle("Arboretum");
        Group root = new Group();
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        stage.setScene(scene);
        stage.show();
    }
}
