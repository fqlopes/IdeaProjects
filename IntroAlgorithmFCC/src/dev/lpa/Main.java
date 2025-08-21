package dev.lpa;


public class Main {

    public static void main(String[] args) {

        Integer[] values = new Integer[Integer.MAX_VALUE/2048];

        for (int i = 0; i < values.length; i++){
            values[i] = i+1;
        }

//        int linearSeach = LinearSearchExample.linearSearch(values, 970);
//        System.out.println(linearSeach);

//       Integer binarySearch =  BinarySearchExample.binarySearch(values, 970);
//
//        System.out.println(binarySearch);


       Integer anotherSearch = BinarySearchExample.binarySearchRecursive(values, 970, 0, values.length -1);

        Array.ArrayExample();

    }

}
