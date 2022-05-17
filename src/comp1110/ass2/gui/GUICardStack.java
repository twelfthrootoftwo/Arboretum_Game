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

public class GUICardStack extends Group {
    private CardStack stack;
    private Game game;
    private Group root;
    private GUICard topCard;
    private final double STACK_X_COORD;
    private final double STACK_Y_COORD;
    private final double STACK_X_WIDTH=88;
    private final double STACK_Y_WIDTH=120;
    private final double margin=4;
    private boolean isDeck;
    private Rectangle backing;

    public GUICardStack(CardStack stack,Game game,Group root, double xCoord, double yCoord) {
        this.stack = stack;
        this.game = game;
        this.root=root;
        this.STACK_X_COORD=xCoord;
        this.STACK_Y_COORD=yCoord;
        this.isDeck=stack instanceof Deck;
        this.setLayoutX(xCoord);
        this.setLayoutY(yCoord);

        //Create a backing rectangle for visual niceness
        this.backing=new Rectangle(STACK_X_WIDTH,STACK_Y_WIDTH);
        backing.setLayoutX(0);
        backing.setLayoutY(0);
        backing.setFill(Color.DARKGREEN);
        this.getChildren().add(backing);

        //Set the top card display
        this.updateTopCard();


        setOnMouseClicked(event -> {
            //only draw cards if the stack is non-empty and less than 2 cards have been drawn this turn
            if (!stack.isEmpty()) {
                if (game.cardsDrawn<2) {
                    Player activePlayer = game.getActivePlayer();
                    activePlayer.draw(this.stack);
                    game.cardsDrawn++;
                    game.updateHand(activePlayer);
                    this.updateTopCard();
                }
            }
        });

        setOnMouseDragEntered(event -> {
            if(!this.isDeck&&!this.game.isCardDiscarded()) {
                this.highlight();
            }
        });

        //if cards can be drawn, highlight on mouse over to illustrate this
        setOnMouseEntered(event -> {
            if(this.game.cardsDrawn<2) {
                this.highlight();
            }
        });

        setOnMouseExited(event -> {
            this.removeHighlight();
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
                this.getChildren().remove(this.topCard);
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
            this.topCard=new GUICard(toTop,this.root, this.game);
        }
        if (this.topCard != null){
            //now that we have the right topCard recorded, add it to the display
            this.topCard.updateCoord(0,0);
            this.getChildren().add(this.topCard);
            this.topCard.setLayoutX(margin);
            this.topCard.setLayoutY(margin);
            this.topCard.toFront();
        }
    }

    /**
     * Contribution: Natasha
     * Check if this cardStack is the discard of the player currently taking their turn
     * @return True if this is a discard and the current turn is this player's, else False
     */
    public boolean isTurn() {
        if(this.isDeck) return false;

        if(game.getActivePlayer().getDiscardPile().equals(this.stack)) return true;
        return false;
    }

    /**
     * Contribution: Natasha
     * Adds a card to this cardStack discard pile, and update backend record to match
     * @param card - the GUICard to discard
     */
    public void discardHere(GUICard card) {
        //update backend tracking for this card
        this.stack.addTopCard(card.getCard());
        this.game.getActivePlayer().play(card.getCard());

        //update display
        this.updateTopCard();
        this.removeHighlight();

        //record that a card has been discarded
        game.trackCardDiscarded();
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
        else if(this.getLayoutX()+this.STACK_X_WIDTH<x) result=false;

        //check y
        if(this.getLayoutY()>y) result=false;
        else if(this.getLayoutY()+this.STACK_Y_WIDTH<y) result=false;

        return result;
    }

    private void highlight() {
        this.backing.setFill(Color.LIGHTSEAGREEN);
    }
    public void removeHighlight() {
        this.backing.setFill(Color.DARKGREEN);
    }
}
