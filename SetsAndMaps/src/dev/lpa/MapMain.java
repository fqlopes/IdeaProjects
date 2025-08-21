package dev.lpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMain {

    public static void main(String[] args) {

        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.forEach(System.out::println);
        System.out.println("-".repeat(45));

        //HashMap requires two arguments, the key and the type of the value, or collection element

        Map<String, Contact> contacts = new HashMap<>(); //bro don't have addAll :sad:
        //to add the fullList, we must use a loop to manually insert elements to our hashMap

        for (Contact contact : fullList){
            //takes a key and a value and inserts an "Entry" to the map
            contacts.put(contact.getName(), contact);
            //put always add the element, and if the element already exits, it overwrites them
        }
        contacts.forEach((k,v) -> System.out.println("key = " + k + ", value = " + v));
        System.out.println("-".repeat(45));

        //you can use the key value to look up elements in a map
        System.out.println(contacts.get("Charlie Brown")); //charlie brown is a key, and if it isn't valid, returns null
        System.out.println(contacts.get("Chuck Brown"));

        Contact defaultContact = new Contact("Chuck Brown");
        System.out.println(contacts.getOrDefault("Chuck Brown", defaultContact));

        System.out.println("-".repeat(45));
        contacts.clear();
        for (Contact contact : fullList){
            Contact duplicate = contacts.put(contact.getName(), contact);
            if (duplicate !=null){
//                System.out.println("duplicate  = " +duplicate);
//                System.out.println("current = " + contact);
                contacts.put(contact.getName(), contact.mergeContactData(duplicate));
            }
        }
        contacts.forEach((k,v) -> System.out.println("key = " + k + ", value = " + v));
        System.out.println("-".repeat(45));

        //There's also a possibility to add new elements to a map without overwriting existing elements
        contacts.clear();
        for(Contact contact : fullList){
        //each additional matching record doesn't replace  the initial entry
        //After an entry is permitted, it won't update if the same key has a different value attached to i
            contacts.putIfAbsent(contact.getName(), contact);
        }
        contacts.forEach((k,v) -> System.out.println("key = " + k + ", value = " + v));
        System.out.println("-".repeat(45));

        contacts.clear();
        for(Contact contact : fullList) {
            Contact duplicate = contacts.putIfAbsent(contact.getName(), contact);
            if (duplicate != null){
                contacts.put(contact.getName(), contact.mergeContactData(duplicate));
            }
        }
        contacts.forEach((k,v) -> System.out.println("key = " + k + ", value = " + v));
        System.out.println("-".repeat(45));

        contacts.clear();
        fullList.forEach(contact -> contacts.merge(contact.getName(),contact,
                Contact::mergeContactData));
        contacts.forEach((k,v) -> System.out.println("key = " + k + ", value = " + v));

        //the compute method acts like the put method in this case,
        //overwriting previous values of a given element, if present
        //computeIfAbsent is a method that doesn't overwrite if present
        System.out.println("-".repeat(45));
        for (String contactName : new String[] {"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}){
//             contacts.compute(contactName, (k,v) -> new Contact(k));
            contacts.computeIfAbsent(contactName, k -> new Contact(k));
        }
        contacts.forEach((k,v) -> System.out.println("key = " + k + ", value = " + v));

        System.out.println("-".repeat(45));
        for (String contactName : new String[] {"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}){
            contacts.computeIfPresent(contactName, (k,v) -> {
              v.addEmail("Fun Place"); return v;
            } );
        }
        contacts.forEach((k,v) -> System.out.println("key = " + k + ", value = " + v));
        System.out.println("-".repeat(45));

        contacts.replaceAll((k,v) -> {
            String newEmail = k.replaceAll(" ", "") + "@funplace.com";
            v.replaceEmailIfExists("DDuck@funplace.com", newEmail);
            return v;
        });
        contacts.forEach((k,v) -> System.out.println("key = " + k + ", value = " + v));
        System.out.println("-".repeat(45));

        Contact daisy = new Contact("Daisy Jane Duck", "daisyj@duck.com");
        Contact replacedContact = contacts.replace("Daisy Duck", daisy);
        System.out.println("daisy = " + daisy);
        System.out.println("replacedContact = " + replacedContact);
        contacts.forEach((k,v) -> System.out.println("key = " + k + ", value = " + v));
        System.out.println("-".repeat(45));

        //replace has an overloaded method that lets you specify that you only want to replace a value in the map
        //If both the keys and values match

        Contact updatedDaisy = replacedContact.mergeContactData(daisy);
        System.out.println("updatedDaisy = " + updatedDaisy);
        boolean success = contacts.replace("Daisy Duck", daisy, updatedDaisy);
        if (success){
            System.out.println("Successfully replaced element");
        } else {
            System.out.printf("Did not match both keys: %s and value %s %n".formatted("Daisy Duck",replacedContact));
        }
        contacts.forEach((k,v) -> System.out.println("key = " + k + ", value = " + v));
        System.out.println("-".repeat(45));

        //the remove method also has 2 overloaded versions:
        //the first: takes a key and returns the value that was removed, or null if the value doesn't exist for that key
        //the second: takes both a key and value, only removing the element from the map, if the key is in the map
        //and the value to be removed equals the value passed. this method returns a boolean

        success = contacts.remove("Daisy Duck", daisy);
        if (success){
            System.out.println("Successfully removed the element");
        } else {
            System.out.printf("Did not match both keys: %s and value %s %n".formatted("Daisy Duck", daisy));
        }
        contacts.forEach((k,v) -> System.out.println("key = " + k + ", value = " + v));
    }
}
