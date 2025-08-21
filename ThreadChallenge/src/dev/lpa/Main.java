package dev.lpa;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args){

        System.out.println("Creating a thread by passing a runnable. First i'll create the what's supposed to \"run\"");

        Runnable oddNumbers = () -> {
            for (int i = 0; i < 100; i++){
                System.out.print(" " + Thread.currentThread().getName());
                if (i % 2 == 1) {
                    System.out.print(" " + i +" ");
                }
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    System.out.println("Oh no!");
                    e.printStackTrace();
                }
            }
        };

        System.out.println("With a Runnable created, we pass it to a Thread's constructor that takes a runnable");




        Thread runThread = new Thread(oddNumbers);
        CustomThread myThread = new CustomThread();
        myThread.start();
        runThread.start();
        myThread.interrupt();

        while (!myThread.isAlive()) {

            try {

                TimeUnit.SECONDS.sleep(5);
                myThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void threadStatus (Thread thread) {

        System.out.println(System.lineSeparator());
        System.out.println("-".repeat(90));
        System.out.println(thread.getState());
        System.out.println("-".repeat(90));

    }
}
