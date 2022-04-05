package comp1110.ass2.game;

public class Card {
    String name;//should a card have name?? NP: Yeah, this isn't going to do anything, we can remove
    Species species;
    int number;

    public Card(){
        this.name = "null";
        this.species = Species.Null;
        this.number = -1;
    }

    public Card(String name, Species species, int number){
        this.name = name;//???
        this.species = species;
        this.number = number;
    }

    public boolean isEmptyCard(){
       if (this.name.equals("null") && this.species == Species.Null && this.number == -1){
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

    public String getName() {
        return name;
    }

    /**
     * Generates card codes in accordance with assignment specification
     * @return a string card code in species/value form (eg a1)
     */
    @Override
    public String toString() {
        String cardCode=this.species.toString()+this.number;
        return cardCode;
    }
}
