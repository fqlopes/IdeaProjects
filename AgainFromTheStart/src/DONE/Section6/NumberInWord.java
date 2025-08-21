package DONE.Section6;

public class NumberInWord {

    public static void printNumberInWord(int number){
        System.out.printf("%s",
                switch (number){
                    case 1 ->    "ONE";
                    case 2 ->    "TWO";
                    case 3 ->  "THREE";
                    case 4 ->   "FOUR";
                    case 5 ->   "FIVE";
                    case 6 ->    "SIX";
                    case 7 ->  "SEVEN";
                    case 8 ->  "EIGHT";
                    case 9 ->   "NINE";
                    case 0 ->   "ZERO";
                    default -> "OTHER";
                });
    }
}
