package dev.lpa;

public class Main {
    public static void main(String[] args) {

        MobilePhone test = new MobilePhone("555");
        Contact x = new Contact("Phil", "666");
        var y = new Contact("Ana", "7777");
        var z = new Contact("Dexter", "17928");
        var a = new Contact("abc","38383");


        test.addContact(x);
        test.addContact(y);
        test.addContact(z);
        test.addContact(a);
         test.printContacts();
    }
}
