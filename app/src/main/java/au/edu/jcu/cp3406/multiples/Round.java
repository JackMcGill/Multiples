package au.edu.jcu.cp3406.multiples;

public class Round {
    private int roundType;
    private int number;

    public Round(int roundType, int number) {
        this.roundType = roundType;
        this.number = number;
    }

    public int getRoundType() {
        return roundType;
    }

    public int getNumber() {
        return number;
    }
}
