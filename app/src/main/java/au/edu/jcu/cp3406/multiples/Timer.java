package au.edu.jcu.cp3406.multiples;

public class Timer {
    private int totalTime;
    private int timeLeft;

    Timer(int timePerRound) {
        totalTime = timeLeft = timePerRound;
    }

    public void tick() {
        timeLeft--;
    }

    public void reset() {
        timeLeft = totalTime;
    }

    @Override
    public String toString() {
        return String.valueOf(timeLeft);
    }

    public int getTimeLeft() {
        return timeLeft;
    }
}
