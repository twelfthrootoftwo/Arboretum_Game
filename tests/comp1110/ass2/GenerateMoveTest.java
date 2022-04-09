package comp1110.ass2;

import org.junit.jupiter.api.Test;

import java.util.*;

import static comp1110.ass2.ExampleGames.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.Timeout(value = 1000, unit = MILLISECONDS)

public class GenerateMoveTest {

    private String errorPrefix(String[][] state) {
        return "comp1110.ass2.Arboretum.generateMove(" + System.lineSeparator() + "sharedState: " + Arrays.toString(state[0]) + System.lineSeparator() +
                "hiddenState: " + Arrays.toString(state[1]) + ")"
                + System.lineSeparator();
    }

    private void test(String[][] state, List<String> locations, List<String> cards) {
        String errorPrefix = errorPrefix(state);
        String[] out = Arboretum.generateMove(state);
        assertNotNull(out, errorPrefix + " expected a move, but you returned `null`");
        String card = out[0].substring(0, 2);
        String location = out[0].substring(2);
        assertTrue(locations.contains(location), errorPrefix + " expected a location from the set " + locations + " but" +
                " you returned " + location);
        assertTrue(cards.contains(card),
                errorPrefix + " expected card played to be from the set " + cards + " but you played " + card);
        cards.remove(card);
        assertTrue(cards.contains(out[1]),
                errorPrefix + " expected a discarded card from the set " + cards + " but " + "you returned " + out[1]);
        cards.remove(out[1]);
    }

    @Test
    public void testSmallArboretum() {
        for (int i = 0; i < SMALL_STATE_MOVES.length; i++) {
            List<String> cards = new ArrayList<>(Arrays.asList(SMALL_STATE_MOVES[i][1]));
            List<String> locations = Arrays.asList(SMALL_STATE_MOVES[i][0]);
            test(SMALL_STATES[i], locations, cards);
        }
    }

    @Test
    public void testFullGame() {
        for (int i = 0; i < FULL_GAME_MOVES_1.length; i++) {
            if (i % 3 == 2) {
                List<String> locations = Arrays.asList(FULL_GAME_MOVES_1[i][0]);
                List<String> cards = new ArrayList<>(Arrays.asList(FULL_GAME_MOVES_1[i][1]));
                test(FULL_GAME_1[i], locations, cards);
            }
        }
        for (int i = 0; i < FULL_GAME_MOVES_2.length; i++) {
            if (i % 3 == 2) {
                List<String> locations = Arrays.asList(FULL_GAME_MOVES_2[i][0]);
                List<String> cards = new ArrayList<>(Arrays.asList(FULL_GAME_MOVES_2[i][1]));
                test(FULL_GAME_2[i], locations, cards);
            }
        }
    }
}
