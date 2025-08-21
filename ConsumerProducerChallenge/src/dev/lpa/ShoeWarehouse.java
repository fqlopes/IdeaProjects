package dev.lpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShoeWarehouse {

    private List<Order> shippingItems;

    private final ExecutorService fulfillmentService;

    public final static String[] PRODUCT_LIST = {"Running Shoes", "Sandals", "Boots", "Slippers", "High Tops"};

    public ShoeWarehouse (){
        this.shippingItems = new ArrayList<>();
        this.fulfillmentService = Executors.newFixedThreadPool(3);
    }

    public void shutDown (){
        fulfillmentService.shutdown();
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
        fulfillmentService.submit(this::shutDown);
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
