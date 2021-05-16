package au.edu.jcu.cp3406.multiples;

import java.util.Random;

public class Game {
    private final int numberOfRounds;
    private int indexOfCurrentRound = 0;
    private Round[] rounds;
    private Round round;

    public Game(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
        round = createRound();
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
        return round = new Round(type, number);
    }

    public int getNumber() {
        return round.getNumber();
    }

    public int getRoundType() {
        return round.getRoundType();
    }

    public int[] getAllOptions() {
        return round.getAllOptions();
    }

    public void nextRound() {
        ++indexOfCurrentRound;
    }

    public boolean checkAnswer(int playerAns) {
        int[] correctAnswers = round.getCorrectAnswers();
        boolean isCorrect = false;

        for (int answer : correctAnswers) {
            if (playerAns == answer) {
                isCorrect = true;
            }
        }
        return isCorrect;
    }
}