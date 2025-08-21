package dev.lpa;

public class Main {
    public static void main(String[] args) {
        //Write a regular expression that matches the exact sentence "Hello, World!".
        //Use the matches method on string to check if the input sentence matches this pattern.
        //Use only literals characters in the regular expression.

        String target = "Hello, World!";
        boolean matches = target.matches("Hello, World!");
        System.out.println(matches);

        //Create a regular expression that matches a sentence starting with an uppercase letter,
        //followed by zero or more lowercase letters, and ending with a period.

        String target2 = "The bike is red.";
        boolean matches2 = target2.matches("^[A-Z][\\p{all}.+");
        System.out.println(matches2);

    }


}
