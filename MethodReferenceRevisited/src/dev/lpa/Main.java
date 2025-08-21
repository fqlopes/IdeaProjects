package dev.lpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

class PlainOld {
    private static int last_id = 1;

    private int id;

    public PlainOld(){
        this.id = last_id++;
        System.out.println("Creating a PlainOld object with ID: " + id);


    }
}

public class Main {
    public static void main(String[] args) {

        BiFunction<String, String, Integer> func = String::indexOf;

        List<String> list = new ArrayList<>(List.of("Anna", "Bob", "Chuck", "Dave"));

        //QUALIFYING TYPE :: METHOD NAME
        list.forEach(System.out::println);

        //we call the sum  with Integer::sum, as a method
        calculator(Integer::sum, 1,1);

        //Supplier -> get().
        //method reference as an variable
        Supplier<PlainOld> aux = PlainOld::new;
        //aux.get().getClass(); //we can "activate" a new instance when using the functional interface method .get()

        //method reference inside a method
        seedArray(aux,5);
        seedArray(PlainOld::new, 5);

        calculator((s1, s2) -> s1 + s2, "Hello ", "World");

        calculator( (s1,s2) -> s1.concat(s2), "Hello ", "World");
        calculator(String::concat, "Hello ", "World");

        BinaryOperator<String> b1  = String::concat;
        BiFunction<String, String, String> b2 = String::concat;
        UnaryOperator<String> u1 = String::toUpperCase;
        System.out.println(b1.apply("OH", "MY"));
        System.out.println(b1.apply("GOD", "WHY"));

        //The transform method in string accepts lambdas and some method references
        String result = "this is a text".transform(u1);
        System.out.println("Result = " + result);

        //Method references can be passed directly as an argument to the transform method
        result = result.transform(String::toLowerCase);
        System.out.println("Result = " + result);

        Function<String, Boolean> test = String::isEmpty;
        boolean resultBoolean = result.transform(test);

    }

    private static <T> void calculator (BinaryOperator<T> function, T value1, T value2){

        T result = function.apply(value1, value2);
        System.out.println("Result = " + result);
    }

    private static PlainOld[] seedArray (Supplier<PlainOld> reference, int count){
        PlainOld[] array = new PlainOld[count];
        Arrays.setAll(array, i -> reference.get());
        return array;
    }
}
