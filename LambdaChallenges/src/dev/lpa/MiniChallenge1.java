package dev.lpa;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class MiniChallenge1 {
    public static void main(String[] args) {



        Consumer <String> printTheParts4 = sentence ->
            Arrays.asList(sentence.split(" ")).forEach(s -> System.out.println(s));


        Consumer <String> printTheParts3 = sentence -> {
            List<String> parts = Arrays.asList(sentence.split(" "));
              for (String part : parts){
                  System.out.println(part);
              }
        };

        Consumer <String> printTheParts2 = sentence -> {
            String [] parts = sentence.split(" ");
            Arrays.asList(parts).forEach(s -> System.out.println(s));
        };

        Consumer<String> printTheParts1 = sentence -> {

            String[] parts = sentence.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };

        printTheParts4.accept("A B C D E F G ASD F");



    }
        Consumer<String> printTheParts = new Consumer<String>() {

            @Override
            public void accept (String sentence){
                String [] parts = sentence.split(" ");
                for (String part : parts){
                    System.out.println(part);
                }
            }
        };
    }

