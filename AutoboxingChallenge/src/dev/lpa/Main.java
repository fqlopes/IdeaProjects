package dev.lpa;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Customer bob = new Customer("Bob Costa", 1000 );
        System.out.println(bob);

        Bank bank = new Bank("Chase");
        bank.addNewCustomer("Jane A", 500.0);
        System.out.println(bank);

        bank.addTransaction("Jane A", -10.25);
        bank.addTransaction("Jane A", -75.01);
        bank.printStatement("Jane a");
        System.out.println(bank);

    }

}

class Bank {

    private String name;
    private ArrayList<Customer> customers = new ArrayList<>(5000);

    public Bank (String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", customers=" + customers +
                '}';
    }

    private Customer getCustomer(String customerName){

        for (var customer: customers){
            if(customer.name().equalsIgnoreCase(customerName)){
                return customer;
            }
        }
        System.out.printf("Customer (%s) was not found %n", customerName);
        return null;
    }

    public void addNewCustomer (String name, Double firstDeposit){

        if (getCustomer(name) == null) {
            var customer = new Customer(name, firstDeposit);
            System.out.println("New customer added: " + customer);
            customers.add(customer);
        }
    }

    public void addTransaction(String name, double transactionAmount){
        Customer customer = getCustomer(name);
        if (customer != null){
            customer.transactions().add(transactionAmount);
        }
    }

    public void printStatement(String customerName){

        Customer customer = getCustomer(customerName);
        if (customer == null){
            return;
        }
        System.out.println("-".repeat(30));
        System.out.println("Customer name: " + customer.name());
        System.out.println("Transactions:");
        for (double d:customer.transactions()){
            System.out.printf("$%10.2f (%s)%n", d , d < 0 ? "debit": "credit");
        }

    }
}