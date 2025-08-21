package dev.lpa;

import java.util.ArrayList;

public class Customer {

    private String name;
    private ArrayList<Double> transactions;

    public Customer (String name, Double firstTransaction){
        this.name = name;
        this.transactions = new ArrayList<>(500 );
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }

    public void addTransaction(double ammount){
        transactions.add(ammount);
    }
}
