package DONE.Section5;

public class TeenNumberChecker {
    public static boolean hasTeen(int a, int b, int c){
        return a > 12 && a < 20 || b > 12 && b < 20 || c > 12 && c < 20;
    }

    public static boolean isTeen (int age){
        return age > 12 && age < 20;
    }
}
