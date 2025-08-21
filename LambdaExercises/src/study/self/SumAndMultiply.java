package study.self;

//    Write a Java program to implement a lambda that adds two
//    and then multiplies the result by a constant factor.
@FunctionalInterface
public interface SumAndMultiply {
    int sum(int a, int b, int factor);
}
