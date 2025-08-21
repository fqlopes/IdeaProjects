package DONE.Section5;

public class DecimalComparator {

    public static boolean areEqualByThreeDecimalPlaces(double numberOne, double numberTwo){

        long n1 = (long) (numberOne * 1000);
        long n2 = (long) (numberTwo * 1000);

        return n1 == n2;


    }
}

