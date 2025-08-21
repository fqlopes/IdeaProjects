package DONE.Section10.BankingSystem;

import java.util.ArrayList;

public class Customer {

    private String name;
    private ArrayList<Double> transactions;


    public Customer (String name, double transactions){
        this.name = name;
        this.transactions = new ArrayList<>();
        this.transactions.add(transactions);

    }

    public String getName(){
        return name;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }

    public boolean addTransaction(double amount){
        System.out.println("Adding transactions");
        transactions.add(amount);
        return true;
    }


}
