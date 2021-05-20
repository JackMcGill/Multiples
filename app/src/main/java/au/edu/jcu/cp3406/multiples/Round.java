package au.edu.jcu.cp3406.multiples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Round {
    private final int roundType;
    private final int number;
    private final boolean isHardMode;
    private final int[] correctAnswers;
    private final int[] allOptions;

    public Round(int roundType, int number, boolean isHardMode) {
        this.roundType = roundType;
        this.number = number;
        this.isHardMode = isHardMode;

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

            if (isHardMode) {
                multiplier = random.nextInt(21) + 10; // hard mode yields larger numbers
            } else {
                multiplier = random.nextInt(11) + 1;
            }


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
        // put factors into an array and reduce size to 5 (if current size is greater than 5)
        int[] factors;
        if (allFactors.size() < 5) {
            factors = new int[allFactors.size()];

            for (int i = 0; i < allFactors.size(); ++i) {
                factors[i] = allFactors.get(i);
            }
        } else {
            factors = new int[5];

            for (int i = 0; i < 5; ++i) {
                factors[i] = allFactors.get(i);
            }
        }

        return factors;
    }

    // returns an array of options for the user to pick from - a mix of correct and incorrect choices
    private int[] generateOptions() {
        int[] options = Arrays.copyOf(correctAnswers, correctAnswers.length);

        Random random = new Random();
        boolean found = false;
        int[] minMax = findMinMax(options);

        // these are defined and used separately for readability
        int min = minMax[0];
        int max = minMax[1];

        while (options.length < 10) {
            int newNum = random.nextInt(max) + min; // create random ints within the range of the exisiting array of answers
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

    // find the smallest and largest value in array
    private int[] findMinMax(int[] array) {
        int[] minMax = new int[2];

        // find smallest
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        // find largest
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        minMax[0] = min;
        minMax[1] = max;

        return minMax;
    }

    public int getNumber() {
        return number;
    }

    public int getRoundType() {
        return roundType;
    }

    public int[] getAllOptions() {
        return allOptions;
    }

    public int[] getCorrectAnswers() {
        return correctAnswers;
    }
}
