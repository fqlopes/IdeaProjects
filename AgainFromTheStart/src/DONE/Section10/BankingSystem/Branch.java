package DONE.Section10.BankingSystem;

import java.util.ArrayList;

public class Branch {

    private String name;
    private ArrayList<Customer> customers;

    public Branch (String name){
        this.name = name;
        customers = new ArrayList<>();

    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public boolean newCustomer(String customerName, double initialTransaction){

        boolean confirmation = false;
        var requested = findCustomer(customerName);
        if (requested == null ){
            customers.add(new Customer (customerName, initialTransaction));
            confirmation = true;
        }
        return confirmation;
    }

    public boolean addCustomerTransaction (String customerName, double amount){

        boolean confirmation = false;
       for (var customer : customers){
           if (customer.getName().equals(customerName)){
               customer.addTransaction(amount);
               confirmation = true;
           }

       }
       return confirmation;

    }

    public Customer findCustomer(String customerName){

        for (var customer : customers){
            if (customer.getName().equals(customerName)){
                return customer;
            }
        }
        return null;
    }

}
