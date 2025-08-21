package DONE.Section6;

public class FirstLastDigitSum {

    public static int sumFirstAndLastDigit (int number){

        if (number >= 0){
            int lastDigit = number %10;
            int firstDigit = number;
            while (firstDigit / 10 != 0){
                firstDigit /=10;
            }
            return lastDigit+firstDigit;
        }
        return -1;
    }
}
