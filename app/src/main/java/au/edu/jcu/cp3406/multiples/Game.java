package au.edu.jcu.cp3406.multiples;

import android.util.Log;

import java.util.Random;

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

        Random random = new Random();
        int type;
        int number;

        for (int i = 0; i < numberOfRounds; i++) {

            // if type == 0, the round contains a multiples question
            // if type == 1, the round contains a factors question
            type = random.nextInt(2);
            if (type == 0) {
                number = random.nextInt(101) + 20; // int between 20 and 100
            } else {
                number = random.nextInt(501) + 100; // int between 100 and 500
            }

            rounds[i] = new Round(type, number);
            Log.i("game", "Created round " + i);
        }

        return rounds;
    }

    public int getNumber() {
        return rounds[indexOfCurrentRound].getNumber();
    }


}