package comp1110.ass2.game;

import java.util.ArrayList;
import java.util.List;

public class Deck extends CardStack {
    List<Card> cards = new ArrayList<>();
    int speciesNums = 0;

    public Deck(int speciesNums){
        this.speciesNums = speciesNums;
        if (speciesNums == 6){
            for (Species species: Species.values()){
                for (int i = 1; i < 9; i++){
                    this.cards.add(new Card("someName",species,i));
                }
            }

        }
        this.randomise();
    }
    public int getCapacity(){
        return speciesNums * 8;
    }
}
