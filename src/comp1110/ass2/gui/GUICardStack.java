package comp1110.ass2.gui;

import comp1110.ass2.game.*;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class GUICardStack extends Shape {
    private CardStack stack;
    private Game game;
    private Group root;
    private GUICard topCard;
    private final double STACK_X_COORD;
    private final double STACK_Y_COORD;
    private final double STACK_X_WIDTH=130;
    private final double STACK_Y_WIDTH=130;
    private boolean isDeck;

    public GUICardStack(CardStack stack,Game game,Group root, double xCoord, double yCoord) {
        this.stack = stack;
        this.game = game;
        this.root=root;
        this.STACK_X_COORD=xCoord;
        this.STACK_Y_COORD=yCoord;
        this.isDeck=stack instanceof Deck;

        //Create a backing rectangle for visual niceness
        Rectangle backing=new Rectangle(STACK_X_WIDTH,STACK_Y_WIDTH);
        backing.setLayoutX(this.STACK_X_COORD);
        backing.setLayoutY(this.STACK_Y_COORD);
        backing.setFill(Color.DARKGREEN);
        root.getChildren().add(backing);

        //Set the top card display
        this.updateTopCard();


        setOnMousePressed(event -> {
            if (stack.isEmpty()) {
                return;
            } else {
                if (game.isDrawPhase()) {
                    Player activePlayer = game.getActivePlayer();
                    activePlayer.draw(this.stack);
                    this.updateTopCard();
                }
            }
        });
    }

    /**
     * Contribution: Natasha
     * Updates display of the top card.
     * If the stack is currently empty, ensures the display is blank.
     * Otherwise, displays a GUICard with either the card face (for discard piles) or a card back image (for deck).
     */
    private void updateTopCard() {

        //Clear any record of the top card and remove the existing top card from display
        //Relevant if the stack is empty, or if it's a discard and the top card display needs to change
        if(stack.isEmpty()||!this.isDeck) {
            if(this.topCard!=null) {
                root.getChildren().remove(this.topCard);
                this.topCard=null;
            }
        } else {
            //this is the deck, and it's not empty
            //create a new GUICard for display only if there isn't one there already
            if(this.topCard==null) {
                this.topCard=new GUICard(root,"back");
            } else return;//if there's already a card back on display, no need to make a change
        }

        //for a discard pile, check the top card, and display it
        if(stack instanceof DiscardPile) {
            Card toTop=((DiscardPile) stack).peekTopCard();
            this.topCard=new GUICard(toTop,this.root);
        }

        //now that we have the right topCard recorded, add it to the display
        this.topCard.updateCoord(this.STACK_X_COORD,this.STACK_Y_COORD);
        root.getChildren().add(this.topCard);
    }
}
