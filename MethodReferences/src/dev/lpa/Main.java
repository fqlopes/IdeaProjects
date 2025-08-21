package dev.lpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

class PlainOld  {

    private static int last_id = 1;

    private int id;

    public PlainOld(){
        this.id = PlainOld.last_id++;
        System.out.println("Creating plain old object ID: " + this.id);
    }
}

public class Main {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>(List.of("Anna", "Bob", "Chuck", "Dave"));

//        list.forEach(s -> System.out.println(s));
        list.forEach(System.out::println);

//        calculator((a, b) -> a+b, 5, 15);
        calculator(Integer::sum, 5,15);
        calculator(Double::sum, 5.0,15.0);


        Supplier<PlainOld> reference1 = () -> new PlainOld();
        Supplier<PlainOld> reference01 = PlainOld::new;

        PlainOld pojo = reference01.get();
        pojo = reference1.get();

        System.out.println("Getting array");
        PlainOld[]  pojo1 =seedArray(PlainOld::new, 10);

        calculator((s1, s2) -> s1 + s2, "Hello ", "World!");
        calculator(String::concat, "Hello ", "World!");

        BinaryOperator<String> b1 = String::concat;
        BiFunction<String, String, String> b2 = String::concat;

        UnaryOperator<String> u1 = String::toUpperCase;

        System.out.println(b1.apply("Hello ", "World"));
        System.out.println(b2.apply("Hello ", "World"));
        System.out.println(u1.apply("Hello "));

        String result = "Hello".transform(u1);
        System.out.println("Result = " + result);

        result = result.transform(String::toLowerCase);
        System.out.println("Result =  " + result);

        Function<String, Boolean> f0 = String::isEmpty;
        boolean resultBoolean = result.transform(f0);
        System.out.println("Result  = " + resultBoolean);
    }

    private static <T> void calculator (BinaryOperator<T> function, T value1, T value2){

        T result = function.apply(value1, value2);
        System.out.println("Result of the operations: " + result);
    }

    private static PlainOld[] seedArray(Supplier<PlainOld> reference, int count){

        PlainOld[] array = new PlainOld[count];
        Arrays.setAll(array, i-> reference.get());
        return array;
    }
}
