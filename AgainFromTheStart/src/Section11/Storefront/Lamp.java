package Section11.Storefront;

public class Lamp extends  ProductForSale {

    private String type;
    private double price;
    private String description;


    public Lamp (double price, String description){
        super("LAMP",price,description);

    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void showDetails() {
        System.out.println("It's a " + getType() + " that costs " + getPrice() + ". It's contents read: " + getDescription());
    }

    @Override
    public String toString (){
        return "%-15s %-15.2f%n".formatted(this.type, this.price);
    }
}
