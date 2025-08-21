package dev.lpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;




public class AnotherMain {

    public static void main(String[] args) {


        String [] names = {"Ana", "Bob", "Cid", "David", "Ed", "Fernando", "Gilmar"};
        List<String> list = new ArrayList<>(List.of("Ana", "Bob", "Cid", "David", "Ed", "Fernando", "Gilmar"));

        //making a methods reference of toUpperCase
        UnaryOperator<String> toUppercase = String::toUpperCase;
        Consumer<String>  upperCase = String::toUpperCase;

        //Applying the function on the list
        list.forEach(upperCase);
        System.out.println(list);
        list.replaceAll(toUppercase);
        System.out.println("Making everything uppercase");
        System.out.println(list);

        //Try using custom method as reference
        System.out.println("Adding random Char as middle name");
        list.replaceAll(s -> s.transform(String::toUpperCase));
        list.replaceAll(s ->  s + " " + getRandomChar());
        System.out.println(list);



        //Expert
        List<UnaryOperator<String>> anotherList = new ArrayList<>(List.of(
                String::toUpperCase,
                s-> s + " %c ".formatted(getRandomChar()),
                s -> s + reverse(s, 0, s.indexOf(" ")),
                AnotherMain::reverse,
                String::new,
                s -> new String ("HELLO MY NAME IS " + s)
        ));

        applyChanges(names, anotherList);
    }

    //Expert method
    private static void applyChanges(String [] names, List<UnaryOperator<String>> stringFunctions){

        List<String> backedByArray = Arrays.asList(names);
        for (var function : stringFunctions){
            backedByArray.replaceAll(s -> s.transform(function));
            System.out.println(Arrays.toString(names));
        }

    }

    private static String reverse(String s){
        return reverse(s, 0 , s.length());
    }

    private static String reverse(String s, int start, int end){
        return new StringBuilder(s.substring(start,end)).reverse().toString();
    }

    public static char getRandomChar(){

        int index = new Random().nextInt((int)'A', (int)'Z');
        return (char)index;

    }

}
