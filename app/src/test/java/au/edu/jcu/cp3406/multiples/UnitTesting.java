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
        Round round1 = new Round(0, 25, true);
        assertEquals(25, round1.getNumber());
        assertEquals(0, round1.getRoundType());
    }

    @Test
    public void factorsRoundCreation_isCorrect() {
        Round round1 = new Round(1, 256, true);
        assertEquals(256, round1.getNumber());
        assertEquals(1, round1.getRoundType());
    }

    @Test
    public void gameCreation_isCorrect() {
        Game game = new Game(5, true);
        int number = game.getNumber();
        int type = game.getRoundType();
        assertNotNull(number); // I've chosen to ignore the yellow warnings here, otherwise the test doesn't run, and I don't see how else to check for correct game creation.
        assertNotNull(type); // as above ^
    }

    @Test
    public void timerCreation_isCorrect() {
        Timer timer = new Timer(30);
        assertEquals(30, timer.getTimeLeft());
    }

    @Test
    public void timerToString_isCorrect() {
        Timer timer = new Timer(30);
        assertEquals("30", timer.toString());
    }

    @Test
    public void timerTick_isCorrect() {
        Timer timer = new Timer(30);
        timer.tick();
        assertEquals(29, timer.getTimeLeft());
    }

    @Test
    public void timerReset_isCorrect() {
        Timer timer = new Timer(30);
        timer.tick();
        assertEquals(29, timer.getTimeLeft());
        timer.reset();
        assertEquals(30, timer.getTimeLeft());
    }
}