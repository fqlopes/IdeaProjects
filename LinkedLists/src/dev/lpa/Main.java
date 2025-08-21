package dev.lpa;

import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {

//        LinkedList<String> placesToVisit = new LinkedList<>();

        var placesToVisit = new LinkedList<String>();
        placesToVisit.add("Sidney");
        placesToVisit.add(0,"Canberra"); //the index adjusts automatically
        System.out.println(placesToVisit);

        addMoreElements(placesToVisit);
        System.out.println(placesToVisit);

//        removeElements(placesToVisit);
//        System.out.println(placesToVisit);

//        gettingElements(placesToVisit);

//        printItinerary3(placesToVisit);

        testIterator(placesToVisit);
    }

    private static void addMoreElements(LinkedList<String> list){

        list.addFirst("Darwin");
        list.addLast("Hobart");

        //LinkedList seen as a queue of elements
        list.offer("Melbourne");
        list.offerFirst("Brisbane");
        list.offerLast("Toowoomba");

        //LinkedList seen as a stack of elements
        list.push("Alice Springs");


    }

    private static void removeElements(LinkedList<String> list){

        list.remove(4); //remove by index
        list.remove("Brisbane");

        System.out.println(list);
        String s1 = list.remove(); //removes first element
        System.out.println(s1 + " was removed");

        String s2 = list.removeFirst(); //removes first element
        System.out.println(s2 + " was removed");


        String s3 = list.removeLast(); //removes last element
        System.out.println("Removed last element " + s3);

        //Queue/dequeue methods
        String p1 = list.poll(); //removes first element
        System.out.println(p1 + " was removed");
        String p2 = list.pollFirst(); //removes first element
        System.out.println(p2 + " was removed");
        String p3 = list.pollLast(); //removes last element
        System.out.println(p3 + " was removed");

        list.push("Sydney");
        list.push("Brisbane");
        list.push("Canberra");
        System.out.println(list);

        String p4 = list.pop();//remove first element
        System.out.println(p4 + " was removed");

    }

    private static void gettingElements (LinkedList <String> list){

        System.out.println("Retrieved Element = " + list.get(4));  //retrieve by index

        System.out.println("First Element =  " + list.getFirst());
        System.out.println("Last Element = " + list.getLast());

        System.out.println("Darwin is at position = " + list.indexOf("Darwin"));
        System.out.println("Melbourne is at position = " + list.lastIndexOf("Melbourne"));

        //Queue retrieval method
        System.out.println("Element retrieved from .element() = " + list.element()); //retrieves the first index

        //Stack retrieval method
        System.out.println("Element retrieved from .peek()" + list.peek()); //retrieves the first index
        System.out.println("Element retrieved from .peek()" + list.peekFirst()); //retrieves the first index
        System.out.println("Element retrieved from .peek()" + list.peekLast()); //retrieves the last index
    }


//Traversing and manipulating elements in the list

    public static void printItinerary(LinkedList<String> list){
        System.out.println("Trip starts at " + list.getFirst());
        //Traversing the list with a simple for loop
        //Indexing the elements of an LinkedList is not the most optimal
        for (int i = 1; i < list.size(); i ++){ //first and last elements already have plenty of methods
            System.out.println("from " + list.get(i - 1) + " to " + list.get(i));
        }
        System.out.println("Trip ends at " + list.getLast());
    }

    public static void printItinerary2(LinkedList<String> list){
        System.out.println("Trip starts at " + list.getFirst());
        //Traversing the list with a for each loop
        String previousTown = list.getFirst();
        for (String town : list){
            if (town == previousTown){
                continue;
            }
            System.out.println("We are going from " + previousTown + " to " + town);
            previousTown = town;
        }

        System.out.println("Trip ends at " + list.getLast());
    }

    public static void printItinerary3(LinkedList<String> list){
        System.out.println("Trip starts at " + list.getFirst());
        //Traversing the list with a for each loop
        String previousTown = list.getFirst();
        ListIterator<String> iterator = list.listIterator(1);
        while (iterator.hasNext()){
            var town = iterator.next();
            System.out.println("We are going from " + previousTown + " to " + town);
            previousTown = town;
        }
        System.out.println("Trip ends at " + list.getLast());
    }

//    public static void testIterator (LinkedList<String> list){
//
//        var iterator = list.iterator(); //var infers that the type is ListIterator<String>
//        while (iterator.hasNext()){
//           /* System.out.println(iterator.next()); */
//            if(iterator.next().equals("Brisbane")){
//                iterator.remove(); // the iterator will remove the element you searched for
//            }
//        }
//        System.out.println(list);
//    }

    public static void testIterator (LinkedList<String> list){

        var iterator = list.listIterator(); //var infers that the type is ListIterator<String>
        while (iterator.hasNext()){
//            System.out.println(iterator.next());
            if(iterator.next().equals("Brisbane")){
                iterator.add("Lake Wivenhoe"); // the ListIterator places after the element you searched
            }
        }

        while (iterator.hasPrevious()){
            System.out.println(iterator.previous());
        }
        System.out.println(list);

        var iterator2 = list.listIterator(3); //set where the ListIterator will begin by passing the index
        System.out.println(iterator2.previous());
    }
}
