package au.edu.jcu.cp3406.multiples;

import java.util.ArrayList;
import java.util.Random;

public class Round {
    private int roundType; // value of 0 == multiples question, value of 1 == factors question
    private int number;
    private int[] correctAnswers;
    private int[] options;
    private int[] userAnswers;

    public Round(int type, int number) {
        this.roundType = type;
        this.number = number;

        if (type == 0) {
            correctAnswers = findMultiples();
        } else {
            correctAnswers = findFactors();
        }
    }

    private int[] findFactors() {
        int[] factors = new int[6];
        ArrayList<Integer> allFactors = new ArrayList<>();

        // determine all factors for the given number
        for (int i = 0; i <= number; ++i) {
            if (number % i == 0) {
                allFactors.add(i);
            }
        }

        // randomly select some factors from allFactors and add them to factors[]
        Random random = new Random();
        for (int i = 0; i <= 6; ++i) {
            factors[i] = allFactors.get(random.nextInt(allFactors.size()));
            //TODO check for duplicates
        }

        return factors;
    }


    private int[] findMultiples() {
        int[] multiples = new int[6];

        Random random = new Random();

        for (int i = 0; i <= 6; ++i) {
            multiples[i] = random.nextInt(10) + 1; // find a random number between 1 and 10 inclusive
            multiples[i] = multiples[i] * number;
            //TODO check for duplicates
        }

        return multiples;
    }

//    private int[] generateOptions(int[] answers) {
//
//    }
}
