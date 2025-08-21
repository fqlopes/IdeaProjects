package DONE.Section6;

public class NumberPalindrome {

    public static boolean isPalindrome (int number){

        int original = number;
        int reverse = 0;

        while (original !=0){
            int lastDigit = original%10;
            reverse = reverse * 10 + lastDigit;
            original /= 10;
        }
        return number == reverse;
    }
}
