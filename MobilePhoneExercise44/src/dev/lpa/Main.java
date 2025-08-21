package dev.lpa;

public class Main {
    public static void main(String[] args) {

            MobilePhone test = new MobilePhone("555");
            Contact guy = new Contact("Someguy","123");
            Contact anotherGuy = new Contact("Another Guy","123456");
            Contact testing = new Contact("Someguy", "don't have a phone number anymore");
            Contact a = new Contact("a", "don't have a phone number anymore");
            Contact b = new Contact("b", "don't have a phone number anymore");
            Contact c = new Contact("c", "don't have a phone number anymore");

            test.addContact(a);
            test.addContact(b);
            test.addContact(c);
            test.addContact(guy);
            test.addContact(anotherGuy);
            test.addContact(testing);
            test.updateContact(guy,testing);
            System.out.println(test.queryContact("a"));


        }
}



