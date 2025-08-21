package dev.lpa;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int [] test = new int[] {1,2,3};
        reverse(test);

    }

    private static int[] reverse (int[] original){

        int [] aux = Arrays.copyOf(original, original.length);
        System.out.println(Arrays.toString(aux));


        return aux;





    }
}
