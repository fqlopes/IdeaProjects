package dev.lpa;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MobilePhone {

    private String myNumber;
    private ArrayList<Contact> contacts;

    public MobilePhone (String myNumber){
        this.myNumber = myNumber;
        this.contacts = new ArrayList<>();
    }

    public boolean addContact(Contact contact){
        contacts.add(contact);
        return true;
    }

    public boolean updateContact (Contact oldContact, Contact newContact){
        int aux = findContact(oldContact);
        if (aux >= 0){
            contacts.set(aux, newContact);
            return true;
        }
        return false;
    }

    public boolean removeContact (Contact contact){
        int aux = findContact(contact);
        if (aux >= 0){
            contacts.remove(aux);
            return true;
        }
        return false;
    }

    public int findContact (Contact contact){
        for (int i = 0; i < contacts.size(); i++){
            if (contacts.get(i).equals(contact)){
                System.out.println("Contact found, index = " + i);
                return i;
            }
        }
        return -1;
    }

    public int findContact (String name){
        for (int i = 0; i < contacts.size(); i++){
            if (contacts.get(i).getName().equals(name)){
                System.out.println("Found contact " + contacts.get(i));
                return i;
            }
        }
        return -1;
    }

    public Contact queryContact(String name){
        int aux = findContact(name);
        if (aux >= 0){
            System.out.println("We found you @@@@@@@@@@@@@@@@@");
            return contacts.get(aux);
        }
        return null;
    }

    public void printContacts (){
        System.out.println("Printing contacts");
        for (int i = 0; i < contacts.size(); i++){
            System.out.println(contacts.get(i));
        }
    }
    @Override
    public String toString() {
        return "MobilePhone{" +
                myNumber + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
