package DONE.Section6;

import java.util.Scanner;

public class InputCalculator {

    public static void inputThenPrintSumAndAverage(){

        Scanner scanner = new Scanner(System.in);
        int count = 0;
        int input = 0;
        while (scanner.hasNextInt()){

            input += scanner.nextInt();
            count++;


        }

        double avg = (double) input/count;
        avg = Math.round(avg);
        System.out.println("SUM = " + input + " AVG = " +(int) avg );




    }
}
