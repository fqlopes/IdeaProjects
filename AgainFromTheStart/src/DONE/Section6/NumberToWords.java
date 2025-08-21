package DONE.Section6;

public class NumberToWords {

    public static void numberToWords (int number){

        int original = reverse(number);
        int missingZeroes = getDigitCount(number) - getDigitCount(original);

        if (number < 0){
            System.out.println("Invalid Value");
        } else {

            while (original != 0) {
                int lastDigit = original % 10;
                System.out.printf("%s ", switch (lastDigit)
                {
                    case 0 -> "Zero";
                    case 1 -> "One";
                    case 2 -> "Two";
                    case 3 -> "Three";
                    case 4 -> "Four";
                    case 5 -> "Five";
                    case 6 -> "Six";
                    case 7 -> "Seven";
                    case 8 -> "Eight";
                    case 9 -> "Nine";
                    default -> "Invalid Value";
                });
                original /=10;
            }

            if (number == 0){
                System.out.println("Zero");
            }

            if (missingZeroes > 0){
                for (int i = 0 ; i < missingZeroes; i++){
                    System.out.print("Zero ");
                }
            }

        }
    }

    public static int reverse (int number){
        int reverse = 0;
        while (number != 0){
            reverse = (reverse*10) + number%10;
            number /=10;
        }
        return reverse;
    }

    public static int getDigitCount (int number){
        if (number == 0) return 1;
        if (number < -0) return -1;
        int count = 0;
        while (number != 0){
            count++;
            number /=10;
        }
        return count;
    }
}
