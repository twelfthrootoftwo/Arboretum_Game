package comp1110.ass2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static comp1110.ass2.ExampleGames.END_GAME_STATES;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAllViablePathsTest {

    static final char[] validChars = {'a', 'b', 'c', 'd', 'j', 'm'};
    public static String[][][][] VIABLE_PATHS = {
            {{{"a5b6a8", "a2c3m4a5b6a8", "a2c3m4a5"}, {null}, {"c1a2c3", "c2a4c6m7c8", "c3m4a5c6", "c1a2c3m4a5c6m7c8", "c3m4a5c6m7c8", "c2a4c6", "c6m7c8", "c1a2c3m4a5c6", "c2m7c8"}, {}, {null}, {"m4a5c6m7m8", "m1a2c3m4a5c6m7", "m7m8", "m1a2c3m4", "m1a2c3m4a5c6m7m8", "m4a5c6m7"}},
                    {{}, {"b2b3j5j6d7b8", "b2b3", "b3j5j6d7b8"}, {null}, {null}, {"j3j4j7", "j3j4", "j3j4j5j6", "j4j5", "j5j6", "j4j7", "j6j7", "j6j8", "j4j5j6", "j4j5j6j7", "j3j4j5j6j8", "j4j5j6j8", "j4j5m6j8", "j5j6j8", "j5j6j7", "j5m6j8", "j3j4j5j6j7", "j3j4j5", "j3j4j5m6j8"}, {null}}},
            {{{"a7a8", "a3d4a7a8", "a3d4m6a8", "a3d4a7"}, {null}, {null}, {null}, {null}, {null}}, {{null}, {"b1m7b8", "b1c7b8", "b1m2d3c7b8", "b2d3c7b8", "b4m7b8", "b2c4c7b8", "b1m3m4m7b8"}, {"c4c7", "c2d3c7"}, {}, {}, {"m4m8", "m4m7", "m3m4m7", "m3m4m8", "m1m2", "m3m4"}}},
            {{{null}, {null}, {null}, {"d4d7d8", "d7d8", "d4d7", "d1d8", "d4d5"}, {null}, {}}, {{"a7a8", "a2j3c4c6a7", "a2j3c4m5a7", "a2j3c4m5a7a8", "a2j3c4c6a7a8"}, {"b2m3b5"}, {"c4m5c7", "c1m3m5c7", "c4c6"}, {null}, {"j3c4c6j8"}, {null}}},
            {{{}, {null}, {null}, {"d2d3", "d2d3d6", "d3d6"}, {}, {null}}, {{null}, {"b3c4b6", "b2c4b6"}, {}, {null}, {null}, {}}},
            {{{null}, {}, {null}, {null}, {}, {null}}, {{}, {null}, {"c2d3j7c8", "c7c8", "c2c8"}, {}, {null}, {}}},
            {{{null}, {"b6b8", "b3m4b6", "b3m4b6b8", "b3a4b6", "b3a4b6b8"}, {"c1c2a3c6", "c1c2", "c1c2c8", "c2c8", "c2a3c6"}, {}, {null}, {null}}, {{}, {null}, {null}, {null}, {}, {"m5b7m8"}}},
            {{{"a4a5a8", "a2c3a5", "a5a8", "a2c3a5a8", "a4a5"}, {null}, {null}, {null}, {}, {"m5m6", "m2a3m6"}}, {{null}, {"b2b6", "b3b5", "b4b6"}, {}, {}, {null}, {null}}},
            {{{null}, {null}, {null}, {"d5c6m7d8", "d2c5j7d8"}, {"j7j8"}, {"m2c6m7", "m5c6m7"}}, {{"a2b3d6a8"}, {}, {}, {null}, {null}, {null}}},
            {{{}, {"b2b5"}, {null}, {null}, {null}, {"m1d4b5m8", "m7m8", "m1b2b5m8", "m1d4m7m8", "m1d4m7"}}, {{}, {null}, {"c2d3c4", "c2m3c4"}, {"d3c4d5", "d3b4d5"}, {}, {null}}},
            {{{null}, {null}, {null}, {}, {}, {null}}, {{"a1a6"}, {}, {}, {null}, {null}, {"m5m8", "m3c4j7m8", "m6m8", "m3c4m6m8", "m3c4m6", "m3a5j7m8"}}},
            {{{null}, {null}, {"c2c4c5", "c2c4", "c4c5", "c2b4c5"}, {null}, {"j2j3", "j2j3j4j6", "j3j4", "j4j6", "j2j3j4", "j3j4j6"}, {"m2j3j4j6m7", "m4c5d6m7", "m2j3j4d6m7"}}, {{"a4a5"}, {"b2a4a5b6", "b3a4a5b6"}, {null}, {}, {null}, {null}}},
            {{{}, {null}, {"c5c7c8", "c7c8", "c5j6c8", "c3c4c8", "c5c7", "c4c8", "c3c4", "c3j6c8"}, {null}, {null}, {}}, {{null}, {"b2b5d6b8", "b3b5", "b3b5d6b8", "b3b8", "b2b5", "b5d6b8"}, {null}, {"d2b3b5d6"}, {}, {null}}},
            {{{}, {"b3b5", "b1d2b5"}, {null}, {null}, {"j3c5j8", "j1a2b3b5j8"}, {"m5m8"}}, {{null}, {null}, {}, {"d3a4d5"}, {null}, {null}}},
            {{{null}, {null}, {"c1b2b3j4b5c7", "c1b2b3b4b5c7"}, {null}, {"j2j4", "j1b2b3j4", "j1b2b3m4j5j6j7", "j1b2b3j4j5j6j7", "j4j5", "j5j6", "j1b2b3m4j5j6", "j6j7", "j2j4j5j6", "j4j5j6", "j2j4j5", "j4j5j6j7", "j1b2b3m4j5", "j2j4j5j6j7", "j5j6j7", "j1b2b3j4j5j6", "j1b2b3j4j5"}, {"m1b2b3m4"}}, {{"a2a3a4a5", "a4a5a6a7", "a5a6a7", "a5a6", "a2a3a4", "a6a7", "a3a4", "a4a5", "a3a4a5a6a7", "a2a3a4a5a6", "a2a3", "a3a4a5a6", "a2a3a4a5a6a7", "a4a5a6", "a3a4a5"}, {}, {null}, {"d1a2a3a4a5d7", "d1a2m6d7", "d1a2a3a4m6d7"}, {null}, {null}}},
            {{{"a2j3m4j5a6"}, {null}, {"c7c8", "c1a2j3c6", "c5c6", "c6c7c8", "c6c7", "c5c8", "c1j2j3c6", "c1j2j3m4j5c6", "c4c5", "c1j2c5c6c7c8", "c5c6c7", "c1j2j3m4j5c6c7c8", "c5c6c7c8", "c4c5c6c7c8", "c1a2j3m4j5a6c7c8", "c1a2j3m4j5c6", "c1a2j3c6c7c8", "c1j2j3m4j5a6c7", "c4c5c8", "c1j2j3m4j5c6c7", "c1a2j3m4j5c6c7c8", "c1a2j3c6c7", "c1j2c5c8", "c4c5c6c7", "c1j2j3c6c7c8", "c4c5c6", "c1j2c5c6", "c1j2c5c6c7", "c1j2j3c6c7", "c1j2c5", "c1a2j3m4j5c6c7", "c1j2j3m4j5a6c7c8", "c1a2j3m4j5a6c7"}, {null}, {null}, {"m4j5a6c7m8", "m4j5c6c7m8"}}, {{null}, {"b1d2d3b4b5", "b4b5b6b8", "b6b8", "b1d2d3b4b5b6", "b4b5", "b1d2d3b4", "b5b6", "b4b5b6", "b1d2d3b4b5b6b8", "b5b6b8"}, {null}, {"d2d3d4d5d6", "d3b4b5b6d8", "d3d4d5d6", "d2d3b4b5d6d8", "d2d3b4b5b6d8", "d3b4b5d6d8", "d6d8", "d5d6d8", "d5d6", "d2d3b4d5", "d1d2d3b4d5d6", "d4d5", "d1d2d3b4b5d6d8", "d3d4d5", "d3b4d5", "d1d2d3d4d5", "d1d2d3b4b5b6d8", "d1d2d3b4d5", "d4d5d6", "d3b4d5d6", "d3b4b5d6", "d3d4d5d6d8", "d3b4d5d6d8", "d1d2d3d4", "d1d2d3d4d5d6d8", "d1d2d3d4d5d6", "d1d2d3", "d1d2d3b4b5d6", "d2d3d4d5", "d4d5d6d8", "d2d3b4d5d6d8", "d3d4", "d2d3b4b5d6", "d2d3", "d1d2", "d2d3b4d5d6", "d2d3d4d5d6d8", "d2d3d4", "d1d2d3b4d5d6d8"}, {}, {null}}},
            {{{"a2j3m4j5a6"}, {null}, {"c7c8", "c1a2j3c6", "c5c6", "c6c7c8", "c6c7", "c5c8", "c1j2j3c6", "c1j2j3m4j5c6", "c4c5", "c1j2c5c6c7c8", "c5c6c7", "c1j2j3m4j5c6c7c8", "c5c6c7c8", "c4c5c6c7c8", "c1a2j3m4j5a6c7c8", "c1a2j3m4j5c6", "c1a2j3c6c7c8", "c1j2j3m4j5a6c7", "c4c5c8", "c1j2j3m4j5c6c7", "c1a2j3m4j5c6c7c8", "c1a2j3c6c7", "c1j2c5c8", "c4c5c6c7", "c1j2j3c6c7c8", "c4c5c6", "c1j2c5c6", "c1j2c5c6c7", "c1j2j3c6c7", "c1j2c5", "c1a2j3m4j5c6c7", "c1j2j3m4j5a6c7c8", "c1a2j3m4j5a6c7"}, {}, {null}, {null}}, {{null}, {"b1d2d3b4b5", "b4b5b6b8", "b6b8", "b1d2d3b4b5b6", "b4b5", "b1d2d3b4", "b5b6", "b4b5b6", "b1d2d3b4b5b6b8", "b5b6b8"}, {null}, {"d3b4b5b6d8", "d3b4b5d6d7", "d2d3b4b5d6d8", "d2d3b4b5b6d8", "d3b4b5d6d8", "d6d8", "d2d3b4b5d6d7", "d6d7", "d4d7", "d2d3d4d7", "d2d3b4d7", "d1d2d3b4b5d6d7", "d1d2d3b4b5d6d8", "d3d4d7", "d1d2d3b4b5b6d8", "d3b4d7", "d1d2d3d4d7", "d1d2d3b4d7", "d3b4b5d6", "d1d2d3d4", "d1d2d3", "d1d2d3b4b5d6", "d3d4", "d2d3b4b5d6", "d2d3", "d1d2", "d2d3d4"}, {}, {}}},
    };

    private String errorPrefix(String[][] state) {
        return "Arboretum.getAllViablePaths(" + System.lineSeparator() + "sharedState: " + Arrays.toString(state[0]) + System.lineSeparator() +
                "hiddenState: " + Arrays.toString(state[1]) + ")"
                + System.lineSeparator();
    }

    private void test(String[][] state, char p, char s, Set<String> expected) {
        String errorPrefix = errorPrefix(state);
        Set<String> out = Arboretum.getAllViablePaths(state, p, s);
        assertEquals(expected, out,
                errorPrefix + " for player \"" + p + "\" and species \"" + s + "\"");
    }

    @Test
    public void testSinglePath() {
        Set<String> expected = new HashSet<>(List.of(VIABLE_PATHS[2][1][4]));
        test(END_GAME_STATES[2], 'B', 'j', expected);
    }

    @Test
    public void testNullOrEmpty() {
        for (int i = 1; i < VIABLE_PATHS[4][0].length; i++) {
            Set<String> expected;
            if (VIABLE_PATHS[4][0][i].length == 0) {
               expected = new HashSet<>();
            } else {
                expected = null;
            }
            test(END_GAME_STATES[4], 'A', validChars[i], expected);
        }
    }

    @Test
    public void testAll() {
        for (int i = 0; i < VIABLE_PATHS.length; i++) {
            for (int p = 0; p < 2; p++) {
                for (int j = 0; j < VIABLE_PATHS[i][p].length; j++) {
                    Set<String> expected;
                    if (VIABLE_PATHS[i][p][j].length == 0) {
                        expected = new HashSet<>();
                    } else if (VIABLE_PATHS[i][p][j][0] == null) {
                            expected = null;
                    } else{
                        expected = new HashSet<>(List.of(VIABLE_PATHS[i][p][j]));
                    }
                    test(END_GAME_STATES[i], (char) ('A' + p), validChars[j], expected);
                }
            }
        }
    }
}
