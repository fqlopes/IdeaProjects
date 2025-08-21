package dev.lpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Main {



    private static Random random = new Random();

    public static void main(String[] args) {

        String [] names = {"Anna", "Bob", "cesar", "Dylan", "edward", "felipe" };

        System.out.println("Original list " + Arrays.toString(names));
        Arrays.setAll(names, i -> names[i].toUpperCase());
        System.out.println("Transforming to uppercase -->" + Arrays.toString(names) );

        //List backed by an array
        List<String> backedByArray = Arrays.asList(names);
        backedByArray.replaceAll(s -> s += " " + getRandomChar('A', 'O') + ".");
        System.out.println("--> Adding random initials");
        System.out.println(Arrays.toString(names));

        backedByArray.replaceAll(s -> s += " " + getReverseName(s.split(" ")[0]));
        System.out.println("---> Reversing first to last name");
        backedByArray.forEach(s -> System.out.println(s));


        List<String> newList = new ArrayList<>(List.of(names));
//        newList.removeIf( s -> s.substring(0, s.indexOf(" ")).equals(s.substring(s.lastIndexOf(" ") + 1)));

        newList.removeIf(s -> {
            String firstName = s.substring(0, s.indexOf(" "));
            String lastName = s.substring(s.lastIndexOf(" ") + 1);
            return firstName.equals(lastName);
        });
        System.out.println("Removing if first and last names are equal");
        newList.forEach(s -> System.out.println(s));
    }

    public static char getRandomChar(char startChar, char endChar){
        return (char) random.nextInt((int) startChar, (int) endChar +1  );
    }

    public static String getReverseName (String firstName){
        return new StringBuilder(firstName).reverse().toString();
    }


}
