package comp1110.ass2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static comp1110.ass2.ExampleGames.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@org.junit.jupiter.api.Timeout(value = 1000, unit = MILLISECONDS)

public class GetAllValidPlacementsTest {

    private String errorPrefix(String[][] state) {
        return "comp1110.ass2.Arboretum.getAllValidPlacements(" + System.lineSeparator() + "sharedState: " + Arrays.toString(state[0]) + System.lineSeparator() +
                "hiddenState: " + Arrays.toString(state[1]) + ")"
                + System.lineSeparator();
    }

    private void test(String[][] state, String card, Set<String> expected) {
        String errorPrefix = errorPrefix(state);
        Set<String> out = Arboretum.getAllValidPlacements(state, card);
        assertEquals(expected, out, errorPrefix);
    }

    @Test
    public void testSmallArboretum() {
        for (int i = 0; i < 3; i++) {
            String[] locations = SMALL_STATE_MOVES[i][0];
            String[] cards = SMALL_STATE_MOVES[i][1];
            for (String c : cards) {
                Set<String> expected = new HashSet<>();
                for (String l : locations) {
                    expected.add(c + l);
                }
                test(SMALL_STATES[i], c, expected);
            }
        }
    }

    @Test
    public void testFullGame() {
        for (int i = 0; i < FULL_GAME_MOVES_1.length; i++) {
            if (i % 3 == 2) {
                String[] locations = FULL_GAME_MOVES_1[i][0];
                String[] cards = FULL_GAME_MOVES_1[i][1];
                for (String c : cards) {
                    Set<String> expected = new HashSet<>();
                    for (String l : locations) {
                        expected.add(c + l);
                    }
                    test(FULL_GAME_1[i], c, expected);
                }
            }
        }
    }
}
