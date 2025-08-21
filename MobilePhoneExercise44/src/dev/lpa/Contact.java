package dev.lpa;

public class Contact {

    private String name;
    private String phoneNumber;

    public Contact (String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName () {
        return name;
    }

    public String getPhoneNumber (){
        return phoneNumber;
    }

    public static  boolean createContact (String name, String phoneNumber){
        Contact createContact = new Contact(name, phoneNumber);
        return true;
    }

    @Override
    public String toString() {
        return (name + " " + phoneNumber);
    }


}
