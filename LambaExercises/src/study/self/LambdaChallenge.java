package study.self;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LambdaChallenge {
    /* Lambda Expressions Challenge
    *
    * This challenge is designed to exercise our skills with methods on Arrays and ArraysLists,
    * that are targets for lambda expressions.
    *
    *   //////////First Task//////////
    *   Create an Array of Strings, which is populated with first names, in mixed case
    *   Include at least one name, which is spelled the same backwards and forwards, like Bob, or
    *   Anna.
    *
    *       - Use Arrays.setAll or List.replaceAll to change the values in this array.
    *
    *   PS: If using List methods, a list backed by the array will be necessary, so that the changes
    *       get made to the initial array.
    *
    *   //////////Second Task//////////
    *   Using one of those two methods, perform the following functions on the elements in the array,
    *   with appropriate lambda expressions.
    *
    *       - Transform names to all uppercase.
    *       - Add a randomly generated middle initial and include a period.
    *       - Add a last name that is the reverse of the first name.
    *
    *   Print your array or the array elements, after each change, using the forEach method,
    *   at least once.
    *
    *   //////////Third Task//////////
    *   Finally, create a new modifiable ArrayList from your names array, removing any names
    *   where the last name equals the first name.
    *
    *       - Use removeIf with a lambda expression to perform this last operation.
    * */

    public static void main(String[] args) {

        String[] names = new String[]{"Anna", "bob", "ClaRK", "DERick", "Edward"};
        System.out.println("We created an array of Strings, called names, and they are:\n" + Arrays.toString(names));
        List<String> backedByArray = new ArrayList<>(Arrays.asList(names));
        System.out.println("\nWe also created a List backed by that array, called backedByArray:\n" + backedByArray);
        System.out.println("\nAs we ca see, both hold the same values and names.");
        System.out.println("\nWe will now fix the names, letting only the first letter be in uppercase");
        backedByArray.replaceAll(s -> {
            var upperCaseFirstLetter = s.toUpperCase().charAt(0);
            var lowerCaseEverythingElse = s.substring(1).toLowerCase();
            return upperCaseFirstLetter + lowerCaseEverythingElse;
                });
        System.out.println(backedByArray);
        System.out.println("\nThe immutability of strings can still be seen, as our original Array remains the same:\n" +
                Arrays.toString(names));
        System.out.println("\nAnd so, we conclude task 1.");

        System.out.println("\nIn task 2, we will print each task given to see changes happening.");
        System.out.println("Using one of those two methods, perform the following functions on the elements in the array," +
                " with appropriate lambda expressions.\n");
        System.out.println("\t- Transform names to all uppercase.\n");
        System.out.println("R: We can achieve this with the following method:\n\t.replaceAll(s -> s.toUpperCase())\n");
        backedByArray.replaceAll(s -> s.toUpperCase());
        System.out.println(backedByArray);
        System.out.println("\nMoving on, we now do the following:");
        System.out.println("\t- Add a randomly generated middle initial and include a period.");
        System.out.println("\nTo achieve this, we will use the use the integer values of the ASCII characters," +
                " converting them to char values and adding them to our list, while using the replaceAll method.");
        backedByArray.replaceAll(s -> s.concat(" %c.".formatted(new Random().nextInt(65, 91))));
        System.out.println("The result is listed below:\n");
        backedByArray.forEach(System.out::println);
        System.out.println("\nAnd the last part is:\n\t- Add a last name that is the reverse of the first name.");
        System.out.println("\nOnce again, we will of of methods contained in strings to achieve this.");
        System.out.println("We split and trim,then reverse the first name to be concatenated to the its name\n");
        backedByArray.replaceAll(s -> {
            String[] parts = s.trim().split(" ", s.lastIndexOf(" "));
            String firstNames = parts[0];
            String middleNames = parts[1];
            String reversed = "";
            for (int i = 0; i < firstNames.length() ; i++){
                reversed = firstNames.charAt(i) + reversed;
            }
            return firstNames + " " + middleNames + " " + reversed;
        });
        System.out.println(backedByArray);

        System.out.println("\nFor the third and final task, we have:\n\t" +
                "- Use removeIf with a lambda expression to perform this last operation.");
        System.out.println("\n It first must be mentioned that the removeIf method uses a Predicate interface.");
        System.out.println("\t- That means we are working with a boolean result, which will be the confirmation" +
                " of our comparisons.\n");
        System.out.println("We will repeat the last strategy of splitting the name into an array of strings");
        backedByArray.removeIf(s ->{
            var parts = s.split(" ");
            var firstName = parts[0];
            var lastName = parts[2];
            return firstName.equals(lastName);
        });
        backedByArray.forEach(System.out::println);
    }
}
