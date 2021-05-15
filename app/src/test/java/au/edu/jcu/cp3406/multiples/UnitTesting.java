package au.edu.jcu.cp3406.multiples;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTesting {

    @Test
    public void multiplesRoundCreation_isCorrect() {
        Round round1 = new Round(0, 25);
        assertEquals(25, round1.getNumber());
        assertEquals(0, round1.getRoundType());
    }

    @Test
    public void factorsRoundCreation_isCorrect() {
        Round round1 = new Round(1, 256);
        assertEquals(256, round1.getNumber());
        assertEquals(1, round1.getRoundType());
    }

    @Test
    public void gameCreation_isCorrect() {
        Game game = new Game(5);
        int number = game.getNumber();
        int type = game.getRoundType();
        assertNotNull(number);
        assertNotNull(type);
    }
}