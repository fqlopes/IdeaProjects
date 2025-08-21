package DONE.Section6;

public class GreatestCommonDivisor {


    public static int getGreatestCommonDivisor(int first, int second){
        if(first < 10 || second < 10){
            return -1;
        }
        int biggestNumber = first > second ? first : second;
        int equalDividers = 0;
        int count = 1;
        while (count < biggestNumber){
            if (first%count == 0 && second%count == 0){
                equalDividers = count;
            }
        count++;
        }
        return equalDividers;
    }
}
