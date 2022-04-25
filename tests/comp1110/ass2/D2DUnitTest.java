package comp1110.ass2;

import comp1110.ass2.game.Arbor;
import comp1110.ass2.game.Position;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class D2DUnitTest {

    public static String[] miniArbors={
            "Aa1C00C00a8C00E01",//simplest case
            "Aa1C00C00a2C00E01b1S01C00b2S01E01",//multiple non-overlapping score routes with different species
            "Aa1C00C00a8N01C00a7C00E01a6S01C00a5C00W01",//multiple branching scoring routes
            "Aa1C00W02b3C00W01c5C00C00a7C00E01",//valid scoring route across multiple species starting off-centre
            "Aa1C00W02b3C00W01c5C00C00",//valid score moves but no score route
            "Aa1C00C00a2C00E01a4N01E01c3C00E02a5S01E02b2S01C00a3S01W01b4S01W02",//multiple branching scoring routes including same-species, cross-species, overlapping cross-species
            };

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

}
