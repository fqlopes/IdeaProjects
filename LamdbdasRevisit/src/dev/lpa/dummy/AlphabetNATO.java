package dev.lpa.dummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;

public class AlphabetNATO {

    public static void main(String[] args) {

         String prefix = "NATO";

        List<String> list = new ArrayList<>(List.of("alpha", "bravo", "charlie", "delta"));

//        list.forEach(s -> System.out.println(s)); // lambda way
       list.forEach(System.out::println); //method reference

//        looping through the elements with forEach and lambdas
        list.forEach(s -> { //we can declare codeblocks inside a lambda structure
            char first = s.toUpperCase().charAt(0); //java infers the data type, and copies the code behavior for each element
            System.out.println("In "+ prefix +" " +first + " means " + s); //lambdas can access local variables
        });

        /*
        var can infer the type used in the lambda expression/method reference

        var result = calculator((a,b) -> a + b, 1, 2);
        var result = calculator(Integer::sum, 3,4); //method reference
*/

        var result = calculator(Double::sum, 5.0 , 5.0);
        var result1 = calculator((o1,o2) -> o1.concat(o2), "Hello", "World");
        var result2 = calculator(String::concat, "Hello", "World");
        var result3 = calculator( (o1,o2) ->o1.toUpperCase() + " " + o2.toUpperCase(), "Hello", "World");

        var coords = Arrays.asList(
                new double[]{+1.0, +2.0},
                new double[]{+3.0, -4.0},
                new double[]{-5.0, +6.0}
        );

        coords.forEach(s -> System.out.println(Arrays.toString(s)));

        //We can create local variables using the functional interfaces
        BiConsumer<Double, Double> p1 = (lat, lng) ->
                System.out.printf("[lat: %.2f lng: %.2f]%n", lat, lng);

        var firstPoint = coords.get(0); //first array element, containing 2 elements
        processPoint(firstPoint[0], firstPoint[1], p1); //assigning the array adress and the behavior we set as a variable

        //we can call for each to print everything
        System.out.println("---------");
        coords.forEach(s -> processPoint(s[0], s[1], p1)); //process point sets the behavior


        list.removeIf( s -> s.equalsIgnoreCase("bravo"));
        list.forEach(System.out::println);

        //utilizing list methods that utilizes lambda
        //replaceAll, setting the argument via lambda expression
        list.replaceAll(s-> s.toUpperCase().charAt(0) + " - " + s.toUpperCase());
        System.out.println("---------");
        list.forEach(System.out::println);

        //The java.util.Arrays has a method called setAll, that populates a generic array.
        String[] emptyStrings = new String[10];
        //Arrays start with null references in each block
        System.out.println(Arrays.toString(emptyStrings));
        //fill takes concrete elements
        Arrays.fill(emptyStrings, "");
        System.out.println(Arrays.toString(emptyStrings));
        //Arrays.setAll takes a lambda expression to be executed, and fills the array.
        Arrays.setAll(emptyStrings, (i) -> "" + (i + 1) +".");
        Arrays.setAll(emptyStrings, (text) -> "%d".formatted(text+1));
        System.out.println(Arrays.toString(emptyStrings));

        Arrays.fill(emptyStrings, "");
        Arrays.setAll(emptyStrings, (i) -> "" + (i+1)+" " + switch (i) {
            case 0 -> "one";
            case 1 -> "two";
            case 2 -> "three";
            default -> "";

        });
        System.out.println(Arrays.toString(emptyStrings));


    }


    //this method works with lambda expressions and method references, as it needs a type derived from a generic interface
    //with only an abstract method. since it's generic, it's best used when appending to a variable,
    //which will perform the operations of the method
    public static <T> T calculator(BinaryOperator<T> function, T value1, T value2){
        T result = function.apply(value1, value2);
        System.out.println("The result is: " + result);
        return result;
    }

    public static <T> void processPoint (T t1, T t2, BiConsumer<T, T> consumer){

        consumer.accept(t1, t2);
    }




}
/*
        public static <T> T calculator(Operation<T> function, T value1, T value2){
            T result = function.operate(value1, value2);
            System.out.println("The result is: " + result);
            return result;
        }
*/