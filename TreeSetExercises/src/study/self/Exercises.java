package study.self;



import com.sun.source.tree.Tree;

import java.util.*;

public class Exercises {


    //    Write a Java program to create a tree set, add some colors (strings) and print out the tree set.
    public static Set<String> treeFabricator(String... colors) {

        Set<String> newTreeSet = new TreeSet<>(List.of(colors));
        System.out.println("Colors added: " + newTreeSet);
        return newTreeSet;
    }

    //   Write a Java program to create a TreeSet of colors and then
    //   use a custom Comparator to sort them by the length of the color name.


    public static Set<String> createAndSortTree(String... colors) {

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
    //          return o1.length() > o2.length() ? 1 : (o1.length() == o2.length() ? 0 : -1); //crude
                return Integer.compare(o1.length(), o2.length()); //easier. better?
            }
        };
        return new TreeSet<>(comparator);
    }

//    Write a Java program to create a TreeSet of colors
//    and add elements in random order, then print the set to demonstrate its natural ordering

    public static Set<String> randomTreeColorSet (String... colors){
      List<String> myStrings = new ArrayList<>(List.of(colors));
      Collections.shuffle(myStrings);
      return new TreeSet<String>(myStrings);
    }

//    Write a Java program to create a TreeSet of colors,
//    convert each color to lowercase before insertion,
//    and then display the sorted unique set.

    public static Set<String> lowerCaseColorTree (String... colors){
//     TreeSet sorts everything automatically
        Set<String> myTreeSet = new TreeSet<>();
        for (String color : colors){
            myTreeSet.add(color.toLowerCase());
        }
        return myTreeSet;
    }

//    Write a Java program to iterate through all elements in a tree set.

    public static <T> void treeViewer (Set<T> genericSet){

        genericSet.forEach(System.out::println);
    }
//    Write a Java program to iterate over a TreeSet
//    using an Iterator and print each element along with its hash code.

    public static <T> void anotherTreeMethod (Set<T> genericSet){

        Iterator<T> genericIterator = genericSet.iterator();
        while(genericIterator.hasNext()){
            T element = genericIterator.next();
            System.out.println( element + " " + element.hashCode());
        }
    }

//    Write a Java program to iterate through a TreeSet
//    using a for-each loop and conditionally print only those elements that start with a vowel.
//    Using forEach instead...
    public static void vowelColors (Set<String> stringSet){
//        String vowels = "AEIOUaeiou";
        Set<Character> vowels = java.util.Set.of('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u');
        stringSet.forEach(color -> {
            if (color != null && vowels.contains(color.charAt(0))){
                System.out.println(color);
            }
        });
    }

//    Using for-Each like a normal person would
    public static void vowelColors2 (Set<String> stringSet){
        Set<Character> vowels = java.util.Set.of('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u');

        for(String string : stringSet){
            if (!string.isEmpty() && vowels.contains(string.charAt(0))){
                System.out.println(string);
            }
        }
    }

//    Write a Java program to iterate over a TreeSet in reverse order
//    by converting it to a descending set and then printing each element.
//    Easy way

    public static <T> void treeIteratorReverseSimple (Set<T> genericSet){
        if (genericSet == null){
            System.out.println("The set is null");
            return;
        }

        if ((genericSet instanceof TreeSet<?>)){
            TreeSet<T> genericTree = new TreeSet<>(genericSet);
            genericTree.descendingSet().forEach(System.out::println);
        } else {
            try {
                new TreeSet<>(genericSet).descendingSet().forEach(System.out::println);
            } catch (ClassCastException error){
                System.out.println("The set presented has no natural order");
            }
        }
    }

//    Write a Java program to add all the elements of a specified tree set to another tree set.

    public static <T> void addAllToSet (Set<T> aSet, Set<T> bSet){
        aSet.addAll(bSet);
    }

//    Write a Java program to merge two TreeSets of strings and then print the resulting sorted set.

    public static void mergeStringSets (Set<String> aSet, Set<String> bSet){
        aSet.addAll(bSet);
        aSet.forEach(System.out::println);
    }

//    Write a Java program to add all elements from one TreeSet
//    to another using addAll() and then remove duplicates from the merged set.

    public static <T> void mergeAndRemove (Set<T> aSet, Set<T> bSet){
        Set<T> tempSet = java.util.Set.copyOf(bSet);
        for (T element : aSet){
            if (!tempSet.contains(element)){
                bSet.add(element);
            }
        }
    }

//    Write a Java program to create two TreeSets, merge them,
//    and then display only the elements that are common to both sets.

    public static <T> Set <T> treeSetCreator (T... elements){
        //adding half of the elements in each tree
        int half = elements.length/2;
        Set<T> aSet = new TreeSet<>();
        Set<T> bSet = new TreeSet<>();
        for (int i = 0; i < half; i++){
            aSet.add(elements[i]);
        }
        for (int i = half; i < elements.length; i++){
            bSet.add(elements[i]);
        }
        Set<T> commonSet = new TreeSet<>(aSet);
        commonSet.retainAll(bSet);
        return commonSet;
    }

//    Write a Java program to join two TreeSets of integers
//    and then compute the sum of all elements in the resulting set.

    public static void sumOfSetElements (Set<Integer> aSet, Set<Integer> bSet){
        //simple and lazy way and optimized
        int count = 0;
        for (Integer val : aSet){
            count += val;
        }

        for (Integer val : bSet){
            if (!aSet.contains(val)){
                count += val;
            }
        }
        System.out.println("Result is =" + count);
    }

//    Write a Java program to create a reverse order view of the elements contained in a given tree set.

    public static <T> NavigableSet<T> reversingSet (Set<T> aSet){
        return new TreeSet<>(aSet).descendingSet();
    }

//    Write a Java program to obtain a reverse order view of a TreeSet
//    using descendingSet() and then print the reversed set.

    public static <T> NavigableSet <T> getReverseTree (Set<T> aSet){

        NavigableSet <T> reverseSet = new TreeSet<>(aSet).descendingSet();
        reverseSet.forEach(System.out::println);
        return reverseSet;
    }

//    Write a Java program to convert a TreeSet to an array,
//    reverse the array, and then create a new TreeSet from the reversed array.

    public static Set<String> toArrayAndBack (Set<String> aSet){
//        purposely pass a small array, java will allocate a bigger one
        String[] stringArray = aSet.toArray(new String [0]);
//        reversing the array
        Collections.reverse(Arrays.asList(stringArray));
//        creating hour treeSet with custom comparator
//        Set<String> reversedTree = new TreeSet<>(Collections.reverseOrder());
//        reversedTree.addAll(Arrays.asList(stringArray));
        return new TreeSet<>(Arrays.asList(stringArray));
    }

//    Write a Java program to use a custom Comparator
//    to create a TreeSet that maintains elements in reverse natural order

    public static Set<String> reverseNaturalOrder (String... elements){
        Set<String> mySet = new TreeSet<>(Collections.reverseOrder());
        mySet.addAll(List.of(elements));
        return mySet;
    }

    public static Set<Integer> reverseNaturalOrder(Integer... elements){
        Set<Integer> mySet = new TreeSet<>(Collections.reverseOrder());
        mySet.addAll(List.of(elements));
        return mySet;
    }
//    Generic

    public static <T> Set<T> reverseNaturalOrder(T... elements){
        Set<T> mySet = new TreeSet<>(Collections.reverseOrder());
        mySet.addAll(List.of(elements));
        return mySet;
    }

//    Write a Java program to demonstrate the difference between
//    the natural ordering and the reverse ordering of a TreeSet by printing both views

//    Dew it like a pro

    public static <T extends Comparable<? super T>> void printBoth (Set<T> aSet){
//        check nulls

        if (aSet == null){
            System.out.println("Null sets are not allowed");
            throw new IllegalArgumentException("We leaving");
        }

//        checking if it's a treeSet
        if (!(aSet instanceof TreeSet)){
            System.out.println("We only do TreeSets here");
        }

//        now we can do it
        System.out.println("Natural order of TreeSet:");
        aSet.forEach(System.out::println);

        // Creating reversed TreeSet
        Set<T> reversedSet = new TreeSet<>(Collections.reverseOrder());
        reversedSet.addAll(aSet);

        // Reversed order of TreeSet
        System.out.println("\nReversed order of TreeSet:");
        reversedSet.forEach(System.out::println);
    }

//    Write a Java program to get the first and last elements in a tree set.

    public static <T> void getFirstAndLast (TreeSet<T> aSet){
        System.out.println("First element of the set: " + aSet.first());
        System.out.println("Last element of the set: " + aSet.last());
    }

//    Write a Java program to compare the first and last elements of a TreeSet
//    and determine if they satisfy a custom condition. Condition = is pair or not.

    public static void compareFirstAndLast (TreeSet<Integer> aSet){

        System.out.println("First element = " + aSet.first());
        System.out.println("Last element of the set: " + aSet.last());

        int firstVal = aSet.first();
        int secondVal =aSet.last();

        System.out.println("Are first and last element pairs?");
        System.out.println(firstVal%2 == 0);
        System.out.println(secondVal%2 == 0);
    }


//    Write a Java program to use a TreeSet’s iterator
//    to get the first element and descendingIterator() to get the last element.

    public static void treeSetIteration (TreeSet<String> aSet){

        Iterator<String> myIterator = aSet.iterator();
        Iterator<String> myDescendingIterator = aSet.descendingIterator();

        String myFirstElement = myIterator.next();;
        String myLastElement = myDescendingIterator.next();

        System.out.println("The first element from an iterator " + myFirstElement);
        System.out.println("The last element from a descendingIterator " + myLastElement);

    }

//    Write a Java program to remove the first element using pollFirst()
//    and the last element using pollLast(), then print the updated set

    public static void removeFirstAndLast (TreeSet<String> aSet){

        System.out.println("We are going to remove the first and last elements from the set and post it again");
        aSet.pollFirst();
        aSet.pollLast();
        System.out.println(aSet);
    }

//    Write a Java program to clone a tree set list to another tree set.

    public static TreeSet<String> treeSetCloning (TreeSet<String> aSet){
        return new TreeSet<String>(aSet);
    }

//    Write a Java program to get the number of elements in a tree set.

    public static int setSize (Set<Integer> aSet){
        return aSet.size();
    }

//    Write a Java program to get the element in a tree set which is greater than or equal to the given element.

    public static void compareToElement (TreeSet<Integer> aSet, int element){

        System.out.println("Element greater or equal than " + element + " = " + aSet.ceiling(element));

    }

//    Write a Java program to implement a lambda expression that retrieves the element
//    in a TreeSet that is greater than or equal to a specified target.

    public static void lambdaInTreeSet (TreeSet<Integer> aSet, Integer target){
        Integer ceiling = aSet.ceiling(target);
        aSet.forEach(element ->{
            if (element.equals(ceiling)){
                System.out.println(element);
            }
        });
    }

//    Write a Java program to compare the result of ceiling()
//    with a custom search algorithm for finding an element ≥ a given value.

    public static void ceilingCompared (TreeSet<String> aSet, String target){
//        ceiling method
        var ceiling = aSet.ceiling(target);
        aSet.higher(target);


    }

//    Write a Java program to get the element in a tree set less than or equal to the given element.

    public static void parseLesserElement (TreeSet<Integer> aSet, Integer element){

//        without floor method
        Integer result = null;
        for (Integer value : aSet){
            if (value <= element){
                if (result == null || value > result){
                    result = value;
                }
            }

        }
        System.out.println("Element found was " + result);
    }

//    Write a Java program to use the floor() method on a TreeSet
//    to retrieve the greatest element less than or equal to a given value.

    public static <T> T lowerOrEqualThan (TreeSet<T> aSet, T target){

        return aSet.floor(target);

    }

//    Write a Java program to implement a custom method that searches for the element ≤ a specified value using iteration.

    public static void parseLesserElement2 (TreeSet<Integer>aSet, Integer element){

        Iterator<Integer> integerIterator = aSet.iterator();
        Integer result = null;
        while (integerIterator.hasNext()){
            Integer temp = integerIterator.next();
            if (temp <= element){
                if (result == null || temp > result){
                    result = temp;
                }
            }
        }
        System.out.println(result);
    }
//    Write a Java program to compare the floor() method with a manual search and print both results for verification.
    public static void parsingComparator (TreeSet<Integer>aSet, Integer element){
        System.out.println("floor is"+ aSet.floor(element));
        Iterator<Integer> integerIterator = aSet.iterator();
        Integer result = null;
        while (integerIterator.hasNext()){
            Integer temp = integerIterator.next();
            if (temp <= element){
                if (result == null || temp > result){
                    result = temp;
                }
            }
        }
        System.out.println("custom search is " + result);
    }

//    Write a Java program to get the element in a tree set strictly greater than or equal to the given element.

    public static Integer strictlyGreaterElement (Set<Integer> aSet, Integer element){
        Integer result = null;
        for (Integer value : aSet){
            if (value > element){
                if (result == null || value < result){
                    result = value;
                }
            }

        }
        return result;
    }

//    Write a Java program to use the higher() method
//    on a TreeSet to get the least element strictly greater than a given value.
    public static Integer higherMethod (Set<Integer> aSet, Integer element){
        return new TreeSet<>(aSet).higher(element);
    }

//    Write a Java program to implement a lambda expression
//    that returns the element strictly greater than a specified target using TreeSet methods.

    public static void lambdaHigherMethod (Set<Integer> aSet, Integer element){
        Integer result = null;
        TreeSet<Integer> treeSet = new TreeSet<>(aSet);
        treeSet.forEach(integer -> {
            if (integer.equals(treeSet.higher(element))){
                System.out.println(integer);;
            }
        });
    }

//    Write a Java program to get an element in a tree set that has a lower value than the given element.

    public static Integer lowerThanElement (Set<Integer> aSet , Integer element){

        if (aSet == null || element == null) {
            return null;
        }

        Integer result = null;
        for (Integer value : aSet){
            if (value < element){
                if ( result == null || value > result){
                    result = value;
                }
            }

        }
        return result;
    }

    public static Integer lowerMethod (Set<Integer> aSet, Integer element){
        return new TreeSet<>(aSet).lower(element);
    }

//    Write a Java program to retrieve and remove the first element of a tree set.

    public static Integer fetchAndRemoveFirst (NavigableSet<Integer> aSet){
        System.out.println("The first element is " + aSet.first());
        System.out.println("Removing the first element");
        return aSet.pollFirst();
    }


}


