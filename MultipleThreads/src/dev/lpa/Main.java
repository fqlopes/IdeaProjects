package dev.lpa;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {


        StopWatch greenWatch  = new StopWatch(TimeUnit.SECONDS);
        StopWatch purpleWatch  = new StopWatch(TimeUnit.SECONDS);
        StopWatch redWatch  = new StopWatch(TimeUnit.SECONDS);

        Thread green = new Thread (greenWatch::countdown, ThreadColor.ANSI_GREEN.name());
        Thread purple = new Thread(() -> purpleWatch.countdown(7), ThreadColor.ANSI_PURPLE.name());
        Thread red = new Thread(redWatch::countdown, ThreadColor.ANSI_RED.name());

        green.start();
        purple.start();
        red.start();



    }
}

class StopWatch {

    private TimeUnit timeUnit;
    private int i;

    public StopWatch(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public void countdown () {
        this.countdown(5);
    }

    public void countdown(int unitCount) {
        String threadName = Thread.currentThread().getName();
        ThreadColor threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf(threadName);
        } catch (IllegalArgumentException e) {
        }
        String color = threadColor.color();
        for (int i = unitCount; i > 0; i--) {
            try {
                timeUnit.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("%s%s Thread : i = %d%n", color, threadName, i);
        }
    }
}
