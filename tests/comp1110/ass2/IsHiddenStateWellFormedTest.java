package comp1110.ass2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static comp1110.ass2.ExampleGames.NOT_WELLFORMED_HIDDEN;
import static comp1110.ass2.ExampleGames.VALID_STATES;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@org.junit.jupiter.api.Timeout(value = 1000, unit = MILLISECONDS)

public class IsHiddenStateWellFormedTest {

    private String errorPrefix(String[] state) {
        return "Arboretum.isHiddenStateWellFormed(" + Arrays.toString(state) + ")";
    }

    private void test(String[] in, boolean expected) {
        String errorPrefix = errorPrefix(in);
        boolean out = Arboretum.isHiddenStateWellFormed(in);
        assertEquals(expected, out, errorPrefix);
    }

    public void trivialFalse() {
        test(new String[]{"", "", ""}, false);
        test(new String[]{}, false);
    }
    public void trivialTrue() {
        test(new String[] {"", "A", "B"}, true);
    }

    @Test
    public void testBasicValid() {
        trivialFalse();
        for (String[][] state : VALID_STATES) {
            test(state[1], true);
        }
    }

    @Test
    public void testNotWellFormed() {
        trivialTrue();
        for (String[] state : NOT_WELLFORMED_HIDDEN) {
            test(state, false);
        }
    }

}
