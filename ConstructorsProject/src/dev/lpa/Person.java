package dev.lpa;

public record Person(String name, String dob) {

//    this bad boy is the canonical/long constructor
//    useful for manipulate entries before they get assigned
//    public Person(String name, String dob) {
//        this.name = name;
//        this.dob = dob.replace('-','/');
//    }

    public Person(Person p) {
        this(p.name, p.dob);
    }

//    compact constructor, it has access to the record's fields implicitly
    public Person {
        if (dob == null) throw new IllegalArgumentException ("Bad data");
        dob = dob.replace('-','/');

    }
}
