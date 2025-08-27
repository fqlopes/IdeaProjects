package academy.learnprogramming;



import java.util.Random;

public class NumberGeneratorImpl implements NumberGenerator {
//Our first time here: constructing the project of the lesson being taken

    // == fields ==
    private final Random random = new Random();
    private int maxNumber = 100;

    // == public methods ==
    @Override
    public int next() {
        return random.nextInt(maxNumber);
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }
}
