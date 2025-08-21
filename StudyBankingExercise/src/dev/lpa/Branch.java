package dev.lpa;

import java.util.ArrayList;

public class Branch {

    private String branchName;
    private ArrayList<Customer> customers;

    public Branch(String branchName){
        this.branchName= branchName;
        this.customers = new ArrayList<>();
    }

    public String getBranchName() {
        return branchName;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public boolean newCustomer(String name, double firstDeposit){

        var customer = new Customer(name, firstDeposit);
        customers.add(customer);
        return true;

    }

    public Customer findCustomer (String name){
        for (int i = 0; i < customers.size(); i++){
            if (customers.get(i).getName().equals(name)){
                return customers.get(i);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "customers=" + customers +
                '}';
    }
}
