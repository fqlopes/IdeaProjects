package DONE.Section6;

public class PerfectNumber {

    public static boolean isPerfectNumber (int number){
        int divider = 0;
        if (number <= 0){
            return false;
        }
        for (int i = 1; i < number; i++){

            if (number % i == 0) {
             divider +=i;
            }
        }
        return divider == number;
    }
}
