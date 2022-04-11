package comp1110.ass2.game;

public class Position {
    int x = -1;
    int y = -1;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    //Constructor for use with assignment specification
    public Position(String posCode) {
        String dir1=posCode.substring(0,1);
        String dir2=posCode.substring(3,4);
        int val1=Integer.parseInt(posCode.substring(1,3));
        int val2=Integer.parseInt(posCode.substring(4));

        //N,W are positive, so flip sign on position values that are S,E
        if(dir1=="S") val1*= -1;
        if(dir2=="S") val2*= -1;

        this.x=val2;
        this.y=val1;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
