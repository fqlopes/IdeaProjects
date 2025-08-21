package dev.lpa;

import com.sun.source.tree.Tree;

import java.util.*;

public class MapViewsMain {
    public static void main(String[] args) {


        Map<String, Contact> contacts = new HashMap<>();
        ContactData.getData("phone").forEach(contact -> contacts.put(contact.getName(), contact));
        ContactData.getData("email").forEach(contact -> contacts.put(contact.getName(), contact));

        Set<String> keysView = contacts.keySet();
        System.out.println(keysView);

        Set<String> copyOfKeys = new TreeSet<>(contacts.keySet());
        System.out.println(copyOfKeys);
        System.out.println();

        if (contacts.containsKey("Linus Van Pelt")) {
            System.out.println("Linus and I go way back, so of course I have info");
        }

        keysView.remove("Daffy Duck");
        System.out.println(keysView);
        contacts.forEach((k, v) -> System.out.println(v));

        System.out.println();

        copyOfKeys.remove("Linus Van Pelt");
        System.out.println(keysView);
        contacts.forEach((k, v) -> System.out.println(v));

        System.out.println();

        //retainAll removes everything that isn't passed as an argument
        keysView.retainAll(List.of("Linus Van Pelt", "Charlie Brown", "Robin Hood", "Mickey Mouse"));  //keep these, remove everything else
        System.out.println(keysView);
        contacts.forEach((k, v) -> System.out.println(v));

        //the viewSet is a reference to the same Set it's looking at. changing the view, reflect on the original Set
        keysView.clear();
        System.out.println(contacts);

        ContactData.getData("email").forEach(contact -> contacts.put(contact.getName(), contact));
        ContactData.getData("phone").forEach(contact -> contacts.put(contact.getName(), contact));
        System.out.println("-".repeat(45));
        System.out.println("keysView be:");
        System.out.println(keysView);
        System.out.println("-".repeat(45));

        var values = contacts.values();
        values.forEach(System.out::println);

        values.retainAll(ContactData.getData("email"));
        System.out.println("-".repeat(45));
        System.out.println(keysView);
        System.out.println("-".repeat(45));
        contacts.forEach((k, v) -> System.out.println(v));
        System.out.println("-".repeat(45));

        List<Contact> list = new ArrayList<>(values);
        list.sort(Comparator.comparing(Contact::getLastName));
        list.forEach(contact -> System.out.println(contact.getLastName() + " : " + contact));
        System.out.println("-".repeat(45));

        Contact first = list.get(0);
        contacts.put(first.getLastName(), first);
        values.forEach(System.out::println);
        System.out.println("-".repeat(45));
        keysView.forEach(System.out::println);

        HashSet<Contact> set = new HashSet<>(values);
        set.forEach(System.out::println);
        if (set.size() < contacts.keySet().size()){
            System.out.println("Duplicate values are in my Map");
        }
        System.out.println("-".repeat(45));

        var nodeSet = contacts.entrySet();
        for (var node : nodeSet){
            System.out.println(nodeSet.getClass().getName());
            if(!node.getKey().equals(node.getValue().getName())){
                System.out.println(node.getClass().getName());
                System.out.println("Key doesn't match the name :" + node.getKey() + " : " + node.getValue());
            }
        }

    }
}
