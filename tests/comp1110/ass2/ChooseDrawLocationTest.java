package comp1110.ass2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static comp1110.ass2.ExampleGames.*;
import static org.junit.jupiter.api.Assertions.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@org.junit.jupiter.api.Timeout(value = 9999, unit = MILLISECONDS)

public class ChooseDrawLocationTest {

    private String errorPrefix(String[][] state) {
        return "Arboretum.chooseDrawLocation(" + System.lineSeparator() + "sharedState: " + Arrays.toString(state[0]) + System.lineSeparator() +
                "hiddenState: " + Arrays.toString(state[1]) + ")"
                + System.lineSeparator();
    }

    private void test(String[][] state, Set<String> expected) {
        String errorPrefix = errorPrefix(state);
        String out = Arboretum.chooseDrawLocation(state);
        assertTrue(expected.contains(out), errorPrefix + " expected location/card from the set " + expected + " but " +
                "you returned " + out);
    }

    @Test
    public void testFullDeck() {
        Set<String> expected = new HashSet<>(List.of("D"));
        test(VALID_STATES[0], expected);
    }

    @Test
    public void testEmptyDeck() {
        String[][] state = {{"A", "Ab1C00C00a4N01C00c3C00E01c6N02C00m7N02W01m4N01E01a5N02E01a2S01E01c8N02W02c1S01C00b6N03E01m8N03W01m1S02E01a8N04E01c2N01W01", "Aa3",
                "Bj5C00C00j6S01C00j7S01W01j4C00W01m6C00E01m3C00W02j3N01W01d2N02W01d7S02C00b8S02E01b3N01C00d1N03W01d8S03C00b2N02C00j8S01E01", "Bd4c4"},
                {"", "Aa7b4c5c7d3d5j1m5", "Ba1a6b5b7d6j2m2"}};
        Set<String> expected = new HashSet<>(List.of("a3", "c4"));
        test(state, expected);
    }

    @Test
    public void testMidGame() {
        for (int i = 0; i < FULL_GAME_MOVES_1.length; i++) {
            if ((i % 3) != 2) {
                Set<String> expected = new HashSet<>(List.of(FULL_GAME_MOVES_1[i][0]));
                test(FULL_GAME_1[i], expected);
            }
        }
        for (int i = 0; i < FULL_GAME_MOVES_2.length; i++) {
            if ((i % 3) != 2) {
                Set<String> expected = new HashSet<>(List.of(FULL_GAME_MOVES_2[i][0]));
                test(FULL_GAME_2[i], expected);
            }
        }
    }
}
