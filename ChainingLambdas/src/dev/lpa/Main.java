package dev.lpa;

import java.util.Arrays;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {

        String name = "Tim";


        Function<String,String> uCase = String::toUpperCase;
        Function<String, String> lastName = s -> s.concat(" Bulchaka");
        Function<String, String> uCaseLastName = uCase.andThen(lastName);

        System.out.println(uCaseLastName.apply(name));

        var test =uCaseLastName.compose(lastName);

        Function<String, String[]> f0 = uCase
                .andThen(s -> s.concat(" Bulchaka"))
                .andThen(s -> s.split(" "));


        System.out.println(Arrays.toString(f0.apply(name)));
    }
}