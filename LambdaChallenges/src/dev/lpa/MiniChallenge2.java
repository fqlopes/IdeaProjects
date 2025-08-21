package dev.lpa;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MiniChallenge2 {
    public static void main(String[] args) {





        Function<String, String> everySecondCharacter = source -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++){
                if (i % 2 == 1){
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };

        UnaryOperator<String> everySecondCharacter2 = source ->{
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++){
                if (i % 2 == 1){
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };

        var atest = everySecondCharacter2.apply("1234567890");
        System.out.println(atest);
        System.out.println();

        var atest2 =everySecondCharacter(everySecondCharacter2, "1234567890");
        System.out.println(atest2);

        Supplier<String> iLoveJava = () -> "i love java";

        Supplier<String> iLoveJava2 =() -> {return "i love java";};


        var supplierResult = iLoveJava2.get();
        System.out.println(iLoveJava2.get());
        System.out.println(supplierResult);
    }

    public static String everySecondCharacter(UnaryOperator<String> method, String source){
        return method.apply(source);
    }

    public static String everySecondCharacter(Function<String, String> method, String source){
        return method.apply(source);
    }

    public static String everySecondChar(String source){

        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < source.length(); i++){
            if (i % 2 == 1){
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();
    }
}
