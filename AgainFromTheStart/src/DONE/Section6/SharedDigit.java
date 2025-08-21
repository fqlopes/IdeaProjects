package DONE.Section6;

public class SharedDigit {

    public static boolean hasSharedDigit (int a, int b){

        if (a >= 10 && a <=99 && b >= 10 && b <= 99){

            int aRight = a % 10;
            int aLeft = a /10;
            int bLeft = b % 10;
            int bRight = b / 10;

            return (aRight == bLeft || aRight == bRight || aLeft == bLeft || aLeft == bRight);
        }

        return false;
    }
}
