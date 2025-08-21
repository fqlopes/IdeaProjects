package study.self;

import java.util.*;

public class BallCage {

    private List<RandomBall> sortedBalls;

    public BallCage (){
        this.sortedBalls = new ArrayList<>();
    }

    public synchronized void producerRandomBalls (RandomBall ball){

        while (sortedBalls.size() > 500) {

            try {

                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
            this.sortedBalls.add(ball);
            notifyAll();

    }

    public synchronized RandomBall consumerBalls () {

        while (sortedBalls.isEmpty()) {
            try{
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Collections.shuffle(sortedBalls);
        var randomBall = sortedBalls.remove(0);
        notifyAll();
        System.out.printf("%n%nAnd the prized ball is: %s", randomBall);

        return randomBall;
    }
}
