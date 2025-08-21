package dev.lpa;

import java.util.ArrayList;
import java.util.List;

public class MobilePhone {

    private String myNumber;
    private Contact myContacts;


    public MobilePhone (String myNumber, Contact myContacts){
    }

    public boolean addNewContact(Contact contact){
        return true;
    }

    public boolean updateContact(Contact oldContact, Contact newContact){
        return true;
    }

    public boolean removeContact(Contact contact){
        return true;
    }

    public int findContact (Contact contact){
        return 0;
    }

    public int findContact (String contact){
        return 0;
    }

    public Contact queryContact(String contact){
        return null;
    }

    public void printContacts(){

    }
}

class Contact {

    private String name;
    private String phoneNumber;
    private ArrayList <MobilePhone> myContacts;




    public Contact (String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.myContacts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void myContacts (){

    }
}
