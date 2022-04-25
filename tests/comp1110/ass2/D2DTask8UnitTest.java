package comp1110.ass2;

import comp1110.ass2.game.Deck;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static comp1110.ass2.ExampleGames.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;


@org.junit.jupiter.api.Timeout(value = 1000, unit = MILLISECONDS)
public class D2DTask8UnitTest {

    private final String[][] cards48DeckOnlyT = {new String[]{"A", "A", "A", "B", "B"}, new String[]{"c4d4a3a1m7m3b7a6c1b1c8b4m1j5c6j6j1a2d2d3d1b5a4j8b2j7m5b3b6d7b8c2c3j4d6a8d8c5m4m2c7a5d5a7m8m6j2j3", "A", "B"}};
    private final String[][] cards48DeckOnlyF= {new String[]{"A", "A", "A", "B", "B"}, new String[]{"c4d4a3a1m7m3b7a6c1b1c8b4m1j5c6j6j1a2d2d3d1b5a4j8b2j7m5b3b6d7b8c2c3j4d6a8d8c5m4m2c7a5d5a7m8m6j2", "A", "B"}};
    private final String[][] cards48DeckHandT = {new String[]{"A", "A", "A", "B", "B"}, new String[]{"c6j6j1a2d2d3d1b5a4j8b2j7m5b3b6d7b8c2c3j4d6a8d8c5m4m2c7a5d5a7m8m6j2j3", "Ac4a3m7b7c1c8m1", "Bd4a1m3a6b1b4j5"}};
    private final String[][] cards48DeckHandF01 = {new String[]{"A", "A", "A", "B", "B"}, new String[]{"c6j6j1a2d2d3d1b5a4j8b2j7m5b3b6d7b8c2c3j4d6a8d8c5m4m2c7a5d5a7m8m6j2j3", "Ac4a3m7b7c1c8", "Bd4a1m3a6b1b4j5"}};
    private final String[][] cards48DeckHandF02 = {new String[]{"A", "A", "A", "B", "B"}, new String[]{"c6j6j1a2d2d3d1b5a4j8b2j7m5b3b6d7b8c2c3j4d6a8d8c5m4m2c7a5d5a7m8m6j2j3", "Ac4a3m7b7c1c8m1", "Bd4a1m3a6b1b4"}};
    private final String[][] cards48AllT = {new String[]{"A", "Ac6C00C00j6N01C00", "Aj1a2", "Bd2C00C00d3C00E01", "Bd1b5"}, new String[]{"a4j8b2j7m5b3b6d7b8c2c3j4d6a8d8c5m4m2c7a5d5a7m8m6j2j3", "Ac4a3m7b7c1c8m1", "Bd4a1m3a6b1b4j5"}};
    private final String[][] cards48AllF01 = {new String[]{"A", "Ac6C00C00", "Aj1a2", "Bd2C00C00d3E01C00", "Bd1b5"}, new String[]{"a4j8b2j7m5b3b6d7b8c2c3j4d6a8d8c5m4m2c7a5d5a7m8m6j2j3", "Ac4a3m7b7c1c8m1", "Bd4a1m3a6b1b4j5"}};//miss arboretumA
    private final String[][] cards48AllF02 = {new String[]{"A", "Ac6C00C00j6N01C00", "Aj1", "Bd2C00C00d3E01C00", "Bd1b5"}, new String[]{"a4j8b2j7m5b3b6d7b8c2c3j4d6a8d8c5m4m2c7a5d5a7m8m6j2j3", "Ac4a3m7b7c1c8m1", "Bd4a1m3a6b1b4j5"}};// miss discardA
    private final String[][] cards48AllF03 = {new String[]{"A", "Ac6C00C00j6N01C00", "Aj1a2", "Bd2C00C00", "Bd1b5"}, new String[]{"a4j8b2j7m5b3b6d7b8c2c3j4d6a8d8c5m4m2c7a5d5a7m8m6j2j3", "Ac4a3m7b7c1c8m1", "Bd4a1m3a6b1b4j5"}};//miss arboretumB
    private final String[][] cards48AllF04 = {new String[]{"A", "Ac6C00C00j6N01C00", "Aj1a2", "Bd2C00C00d3E01C00", "Bd1"}, new String[]{"a4j8b2j7m5b3b6d7b8c2c3j4d6a8d8c5m4m2c7a5d5a7m8m6j2j3", "Ac4a3m7b7c1c8m1", "Bd4a1m3a6b1b4j5"}};// miss discardB



    private final String[][] duplicatesT = {new String[]{"A", "Am4C00C00b5C00W01", "Ab1d5", "Bb3C00C00c6S01C00", "Ba4c8"}, new String[]{"m5d3a1a8b7j8d2c5j4m1c1a2a5j6b6b8d1c2b4d6m7j5j2a6j3c3", "Am2j7b2a3m3c4a7", "Bd7d8m6j1d4m8c7"}};
    private final String[][] duplicatesF01 = {new String[]{"A", "Ab5C00C00b5C00W01", "Ab1d5", "Bb3C00C00c6S01C00", "Ba4c8"}, new String[]{"m5d3a1a8b7j8d2c5j4m1c1a2a5j6b6b8d1c2b4d6m7j5j2a6j3c3", "Am2j7b2a3m3c4a7", "Bd7d8m6j1d4m8c7"}};//duplicate arboretumA
    private final String[][] duplicatesF02 = {new String[]{"A", "Am4C00C00b5C00W01", "Ab1d5", "Bb3C00C00b3S01C00", "Ba4c8"}, new String[]{"m5d3a1a8b7j8d2c5j4m1c1a2a5j6b6b8d1c2b4d6m7j5j2a6j3c3", "Am2j7b2a3m3c4a7", "Bd7d8m6j1d4m8c7"}};//duplicate arboretumB
    private final String[][] duplicatesF03 = {new String[]{"A", "Am4C00C00b5C00W01", "Ab1b1", "Bb3C00C00c6S01C00", "Ba4c8"}, new String[]{"m5d3a1a8b7j8d2c5j4m1c1a2a5j6b6b8d1c2b4d6m7j5j2a6j3c3", "Am2j7b2a3m3c4a7", "Bd7d8m6j1d4m8c7"}};//duplicate discardA
    private final String[][] duplicatesF04 = {new String[]{"A", "Am4C00C00b5C00W01", "Ab1d5", "Bb3C00C00c6S01C00", "Bc8c8"}, new String[]{"m5d3a1a8b7j8d2c5j4m1c1a2a5j6b6b8d1c2b4d6m7j5j2a6j3c3", "Am2j7b2a3m3c4a7", "Bd7d8m6j1d4m8c7"}};//duplicate discardB
    private final String[][] duplicatesF05 = {new String[]{"A", "Am4C00C00b5C00W01", "Ab1d5", "Bb3C00C00c6S01C00", "Ba4c8"}, new String[]{"m5d3a1a8b7j8d2c5j4m1c1a2a5j6b6b8d1c2b4d6m7j5j2a6j3j3", "Am2j7b2a3m3c4a7", "Bd7d8m6j1d4m8c7"}};//duplicate deck
    private final String[][] duplicatesF06 = {new String[]{"A", "Am4C00C00b5C00W01", "Ab1d5", "Bb3C00C00c6S01C00", "Ba4c8"}, new String[]{"m5d3a1a8b7j8d2c5j4m1c1a2a5j6b6b8d1c2b4d6m7j5j2a6j3c3", "Am2j7b2a3m2c4a7", "Bd7d8m6j1d4m8c7"}};//duplicate handA
    private final String[][] duplicatesF07 = {new String[]{"A", "Am4C00C00b5C00W01", "Ab1d5", "Bb3C00C00c6S01C00", "Ba4c8"}, new String[]{"m5d3a1a8b7j8d2c5j4m1c1a2a5j6b6b8d1c2b4d6m7j5j2a6j3c3", "Am2j7b2a3m3c4a7", "Bd8d8m6j1d4m8c7"}};//duplicate handB

    private final String[][] arboretumT = {new String[]{"A", "Am7C00C00b3C00E01j6S01C00d2S01E01", "Aa6c6", "Bj7C00C00j2N01C00a2N02C00c8N02W01", "Bd8j3"}, new String[]{"j8d1m2j4a4m5m3a8d7j5m1m4c3a3b5b1j1c4b4a5d4d5", "Ac2b2b6c1m8c7m6", "Ba7b8b7a1d6d3c5"}};
    private final String[][] arboretumF01 = {new String[]{"A", "Am7C00C00d2S01E01b3C00E01j6S01C00", "Aa6c6", "Bj7C00C00j2N01C00a2N02C00c8N02W01", "Bd8j3"}, new String[]{"j8d1m2j4a4m5m3a8d7j5m1m4c3a3b5b1j1c4b4a5d4d5", "Ac2b2b6c1m8c7m6", "Ba7b8b7a1d6d3c5"}};
    private final String[][] arboretumF02 = {new String[]{"A", "Am7C00C00b3C00E01j6S01C00d2S02E01", "Aa6c6", "Bj7C00C00j2N01C00a2N02C00c8N02W01", "Bd8j3"}, new String[]{"j8d1m2j4a4m5m3a8d7j5m1m4c3a3b5b1j1c4b4a5d4d5", "Ac2b2b6c1m8c7m6", "Ba7b8b7a1d6d3c5"}};
    private final String[][] arboretumF03 = {new String[]{"A", "Am7C01C01b3C00E01j6S01C00d2S01E01", "Aa6c6", "Bj7C00C00j2N01C00a2N02C00c8N02W01", "Bd8j3"}, new String[]{"j8d1m2j4a4m5m3a8d7j5m1m4c3a3b5b1j1c4b4a5d4d5", "Ac2b2b6c1m8c7m6", "Ba7b8b7a1d6d3c5"}};

    private final String[][] arboretumCardsBEqualsA = {new String[]{"A", "Ad6C00C00d1C00E01", "Ab6b2", "Bj6C00C00c7S01C00", "Bc4m4"}, new String[]{"d7a1m7a6m8a8m2j4b8d4d5c8m6c6j8d2j1m3j5m5a3j2b7d8m1j7", "Ab5a4b1b3a5j3c2", "Bd3a2a7b4c1c5c3"}};
    private final String[][] arboretumCardsBless1A = {new String[]{"A", "Ad6C00C00d1C00E01d7C00E02", "Ab6b2", "Bj6C00C00c7S01C00", "Bc4m4"}, new String[]{"a1m7a6m8a8m2j4b8d4d5c8m6c6j8d2j1m3j5m5a3j2b7d8m1j7", "Ab5a4b1b3a5j3c2", "Bd3a2a7b4c1c5c3"}};
    private final String[][] arboretumCardsBless2A = {new String[]{"A", "Ad6C00C00d1C00E01d7C00E02a1S01C00", "Ab6b2", "Bj6C00C00c7S01C00", "Bc4m4"}, new String[]{"m7a6m8a8m2j4b8d4d5c8m6c6j8d2j1m3j5m5a3j2b7d8m1j7", "Ab5a4b1b3a5j3c2", "Bd3a2a7b4c1c5c3"}};
    private final String[][] arboretumCardsBGreatA = {new String[]{"A", "Ad6C00C00d1C01E01", "Ab6b2", "Bj6C00C00c7S01C00d7C00E01", "Bc4m4"}, new String[]{"a1m7a6m8a8m2j4b8d4d5c8m6c6j8d2j1m3j5m5a3j2b7d8m1j7", "Ab5a4b1b3a5j3c2", "Bd3a2a7b4c1c5c3"}};

    private final String[][] hand0T = {new String[]{"A", "A", "A", "B", "B"}, new String[]{"d6a1c4c2j2c3a3d1a4a6j7a7a2d8d5c8b7b3d4b8m5m8m3b4c1j5m2b1d2c6c5a8d3m1d7j1m7b2b6j3j6j4b5j8a5m4m6c7", "A", "B"}};
    private final String[][] hand0F01 = {new String[]{"A", "A", "Ad6a1", "B", "B"}, new String[]{"c4c2j2c3a3d1a4a6j7a7a2d8d5c8b7b3d4b8m5m8m3b4c1j5m2b1d2c6c5a8d3m1d7j1m7b2b6j3j6j4b5j8a5m4m6c7", "A", "B"}};
    private final String[][] hand0F02 = {new String[]{"A", "Ad6C00C00", "A", "B", "B"}, new String[]{"a1c4c2j2c3a3d1a4a6j7a7a2d8d5c8b7b3d4b8m5m8m3b4c1j5m2b1d2c6c5a8d3m1d7j1m7b2b6j3j6j4b5j8a5m4m6c7", "A", "B"}};

    private final String[][] notTurnHand7T = {new String[]{"A", "A", "A", "B", "B"}, new String[]{"j5m2m6c8c3j7a5d2c7a3m7m4c1a2a1a7d4j2b4d8d5j4b7d1b8c6j8a8m3a6a4b2b5d3", "Ad7b3d6c5j1b6c4", "Bm5m8c2j6m1b1j3"}};
    private final String[][] notTurnHand7F01 = {new String[]{"A", "A", "A", "B", "B"}, new String[]{"m2m6c8c3j7a5d2c7a3m7m4c1a2a1a7d4j2b4d8d5j4b7d1b8c6j8a8m3a6a4b2b5d3", "Ad7b3d6c5j1b6c4", "Bm5m8c2j6m1b1j3j5"}};
    private final String[][] notTurnHand7F02 = {new String[]{"A", "A", "A", "B", "B"}, new String[]{"j3j5m2m6c8c3j7a5d2c7a3m7m4c1a2a1a7d4j2b4d8d5j4b7d1b8c6j8a8m3a6a4b2b5d3", "Ad7b3d6c5j1b6c4", "Bm5m8c2j6m1b1"}};

    private final String[][] turnHand7T = {new String[]{"A", "A", "A", "B", "B"}, new String[]{"j8c8a3j3a2j6b4m1a1a8m4a5d5c6b8d8b3b2c5c3c7d3a4b7b5j2d6m5j4c2a7j5d1d7", "Am7m8d4b1c1b6c4", "Bm6j7d2m3a6j1m2"}};
    private final String[][] turnHand8T = {new String[]{"A", "A", "A", "B", "B"}, new String[]{"c8a3j3a2j6b4m1a1a8m4a5d5c6b8d8b3b2c5c3c7d3a4b7b5j2d6m5j4c2a7j5d1d7", "Am7m8d4b1c1b6c4j8", "Bm6j7d2m3a6j1m2"}};
    private final String[][] turnHand9T = {new String[]{"A", "A", "A", "B", "B"}, new String[]{"a3j3a2j6b4m1a1a8m4a5d5c6b8d8b3b2c5c3c7d3a4b7b5j2d6m5j4c2a7j5d1d7", "Am7m8d4b1c1b6c4j8c8", "Bm6j7d2m3a6j1m2"}};
    private final String[][] turnHand7F01 = {new String[]{"A", "A", "A", "B", "B"}, new String[]{"c4j8c8a3j3a2j6b4m1a1a8m4a5d5c6b8d8b3b2c5c3c7d3a4b7b5j2d6m5j4c2a7j5d1d7", "Am7m8d4b1c1b6", "Bm6j7d2m3a6j1m2"}};
    private final String[][] turnHand7F02 = {new String[]{"A", "A", "A", "B", "B"}, new String[]{"j3a2j6b4m1a1a8m4a5d5c6b8d8b3b2c5c3c7d3a4b7b5j2d6m5j4c2a7j5d1d7", "Am7m8d4b1c1b6c4j8c8a3", "Bm6j7d2m3a6j1m2"}};

    private final String[][] discardLessT = {new String[]{"A", "Ad3C00C00m3C00E01b2S01C00j2S01E01", "Ad7j4d8", "Bj7C00C00j8C00E01b1S01C00", "Bd2c7"}, new String[]{"j3m6c8a5m1a1c1b6d4j6a7m8m5j1b8b5a3d1m7b7d6c6", "Ab3a8m4c2a2j5b4", "Ba4a6m2d5c5c4c3"}};
    private final String[][] discardEqualsT = {new String[]{"A", "Ad3C00C00m3C00E01b2S01C00", "Ad7j4d8", "Bj2C00C00j7C00E01", "Bj8b1"}, new String[]{"d2c7j3m6c8a5m1a1c1b6d4j6a7m8m5j1b8b5a3d1m7b7d6c6", "Ab3a8m4c2a2j5b4", "Ba4a6m2d5c5c4c3"}};
    private final String[][] discardF = {new String[]{"A", "Ad3C00C00m3C00E01b2S01C00", "Ad7j4d8j2j7j8b1d2c7", "B", "B"}, new String[]{"j3m6c8a5m1a1c1b6d4j6a7m8m5j1b8b5a3d1m7b7d6c6", "Ab3a8m4c2a2j5b4", "Ba4a6m2d5c5c4c3"}};

    private String errorPrefix(String[][] state) {
        return "Arboretum.isStateValid(" + System.lineSeparator() + "sharedState: " + Arrays.toString(state[0]) + System.lineSeparator() +
                "hiddenState: " + Arrays.toString(state[1]) + ")"
                + System.lineSeparator();
    }

    private void test(String[][] state, boolean expected) {
        String errorPrefix = errorPrefix(state);
        boolean out = Arboretum.isStateValid(state);
        assertEquals(expected, out, errorPrefix);
    }

    @Test
    public void test48CardsState() {
        test(cards48DeckOnlyT, true);
        test(cards48DeckOnlyF, false);
        test(cards48DeckHandT, true);
        test(cards48DeckHandF01, false);
        test(cards48DeckHandF02, false);
        test(cards48AllT, true);
        test(cards48AllF01, false);
        test(cards48AllF02, false);
        test(cards48AllF03, false);
        test(cards48AllF04, false);
    }
    @Test
    public void testDuplicatesState() {
        test(duplicatesT, true);
        test(duplicatesF01, false);
        test(duplicatesF02, false);
        test(duplicatesF03, false);
        test(duplicatesF04, false);
        test(duplicatesF05, false);
        test(duplicatesF06, false);
        test(duplicatesF07, false);
    }
    @Test
    public void testArboretumState() {
        test(arboretumT, true);
        test(arboretumF01, false);
        test(arboretumF02, false);
        test(arboretumF03, false);

    }
    @Test
    public void testArboretumCardsState() {
        test(arboretumCardsBEqualsA, true);
        test(arboretumCardsBless1A, true);
        test(arboretumCardsBless2A, false);
        test(arboretumCardsBGreatA, false);

    }

    @Test
    public void testHand0State() {
        test(hand0T, true);
        test(hand0F01, false);
        test(hand0F02, false);

    }

    @Test
    public void testNotTurnHand0State() {
        test(notTurnHand7T, true);
        test(notTurnHand7F01, false);
        test(notTurnHand7F02, false);

    }
    @Test
    public void testTurnHand7State() {
        test(turnHand7T, true);
        test(turnHand8T, true);
        test(turnHand9T, true);
        test(turnHand7F01, false);
        test(turnHand7F02, false);

    }
    @Test
    public void testDiscardState() {
        test(discardLessT, true);
        test(discardEqualsT, true);
        test(discardF, false);

    }
}
