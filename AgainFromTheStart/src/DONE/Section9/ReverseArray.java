package DONE.Section9;

import java.util.Arrays;

public class ReverseArray {

    private static void reverse (int[] intArray){

        System.out.println("Array = "+ Arrays.toString(intArray));
        int[] copyArray = Arrays.copyOf(intArray, intArray.length);
        for (int i =  intArray.length -1, j = 0; i >= 0; i--){
            intArray[j] = copyArray[i];
            j++;
        }
        System.out.println("Reversed Array = " + Arrays.toString(intArray));
    }
}
