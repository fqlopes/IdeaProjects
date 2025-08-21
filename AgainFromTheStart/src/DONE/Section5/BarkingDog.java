package DONE.Section5;

public class BarkingDog {
    public static boolean shouldWakeUp(boolean barking, int hourOfDay) {
        if (hourOfDay > 23){
            return false;
        }
        return barking && hourOfDay >= 0 && hourOfDay < 8 || hourOfDay > 22;
    }
}
