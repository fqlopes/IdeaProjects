package DONE.Section6;

public class LastDigitChecker {

    public static boolean hasSameLastDigit (int a, int b, int c){

        if (a>= 10 && a <= 1000 && b>= 10 && b <= 1000 && c>= 10 && c <= 1000){

            int aLast = a%10;
            int bLast = b%10;
            int cLast = c%10;

            return (aLast == bLast || aLast == cLast || bLast == cLast);

        }
        return false;
    }

    public static boolean isValid(int number){
        return number >= 10 && number <= 1000;
    }
}
