package au.edu.jcu.cp3406.multiples;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTesting {

    @Test
    public void roundCreation_isCorrect() {
        Round round = new Round(1, 256);
        assertEquals(256, round.getNumber());
        assertEquals(1, round.getRoundType());
    }
}