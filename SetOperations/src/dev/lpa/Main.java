package dev.lpa;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Set<Task> tasks = TaskData.getTasks("all");
        sortAndPrint("All Tasks", tasks);
        Set<Task> annTasks = TaskData.getTasks("ann");
        Set<Task> bobTasks = TaskData.getTasks("bob");

        Set<Task> carolTasks = TaskData.getTasks("carol");
        Comparator<Task> sortByPriority = Comparator.comparing(Task::getPriority);

        sortAndPrint("Carol's Tasks", carolTasks ,sortByPriority);

        List<Set<Task>> sets = List.of(annTasks, bobTasks, carolTasks);

        Set<Task> assignedTasks = getUnion(sets);
        sortAndPrint("Assigned Tasks", assignedTasks);

        Set<Task> everyTasks = getUnion(List.of(tasks, assignedTasks));
        sortAndPrint("True all tasks", everyTasks);

        Set<Task> missingTasks = getDifference(everyTasks, tasks);
        sortAndPrint("Missing tasks", missingTasks);

        Set<Task> unassignedTasks = getDifference(tasks, assignedTasks);
        sortAndPrint("Unsigned tasks",unassignedTasks, sortByPriority );

        Set<Task> overlap = getUnion(List.of(
                getIntersection(annTasks, bobTasks),
                getIntersection(carolTasks, bobTasks),
                getIntersection(annTasks,carolTasks)
        ));
        sortAndPrint("Assigned to multiples", overlap, sortByPriority);

        List<Task> overlapping = new ArrayList<>();
        for(Set<Task> set : sets){
            Set<Task> dupes = getIntersection(set, overlap);
            overlapping.addAll(dupes);
        }

        Comparator<Task> priorityNatural = sortByPriority.thenComparing(Comparator.naturalOrder());
        sortAndPrint("Overlapping", overlapping, priorityNatural);


        Set<Task> trueAllTasks = getUnion(List.of(annTasks,bobTasks,carolTasks,tasks));

        System.out.println(trueAllTasks.equals(everyTasks));
    }





















    private static void sortAndPrint(String header, Collection<Task> collection){
        sortAndPrint(header, collection,null);
    }




    private static void sortAndPrint(String header, Collection<Task> collection, Comparator<Task> sorter){

        String lineSeparator = "-".repeat(90);
        System.out.println(lineSeparator);
        System.out.println(header);
        System.out.println(lineSeparator);

        List<Task> list = new ArrayList<>(collection);
        list.sort(sorter);
        list.forEach(System.out::println);
    }




    private static Set<Task> getUnion(List<Set<Task>> sets)  {

        Set<Task> union = new HashSet<>();
        for (var taskSet: sets){
            union.addAll(taskSet);
        }
        return union;
    }





    private static Set<Task> getIntersection(Set<Task> a, Set<Task>b){

        Set<Task> intersection = new HashSet<>(a);
        intersection.retainAll(b);
        return intersection;
    }




    private static Set<Task> getDifference (Set<Task> a, Set<Task>b){
        Set<Task> result = new HashSet<>(a);
        result.removeAll(b);
        return result;
    }

}

