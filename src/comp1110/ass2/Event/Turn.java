package comp1110.ass2.Event;

import comp1110.ass2.game.Deck;
import comp1110.ass2.game.Player;

public class Turn {
    public Turn(Player player, Deck deck){
        player.draw(deck);
        player.draw(deck);

    }
}
