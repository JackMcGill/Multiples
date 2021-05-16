package au.edu.jcu.cp3406.multiples;

import java.util.Random;

public class Game {
    private final int numberOfRounds;
    private int indexOfCurrentRound = 0;
    private Round[] rounds;
    private Round currentRound;

    public Game(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
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
        if (type == 0) {
            number = random.nextInt(81) + 20; // int between 20 and 100
        } else {
            number = random.nextInt(401) + 100; // int between 100 and 500
        }
        return currentRound = new Round(type, number);
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

    public void nextRound() {
        ++indexOfCurrentRound;
        currentRound = rounds[indexOfCurrentRound];
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
            }
        }
        return isCorrect;
    }

    public int getCurrentRound() {
        return indexOfCurrentRound;
    }
}