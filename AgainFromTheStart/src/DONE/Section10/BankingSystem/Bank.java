package DONE.Section10.BankingSystem;

import java.util.ArrayList;

public class Bank {

    private String name;
    private ArrayList<Branch> branches;


    public Bank (String name){

        this.name = name;
        this.branches = new ArrayList<>();

    }

    public boolean addBranch (String name){
        boolean confirmation = false;

        if (findBranch(name) == null){
            branches.add(new Branch (name));
            confirmation = true;
        }

        return confirmation;
    }

    public boolean addCustomer (String name, String customerName, double initialTransaction){

        boolean confirmation = false;

        var branch = findBranch(name);
        if (branch != null) {
            return branch.newCustomer(customerName, initialTransaction);
        }
        return confirmation;
    }

    public boolean addCustomerTransaction (String name, String customerName, double transaction){

        boolean confirmation = false;
        for (var branch : branches){
            for (var customer : branch.getCustomers()){
                if (branch.getName() == name && customer.getName() == customerName){
                    customer.addTransaction(transaction);
                    confirmation = true;
                }
            }
        }
        return confirmation;
    }

    private Branch findBranch (String name){

        for (var branch : branches){
            if (branch.getName().equals(name)){
                return branch;
            }
        }
        return null;
    }

    public boolean listCustomers(String name, boolean printTransactions){

        boolean confirmation = false;
        var branch = findBranch(name);
        if (branch != null){
            var customers = branch.getCustomers();
            System.out.println("Customer details for branch " + branch.getName());

            if(!printTransactions) {
                for (int i = 0; i < customers.size(); i++) {
                    var customer = customers.get(i);
                    System.out.println("Customer: " + customer.getName() + "[" + (i + 1) + "]");
                    confirmation = true;
                }
            } else {
                for (int i = 0; i < customers.size(); i++) {
                    var customer = customers.get(i);
                    System.out.println("Customer: " + customer.getName() + "[" + (i + 1) + "]");
                    System.out.println("Transactions");
                    for (int j = 0; j < customer.getTransactions().size(); j++){
                        System.out.printf("[%d] Amount %.2f%n",j+1,customer.getTransactions().get(j));
                        confirmation = true;

                    }
                }
            }

        }
        return confirmation;
    }
}
