package dev.lpa;

import java.util.concurrent.TimeUnit;

public class CustomThread extends Thread {

    @Override
    public void run() {




            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Creating thread through extending Thread");
                for (int i = 0; i < 100; i++) {
                    System.out.print(" " + Thread.currentThread().getName());
                    if (i % 2 == 0 && i != 0) {
                        System.out.print(" " + i + " ");
                    }
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        System.out.println("Ops, you fucked up big time");
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("IT'S OVER BRUH");
    }
}

