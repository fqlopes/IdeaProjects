package dev.lpa;


//CAUTION!!!
//this code is only usable only and if only the values inside the array are already sorted by natural order!

public class BinarySearchExample {

//    Iterative method
//    Wrapper class -> returning null if not found
    public static Integer binarySearch (Integer [] values, int key){
        int first = 0;
        int last = values.length -1; // .length returns humane number, so we -1 to match programmer counting

        while (first <= last){
            int mid = (first + last) / 2;

            if( values[mid] == key){
                System.out.println("Found!");
                return mid;
            }

            if (values[mid] < key){
                System.out.println(values[mid] +   " is smaller than " + key);
                first = mid +1;
            }

            if (values[mid] > key){
                System.out.println(values[mid] +   " is bigger than " + key);
                last = mid - 1;
            }
        }
        return null;
    }

    public static Integer binarySearchRecursive (Integer [] values, int key, int start , int end ){

        int timesToFind = 0;
        if (start >= end){
            System.out.println("Number not found");
            return null;
        }

        int mid = start + (end - start)/2;


            if(key < values[mid]){

                System.out.println("Looking at number " + values[mid] + " at index " + mid );

                return binarySearchRecursive(values, key, start, mid-1);

            }

            if (key > values[mid]){
                System.out.println("Looking at number " + values[mid] + " at index " + mid );

                return binarySearchRecursive(values, key, mid+1, end);
            }

        System.out.println("Somehow we found it! Rejoice!");
        System.out.printf("Number asked %d%n  Index at %d%n ", key, mid);

            return mid;


    }
}
