package comp1110.ass2.game;

public class Position {
    int x = -1;
    int y = -1;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Contribution: Natasha
     * Given a position code in assignment specification format, create the appropriate position
     * Position codes are defined in comp1110.ass2.Arboretum.isSharedStateWellFormed (placement codes, without the card code at the start)
     * @param posCode - the code representing a position
     */
    public Position(String posCode) {
        String dir1=posCode.substring(0,1);
        String dir2=posCode.substring(3,4);
        int val1=Integer.parseInt(posCode.substring(1,3));
        int val2=Integer.parseInt(posCode.substring(4));

        //N,E are positive, so flip sign on position values that are S,W
        if(dir1.equals("S")) val1*= -1;
        if(dir2.equals("W")) val2*= -1;

        this.x=val2;
        this.y=val1;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /**
     * Contribution: Natasha
     * Alternate toString that prints useful information and is identical as long as positions have the same x and y coordinates
     * @return a string of form "(x, y)"
     */
    @Override
    public String toString() {
        return("("+this.x+", "+this.y+")");
    }

    /**
     * Contribution: Natasha
     * Our Arboretums are hashmaps referenced via position.
     * We want to ensure that equivalent positions (but with different memory addresses) map to the same entry,
     * so rewrite the Object.equals() method to check x and y coordinates
     * @param any - object to compare
     * @return True if any is an equivalent position, False otherwise
     */
    @Override
    public boolean equals(Object any) {
        if(this==any) return true;
        if(!(any instanceof Position)) return false;

        Position toCompare=(Position) any;
        if(this.x!=toCompare.getX()) return false;
        if(this.y!=toCompare.getY()) return false;

        return true;
    }

    /**
     * Contribution: Natasha
     * Our Arboretums are hashmaps referenced via position.
     * We want to ensure that equivalent positions (but with different memory addresses) map to the same entry,
     * so force the hash code to use something that will be consistent as long as the positions are equal
     * @return an int hash code
     */
    @Override
    public int hashCode() {
        String refCode=this.toString();
        return refCode.hashCode();
    }
}
