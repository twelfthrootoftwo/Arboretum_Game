package comp1110.ass2;

import comp1110.ass2.game.Arbor;
import comp1110.ass2.game.Card;
import comp1110.ass2.game.Position;

import comp1110.ass2.game.Species;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static LinkedList<LinkedList<Card>> scoringPaths(int i) {
        LinkedList<LinkedList<Card>> allPaths=new LinkedList<>();

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
                LinkedList<Card> list11=new LinkedList<Card>();
                list11.add(new Card(Species.a,2));
                list11.add(new Card(Species.c,3));
                list11.add(new Card(Species.a,6));
                LinkedList<Card> list12=new LinkedList<Card>();
                list12.add(new Card(Species.a,1));
                list12.add(new Card(Species.a,2));
                list12.add(new Card(Species.c,3));
                list12.add(new Card(Species.a,6));

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
                allPaths.add(list11);
                allPaths.add(list12);
            }
            default-> {return null;}
        }
        return allPaths;
    }

    public static LinkedList<Integer> scoreValues(int i){
        LinkedList<Integer> scores=new LinkedList<>();

        switch(i) {
            case 0->{
                //a1a8 - length 2, +1 start 1, +2 end 8
                scores.add(5);
            }
            case 1->{
                //a1a2 - length 2, +1 start 1
                scores.add(3);
                //b1b2 - length 2, +1 start 1
                scores.add(3);
            }
            case 2->{
                //a1a8 - length 2, +1 start 1, +2 end 8
                scores.add(5);
                //a1a7 - length 2, +1 start 1
                scores.add(3);
                //a1a6 - length 2, +1 start 1
                scores.add(3);
                //a1a5 - length 2, +1 start 1
                scores.add(3);
            }
            case 3->{
                //a1b3c5a7 - length 4, +1 start 1
                scores.add(5);
            }
            case 4->{
                //no scoring paths
            }
            case 5->{
                //this is in the order they'll be determiend by the finder
                //b2a3b4 - length 3
                scores.add(3);
                //a1a2 - length 2, +1 start
                scores.add(3);
                //a1a2a4 - length 3, +1 start
                scores.add(4);
                //a1a2a4a6 - length 4, +1 start, +4 species bonus
                scores.add(9);
                //a1a2c3a6 - length 4, +1 start
                scores.add(5);
                //a1a2c3a5 - length 4, +1 start
                scores.add(5);
                //a1b2a3 - length 3, +1 start
                scores.add(4);
                //a2a4 - length 2
                scores.add(2);
                //a2a4a6 - length 3
                scores.add(3);
                //a2c3a6 - length 3
                scores.add(3);
                //a2c3a5 - length 3
                scores.add(3);
                //a4a6 - length 2
                scores.add(2);
            }
            default-> {return null;}
        }

        return scores;
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
    public void scorePaths() {
        Arbor[] arbors=constructedMiniArbors();

        for(int i=0;i<arbors.length;i++) {
            LinkedList<LinkedList<Card>> identifiedPaths=arbors[i].findScoringPaths();
            LinkedList<LinkedList<Card>> expectedPaths=scoringPaths(i);

            if(identifiedPaths.size()!=expectedPaths.size()) System.out.println("Different number of score paths for arbor "+i);
            assertEquals(expectedPaths.size(),identifiedPaths.size());

            for(int j=0;j<expectedPaths.size();j++) {
                LinkedList<Card> testPath=expectedPaths.get(j);
                boolean compare=false;

                for(int k=0;k<expectedPaths.size();k++) {
                    if(testPath.equals(identifiedPaths.get(k))) compare=true;
                }

                if(!compare) System.out.println("Did not find path "+testPath+" in scoring set for arbor "+i);
                assertEquals(compare,true);
            }
        }

    }

    /**
     * Contribution: Natasha
     * Tests correct scoring
     */
    @Test
    public void scoreIdentification() {
        Arbor[] arbors=constructedMiniArbors();

        for(int i=0;i<arbors.length;i++) {
            LinkedList<LinkedList<Card>> identifiedPaths = arbors[i].findScoringPaths();
            LinkedList<Integer> scores=scoreValues(i);
            if(scores.size()!=identifiedPaths.size()) System.out.println("Different size for arbor "+i);
            assertEquals(scores.size(),identifiedPaths.size());
            for(int j=0;j<scores.size();j++) {
                int calcScore=Arbor.findScore(identifiedPaths.get(j));
                if(scores.get(j)!=calcScore) System.out.println("Different score for arbor "+i+", path "+identifiedPaths.get(j));
                assertEquals(scores.get(j),Arbor.findScore(identifiedPaths.get(j)));
            }
        }
    }

    /**
     * Contribution: Hongzhe
     * Reformed functions from given tests in IsHiddenStateWellFormedTest.java
     */
    private String errorPrefixHidden(String[] state) {
        return "Arboretum.isHiddenStateWellFormed(" + Arrays.toString(state) + ")";
    }

    private void testHidden(String[] actual, boolean expected) {
        String errorPrefix = errorPrefixHidden(actual);
        boolean out = Arboretum.isHiddenStateWellFormed(actual);
        assertEquals(expected, out, errorPrefix);
    }

    /**
     * Contribution: Hongzhe
     * Reformed functions from given tests in IsSharedStateWellFormedTest.java
     */
    private String errorPrefixShared(String[] state) {
        return "Arboretum.isHiddenStateWellFormed(" + Arrays.toString(state) + ")";
    }

    private void testShared(String[] actual, boolean expected) {
        String errorPrefix = errorPrefixShared(actual);
        boolean out = Arboretum.isHiddenStateWellFormed(actual);
        assertEquals(expected, out, errorPrefix);
    }

    /**
     * Contribution: Hongzhe
     * Test edge cases for isHiddenStateWellFormed()
     */
    @Test
    public void edgeCasesHidden() {
        // Wrong size
        testHidden(new String[]{"", "AB", "BA"}, false);
        // Wrong format
        testHidden(new String[]{"C", "A", "B"}, false);
        // Wrong player
        testHidden(new String[]{"", "B", "A"}, false);
        // Length larger than 9
        testHidden(new String[]{"", "Aa7c5c7d3d5j1m5c2", "Ba1a6b5b7d6j2m2"}, false);
        // Not existing species
        testHidden(new String[]{"", "Aa7c5c7d3d5j1m5", "Ba1a6b5b7d6j2e2"}, false);
        // Not existing value
        testHidden(new String[]{"", "Aa7c5c7d3d5j1m5", "Ba1a6b5b7d6j2m9"}, false);
        // Two value
        testHidden(new String[]{"", "Aa7c547d3d5j1m5", "Ba1a6b5b7d6j2m2"}, false);
        // Repeat cards in same place
        testHidden(new String[]{"", "Aa7c5c7d3d5j1m5", "Ba1a6b5b7d6j2j2"}, false);
        // Repeat cards in different places
        testHidden(new String[]{"", "Aa7c5c7d3d5j1m5", "Ba7a6b5b7d6j2m2"}, false);
        testHidden(new String[]{"a7", "Aa7c5c7d3d5j1m5", "Ba2a6b5b7d6j2m2"}, false);
        testHidden(new String[]{"j2", "Aa7c5c7d3d5j1m5", "Ba2a6b5b7d6j2m2"}, false);
        testHidden(new String[]{"j1c2b5", "Aa7b5c7d3d5j1m5", "Ba2a6b5b7d6j2m2"}, false);
    }

    /**
     * Contribution: Hongzhe
     * Test helper function checkSpecies() for isHiddenStateWellFormed()
     */
    @Test
    public void checkSpeciesTest() {
        String[] state1 = new String[]{"j1c2", "Aa7b5c7d3d5j1m5", "Ba2a6b5b7d6j2m2"};
        boolean out1 = Arboretum.checkSpecies(state1, 1, 1);
        assertEquals(true, out1, "Arboretum.checkSpecies(" + Arrays.toString(state1) + ")");
        String[] state2 = new String[]{"j1c2z2", "Aa7b5c7d3d5j1m5", "Ba2a6b5b7d6j2m2"};
        boolean out2 = Arboretum.checkSpecies(state2, 4, 0);
        assertEquals(false, out2, "Arboretum.checkSpecies(" + Arrays.toString(state1) + ")");
        String[] state3 = new String[]{"j1c2a2", "Aa7b5c7d3d5j1m5", "Ba2a6b5b7d6j2m2"};
        boolean out3 = Arboretum.checkSpecies(state2, 0, 2);
        assertEquals(false, out3, "Arboretum.checkSpecies(" + Arrays.toString(state1) + ")");
    }

    /**
     * Contribution: Hongzhe
     * Test helper function toNumber() for isHiddenStateWellFormed() and isSharedStateWellFormed()
     */
    @Test
    public void toNumberTest() {
        int charA = 'a';
        int charB = '8';
        int out1 = Arboretum.toNumber(charA, charB);
        assertEquals(charB-48, out1, "Arboretum.toNumber");
        charA = 'd';
        charB = '0';
        int out2 = Arboretum.toNumber(charA, charB);
        assertEquals(charB-18, out2, "Arboretum.toNumber");
    }

    /**
     * Contribution: Hongzhe
     * Test helper function contains() for isHiddenStateWellFormed() and isSharedStateWellFormed()
     */
    @Test
    public void containsTest() {
        Integer [] array= new Integer[] {1,2,3,4,99,101};
        boolean out1 = Arboretum.contains(array, 28);
        assertEquals(false, out1, "Arboretum.contains(" + Arrays.toString(array) + ")");
        boolean out2 = Arboretum.contains(array, 99);;
        assertEquals(true, out2, "Arboretum.contains(" + Arrays.toString(array) + ")");
    }

    /**
     * Contribution: Hongzhe
     * Test edge cases for isSharedStateWellFormed()
     */
    @Test
    public void edgeCasesShared() {
        // Wrong format
        testShared(new String[]{"", "A", "A", "B", "B"}, false);
        // Wrong player
        testShared(new String[]{"A", "B", "B", "A", "A"}, false);
        // A discard more than B
        testShared(new String[]{"A", "Aa6C00C00d6S01C00", "Ab5m4j3", "Bc7C00C00b8C00E01", "B"}, false);
        // B discard more than A
        testShared(new String[]{"A", "Aa6C00C00d6S01C00", "A", "Bc7C00C00b8C00E01", "Bb5m4j3"}, false);
        // Not existing species in A discard
        testShared(new String[]{"A", "Aa6C00C00d6S01C00", "Az5m4j3", "Bc7C00C00b8C00E01", "Ba1b1c1"}, false);
        // Not existing species in B discard
        testShared(new String[]{"A", "Aa6C00C00d6S01C00", "Aa5m4j3", "Bc7C00C00b8C00E01", "Bz1b1c1"}, false);
        // Not existing species in arboretum A
        testShared(new String[]{"A", "Ax6C00C00d6S01C00", "Ab5m4j3", "Bc7C00C00b8C00E01", "Ba1b1c1"}, false);
        // Not existing species in arboretum B
        testShared(new String[]{"A", "Aa6C00C00d6S01C00", "Ab5m4j3", "Bx7C00C00b8C00E01", "Ba1b1c1"}, false);
        // Not existing card in arboretum A
        testShared(new String[]{"A", "Aa0C00C00d6S01C00", "Ab5m4j3", "Bc7C00C00b8C00E01", "Ba1b1c1"}, false);
        // Not existing card in arboretum B
        testShared(new String[]{"A", "Aa6C00C00d6S01C00", "Ab5m4j3", "Bc9C00C00b8C00E01", "Ba1b1c1"}, false);
        // Wrong direction in arboretum A
        testShared(new String[]{"A", "Aa6C00C00d6X01C00", "Ab5m4j3", "Bc7C00C00b8C00E01", "Ba1b1c1"}, false);
        // Wrong direction in arboretum B
        testShared(new String[]{"A", "Aa6C00C00d6S01C00", "Ab5m4j3", "Bc7C00C00b8C00X01", "Ba1b1c1"}, false);
        // First card in arboretum A not C00C00
        testShared(new String[]{"A", "Aa6C01C00d6S01C00", "Ab5m4j3", "Bc7C00C00b8C00E01", "Ba1b1c1"}, false);
        // First card in arboretum B not C00C00
        testShared(new String[]{"A", "Aa6C00C00d6S01C00", "Ab5m4j3", "Bc7C00C01b8C00E01", "Ba1b1c1"}, false);
    }


    /**
     * Contribution: Hongzhe
     * Test all necessary functions in task 3 and task 4
     */
    @Test
    public void checkStatesWellFormedTest(){
        edgeCasesHidden();
        checkSpeciesTest();
        containsTest();
        toNumberTest();
        edgeCasesShared();
    }
}


