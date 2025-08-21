package dev.lpa;

public class ArtObject extends ProductForSale {
    public ArtObject(String type, String description, double price) {
        super(type, description, price); // inheritance super will set the abstract superclass datatypes we declared
    }

    @Override
    void showDetails() {
        System.out.println("This " + type + " is a beautiful artwork");
        System.out.println("The price is " + price);
        System.out.println(description);

    }
}
