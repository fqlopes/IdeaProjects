package dev.lpa.dummy;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MoreLambda {
    public static void main(String[] args) {

        UnaryOperator<String> everySecondCharacter = (source -> everySecondChar(source));
        UnaryOperator<String> everySecondChardReference = (MoreLambda::everySecondChar);
        Function<String, String> everySecondCharacter1 = (s -> everySecondChar(s));
        Function<String, String> everySecondChardReference1 = (MoreLambda::everySecondChar);

        String text = "1234567890";
        everySecondCharacter.apply(text);
        everySecondChardReference.apply(text);
        everySecondCharacter1.apply(text);
        everySecondChardReference1.apply(text);

//        System.out.println(everySecondCharacter.apply(text));
//        System.out.println(everySecondChardReference.apply(text));
//        System.out.println(everySecondCharacter1.apply(text));
//        System.out.println(everySecondChardReference1.apply(text));

        String result = everySecondCharacter(everySecondCharacter1, text );
        System.out.println(result);

        Supplier<String> builder = () -> "I love java";

        System.out.println(builder.get());

    }

    public static String everySecondChar (String source){

        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < source.length(); i++){
            if( i%2 == 1 ){
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();
    }

    public static String everySecondCharacter (Function<String, String> function, String source){
        return function.apply(source);

    }
}
