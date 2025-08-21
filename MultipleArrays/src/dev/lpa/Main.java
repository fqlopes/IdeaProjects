package dev.lpa;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        int[][] array2 = new int [4][4];
//        System.out.println(Arrays.toString(array2));
//
//        for (int [] outer : array2){
//            System.out.println(Arrays.toString(outer));
//        }

//        for (int i = 0; i < array2.length; i++){
//            var innerArray = array2[i];
//            for (int j = 0; j < array2.length; j++){
//                System.out.println(array2[i][j] + "");
//            }
//            System.out.println(); 
//        }

        for (var outer : array2){
            for (var element : outer){
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
