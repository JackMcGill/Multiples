package au.edu.jcu.cp3406.multiples;

public class Game {
    private int rounds;
    private int difficulty;
    private Round round;

    public Game(int rounds, int difficulty) {
        this.rounds = rounds;
        this.difficulty = difficulty;
    }

    public void play() {
        for (int i = 1; i < rounds; ++i) {
            // create new round
        }
    }
}

