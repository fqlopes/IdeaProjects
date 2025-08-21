package DONE.Section6;

public class FlourPacker {

    public static boolean canPack (int bigCount, int smallCount, int goal){

        if ( bigCount < 0 || smallCount < 0 || goal < 0){
            System.out.println("WHY DON'T YOU FUCK OFF");
            return false;
        }

        boolean result = false;
        int bigWeight = bigCount * 5;

        if (bigWeight >= goal){
            int remaining = goal % 5;

            if (smallCount >= remaining){

                result = true;
            }
        } else {

            if (smallCount >= goal - bigWeight) {
                result = true;
            }
            return result;

        }
        return result;
    }
}
