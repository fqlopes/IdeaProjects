package Section11.Storefront;

public abstract class ProductForSale {

    protected String type;
    protected double price;
    protected String description;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductForSale (String type, double price, String description){
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public double getSalesPrice (int qty){
        return price * qty;
    }

    public void printPricedItem (int qty){
        String separator = "-".repeat(60);
        System.out.println(separator);
        System.out.printf("%-15s %-15.2f%n", this.type, this.price);
        System.out.println(separator);
    }

    public abstract void showDetails ();

    public double getPrice(){
        return price;
    }






}
