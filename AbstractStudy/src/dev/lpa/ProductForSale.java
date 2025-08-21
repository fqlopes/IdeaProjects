package dev.lpa;


public abstract class ProductForSale {

    protected String type;
    protected String description;
    protected double price;

    public ProductForSale(String type, String description, double price) {
        this.type = type;
        this.description = description;
        this.price = price;
    }

    protected double getSalesPrice (int qty){

        return price *qty;

    }

    public void printPricedItem(int qty){ //concrete method on a abstract class works on any inherited classes

        double totalPrice = getSalesPrice(qty);
        System.out.println(type + " " + description + " $" +  price  +" each. Quantity "+ qty +  ". Total Price = $" + totalPrice);

    }

    abstract void showDetails(); //print type, and desc

}
