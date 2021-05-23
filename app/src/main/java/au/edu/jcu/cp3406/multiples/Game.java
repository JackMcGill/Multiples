package au.edu.jcu.cp3406.multiples;

import android.util.Log;

import java.util.Random;

public class Game {
    private final boolean isHardMode;
    private int indexOfCurrentRound = 0;
    private final Round[] rounds;
    private Round currentRound;

    public Game(int numberOfRounds, boolean isHardMode) {
        this.isHardMode = isHardMode;
        rounds = new Round[numberOfRounds];

        for (int i = 0; i < numberOfRounds; i++) {
            rounds[i] = createRound();
        }

        currentRound = rounds[0];
    }

    private Round createRound() {
        int type;
        int number;

        // if type == 0, the round contains a multiples question
        // if type == 1, the round contains a factors question
        Random random = new Random();
        type = random.nextInt(2);

        if (isHardMode) {
            // hard mode
            if (type == 0) {
                number = random.nextInt(51) + 50; // int between 50 and 100
            } else {
                number = random.nextInt(401) + 100; // int between 100 and 500
            }
        } else {
            // easy mode
            if (type == 0) {
                number = random.nextInt(41) + 10; // int between 10 and 50
            } else {
                number = random.nextInt(81) + 20; // int between 20 and 100
            }
        }

        return currentRound = new Round(type, number, isHardMode);
    }

    public int getNumber() {
        return currentRound.getNumber();
    }

    public int getRoundType() {
        return currentRound.getRoundType();
    }

    public int[] getAllOptions() {
        return currentRound.getAllOptions();
    }

    public boolean nextRound() {
        Log.i("Game", "Index = " + indexOfCurrentRound);
        Log.i("Game", "length = " + rounds.length);

        ++indexOfCurrentRound;
        if (indexOfCurrentRound == rounds.length) {
            // end of game
            return true;
        }

        currentRound = rounds[indexOfCurrentRound];
        return false;
    }

    public int getNumberOfAnswers() {
        return currentRound.getCorrectAnswers().length;
    }

    public boolean checkAnswer(int playerAns) {
        int[] correctAnswers = currentRound.getCorrectAnswers();
        boolean isCorrect = false;

        for (int answer : correctAnswers) {
            if (playerAns == answer) {
                isCorrect = true;
                break;
            }
        }
        return isCorrect;
    }

    public int getCurrentRound() {
        return indexOfCurrentRound;
    }
}