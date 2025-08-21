package DONE.Section9;

import java.util.Arrays;
import java.util.Scanner;

public class MinimumElement {

    public static int readInteger(){
        return new Scanner(System.in).nextInt();
    }

    public static int[] readElements (int element){

        Scanner scanner = new Scanner(System.in);
        int[] intArray = new int[element];
        for (int i = 0; i < intArray.length; i++){
            intArray[i] = readInteger();

//             int number =  scanner.nextInt();
//            intArray[i] = number;
        }
        System.out.println(Arrays.toString(intArray));
        return intArray;
    }

    public static int findMin (int[] intArray){

        var copy = Arrays.copyOf(intArray, intArray.length);
        Arrays.sort(copy);
        return copy[0];
    }
}
