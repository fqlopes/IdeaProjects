package DONE.Section5;

public class MinutesToYearCalculator {

    public static void printYearsAndDays (long minutes){
        if (minutes < 0){
            System.out.println("Invalid Value");
        } else {
            int minToYear =(int)(minutes/525600);
            int minToYearRemaining =(int) (minutes%525600);
            int remainingDays = minToYearRemaining /1440;
            System.out.println(minutes + " min = " + minToYear + " y and " + remainingDays + " d");
        }
    }
}
