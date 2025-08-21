package dev.lpa;

public class LinearSearchExample {

    /*
        Returns the index position of the target, if found, else returns -1
    */
//    Classic loop
    public static int linearSearch (Integer [] data, int key){
        for (int index = 0 ; index < data.length ; index++){

            System.out.printf(" is %d == %d ? ... we looking that shit up %n", index, key);
            if(data[index] == key){
                System.out.println(key + " found at index " + index);

                return index;
            }
        }

        System.out.println("Number " + key + " not found.");
        return -1;
    }

//    Enhanced Loop
    public static int linearSearch2 (Integer [] data, int key){
        for( var value : data){
            if (value == key){
                return value;
            }
        }

        System.out.println("Number " + key + " not found.");
        return -1;
    }
}

