package study.self;

import java.util.*;

public class HashSetStudy {
    //    1. Write a Java program to append the specified element to the end of a hash set.
    public static Set<String> appendElement(List<String> elements) {

        Set<String> hashSet = new HashSet<>();
        elements.forEach(e -> hashSet.add(e));
        return hashSet;
    }

    public static Set<String> appendElement(String element) {

        Set<String> hashSet = new HashSet<>();
        hashSet.add(element);
        return hashSet;
    }

    public static void appendElement(Set<String> aSet, String element) {
        aSet.add(element);
    }


    //    2. Write a Java program to iterate through all elements in a hash list.
    public static void iterateElements(Set<String> aSet) {
        aSet.forEach(System.out::println);


        Iterator<String> iterator = aSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    //    3. Write a Java program to get the number of elements in a hash set.
    public static void hashSize(Set<String> aSet) {
        int size = aSet.size();
        System.out.println(size);
    }

    //    4. Write a Java program to empty a hash set.
    public static void clearHashSet(Set<String> aSet) {
        if (aSet != null) {
            aSet.clear();
        }
    }

    //    5. Write a Java program to test if a hash set is empty or not.
    public static void hashTest(Set<String> aSet) {
        if (aSet != null) {
            System.out.println("is not null");
            if (aSet.size() == 0 || aSet.isEmpty()) { //do the same, but showing possible ways here
                System.out.println("is empty");

            }
        }
    }

    //    6. Write a Java program to clone a hash set to another hash set.
    public static Set<String> cloningSet(Set<String> aSet) {
        return new HashSet<>(aSet);
    }

    //    7. Write a Java program to convert a hash set to an array.
    public static String[] setToArray(Set<String> aSet) {
        String[] stringArray = new String[aSet.size()];
        aSet.toArray(stringArray);
        return stringArray;
    }

    //    8. Write a Java program to convert a hash set to a tree set.
    public static Set<String> hashSetToTreeSet(Set<String> aSet) {
        return new TreeSet<>(aSet);
    }

    //    9. Write a Java program to find numbers less than 7 in a tree set.
    public static void findingNumbers(TreeSet<Integer> aSet) {
        int count = 0;
        List<Integer> numbers = new ArrayList<>();
        for (var number : aSet) {

            if (number < 7) {
                System.out.println(number); //printing elements less than 7 of the set
                numbers.add(number);
                count++;
            }
        }
        System.out.println(count + "  numbers were found");
        System.out.println(numbers);

        //another way -> headSet < N ; tailSet > N

        Set<Integer> lessThanSeven = new TreeSet<Integer>(aSet.headSet(7)); // headSeat == elements less than seven
        //tailSet == elements bigger than seven
        System.out.println("Elements less than 7 = " + lessThanSeven.size());
        System.out.println(lessThanSeven);
    }

//    11. Write a Java program to compare two sets and retain elements that are the same.

    public static Set<String> setComparator(Set<String> aSet, Set<String> bSet) {

        var retainedSet = new HashSet<String>(aSet);
        retainedSet.retainAll(bSet);
        return retainedSet;
    }

    //    12. Write a Java program to remove all elements from a hash set.
    public static <T> Set<T> remevoeAllSet(Set<T> aGenericSet) {
        aGenericSet.clear();
        return aGenericSet;


    }

    //Related Problems

//    Write a Java program to add an element to a HashSet and verify its presence using the contains() method.
    public static <T> void addAndVerify(T element, Set<T> genericSet) {
//         return addAndVerify.add(element) && addAndVerify.contains(element); //very lazy, smart but stupid
        if (!genericSet.contains(element)) {
            genericSet.add(element);
            System.out.println("Element was added");
        } else {
            System.out.println("Element already present");
        }
    }

//    Write a Java program to append multiple elements to a HashSet
//    and then convert it to a TreeSet to display the elements in sorted order.
    public static <T> TreeSet<T> appendAndConvert(Set<T> hashSet, List<T> elements) {
        hashSet.addAll(elements);
        System.out.println("Elements added");
        return new TreeSet<T>(hashSet);
    }

//    Write a Java program to add an element to a HashSet, remove it,
//    and then re-add it to observe the effect on the set's ordering.

    public static <T> void whyWouldYouDoThis (Set<T> hashSet, T element){

        System.out.println("Order we received is shown below:\n " + hashSet + "\nThen we add and remove a few times the element " + element );
        hashSet.add(element);
        System.out.println("Added | Checking order " + hashSet );
        hashSet.remove(element);
        System.out.println("Removed | Checking order " + hashSet);
    }


//    Write a Java program to iterate over a HashSet
//    using an Iterator and print only the elements that satisfy a given condition.

    public static <T> void  imTiredAlready (Set<T> aSet){

        System.out.println("The set\n" + aSet);
        System.out.println("I'm gonna print everything that has a pair hashcode because why not?");

        Iterator<T>genericIterator = aSet.iterator();

        while(genericIterator.hasNext()){
            T element = genericIterator.next();
            if(element.hashCode() % 2 == 0){
                System.out.println(element);
            }
        }
    }

//    Write a Java program to iterate through a HashSet
//    using Java 8 forEach() and a lambda expression, printing each element with its hash code.

    public static <T> void anotherPointlessMethod (Set<T> aSet){

        aSet.forEach(t -> System.out.println("this is " + t + " and it's hashcode is " + t.hashCode()));

    }

//    Write a Java program to iterate over a HashSet,
//    convert each element to uppercase, and collect the results into a new HashSet.

    public static Set<String> toUpperCase (Set<String> aSet ){
        System.out.println("Strings are immutable, we will return another Set<String>");
        Set<String> newSet = new HashSet<>();
        for (var element : aSet){
            var upperCased = element.toUpperCase();
            newSet.add(upperCased);
        }
        return newSet;
    }

    public static Set<String> toUpperCaseReplacing (Set<String> aSet ){
        System.out.println("Strings are immutable,but we will break some rules");
        Set<String> newSet = new HashSet<>();
        for (var element : aSet){
            var upperCased = element.toUpperCase();
            newSet.add(upperCased);
        }
        aSet.clear();
        aSet.addAll(newSet);
        return aSet;
    }

}



