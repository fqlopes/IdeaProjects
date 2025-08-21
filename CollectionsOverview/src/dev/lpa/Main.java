package dev.lpa;

import java.util.*;

public class Main {

    public static void main(String[] args) {


        Collection<String> list = new HashSet<>();

        String [] names = {"Ana", "Bob", "Cid", "David", "Ed", "Fernando", "Gilmar"};

        list.addAll(Arrays.asList(names));
        System.out.println(list);

        list.add("Fred");
        list.addAll(Arrays.asList("George", "Humanic", "Jeppo"));
        System.out.println(list);
        System.out.println("Is George in the list? " + list.contains("George"));

        list.removeIf(s -> s.charAt(0) == 'G');

        System.out.println(list);
        System.out.println("Is George in the list? " + list.contains("George"));


    }
}
