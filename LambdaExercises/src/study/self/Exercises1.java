package study.self;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Exercises1 {
//    Write a Java program to implement a lambda expression to find the sum of two integers.

    public static BiFunction<Integer, Integer, Integer> sumMethod () {
        return  (x, y) -> x+y;
    }

//    Write a Java program to implement a lambda that adds two integers
//    and then multiplies the result by a constant factor.

    public static BiFunction<Integer, Integer, Integer> sumAgain(int factor) {
        return (x,y) -> (x+y)*factor;
    }

//    Write a Java program to chain two lambda expressions:
//    one for summing two integers and another for formatting the output.

    public static BiFunction<Integer, Integer, String> sumAndFormat(BiFunction<Integer, Integer, Integer> s, Function<Integer, String> formatFunction){

           return (a, b) -> formatFunction.apply(s.apply(a,b));
    }

}
