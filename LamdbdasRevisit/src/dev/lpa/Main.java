package dev.lpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static int lineCounter;

    record Person (String firstName, String lastName){
        @Override
        public String toString (){
            return firstName + " " + lastName;
        }
    }

    public static int lineCounter (){
        lineCounter ++;
        return lineCounter;
    }
    public static void main(String[] args) {


        System.out.println(lineCounter() + "We create a list of type arraylist of a record=Person called people");
        System.out.println(lineCounter() + "The names are hardcoded in this order");
        List<Person> people = new ArrayList<>(Arrays.asList(
                new Person ("Felipe", "Lopes"),
                new Person ("Sally", "Brown"),
                new Person ("Anna", "Banana"),
                new Person ("Beto", "Carreiro"),
                new Person ("Carlos", "Lacerda"),
                new Person ("Claudio", "Raio"),
                new Person ("Bruno", "Katsumi Brit Games Net Junior")
        ));
        System.out.println(people);


        //comparator using anonymous class to declare its functionality
//        var comparatorLastName = new Comparator<Person>(){
//
//            @Override
//            public int compare(Person o1, Person o2) {
//                return o1.lastName.compareTo(o2.lastName);
//            }
//        };


//        comparator interface being used with lambda expressions
//        people.sort(((o1, o2) -> o1.lastName.compareTo(o2.lastName)));

        //comparator comparing method using lambdas
//        System.out.println(lineCounter() + " We sort comparing the last name");
//        people.sort(Comparator.comparing(o -> o.lastName)); //compares last name
//        System.out.println(people);
//
//        System.out.println(lineCounter() + " We sort comparing the first name");
//        people.sort(Comparator.comparing(o ->o.firstName)); //compares first name
//        System.out.println(people);

        //creating a generic interface


        System.out.println(lineCounter() + " Generic interface EnhancedComparator <T> created, extends comparator");
        interface EnhancedComparator <T> extends Comparator<T>{

            int secondLevel(T o1, T o2);
        }


        var comparatorMixed = new EnhancedComparator<Person>() {
            @Override
            public int compare (Person o1, Person o2){
                //assign the comparison between both o1 and o2
                int result =  o1.lastName.compareTo(o2.lastName); //returns (int) o1.char - (int) o2.char
                return (result == 0 ? secondLevel(o1, o2) : result);
            }

            @Override
            public int secondLevel(Person o1, Person o2) {
                return o1.firstName.compareTo(o2.firstName);
            }
        };

        people.sort(comparatorMixed);
        System.out.println(lineCounter() + " We use our own method now");
        System.out.println(people);


    }
}
