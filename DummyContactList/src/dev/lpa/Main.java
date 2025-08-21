package dev.lpa;

import java.util.*;

public class Main {

    public static void main(String[] args) {

//        Contact myList = new Contact("TEST", "test@test.com", 1234567890);
//        Contact myList2 = new Contact("TAB",  133333333);
//
//        Set<Contact> contacts = new HashSet<>();
//        contacts.add(new Contact("TEST", "test@test.com", 1234567890));
//        contacts.add(new Contact("TEST", "test@test.com", 1234567890));
//        contacts.add(new Contact("TAB",  133333333));
//
//        printData(contacts);


        List<Contact> emails = ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");

        System.out.print("\n[@@@@] Email list [@@@@]\n");

        printData(emails);

        System.out.println("\n[@@@@] Phone list [@@@@]\n");
        printData(phones);


        Set<Contact> emailContacts = new HashSet<>(emails);
        Set<Contact> phoneContacts = new HashSet<>(phones);
//
//        printData(emailContacts);
//        System.out.println();
//        printData(phoneContacts);

        int index = emails.indexOf(new Contact("Robin Hood"));
        Contact robinHood = emails.get(index);
        robinHood.addEmail("Sherwood Forest");
        robinHood.addEmail("Silent Hill");
        System.out.println(robinHood);
        System.out.println();

        //Working with sets

        //Set union (∪) => C =  A + B -> all elements of A and B, no duplicates
        Set<Contact> unionAB = new HashSet<>();
        unionAB.addAll(emailContacts);
        unionAB.addAll(phoneContacts);
        System.out.println("-".repeat(32));
        System.out.println("(A ∪ B)   Union of emails (A) with phones (B)");
        System.out.println("-".repeat(32));
        printData(unionAB);
        System.out.println();
        //Set intersection(∩)  A ∩ B -> elements present both in A and B, intersections.
        Set<Contact> intersectAB = new HashSet<>(emailContacts);
        intersectAB.retainAll(phoneContacts);
        System.out.println("-".repeat(32));
        System.out.println("(A ∩ B) Intersection of emails (A) with phones (B)");
        System.out.println("-".repeat(32));
        printData(intersectAB);
        System.out.println();
        //Set intersection(∩)  B ∩ A -> elements present both in A and B, intersections.
//        Set<Contact> intersectBA = new HashSet<>(phoneContacts);
//        intersectBA.retainAll(emailContacts);
//        System.out.println("-".repeat(32));
//        System.out.println("(B ∩ A) Intersection of emails (B) with phones (A) is symmetric");
//        System.out.println("-".repeat(32));
//        printData(intersectBA);
//        System.out.println();
        
        //The difference of set A from set B can be equated to the expression A - B
        //The result of A - B are the elements only present and A
        Set<Contact> AMinusB = new HashSet<>(emailContacts);
        AMinusB.removeAll(phoneContacts);
        System.out.println("-".repeat(32));
        System.out.println("(A - B) Difference of emails (A) minus phones (B)");
        System.out.println("-".repeat(32));
        printData(AMinusB);
        System.out.println();

        Set<Contact> BMinusA = new HashSet<>(phoneContacts);
        BMinusA.removeAll(emailContacts);
        System.out.println("-".repeat(32));
        System.out.println("(B - A) Difference of phones (A) minus emails (B)");
        System.out.println("-".repeat(32));
        printData(BMinusA);
        System.out.println();

        //Symmetric difference (∆) of two sets
        // A ∆ B = (A - B) ∪ (B-A)
        //The symmetric difference of A and B are all the elements that are present either in A or B, no intersections
        Set<Contact> symmetricDiff = new HashSet<>(AMinusB);
        symmetricDiff.addAll(BMinusA);
        System.out.println("-".repeat(32));
        System.out.println("(A ∆ B) Symmetric Difference of emails (A) and phones (B)");
        System.out.println("-".repeat(32));
        printData(symmetricDiff);
        System.out.println();

        //Like in this code, if we already have the intersection, we can get a union then remove the intersection.


    }


    public static void printData (Collection<Contact> contacts){
        contacts.forEach(System.out::println);
    }
}
