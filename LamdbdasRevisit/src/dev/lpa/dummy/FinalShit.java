package dev.lpa.dummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FinalShit {

    public static void main(String[] args) {

        String [] names = {"Ana", "Bob", "Clair", "Deuce", "Edy"};


        List<String> nameList = new ArrayList<>(List.of(names));
        nameList.replaceAll(s -> s.toUpperCase());
        nameList.replaceAll(s -> s + " "+(char) new Random().nextInt(65, 90) + ".");
        nameList.replaceAll(s -> s + " " + FinalShit.reverseName(s) );

        //the hard one
        nameList.removeIf(s -> s.substring(0, s.indexOf(" ")).equals(s.substring(s.lastIndexOf(" " )+1)));
        System.out.println(nameList);

    }

    public static String reverseName (String name){
        String[] source = name.split(" ", 0);
        StringBuilder reversing = new StringBuilder(source[0]);
        return reversing.reverse().toString();
    }

    public static boolean equalNames (String name){
        String[] source = name.split(" ");
        return source[0] == source[2];
    }
}
