package comp1110.ass2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static comp1110.ass2.ExampleGames.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@org.junit.jupiter.api.Timeout(value = 1000, unit = MILLISECONDS)
public class IsStateValidTest {

    private String errorPrefix(String[][] state) {
        return "comp1110.ass2.Arboretum.isStateValid(" + System.lineSeparator() + "sharedState: " + Arrays.toString(state[0]) + System.lineSeparator() +
                "hiddenState: " + Arrays.toString(state[1]) + ")"
                + System.lineSeparator();
    }

    private void test(String[][] state, boolean expected) {
        String errorPrefix = errorPrefix(state);
        boolean out = Arboretum.isStateValid(state);
        assertEquals(expected, out, errorPrefix);
    }

    private void testTrivialTrue() {
        test(VALID_STATES[0], true);
    }

    private void testTrivialFalse() {
        String[][] invalid = {new String[]{"A", "A", "A", "B", "B"}, new String[]{"", "A", "B"}};
        test(invalid, false);
    }

    @Test
    public void testValidState() {
        testTrivialFalse();
        for (String[][] state : FULL_GAME_1) {
            test(state, true);
        }
    }

    @Test
    public void testInvalidState() {
        testTrivialTrue();
        for (String[][] state : INVALID_STATES) {
            test(state, false);
        }
    }
}
