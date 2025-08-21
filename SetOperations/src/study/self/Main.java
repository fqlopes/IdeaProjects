package study.self;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        var bossList = TaskData.getTasks("boss");
//        printSets(bossList);
        var annList = TaskData.getTasks("ann");
//        printSets(annList);
        var bobList = TaskData.getTasks("bob");
//        printSets(bobList);
        var carolList = TaskData.getTasks("carol");
//        printSets(carolList);

        //Adding all data into a single set as a reference to all other sets

        List<Set <Task>> AllList = (List.of(bossList,annList,bobList,carolList));


        List<Set <Task>> assignedT = List.of(annList, bobList, carolList);
        Set<Task> assignedTasks = getUnion(assignedT);
        System.out.println("Union of the assigned tasks");
        printSets(assignedTasks);
        Set<Task> unassignedTasks = TaskData.getTasks("null");
        System.out.println("Union of the unassigned tasks");
        printSets(unassignedTasks);
        Set<Task> trueAllTasks = getUnion(List.of(unassignedTasks, assignedTasks));


        System.out.println("UNION OF ALL LISTS IN ONE GO");
        var everyList = getUnion(List.of(bossList, annList, bobList, carolList));
        printSets(everyList);
//        System.out.println("INTERSECTION BETWEEN ALL LISTS AND ANN'S LIST");
        var intersectAB = getIntersection(everyList, annList);
//        printSets(intersectAB);
//        System.out.println("DIFFERENCE BETWEEN ALL LISTS AND ANN'S LIST");
        var AMinusB = getDifferenceAB(everyList, annList);
//        printSets(AMinusB);

        //Instantiating a Comparator -> Comparing by Priority
        Comparator<Task> sortByPriority = Comparator.comparing(Task::getPriority);
//        System.out.println("ANN'S LIST SORTED BY PRIORITY");
//        printAndSort(annList, sortByPriority);

        var trueAllList = getUnion(AllList);
        System.out.println("ALL LIST AGAIN");
        printSets(trueAllList);

        boolean equalsTest = everyList.equals(trueAllList);
        boolean secondEqualsTest = everyList.equals(trueAllTasks);
        System.out.println(secondEqualsTest);





    }

    public static void printSets (Set<Task> taskList){
        System.out.printf("%-20s %-25s %-15s %-10s %s%n","PROJECT NAME","DESCRIPTION", "WORKER", "PRIORITY", "STATUS" );
        System.out.println("-".repeat(90));
        List<Task> newList = new ArrayList<>(taskList);
        newList.forEach(System.out::println);
        System.out.printf("%n");
    }

    public static void printAndSort(Set<Task> taskList , Comparator<Task> comparator){
        System.out.printf("%-20s %-25s %-15s %-10s %s%n","PROJECT NAME","DESCRIPTION", "WORKER", "PRIORITY", "STATUS" );
        System.out.println("-".repeat(90));
        List<Task> newList = new ArrayList<>(taskList);
        newList.sort(comparator);
        newList.forEach(System.out::println);
        System.out.printf("%n");

    }

    public static Set<Task> getUnion (List<Set<Task>> setList){

        Set<Task> union = new HashSet<>();
        for (var sets : setList){
            union.addAll(sets);
        }
        return union;
    }

    public static Set<Task> getIntersection (Set<Task> a, Set<Task> b){

        Set<Task> intersection = new HashSet<>(a);
        intersection.retainAll(b);
        return intersection;
    }

    public static Set<Task> getDifferenceAB (Set<Task> a, Set<Task> b){

        Set<Task> difference = new HashSet<>(a);
        difference.removeAll(b);
        return difference;
    }

    public static Set<Task> getDifferenceBA (Set<Task> a, Set<Task> b){

        Set<Task> difference = new HashSet<>(b);
        difference.removeAll(a);
        return difference;
    }


    public static Set<Task> symmetricDiff (Set<Task> a, Set<Task> b){
        Set<Task> fullSet = getUnion(List.of(a,b)); // gets a union of two sets
        Set<Task> intersect = getIntersection(a,b); // removes only the intersection between them
        fullSet.remove(intersect);
        return fullSet;
    }
}
