package comp1110.ass2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static comp1110.ass2.ExampleGames.END_GAME_STATES;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CanScoreTest {

    public static boolean[][][] SCORING_SPECIES = {
            {{true, false, true, true, false, true}, {true, true, false, false, true, false}},
            {{true, false, false, false, false, false}, {false, true, true, true, true, true}},
            {{false, false, false, true, false, true}, {true, true, true, false, true, false}},
            {{true, false, false, true, true, false}, {false, true, true, false, false, true}},
            {{false, true, false, false, true, false}, {true, false, true, true, false, true}},
            {{false, true, true, true, false, false}, {true, false, false, false, true, true}},
            {{true, false, false, false, true, true}, {false, true, true, true, false, false}},
            {{false, false, false, true, true, true}, {true, true, true, false, false, false}},
            {{true, true, false, false, false, true}, {true, false, true, true, true, false}},
            {{false, false, false, true, true, false}, {true, true, true, false, false, true}},
            {{false, false, true, false, true, true}, {true, true, false, true, false, false}},
            {{true, false, true, false, false, true}, {false, true, false, true, true, false}},
            {{true, true, false, false, true, true}, {false, false, true, true, false, false}},
            {{false, false, true, false, true, true}, {true, true, false, true, false, false}},
            {{true, false, true, false, false, true}, {false, true, false, true, true, false}},
            {{true, false, true, true, false, false}, {false, true, false, true, true, true}},
    };

    static final char[] validChars = {'a', 'b', 'c', 'd', 'j', 'm'};

    private String errorPrefix(String[][] state) {
        return "Arboretum.canScore(" + System.lineSeparator() + "sharedState: " + Arrays.toString(state[0]) + System.lineSeparator() +
                "hiddenState: " + Arrays.toString(state[1]) + ")"
                + System.lineSeparator();
    }

    private void test(String[][] state, char s, char p, boolean expected) {
        String errorPrefix = errorPrefix(state);
        boolean out = Arboretum.canScore(state, p, s);
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
                    test(END_GAME_STATES[i], validChars[j], player, SCORING_SPECIES[i][p][j]);
                }
            }
        }
    }
}
