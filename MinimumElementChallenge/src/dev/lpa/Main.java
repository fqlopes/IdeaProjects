package dev.lpa;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int[] array =readIntegers();

        System.out.println(findMin(array));

        System.out.println("The minimal value found was "+ findMin(array));
    }

    private static int[] readIntegers(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set the size of the array:");
        int sizeInput = scanner.nextInt();
        System.out.println("The size chosen was "+ sizeInput);
        int[] newArray = new int[sizeInput];

        for (int i = 0; i < sizeInput; i ++){
            System.out.println("Choose the " +( i +1 )+ " Number");
            newArray[i] = scanner.nextInt();
        }
        System.out.println(Arrays.toString(newArray));
        return newArray;
    }

    private static int findMin (int[] array){

        int minValue = array[0];

        for (int i = 0; i <= array.length -1; i++){
            if (array[i] < minValue){
                minValue = array[i];
            }
        }
        return minValue;
    }
}
