package study.self;

import java.util.Random;

record RandomBall(char letter, int number) {

    @Override
    public String toString() {
        return "%c%d".formatted(letter, number).toUpperCase();
    }

}

public class Main {
    public static Random random = new Random();
    public static void main(String[] args) {
        BallCage prizedBall = new BallCage();
        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                var randomChars = random.nextInt(97, 123);
                var randomNumber = random.nextInt(1, 11);
                prizedBall.producerRandomBalls(new RandomBall((char) randomChars, randomNumber));
            }
        });
        producerThread.start();


        Thread consumerThread = new Thread(() -> {

            for (int i = 0; i < 10; i ++) {
                RandomBall item = prizedBall.consumerBalls();
            }
        });

        consumerThread.start();

    }
}
