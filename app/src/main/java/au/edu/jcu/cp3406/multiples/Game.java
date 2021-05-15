package au.edu.jcu.cp3406.multiples;

import android.util.Log;

public class Game {
    private final int numberOfRounds;
    private int indexOfCurrentRound = 0;
    private Round[] rounds;

    public Game(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
        rounds = generateRounds();
    }

    private Round[] generateRounds() {
        rounds = new Round[numberOfRounds];

        for (int i = 0; i < numberOfRounds; i++) {
            rounds[i] = new Round(0, i + 1);
            Log.i("game", "Created round " + i);
        }

        return rounds;
    }

    public int getNumber() {
        return rounds[indexOfCurrentRound].number;
    }


}