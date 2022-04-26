package comp1110.ass2;

import comp1110.ass2.game.Arbor;
import comp1110.ass2.game.Card;
import comp1110.ass2.game.Position;

import comp1110.ass2.game.Species;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class D2DUnitTest {

    public static String[] miniArbors={
            "Aa1C00C00a8C00E01",//simplest case
            "Aa1C00C00a2C00E01b1S01C00b2S01E01",//multiple non-overlapping score routes with different species
            "Aa1C00C00a8N01C00a7C00E01a6S01C00a5C00W01",//multiple branching scoring routes
            "Aa1C00W02b3C00W01c5C00C00a7C00E01",//valid scoring route across multiple species starting off-centre
            "Aa1C00W02b3C00W01c5C00C00",//valid score moves but no score route
            "Aa1C00C00a2C00E01a4N01E01c3C00E02a5S01E02b2S01C00a3S01W01b4S01W02a6N01E02",//multiple branching scoring routes including same-species, cross-species, overlapping cross-species
            };

    public static HashSet<LinkedList<Card>> scoringPaths(int i) {
        HashSet<LinkedList<Card>> allPaths=new HashSet<>();

        switch(i) {
            case 0->{
                LinkedList<Card> list1=new LinkedList<Card>();
                list1.add(new Card(Species.a,1));
                list1.add(new Card(Species.a,8));
                allPaths.add(list1);
            }
            case 1->{
                LinkedList<Card> list1=new LinkedList<Card>();
                list1.add(new Card(Species.a,1));
                list1.add(new Card(Species.a,2));
                allPaths.add(list1);
                LinkedList<Card> list2=new LinkedList<Card>();
                list2.add(new Card(Species.b,1));
                list2.add(new Card(Species.b,2));
                allPaths.add(list2);
            }
            case 2->{
                LinkedList<Card> list1=new LinkedList<Card>();
                list1.add(new Card(Species.a,1));
                list1.add(new Card(Species.a,8));
                allPaths.add(list1);
                LinkedList<Card> list2=new LinkedList<Card>();
                list2.add(new Card(Species.a,1));
                list2.add(new Card(Species.a,7));
                allPaths.add(list2);
                LinkedList<Card> list3=new LinkedList<Card>();
                list3.add(new Card(Species.a,1));
                list3.add(new Card(Species.a,6));
                allPaths.add(list3);
                LinkedList<Card> list4=new LinkedList<Card>();
                list4.add(new Card(Species.a,1));
                list4.add(new Card(Species.a,5));
                allPaths.add(list4);
            }
            case 3->{
                LinkedList<Card> list1=new LinkedList<Card>();
                list1.add(new Card(Species.a,1));
                list1.add(new Card(Species.b,3));
                list1.add(new Card(Species.c,5));
                list1.add(new Card(Species.a,7));
                allPaths.add(list1);
            }
            case 4-> {}
            case 5->{
                LinkedList<Card> list1=new LinkedList<Card>();
                list1.add(new Card(Species.a,1));
                list1.add(new Card(Species.b,2));
                list1.add(new Card(Species.a,3));
                LinkedList<Card> list2=new LinkedList<Card>();
                list2.add(new Card(Species.b,2));
                list2.add(new Card(Species.a,3));
                list2.add(new Card(Species.b,4));
                LinkedList<Card> list3=new LinkedList<Card>();
                list3.add(new Card(Species.a,1));
                list3.add(new Card(Species.a,2));
                LinkedList<Card> list4=new LinkedList<Card>();
                list4.add(new Card(Species.a,2));
                list4.add(new Card(Species.a,4));
                LinkedList<Card> list5=new LinkedList<Card>();
                list5.add(new Card(Species.a,1));
                list5.add(new Card(Species.a,2));
                list5.add(new Card(Species.a,4));
                LinkedList<Card> list6=new LinkedList<Card>();
                list6.add(new Card(Species.a,1));
                list6.add(new Card(Species.a,2));
                list6.add(new Card(Species.c,3));
                list6.add(new Card(Species.a,5));
                LinkedList<Card> list7=new LinkedList<Card>();
                list7.add(new Card(Species.a,2));
                list7.add(new Card(Species.c,3));
                list7.add(new Card(Species.a,5));
                LinkedList<Card> list8=new LinkedList<Card>();
                list8.add(new Card(Species.a,1));
                list8.add(new Card(Species.a,2));
                list8.add(new Card(Species.a,4));
                list8.add(new Card(Species.a,6));
                LinkedList<Card> list9=new LinkedList<Card>();
                list9.add(new Card(Species.a,2));
                list9.add(new Card(Species.a,4));
                list9.add(new Card(Species.a,6));
                LinkedList<Card> list10=new LinkedList<Card>();
                list10.add(new Card(Species.a,4));
                list10.add(new Card(Species.a,6));

                allPaths.add(list1);
                allPaths.add(list2);
                allPaths.add(list3);
                allPaths.add(list4);
                allPaths.add(list5);
                allPaths.add(list6);
                allPaths.add(list7);
                allPaths.add(list8);
                allPaths.add(list9);
                allPaths.add(list10);
            }
            default-> {return null;}
        }
        return allPaths;
    }

    public static Arbor[] constructedMiniArbors() {
        Arbor[] arbors=new Arbor[miniArbors.length];
        int i=0;
        for(String code : miniArbors) {
            arbors[i]=new Arbor(miniArbors[i]);
            i++;
        }
        return arbors;
    }

    public static Position[][] testPositions() {
        Position[][] testPos=new Position[5][3];
        int i=0;
        int j=0;
        for(int x=-2;x<=2;x++) {
            j=0;
            for(int y=1;y>=-1;y--) {
                testPos[i][j]=new Position(x,y);
                j++;
            }
            i++;
        }
        return testPos;
    }

    /**
     * Contribution: Natasha
     * Tests identification of scoring steps on mini arbors 0,1,2,3
     */
    @Test
    public void simpleScoringSteps() {
        Arbor[] arbors=constructedMiniArbors();
        Position[][] testPos=testPositions();
        ArrayList<Position> resultPositions=new ArrayList<>();

        assertEquals(arbors[0].getScoringSteps(testPos[2][1]).size(),1);
        assertEquals(arbors[0].getScoringSteps(testPos[2][1]).get(0),testPos[3][1]);
        assertEquals(arbors[0].getScoringSteps(testPos[3][1]).size(),0);
        assertEquals(arbors[0].getScoringSteps(testPos[2][0]).size(),0);
        assertEquals(arbors[0].getScoringSteps(testPos[3][0]).size(),0);

        assertEquals(arbors[1].getScoringSteps(testPos[2][1]).size(),1);
        assertEquals(arbors[1].getScoringSteps(testPos[2][1]).get(0),testPos[3][1]);
        assertEquals(arbors[1].getScoringSteps(testPos[3][1]).size(),0);
        assertEquals(arbors[1].getScoringSteps(testPos[2][2]).size(),1);
        assertEquals(arbors[1].getScoringSteps(testPos[2][2]).get(0),testPos[3][2]);
        assertEquals(arbors[1].getScoringSteps(testPos[3][2]).size(),0);

        assertEquals(arbors[2].getScoringSteps(testPos[2][1]).size(),4);
        resultPositions.add(testPos[2][0]);
        resultPositions.add(testPos[3][1]);
        resultPositions.add(testPos[2][2]);
        resultPositions.add(testPos[1][1]);
        assertEquals(arbors[2].getScoringSteps(testPos[2][1]),resultPositions);
        assertEquals(arbors[2].getScoringSteps(testPos[2][0]).size(),0);
        assertEquals(arbors[2].getScoringSteps(testPos[3][1]).size(),0);
        assertEquals(arbors[2].getScoringSteps(testPos[2][2]).size(),0);
        assertEquals(arbors[2].getScoringSteps(testPos[1][1]).size(),0);

        assertEquals(arbors[3].getScoringSteps(testPos[0][1]).size(),1);
        assertEquals(arbors[3].getScoringSteps(testPos[0][1]).get(0),testPos[1][1]);
        assertEquals(arbors[3].getScoringSteps(testPos[1][1]).size(),1);
        assertEquals(arbors[3].getScoringSteps(testPos[1][1]).get(0),testPos[2][1]);
        assertEquals(arbors[3].getScoringSteps(testPos[2][1]).size(),1);
        assertEquals(arbors[3].getScoringSteps(testPos[2][1]).get(0),testPos[3][1]);
        assertEquals(arbors[3].getScoringSteps(testPos[3][1]).size(),0);
    }

    /**
     * Contribution: Natasha
     * Tests identification of scoring paths for mini arbors
     */
    @Test
    public void scoringPaths() {
        Arbor[] arbors=constructedMiniArbors();

        for(int i=0;i<arbors.length;i++) {
            HashSet<LinkedList<Card>> identifiedPaths=arbors[i].findScoringPaths();
            HashSet<LinkedList<Card>> expectedPaths=scoringPaths(i);
            assertEquals(identifiedPaths.size(),expectedPaths.size());
            for(LinkedList<Card> path : expectedPaths) {
                if(!identifiedPaths.contains(path)) System.out.println("Did not find path "+path+" in scoring set for arbor "+i);
                assertEquals(identifiedPaths.contains(path),true);
            }
        }

    }

}
