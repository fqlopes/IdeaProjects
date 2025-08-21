package dev.lpa;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        char[] test = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        var result = new Utilities().everyNthChar(test, 2);
        System.out.println("result = " + Arrays.toString(result));


    }
}
