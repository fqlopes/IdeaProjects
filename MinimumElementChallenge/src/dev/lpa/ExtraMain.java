package dev.lpa;

import java.util.Arrays;
import java.util.Scanner;

public class ExtraMain {
    public static void main(String[] args) {
        int [] newArray = readIntegers();
        System.out.println("The array written was " + Arrays.toString(newArray));
        int minValue = findMin(newArray);
        System.out.println("The minimal value found inside the array was " + minValue);
        readElements(newArray);}

    private static int[] readIntegers(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write a comma delimited list of integers");
        String intList = scanner.nextLine();
        String[] integerList = intList.split(",");
        int[] intIntegerList = new int[integerList.length];
        for (int i = 0; i < integerList.length; i++){
           intIntegerList[i] = Integer.parseInt(integerList[i]);
        }
        System.out.println(Arrays.toString(intIntegerList));
       return intIntegerList;
    }

    private static void readElements (int [] array){

        for (int i : array){
            System.out.println("Element "+ i + " is " +array[i]);
        }
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

