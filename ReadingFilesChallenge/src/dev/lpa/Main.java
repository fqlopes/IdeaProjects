package dev.lpa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


        try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {

            Pattern p = Pattern.compile("\\p{javaWhitespace}+");
//            System.out.printf("%,d words in the file%n",
//                    br.lines()
////                            .flatMap(p::splitAsStream)
//                            .flatMap(l -> Arrays.stream(l.split(p.pattern())))
//                            .count());


//            System.out.printf("%,d words in the file%n",
//                    br.lines()
//                            .mapToLong(l -> l.split(p.toString()).length)
//                            .sum());

            var result = br.lines()
                    .flatMap(p::splitAsStream)
                    .map(w -> w.replaceAll("\\p{Punct}", ""))
                    .filter(w -> w.length() > 4)
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

            result.entrySet()
                    .stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                    .limit(10)
                    .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue() + " times"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
