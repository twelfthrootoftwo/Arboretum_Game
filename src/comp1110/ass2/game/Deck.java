package comp1110.ass2.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck extends CardStack {
    int speciesNums = 0;

    /**
     * Constructs the initial deck with all required cards, then shuffles
     *
     * @param speciesNums - the number of species to include in the deck
     */
    public Deck(int speciesNums) {
        this.speciesNums = speciesNums;
        if (speciesNums == 6) {
            for (Species species : Species.values()) {
                if (species != Species.Null) {
                    for (int i = 1; i < 9; i++) {
                        this.cards.add(new Card(species, i));
                    }
                }

            }

        }
        this.randomise();
    }

    /**
     * Contribution: Natasha
     * Given a string code as per the assignment specification, construct an equivalent Deck
     * (see comments for comp1110.ass2.Arboretum.isHiddenStateWellFormed for string code details)
     *
     * @param deckCode - a string specifying the deck order
     */
    public Deck(String deckCode) {

        if (deckCode.length() > 0) {
            int numCards = deckCode.length() / 2;

            //add card to deck, using card constructor built for assignment spec strings
            for (int i = 0; i < numCards; i++) {
                Card nextCard = new Card(deckCode.substring(i * 2, i * 2 + 2));
                this.cards.add(nextCard);
            }

            //the deck code is presented in sorted order, so shuffle it after creating
            this.randomise();
        }

    }

    public int getCapacity() {
        return speciesNums * 8;
    }

    public String getDeckString() {
        String stack = "";
        List<String> sortedCodes = new ArrayList<>();

        //gather list of card codes, then sort
        for (int i = 0; i < this.cards.size(); i++) {
            sortedCodes.add(this.cards.get(i).toString());
        }

        //convert sorted list into single string
        for (String cardString : sortedCodes) {
            stack += cardString;
        }

        return stack;
    }

    /**
     * Contribution: Natasha
     * Generate a sorted deck code string, as per assignment specifications
     * Our implementation shuffles the deck at creation, but the specification wants the deck to be unshuffled
     *
     * @return a string deck code in sorted order
     */
    public String getSortedDeckCode() {
        String stack = "";
        List<String> sortedCodes = new ArrayList<>();

        //gather list of card codes, then sort
        for (int i = 0; i < this.cards.size(); i++) {
            sortedCodes.add(this.cards.get(i).toString());
        }
        Collections.sort(sortedCodes);

        //convert sorted list into single string
        for (String cardString : sortedCodes) {
            stack += cardString;
        }

        return stack;
    }
}
