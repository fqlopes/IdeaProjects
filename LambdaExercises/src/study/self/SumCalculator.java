package study.self;

//    Write a Java program to implement a lambda expression to find the sum of two integers.

@FunctionalInterface
public  interface SumCalculator {
    int sum(int a, int b);
}

