package DONE.Section5;

public class PlayingCat {

    public static boolean isCatPlaying (boolean isSummer, int temperature){

        boolean result = false;

        if (!isSummer && temperature > 24 && temperature <36){
            result = true;
        }

        if (isSummer && temperature > 24 && temperature <46){
            result = true;
        }
        return result;
    }
}
