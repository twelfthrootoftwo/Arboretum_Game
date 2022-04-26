package comp1110.ass2.game;

public class Card {
    Species species;
    int number;

    public Card() {
        this.species = Species.Null;
        this.number = -1;
    }

    public Card(Species species, int number) {
        this.species = species;
        this.number = number;
    }

    //constructor for use with assignment spec strings
    //cardCode should be a 2-char string as per specification format

    /**
     * Contribution: Natasha
     * Given a card code typical of the assingment specification format, return the equivalent card
     * Card codes are eg "j3", strings of length 2 with first character the species code and second character the value
     *
     * @param cardCode - the card code to translate into a card
     */
    public Card(String cardCode) {
        String speciesCode = cardCode.substring(0, 1);
        int value = Integer.parseInt(cardCode.substring(1));
        Species species = Species.valueOf(speciesCode);

        this.species = species;
        this.number = value;
    }

    public boolean isEmptyCard() {
        if (this.species == Species.Null && this.number == -1) {
            return true;
        }
        return false;
    }

    public int getNumber() {
        return number;
    }

    public Species getSpecies() {
        return species;
    }

    /**
     * Contribution: Natasha
     * Generates card codes in accordance with assignment specification
     *
     * @return a string card code in species/value form (eg a1)
     */
    @Override
    public String toString() {
        String cardCode = this.species.toString() + this.number;
        return cardCode;
    }

    /**
     * Contribution: Natasha
     * Check if this card is equal to another card
     *
     * @param card - the card to check against
     * @return True if the two cards are identical, False otherwise
     */
    public Boolean isEqual(Card card) {
        Boolean result = true;
        if (card.getNumber() != this.number) result = false;
        if (card.getSpecies() != this.species) result = false;

        return result;
    }

    /**
     * Contribution: Natasha
     * Override equals method to check if cards are equivalent (not same instance).
     * TODO - replace instances of isEqual with this method
     *
     * @param any - object to compare
     * @return True if the two cards have the same species and number, False otherwise
     */
    @Override
    public boolean equals(Object any) {
        if (this == any) return true;
        if (!(any instanceof Card)) return false;

        Card toCompare = (Card) any;
        if (this.species != toCompare.getSpecies()) return false;
        if (this.number != toCompare.getNumber()) return false;

        return true;
    }
}
