package comp1110.ass2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.Timeout(value = 1000, unit = MILLISECONDS)
public class IsPlacementValidTest {

    private String errorPrefix(String[][] state) {
        return "Arboretum.isPlacementValid(sharedState: " + Arrays.toString(state[0]) + System.lineSeparator() +
                "hiddenState: " + Arrays.toString(state[1]) + ")"
                + System.lineSeparator();
    }

    public void test(String[][] state, String placement, boolean expected) {
        String errorPrefix = errorPrefix(state);
        boolean out = Arboretum.isPlacementValid(state, placement);
        assertEquals(expected, out, errorPrefix);
    }

    public void trivialFalse() {
        test(ExampleGames.VALID_STATES[1], "b5C99C99", false);
    }

    public void trivialTrue() {
        String[][] state = new String[][]{new String[]{"A", "A", "A", "B", "B"}, new String[]{
                "c1c2c3c4c5c6c7c8d1d2d3d4d5d6d7d8j1j2j3j4j5j6j7j8m1m2m3m4m5m6m7m8", "Aa1a2a3a4a5a6a7a8b1",
                "Bb2b3b4b5b6b7b8" }};
        test(state, "a1C00C00", true);
    }

    @Test
    public void testValidPlacement() {
        trivialFalse();
        String[][] validState = new String[][]{new String[]{"B",
                "Ab1C00C00a4N01C00c3C00E01c6N02C00m7N02W01m4N01E01a5N02E01a2S01E01", "A",
                "Bj5C00C00j6S01C00j7S02C00j4S01W01m6C00E01m3C00W02j3N01W01", "B"},
                new String[]{"a7a8b2b5b6c2c4c5c7d1d3d4d6d7d8m1", "Ab3b4c1j1m2m5m8", "Ba1a3a6b7b8c8d2j2j8"}};
        String[] placements = {"N01W02", "N02W01", "N01C00", "N01E01", "C00E02", "S01E01", "S02W01",
                "S01W02", "C00W03", "S02E01"};
        String[] cards = validState[1][2].substring(1).split("(?<=\\G.{2})");
        for (String placement : placements) {
            for (String card : cards) {
                test(validState, card + placement, true);
            }
        }
    }

    @Test
    public void testInvalidPlacement() {
        trivialTrue();
        String[][] validState = new String[][]{new String[]{"B",
                "Ab1C00C00a4N01C00c3C00E01c6N02C00m7N02W01m4N01E01a5N02E01a2S01E01", "A",
                "Bj5C00C00j6S01C00j7C00W01j4S01W01m6C00E01m3C00W02j3N01W01", "B"},
                new String[]{"a7a8b2b5b6c2c4c5c7d1d3d4d6d7d8m1", "Ab3b4c1j1m2m5m8", "Ba1a3a6b7b8c8d2j2j8"}};
        String[] invalidPlacements = {"N02W02", "N02C00", "N01E02", "S01E02", "S02E01", "S02W02", "S01W03", "N01W03"};
        String[] cards = validState[1][2].substring(1).split("(?<=\\G.{2})");

        for (String placement : invalidPlacements) {
            for (String card : cards) {
                test(validState, card + placement, false);
            }
        }
    }
}
