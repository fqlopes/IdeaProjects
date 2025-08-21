package dev.lpa.dummy;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RandomNames {

    public static void main(String[] args) {

        String[] names = {"Ann", "Bob", "Cid", "David", "Ed", "Fred"};
        String[] randomNamesList = randomlySelectedValues(15, names, () -> new Random().nextInt(0, names.length));

        System.out.println(Arrays.asList(randomNamesList));

        Consumer<String> printTheParts = new Consumer<String>() {
            @Override
            public void accept(String sentence) {
                String[] parts = sentence.split(" ");
                for (String part : parts){
                    System.out.println(part);
                }
            }
        };

        Consumer<String> printTheParts1 = sentence -> {
            String[] parts = sentence.split(" ");
            for (String part : parts){
                System.out.println(part);
            }
        };

        Consumer<String> printTheParts2 = sentence -> Arrays.asList(sentence.split(" ")).forEach(s -> System.out.println(s));

        String text = "This is a text.";
//        printTheParts1.accept(text);
        printTheParts2.accept(text);
        printTheParts2.accept("cmon bruh");

    }




    public static String[] randomlySelectedValues (int count, String [] values, Supplier<Integer> s){

        String[] selectedValues = new String[count];

        for (int i = 0; i < count; i++){
            selectedValues[i] = values[s.get()]; // s.get() -> generates a random number
        }
        return selectedValues;
    }
}
