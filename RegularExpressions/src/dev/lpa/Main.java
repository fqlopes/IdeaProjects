package dev.lpa;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String helloWorld = "%s %s".formatted("Hello", "World");
        String helloWorld2 = String.format("%s %s", "Hello", "World");
        System.out.println("Using string's formatted method: " + helloWorld);
        System.out.println("Using string.format: " + helloWorld2);
        var format = format("%s %s", "Hello", "World");
        System.out.println(format);

        String helloWorld3 = Main.format("%s %s", "Hello", "World");
        System.out.println("Using Main.format: " + helloWorld3);

        String testString = "Anyone can learn abc's, 123's, and any regular expressions";
        String replacement = "(-)";

        String[] patterns = {
                "[a-zA-Z0-9]*$",
                "^[a-zA-Z]{3}",
                "[aA]ny\\b"
        };

        for (String pattern : patterns) {
            String output = testString.replaceFirst(pattern, replacement);
            System.out.println("Pattern: "+ pattern + " => " + output);
        }

        // Song of the Witches in MacBeth, a Play by Shakespeare
        String paragraph = """
                Double, double toil and trouble;
                Fire burn caldron bubble.
                Fillet of a fenny snake,
                In the caldron boil an bake
                Eye of newt and toe of frog,
                Wool of bat and tongue of dog,
                Adder's fork and blind-worm's sting,
                Lizard's leg and howlet's wing,
                For a charm of powerful trouble,
                Like a hell-broth boil and bubble.
                """;

        String[] lines = paragraph.split("\\R");
        System.out.println("This paragraph has " + lines.length + " lines.");
        String[] words = paragraph.split("\\s");
        System.out.println("This paragraph has " + words.length + " words.");
        System.out.println(paragraph.replaceAll("[a-zA-Z]+ble", "[GRUB]"));

        Scanner scanner = new Scanner (paragraph);
        System.out.println(scanner.delimiter());

        scanner.useDelimiter("\\R");

//        scanner.tokens()
//                .map(s -> s.replaceAll("\\p{Punct}", ""))
//                .flatMap(s -> Arrays.stream(s.split("\\s+")))
//                .filter(s -> s.matches("[a-zA-Z]+ble"))
//                .forEach(System.out::println);


//        while (scanner.hasNext()){
//            String element = scanner.next();
//            System.out.println(element);
//        }

        System.out.println(scanner.findInLine("[a-zA-Z]+ble+").lines());
        System.out.println(scanner.findInLine("[a-zA-Z]+ble+"));
        scanner.close();


    }

    private static String format(String regex,  String... args){

        int index = 0;
        while (regex.matches(".*%s.*")) {
            regex = regex.replaceFirst("%s", args[index++]);
        }
        return regex;
    }
}
