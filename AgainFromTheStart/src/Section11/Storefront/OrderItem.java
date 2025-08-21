package Section11.Storefront;

public class OrderItem {

    protected int qty;
    protected ProductForSale product;


    public OrderItem (ProductForSale product, int qty){

        this.product = product;
        this.qty = qty;
    }

}
