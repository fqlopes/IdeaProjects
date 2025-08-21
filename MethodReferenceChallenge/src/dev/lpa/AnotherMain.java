package dev.lpa;

import java.util.Arrays;
import java.util.function.UnaryOperator;

public class AnotherMain {

    //enums are classes? or at least viewed as
    enum firstNames {Anna, Bob, Cameron, Donald, Eva, Francis, Gabriel, Helix, Indra, Junior;



    }

    enum lastNames{Anna, Bob, Cameron, Donald, Eva, Francis, Gabriel, Helix, Indra, Junior}


    public static void main(String[] args) {

        System.out.println(Arrays.toString(firstNames.values()));
        var something = firstNames.values();

        System.out.println(something.getClass());

        System.out.println(firstNames.Bob.ordinal()); //return index value


    }

    public static <T> T applyGenericOperation (T anything, UnaryOperator<T> operator){
        return operator.apply(anything);
    }
}
