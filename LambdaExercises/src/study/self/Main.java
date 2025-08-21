package study.self;

public class Main {
    public static void main(String[] args) {

//        SumCalculator sum =  (x, y) -> x + y;
//        SumAndMultiply sumAndMultiply = (a, b , c) -> (a+b)*c;

        firstSum sum1 = (x,y) -> x+y;
        numberToText format = result ->  "The result is " + result;
        sumAndText theResult = (a, b) -> format.numberToText((sum1));
    }
}
