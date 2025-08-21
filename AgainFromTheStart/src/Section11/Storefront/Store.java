package Section11.Storefront;

import java.util.ArrayList;
import java.util.List;

public class Store {

    public static List<ProductForSale> shoppingList = new ArrayList<>();
    public static void main(String[] args) {

        ProductForSale aLamp = new Lamp(5, "A new Lamp");
        ProductForSale bLamp = new Lamp(6, "Another Lamp");
        ProductForSale cLamp = new Lamp(8, "Yet another Lamp");
        ProductForSale dLamp = new Lamp(10, "Guess what? It's a Lamp");

        shoppingList.addAll(List.of(aLamp, bLamp, cLamp, dLamp));

        printList(shoppingList);


        List<OrderItem> shopping = new ArrayList<>();



    }

    public static void printList (List<ProductForSale> shoppingList){

        if (!shoppingList.isEmpty()){
            for (var items : shoppingList){
                items.showDetails();
            }
        }
    }

    public static void addItemToOrder (ArrayList<OrderItem> order, int orderIndex, int qty){

        order.add(new OrderItem(shoppingList.get(orderIndex), qty));

    }


    public static void printOrder (ArrayList<OrderItem> order){

        double totalPrice = 0;
        for(var item : order){
            item.product.printPricedItem(1);
            totalPrice += item.product.getPrice();

        }

        System.out.println("Sales Total = " + totalPrice);
    }
}
