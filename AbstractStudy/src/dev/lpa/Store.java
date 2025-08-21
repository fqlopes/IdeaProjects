package dev.lpa;


import java.util.ArrayList;

record OrderItem (int qty, ProductForSale product){

}

public class Store {

    private static ArrayList<ProductForSale> storeProducts = new ArrayList<>(); //Arraylist type is abstract


    public static void main(String[] args) {


        //the orderIndex will search between the hardcoded added products
        storeProducts.add(new ArtObject ("WARDROBE", "Made of wood", 50));
        storeProducts.add(new ArtObject("LAMP", "Beautiful and antique", 123.50));


        var order1 = new ArrayList<OrderItem>();
        addItemToOrder(order1, 1,1);
        addItemToOrder(order1,0,3);

        printOrder(order1);

    }

    public static void listProducts(){

        for (var item:storeProducts){
            System.out.println("-".repeat(30));
            item.showDetails();
        }
    }

    public static void addItemToOrder (ArrayList<OrderItem> order, int orderIndex, int qty){

        order.add(new OrderItem(qty,storeProducts.get(orderIndex)));
    }

    public static void printOrder(ArrayList<OrderItem> order){

        double salesTotal = 0;
        for (var item : order){
            item.product().printPricedItem(item.qty());
            salesTotal += item.product().getSalesPrice(item.qty());
        }

        System.out.println("Total $" + salesTotal);
    }
}
