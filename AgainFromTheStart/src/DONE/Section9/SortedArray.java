package DONE.Section9;

import java.util.Arrays;
import java.util.Scanner;

public class SortedArray {



    public static int[] getIntegers (int size){
        Scanner scanner = new Scanner(System.in);
        int[] intArray = new int[size];
        for (int i = 0; i < intArray.length; i++){

            intArray[i] = scanner.nextInt();
        }

        return intArray;
    }

    public static void printArray (int[] intArray){

        for (int i = 0; i < intArray.length; i++){
            System.out.println("Element " + i + " contents " + intArray[i] );
        }

    }

    public static int[] sortIntegers(int[] intArray){


        Arrays.sort(intArray);

        int[] copyArray = Arrays.copyOf(intArray, 5);

        for (int i =  intArray.length -1, j = 0; i >= 0; i--){
            intArray[j] = copyArray[i];
            j++;
        }
        return intArray;
    }
}
