package dev.lpa;

import com.sun.source.tree.Tree;

import java.util.*;

public class TreeSetMain {

    public static void main(String[] args) {


        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        Comparator<Contact> myComparator = Comparator.comparing(Contact::getName);

        NavigableSet<Contact> sorted = new TreeSet<>(myComparator);
        sorted.addAll(phones);
        sorted.forEach(System.out::println);

        NavigableSet<String> justNames = new TreeSet<>();
        phones.forEach(c -> justNames.add(c.getName()));
        System.out.println(justNames);


        NavigableSet<Contact> fullSet = new TreeSet<>(sorted);
        fullSet.addAll(emails);
        fullSet.forEach(System.out::println);

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.sort(sorted.comparator());
        System.out.println("-".repeat(45));
        fullList.forEach(System.out::println);
        System.out.println("-".repeat(45));


        Contact min = Collections.min(fullSet, fullSet.comparator());
        Contact max = Collections.max(fullSet, fullSet.comparator());

        Contact first = fullSet.first();
        Contact last = fullSet.last();

        System.out.println("Collections.min  = (" + min + ") and fullSet.last (" + last + ")");
        System.out.println("Collections.max  = (" + max + ") and fullSet.first (" + first  + ")");


        //pollFirst and pollLast remove the element from the set
        NavigableSet<Contact> copiedSet = new TreeSet<>(fullSet);
        System.out.println("First element -> pollFirst()= " + copiedSet.pollFirst());
        System.out.println("Last element  -> pollLast()= " + copiedSet.pollLast());
        copiedSet.forEach(System.out::println);


        System.out.println("-".repeat(45));

        System.out.println("fullSet.pollFirst() = " + copiedSet.pollFirst());
        System.out.println("fullSet.pollLast() = " + copiedSet.pollLast());
        System.out.println("-".repeat(45));
        copiedSet.forEach(System.out::println);

        //Further functionalities are shown below
        //Identifying the closest match inside a set, to a value you pass to the method
        System.out.println("-".repeat(45));
        Contact daffy = new Contact("Daffy Duck");
        Contact daisy = new Contact("Daisy Duck");
        Contact snoopy = new Contact("Snoopy");
        Contact archie = new Contact("Archie");


        //ceiling returns an element that's greater than or equal to the element passed
        for (Contact c : List.of(daffy, daisy, last, snoopy)) {
            //ceiling with return the element that equals the value passed
            System.out.printf("ceiling(%s)=%s%n", c.getName(), fullSet.ceiling(c));
            //higher method never return the value that's equal to an element on a set,
            // it always returns the next greater element
            System.out.printf("higher(%s)=%s%n", c.getName(), fullSet.higher(c));
        }
        System.out.println("-".repeat(45));

        //floor and lower
        for (Contact c : List.of(daffy, daisy, first, archie)) {
            System.out.printf("floor(%s)=%s%n", c.getName(), fullSet.floor(c));
            System.out.printf("lower(%s)=%s%n", c.getName(), fullSet.lower(c));
        }
        System.out.println("-".repeat(45));

        NavigableSet<Contact> descendingSet = fullSet.descendingSet();
        descendingSet.forEach(System.out::println);
        System.out.println("-".repeat(45));

        Contact lastContact = descendingSet.pollLast();
        System.out.println("Removed " + lastContact);
        System.out.println("-".repeat(45));
        descendingSet.forEach(System.out::println);
        System.out.println("-".repeat(45));
        fullSet.forEach(System.out::println);
        System.out.println("-".repeat(45));

        //Returns a view of the portion of this set whose elements are greater than or equal to fromElement.
        // The returned set is backed by this set, so changes in the returned set are reflected in this set, and vice-versa.
        // The returned set supports all optional set operations that this set supports.
        Contact marion = new Contact("Maid Marion");
        var headSet = fullSet.headSet(marion, true); //headSet is exclusive, second argument boolean to make it inclusive
        headSet.forEach(System.out::println);
        System.out.println("-".repeat(45));

        var tailSet = fullSet.tailSet(marion, false); //tailSet is inclusive, second argument boolean to make it exclusive
        tailSet.forEach(System.out::println);
        System.out.println("-".repeat(45));

        //subSet returns a set that is in between the values passed.
        // overloaded versions can use a boolean flag to turn inclusive or exclusive behavior
        Contact linus = new Contact("Linus Van Pelt");
        var subset = fullSet.subSet(linus, false, marion, true);
        subset.forEach(System.out::println);

    }

    public static void printData (String header, Collection<Contact> contacts){


        System.out.println("-".repeat(45));
        System.out.println(header);
        System.out.println("-".repeat(45));
        contacts.forEach(System.out::println);
    }
}
