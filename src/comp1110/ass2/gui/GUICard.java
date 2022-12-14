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
import java.util.List;

class GUICard extends ImageView {

    Card card;
    String name;
    double homeX, homeY;
    double mouseX, mouseY;
    Group root;
    Game game;
    private boolean draggable;

    /**
     * Contribution: Junxian, Natasha
     * Gets the image associated with a card
     * @param card - the card to find
     * @return an Image of the card with bounding size 112,112
     * @throws FileNotFoundException if the image could not be found (eg if the cardCode is not one with a recognised image)
     */
    private static Image getImage(Card card) throws FileNotFoundException {
        String cardCode="back";
        if(card!= null) {
            cardCode = card.toString();
        }
        String fileAddress="";
        switch(cardCode) {
            case "a1" -> fileAddress="assets/cards/a_01.png";
            case "a2" -> fileAddress="assets/cards/a_02.png";
            case "a3" -> fileAddress="assets/cards/a_03.png";
            case "a4" -> fileAddress="assets/cards/a_04.png";
            case "a5" -> fileAddress="assets/cards/a_05.png";
            case "a6" -> fileAddress="assets/cards/a_06.png";
            case "a7" -> fileAddress="assets/cards/a_07.png";
            case "a8" -> fileAddress="assets/cards/a_08.png";
            case "b1" -> fileAddress="assets/cards/b_01.png";
            case "b2" -> fileAddress="assets/cards/b_02.png";
            case "b3" -> fileAddress="assets/cards/b_03.png";
            case "b4" -> fileAddress="assets/cards/b_04.png";
            case "b5" -> fileAddress="assets/cards/b_05.png";
            case "b6" -> fileAddress="assets/cards/b_06.png";
            case "b7" -> fileAddress="assets/cards/b_07.png";
            case "b8" -> fileAddress="assets/cards/b_08.png";
            case "c1" -> fileAddress="assets/cards/c_01.png";
            case "c2" -> fileAddress="assets/cards/c_02.png";
            case "c3" -> fileAddress="assets/cards/c_03.png";
            case "c4" -> fileAddress="assets/cards/c_04.png";
            case "c5" -> fileAddress="assets/cards/c_05.png";
            case "c6" -> fileAddress="assets/cards/c_06.png";
            case "c7" -> fileAddress="assets/cards/c_07.png";
            case "c8" -> fileAddress="assets/cards/c_08.png";
            case "d1" -> fileAddress="assets/cards/d_01.png";
            case "d2" -> fileAddress="assets/cards/d_02.png";
            case "d3" -> fileAddress="assets/cards/d_03.png";
            case "d4" -> fileAddress="assets/cards/d_04.png";
            case "d5" -> fileAddress="assets/cards/d_05.png";
            case "d6" -> fileAddress="assets/cards/d_06.png";
            case "d7" -> fileAddress="assets/cards/d_07.png";
            case "d8" -> fileAddress="assets/cards/d_08.png";
            case "j1" -> fileAddress="assets/cards/j_01.png";
            case "j2" -> fileAddress="assets/cards/j_02.png";
            case "j3" -> fileAddress="assets/cards/j_03.png";
            case "j4" -> fileAddress="assets/cards/j_04.png";
            case "j5" -> fileAddress="assets/cards/j_05.png";
            case "j6" -> fileAddress="assets/cards/j_06.png";
            case "j7" -> fileAddress="assets/cards/j_07.png";
            case "j8" -> fileAddress="assets/cards/j_08.png";
            case "m1" -> fileAddress="assets/cards/m_01.png";
            case "m2" -> fileAddress="assets/cards/m_02.png";
            case "m3" -> fileAddress="assets/cards/m_03.png";
            case "m4" -> fileAddress="assets/cards/m_04.png";
            case "m5" -> fileAddress="assets/cards/m_05.png";
            case "m6" -> fileAddress="assets/cards/m_06.png";
            case "m7" -> fileAddress="assets/cards/m_07.png";
            case "m8" -> fileAddress="assets/cards/m_08.png";
            case "back" ->fileAddress="assets/cards/z_back.png";
            default -> {}
        }
        Image image=new Image(new FileInputStream(fileAddress), 112, 112, true, true);
        return image;
    }


    /**
     * Contribution: Natasha
     * Alternate constructor using backend structure
     * @param card - the card to associate
     * @param root - the base display group
     */
    public GUICard(Card card, Group root, Game game) {
        this.name=card.toString();
        this.card=card;
        this.game=game;
        this.root=root;
        this.draggable=true;
        try {
            setImage(getImage(card));
        } catch (FileNotFoundException e) {
            System.out.println(e + " Image not found for card "+ card);
        }


        setOnMousePressed(event -> {
            if(draggable) mousePress(event);
        });

        setOnMouseReleased(event -> {
            if(draggable) {
                Card newCard = mouseRelease(event);
            }
        });

        setOnMouseDragged(event -> {
            if(draggable) mouseDrag(event);
        });
    }

    /**
     * Contribution: Natasha
     * Second constructor to generate a card-back-only variant (for use in deck display)
     * @param root - the root group of the GUI display
     * @param checkBack - string "back" (just to confirm you want the card back image and are intentionally using this constructor)
     */
    public GUICard(Group root,String checkBack) {
        if(checkBack.equals("back"))
            this.root=root;
            this.draggable=false;
            Card card=null;
            this.card=card;
            try {
                setImage(getImage(card));
            } catch(FileNotFoundException e) {
                System.out.println("Card back not found");
            }

    }

    public String getName() {return this.name;}
    public Card getCard() {return this.card;}

    public void updateImage(Image image) {
        this.setImage(image);
    }


    /**
     * Contribution: Natasha
     * Finds the GUIArbor the card is currently over
     * This is used when this GUICard is being dragged by the mouse, as part of identifying places to play
     * @return the GUIArbor if the card is currently over one and it's currently the turn of that arbor's player, Null otherwise
     */
    private GUIArbor findArbor() {
        //look through all nodes on root plane to find GUIArbors
        ObservableList<Node> nodes=root.getChildren();
        for(Node node:nodes) {
            if(node instanceof GUIArbor) {
                //check if this card is over the arbor & it's that arbor's turn
                GUIArbor gArb=(GUIArbor) node;
                if(gArb.isTurn()&&gArb.coordsInside(mouseX,mouseY)) {
                    return gArb;
                }
            }
        }
        return null;
    }

    /**
     * Contribution: Junxian, Natasha
     * Updates the display position of this GUICard as the mouse is dragged
     * @param event - mouse drag
     */
    private void mouseDrag(MouseEvent event) {
        //update the card's display coordinates
        double diffX = event.getSceneX() - mouseX;
        double diffY = event.getSceneY() - mouseY;
        this.setLayoutX(this.getLayoutX() + diffX);
        this.setLayoutY(this.getLayoutY() + diffY);
        mouseX = event.getSceneX();
        mouseY = event.getSceneY();

        //highlight slots below if they're valid places to play & game is waiting for play
        GUIArbor localArbor=findArbor();
        if(localArbor!=null&&game.waitingForPlay()) {
            localArbor.highlightSlot(mouseX,mouseY);
        }

        //highlight discard below if it's this player's discard & game is waiting for discard
        GUICardStack localDiscard=findDiscard();
        if(localDiscard!=null&&game.waitingForDiscard()) {
            localDiscard.highlight();
        }
    }

    /**
     * Contribution: Junxian, Natasha
     * When the card is dropped, play it to the position it's over; otherwise return it to the starting position
     * @param event - mouse release
     */
    private Card mouseRelease(MouseEvent event) {
        GUIArbor localArbor=findArbor();
        GUICardStack localDiscard=findDiscard();
        mouseX = event.getSceneX();
        mouseY = event.getSceneY();
        boolean played=false;

        //if the card was dropped onto a valid place to play, play it there
        if(localArbor!=null) {
            GUIPosition localPos=localArbor.findNearestSlot(mouseX,mouseY);
            //check hovered position is playable and it's the play phase
            if(localPos.canPlayArbor()&&game.waitingForPlay()) {
                //update backend & attach GUICard to GUIPosition
                localPos.playHere(this);

                //update displayed game state
                removeFromHand();
                game.trackCardPlayed();

                this.draggable=false;//lock this card so it can't be moved again
                return this.getCard();
            }
        } else if(localDiscard!=null) {
            //if the card was dropped onto the player's discard pile, add it to the discard
            //check it's this player's discard & it's the discard phase
            if(localDiscard.isTurn()&&game.waitingForDiscard()) {
                //update backend
                localDiscard.discardHere(this);

                //update displayed game state
                root.getChildren().remove(this);
                removeFromHand();
                game.trackCardDiscarded();

                this.draggable=false;//lock this card so it can't be moved again
                return null;
            }
        }

        //if the card wasn't played, return it to where it was
        this.setLayoutX(this.homeX);
        this.setLayoutY(this.homeY);

        return null;
    }

    /**
     * Cotnribtuion: Natasha
     * Stores information when the card is initially clocked
     * @param event - mouse press
     */
    private void mousePress(MouseEvent event) {
        this.homeX=this.getLayoutX();
        this.homeY=this.getLayoutY();
        this.mouseX=event.getSceneX();
        this.mouseY=event.getSceneY();
        this.toFront();
    }

    /**
     * Cotnribtuion: Natasha
     * As the Arbor expands and position slots get smaller, update the card's scale to match
     * @param width - the new width to fill
     * @param height - the new height to fill
     */
    public void updateScale(double width,double height) {
        this.setFitHeight(height);
        this.setFitWidth(width);
    }

    /**
     * Contribution: Natasha
     * as the Arbor expands and GUIPositions mvoe around, update the card's display position to map
     * @param x - the new x coord
     * @param y - the new y coord
     */
    public void updateCoord(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    @Override
    public String toString() {
        return "GUICard{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * Contribution: Natasha
     * Remove this guiCard from the currently displayed hand
     */
    public void removeFromHand() {
        List<GUICard> hand=this.game.getDisplayHand();
        hand.remove(this);
    }

    private GUICardStack findDiscard() {
        //look through all nodes on root plane to find GUICardStacks
        ObservableList<Node> nodes=root.getChildren();
        for(Node node:nodes) {
            if(node instanceof GUICardStack) {
                //check if this card is over a discard pile & it's that arbor's turn
                GUICardStack gStack=(GUICardStack) node;
                if(gStack.isTurn()&&gStack.coordsInside(mouseX,mouseY)) {
                    return gStack;
                }
            }
        }
        return null;
    }
}
