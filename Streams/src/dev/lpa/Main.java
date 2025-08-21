package dev.lpa;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> bingoPool = new ArrayList<>(75);

        int start = 1;
        for (char c : "BINGO".toCharArray()) {
            for (int i = start; i < (start + 15); i++){
                bingoPool.add("" + c + i);
//                System.out.println("" + c + i);
            }
        }

        Collections.shuffle(bingoPool);
        for (int i = 0; i < 15; i++){
            System.out.println(bingoPool.get(i));
        }
        System.out.println("------------------------------------------");

//        List<String> firstOnes = bingoPool.subList(0, 15);
        List<String> firstOnes = new ArrayList<>(bingoPool.subList(0, 15));
        firstOnes.sort(Comparator.naturalOrder());
        firstOnes.replaceAll(s -> {
            if (s.indexOf('G') == 0|| s.indexOf("O") == 0){
                String updated = s.charAt(0) + "-" + s.substring(1);
                System.out.print(updated + " ");
                return updated;
            }
            return s;
        });
        System.out.println("\n------------------------------------------");

        for (int i = 0; i < 15; i++){
            System.out.println(bingoPool.get(i));
        }
        System.out.println("------------------------------------------");
        // The stream() usage:
                // Returns an implementation of the stream interface
        var pipeline = bingoPool.stream()
                // Returns a stream consisting of the remaining elements of this stream
                .limit(15)
                //Returns the results of applying the given function to the elements of this stream.
                .filter(s -> s.indexOf("G") == 0|| s.indexOf("O") == 0)
                //...Function to apply to each element
                .map(s -> s.charAt(0) + "-" + s.substring(1))
                //Sorts the entries
                .sorted();
                //forEach, much like the forEach methods previously studied
//                .forEach(s -> System.out.print(s + " "));
        pipeline.forEach(s -> System.out.print(s + " "));
        System.out.println("\n------------------------------------------");


        String[] strings = {"One", "Two", "Three"};
        var firstStream = Arrays.stream(strings)
                .sorted(Comparator.reverseOrder());
//                .forEach(System.out::println);

        var secondStream = Stream.of("Six", "Five", "Four")
                .map(String::toUpperCase);
//                .forEach(System.out::println);
        Stream.concat(secondStream, firstStream)
                .map(s-> s.charAt(0) + "-" + s)
                .forEach(System.out::println);

        Map<Character, int[]> myMap = new LinkedHashMap<>();
        int bingoIndex = 1;
        for (char c : "BINGO".toCharArray()){
            int[] numbers = new int[15];
            int labelNo = bingoIndex;
            Arrays.setAll(numbers,i -> i + labelNo);
            myMap.put(c, numbers);
            bingoIndex+=15;
        }


        myMap.entrySet()
                .stream()
                .map(e -> e.getKey() + " has range: " + e.getValue()[0] + " - "
                        + e.getValue()[e.getValue().length - 1])
                .forEach(System.out::println);

        Random random = new Random();
        Stream.generate(() -> random.nextInt(2))
                .limit(10)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.iterate(1, n -> n + 1)
                .filter(Main::isPrime)
                .limit(20)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.iterate(1, n -> n + 1)
                .limit(100)
                .filter(Main::isPrime)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.iterate(1,
                        n -> n <=100,
                        n -> n + 1)
                .filter(Main::isPrime)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.rangeClosed   (1,100)
                .filter(Main::isPrime)
                .forEach(s -> System.out.print(s + " "));
    }

    public static boolean isPrime (int wholeNumber){

        if (wholeNumber <= 2){
            return (wholeNumber == 2);
        }
        for (int divisor = 2; divisor < wholeNumber; divisor++){
            if (wholeNumber % divisor == 0){
                return false;
            }
        }
        return true;
    }




}
