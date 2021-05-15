package au.edu.jcu.cp3406.multiples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Round {
    private final int roundType;
    private final int number;
    private final int[] correctAnswers;
    private final int[] allOptions;
//    private final int[] userAnswers;

    public Round(int roundType, int number) {
        this.roundType = roundType;
        this.number = number;

        if (roundType == 0) {
            correctAnswers = generateMultiples();
        } else {
            correctAnswers = generateFactors();
        }

        allOptions = generateOptions();
    }


    // returns an array of multiples with 5 elements
    private int[] generateMultiples() {
        int[] multiples = new int[5];
        int multiplier;
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            multiplier = random.nextInt(11) + 1;
            multiples[i] = number * multiplier;
        }

        return multiples;
    }

    // returns an array of factors with <= 5 elements
    private int[] generateFactors() {
        ArrayList<Integer> allFactors = new ArrayList<>();

        // determine all factors for the given number
        for (int i = 1; i <= number; ++i) {
            if (number % i == 0) {
                allFactors.add(i);
            }
        }

        Collections.shuffle(allFactors);

        // put factors into an array and reduce size to 5
        int[] factors = new int[5];

        for (int i = 0; i < 5; ++i) {
            factors[i] = allFactors.get(i);
        }

        return factors;


    }

    private int[] generateOptions() {
        int[] options = Arrays.copyOf(correctAnswers, correctAnswers.length);


        Random random = new Random();
        boolean found = false;
        while (options.length < 10) {

            int newNum = random.nextInt(100);
            for (int num : options) {

                if (newNum == num) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                // create a new array one size larger
                int newLength = options.length + 1;
                options = Arrays.copyOf(options, newLength);
                options[options.length - 1] = newNum;
                found = false;
            }
        }

        // randomize array
        for (int i = 0; i < options.length; i++) {
            int randomIndexToSwap = random.nextInt(options.length);
            int temp = options[randomIndexToSwap];
            options[randomIndexToSwap] = options[i];
            options[i] = temp;
        }

        return options;
    }

    public int getRoundType() {
        return roundType;
    }

    public int getNumber() {
        return number;
    }
}
