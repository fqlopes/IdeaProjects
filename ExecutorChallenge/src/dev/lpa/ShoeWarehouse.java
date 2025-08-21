package dev.lpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShoeWarehouse {

    private List<Order> shippingItems;
    public final static String[] PRODUCT_LIST = {"Running Shoes", "Sandals", "Boots", "Slippers", "High Tops"};

    public ShoeWarehouse(){
        this.shippingItems = new ArrayList<>();

    }

    public synchronized  void receiveOrder (Order item) {

        while (shippingItems.size() > 20) {
            try {
                wait();
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        shippingItems.add(item);
        System.out.println(Thread.currentThread().getName() + " Incoming: " + item);
        notifyAll();
    }

    public synchronized Order fulfillOder() {

        while (shippingItems.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Order item = shippingItems.remove(new Random().nextInt(shippingItems.size()));
        System.out.println(Thread.currentThread().getName() + " Fulfilled: " + item);
        notifyAll();
        return item;
    }
}
