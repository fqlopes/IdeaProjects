package DONE.Section10.MobilePhoneContacts;

import java.util.ArrayList;

public class MobilePhone {

    private String myNumber;
    private ArrayList<Contact> myContacts;

    public MobilePhone (String myNumber){

        this.myNumber = myNumber;
        myContacts = new ArrayList<>();
    }

    public boolean addNewContact (Contact contact){

        if ( findContact(contact.getName()) >= 0 ){
            return false;
        } else {
            myContacts.add(contact);
            return true;
        }
    }

    public boolean updateContact (Contact oldContact, Contact newContact){

        if ( findContact(oldContact) >= 0){
            myContacts.add (findContact(oldContact), newContact);
            return true;
        }
        return false;
    }

    public boolean removeContact (Contact contact){

        if ( findContact(contact) >= 0){
            myContacts.remove(findContact(contact));
            return true;
        } else {
            return false;
        }
    }

    private int findContact (Contact contact){

        if(myContacts.contains(contact)){
            return myContacts.indexOf(contact);
        } else {
            return -1;
        }
    }

    private int findContact (String contactName){


        for (var contact : myContacts){
            if (contact.getName() == contactName){
                return myContacts.indexOf(contact);
            }
        }
        return -1;
    }


    public Contact queryContact (String name){

        if (findContact(name) >= 0){

            return myContacts.get((findContact(name)));

        } else {
            return null;
        }
    }

    public void printContacts() {
        System.out.println("Contact List:");
        for (int i = 0; i < myContacts.size(); i++) {
            System.out.printf("%d. %s -> %s%n", i + 1, myContacts.get(i).getName(), myContacts.get(i).getPhoneNumber());
        }
    }
}
