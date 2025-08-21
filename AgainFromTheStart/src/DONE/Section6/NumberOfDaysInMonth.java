package DONE.Section6;

public class NumberOfDaysInMonth {

    public static boolean isLeapYear (int year){
        if ( year >= 1 && year <= 9999){
            if (year % 4 == 0){
                if (year % 100 == 0){
                    if (year % 400 == 0){
                        return true;
                    }
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public static int getDaysInMonth (int month, int year){

        if (year > 1 && year < 9999){
            return -1;
        }
        return switch (month){
            case 1 -> 31;
            case  2 -> isLeapYear(year) ? 29 : 28;
            case 3 -> 31;
            case 4 -> 30;
            case 5 -> 31;
            case 6 -> 30;
            case 7 -> 31;
            case 8 -> 31;
            case 9 -> 30;
            case 10 ->31;
            case 11 ->30;
            case 12 ->31;
            default -> -1;
        };
    }
}
