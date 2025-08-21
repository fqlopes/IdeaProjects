package DONE.Section6;

public class EvenDigitSum {
    public static int getEvenDigitSum(int number){

        if (number >=0){
            int sum = 0;
            while (number > 0){
                int lastDigit = number %10;
                sum = lastDigit % 2 == 0 ? sum +=lastDigit : sum;
                number /=10;
            }
            return sum;
        }
        return -1;
    }
}
