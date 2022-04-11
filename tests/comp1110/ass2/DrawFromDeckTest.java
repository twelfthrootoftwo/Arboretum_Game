package comp1110.ass2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.Timeout(value = 2000, unit = MILLISECONDS)
public class DrawFromDeckTest {

    public static int BASE_ITERATIONS = 10000;
    static final List<Character> validChars = Arrays.asList('a', 'b', 'c', 'd', 'j', 'm');

    private String errorPrefix(String deck) {
        return "Arboretum.drawFromBag(\"" + deck + "\")"
                + System.lineSeparator();
    }

    @Test
    public void testDistribution() {
        int[] count = new int[48];
        String deck = "a1a2a3a4a5a6a7a8b1b2b3b4b5b6b7b8c1c2c3c4c5c6c7c8d1d2d3d4d5d6d7d8j1j2j3j4j5j6j7j8m1m2m3m4m5m6m7m8";

        String errorMessagePrefix = errorPrefix(deck);
        for (int i = 0; i < BASE_ITERATIONS; i++) {
            String c = Arboretum.drawFromDeck(deck);
            assertNotNull(c, errorMessagePrefix + " expected a card drawn, but you returned null");
            int index = (validChars.indexOf(c.charAt(0)) * 8) + Character.getNumericValue(c.charAt(1)) - 1;
            assertTrue(validChars.contains(c.charAt(0)));
            assertTrue(Character.getNumericValue(c.charAt(1)) >= 0 && Character.getNumericValue(c.charAt(1)) <= 9);
            count[index]++;
        }
        assertTrue(Arrays.stream(count).min().getAsInt() > 0, errorMessagePrefix + "expected you to draw at least one" +
                " of each card in 10,000 draws, but you missed a value");

        double[] expectedProb = new double[48];
        Arrays.fill(expectedProb, 1 / 48.0);
        double chi = chiSquared(expectedProb, BASE_ITERATIONS, count);
        assertTrue(chi < 64.001, errorMessagePrefix + "Distribution of cards drawn doesn't appear to be uniform (chi " +
                "squared value of " + chi + ")");
    }

    private static double chiSquared(double[] expectedProb, int samples, int[] counts) {
        double total = 0;
        for (int i = 0; i < expectedProb.length; i++) {
            double mi = ((double) samples) * expectedProb[i];
            total += ((double) counts[i] - mi) * ((double) counts[i] - mi) / mi;
        }
        return total;
    }

    @Test
    public void testCorrectCard() {
        String deck = "";
        String errorMessagePrefix = errorPrefix(deck);
        String output = Arboretum.drawFromDeck(deck);
        assertEquals("", output, errorMessagePrefix + "Expected empty string for empty deck");

        String[] cards = "a1a3a7a8b2b5b6c2c4c5c7d1d3d4d6d7d8".split("(?<=\\G.{2})");
        int[] count = new int[17];

        for (int i = 0; i < BASE_ITERATIONS; i++) {
            deck = "a1a3a7a8b2b5b6c2c4c5c7d1d3d4d6d7d8";
            errorMessagePrefix = errorPrefix(deck);
            output = Arboretum.drawFromDeck(deck);
            for (int j = 0; j < cards.length; j++) {
                if (output.equals(cards[j])) {
                    count[j]++;
                }
            }
        }
        List<String> missed = new ArrayList<>();
        for (int j = 0; j < count.length; j++) {
            if (count[j] == 0) {
                missed.add(cards[j]);
            }
        }
        assertTrue(Arrays.stream(count).min().getAsInt() > 0, errorMessagePrefix + "expected you to draw at " +
                "least one of each card in 10,000 draws, but you missed cards: " + missed);
        assertTrue(deck.contains(output),
                errorMessagePrefix + "Expected card drawn to be in deck, but you returned " + output);
    }
}
