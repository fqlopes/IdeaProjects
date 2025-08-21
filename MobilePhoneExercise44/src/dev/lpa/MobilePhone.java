package dev.lpa;

import java.util.ArrayList;

public class MobilePhone {

    private String myNumber;
    private ArrayList<Contact> myContacts;

    public MobilePhone (String myNumber){
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<>();

    }

    public MobilePhone(Contact myContacts) {
        this.myContacts = new ArrayList<>();
    }

    public boolean addContact (Contact contact){

        for (Contact myContact : myContacts) {
            if (myContact.getName().equals(contact.getName())) {
                return false;
            }
        }
        myContacts.add(contact);
        return true;
    }

    public boolean updateContact (Contact oldContact, Contact newContact){
        for (int i = 0; i < myContacts.size() ; i++){
            if (myContacts.get(i).getName().equals(oldContact.getName())){
                myContacts.set(i, newContact);
                return true;
            }
        }
        return false;
    }


    public boolean removeContact(Contact contact) {
        for (int i = 0; i < myContacts.size(); i++) {
            if (myContacts.get(i).getName().equals(contact.getName())) {
                myContacts.remove(i);
                return true;
            }
        }
        return false;
    }

    private int findContact(String name) {
        for (int i = 0; i < myContacts.size(); i++) {
            if (myContacts.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private int findContact(Contact contact) {
        return findContact(contact.getName());
    }

    public Contact queryContact(String name) {
        if (findContact(name) >= 0) {
            return myContacts.get(findContact(name));
        }
        return null;
    }

    public void printContacts(){
        System.out.println("Contact List:");
        for (int i = 0; i < myContacts.size(); i++) {
            String name = myContacts.get(i).getName();
            String phoneNumber = myContacts.get(i).getPhoneNumber();
            System.out.println((i + 1) + ". " + name + " -> " + phoneNumber);
        }
    }

    @Override
    public String toString() {
        return "Owner number:  "+ myNumber + " his contacts" + myContacts.toString();
    }

    //get the size of the contact list
    public int size (){
        return myContacts.size();
    }
}
