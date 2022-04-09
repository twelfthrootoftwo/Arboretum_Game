package comp1110.ass2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static comp1110.ass2.ExampleGames.END_GAME_STATES;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetHighestViablePathScoreTest {

    static final char[] validChars = {'a', 'b', 'c', 'd', 'j', 'm'};

    static int[][][] SPECIES_SCORES = {
            {{8, -1, 11, 0, -1, 11}, {0, 8, -1, -1, 12, -1}},
            {{6, -1, -1, -1, -1, -1}, {-1, 8, 3, 0, 0, 5}},
            {{-1, -1, -1, 5, -1, 0}, {8, 3, 5, -1, 6, -1}},
            {{0, -1, -1, 3, 0, -1}, {-1, 3, 0, -1, -1, 0}},
            {{-1, 0, -1, -1, 0, -1}, {0, -1, 6, 0, -1, 0}},
            {{-1, 6, 6, 0, -1, -1}, {0, -1, -1, -1, 0, 5}},
            {{6, -1, -1, -1, 0, 3}, {-1, 2, 0, 0, -1, -1}},
            {{-1, -1, -1, 6, 4, 3}, {6, 0, 0, -1, -1, -1}},
            {{0, 2, -1, -1, -1, 7}, {0, -1, 3, 3, 0, -1}},
            {{-1, -1, -1, 0, 0, -1}, {3, 0, 0, -1, -1, 6}},
            {{-1, -1, 3, -1, 8, 5}, {2, 4, -1, 0, -1, -1}},
            {{0, -1, 5, -1, -1, 0}, {-1, 6, -1, 4, 0, -1}},
            {{0, 4, -1, -1, 8, 4}, {-1, -1, 0, 3, -1, -1}},
            {{-1, -1, 7, -1, 10, 5}, {12, 0, -1, 7, -1, -1}},
            {{5, -1, 12, -1, -1, 7}, {-1, 10, -1, 17, 0, -1}},
            {{5, -1, 12, 0, -1, -1}, {-1, 10, -1, 11, 0, 0}},
    };

    private String errorPrefix(String[][] state) {
        return "comp1110.ass2.Arboretum.getHighestViablePathScore(" + System.lineSeparator() + "sharedState: " + Arrays.toString(state[0]) + System.lineSeparator() +
                "hiddenState: " + Arrays.toString(state[1]) + ")"
                + System.lineSeparator();
    }

    private void test(String[][] state, char s, char p, int expected) {
        String errorPrefix = errorPrefix(state);
        int out = Arboretum.getHighestViablePathScore(state, p, s);
        assertEquals(expected, out,
                errorPrefix + " for player \"" + p + "\" and species \"" + s + "\" expected " + expected + " but you " +
                        "returned" +
                        " " + out);
    }

    @Test
    public void testGeneral() {
        for (int i = 0; i < END_GAME_STATES.length; i++) {
            for (int p = 0; p < 2; p++) {
                char player = (char) (p + 'A');
                for (int j = 0; j < validChars.length; j++) {
                    test(END_GAME_STATES[i], validChars[j], player, SPECIES_SCORES[i][p][j]);
                }
            }
        }
    }
}

