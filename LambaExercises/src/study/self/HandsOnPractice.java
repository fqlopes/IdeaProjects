package study.self;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class HandsOnPractice {

    public static void main(String[] args) {

        /*Task 1
        * Write the following anonymous class as a lambda expression*/
        Consumer<String> printTheParts = new Consumer<String>() { //Consumer is void, returns nothing
            @Override
            public void accept(String sentence) {
                String[] parts = sentence.split(" ");
                for (String part : parts){
                    System.out.println(part);
                }
            }
        };
        /*Answer for task 1*/

        Consumer<String> printThePartsLambda = s -> {
            String[] parts = s.split(" ");
            for (String part : parts){
                System.out.println(part);
            }
        };

        /*Answer for task 1*/
        Consumer<String> printThePartsLambdaConcise = s -> Arrays.asList(s.split("")).forEach(System.out::println);


        /*Answer for task 3*/

        String task3Text = "1234567890";

        System.out.println(everySecondCharLambda.apply(task3Text));

        /*Answer for task 3 while using our method*/

        everySecondCharPrintedByLambda(task3Text, everySecondCharLambda);




        /*Usage of task 4 method's answer is task 5*/

        String formattedString = everySecondCharacter(s -> {
            StringBuilder returned = new StringBuilder();
            for (int i = 0; i < s.length(); i++){
                if (i % 2 == 1){
                    returned.append(s.charAt(i));
                }
            }
            return returned.toString();
        }, task3Text);

        /*Task 6
        *
        * Write a lambda expression that is declared with the Supplier interface
        * This lambda should return a String "I ove Java", and assign it to a variable called
        * iLoveJava*/

        /*Answer for task 6*/
        Supplier<String> javaLoving = () -> "I Love Java";
        String iLoveJava = javaLoving.get();
        System.out.println(iLoveJava);

        String iLoveJavaMethods = javaLoving().get();
        System.out.println(iLoveJavaMethods);

    }


    /*Task 2
     * Create a variable, using a type that makes sense for this method.
     *
     * Write the following expression as a lambda expression*/

    public static String everySecondChar (String source){

        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < source.length(); i++){
            if (i % 2 == 1){
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();
    }

    /*Answer for Task 2*/

    public static UnaryOperator<String> everySecondCharLambda = s -> {
        StringBuilder myString = new StringBuilder();
        for (int i = 0; i< s.length(); i++){
            if (i % 2 == 1){
                myString = myString.append(s.charAt(i));
            }
        }
        return myString.toString();
    };

    /*Task 3

    * The lambda expression created in Task 2 doesn't do anything right now

    * Write a code to execute this lambda expression's functional method, using
    * 1234567890, as the argument to that method, and print the result out*/

    /*Task 3 answer with a method
    * */
    public static void everySecondCharPrintedByLambda (String text, UnaryOperator<String> unaryOperator){
        System.out.println(unaryOperator.apply(text));

    }

    /*Task 4
    *
    * Suppose we want to pass to a method.
    * Write another method on your class, called everySecondCharacter.
    * This method should accept Function as a parameter, as well as a
    * second parameter to let us pass "1234567890"*/

    /*Answer to task 4*/
    public static String everySecondCharacter (Function<String, String> func, String myString){
        return func.apply(myString);
    }

    /*Another answer for task 6*/
    public static Supplier<String> javaLoving (){
       return  () -> "I love Java";
    }






}
