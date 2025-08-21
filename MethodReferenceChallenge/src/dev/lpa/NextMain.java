package dev.lpa;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.IntFunction;

public class NextMain {

    record Person (String firstName, String lastName){
        @Override
        public String toString() {
            return firstName + " " + lastName + ", ";
        }
    }

    private static final Random random = new Random();
    public static void main(String[] args) {
        String[] names = {"Anna", "Bob", "Cameron", "Donald", "Eva", "Francis", "Gabriel", "Helix", "Indra", "Junior"};
        System.out.println("Generic method <T> that creates a T[] array, using BifFunction and IntFunction");

        //Populating a record array using generic function

        Person[] randomPeople = getRandomNamesGeneric(names, (f, l) -> new Person (f, l), Person[]::new);

        System.out.println(Arrays.toString(randomPeople));

        List<Person> list = new ArrayList<>(Arrays.asList(randomPeople));
        list.forEach(s -> System.out.printf("%s ",s ));

        System.out.println("Using the comparator");

        list.sort (Comparator.comparing(o1 -> o1.firstName));
        System.out.println("-------------------------");
        list.forEach(s -> System.out.printf("%s ", s));


    }


    private static String[] getRandomNames (String[] names){

        String[] randomNames = new String[names.length];

        for (int i = 0; i < names.length; i++){
            int randomIndex = random.nextInt(0, names.length);
            randomNames[i] = names[randomIndex];

        }
        return randomNames;

    }

    private static <T> T[] getRandomNamesGeneric (String[] names,
                                                  BiFunction<String,String, T> mapper, // Function to create T from two strings
                                                  IntFunction<T[]> arrayGenerator) // Generator for the result array
    {
        T[] results = arrayGenerator.apply(names.length);
        for (int i = 0; i < names.length; i++){
            int firstNameIndex = random.nextInt(names.length);
            int lastNameIndex = random.nextInt(names.length);
            results[i] = mapper.apply(names[firstNameIndex], names[lastNameIndex]);

        }
        return results;
    }
}
