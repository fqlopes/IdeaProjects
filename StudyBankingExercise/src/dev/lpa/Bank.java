package dev.lpa;

import java.util.ArrayList;

public class Bank {

    private String name;
    private ArrayList<Branch> branches;

    public Bank(String name) {
        this.name = name;
        this.branches = new ArrayList<>();
    }

    public boolean addBranch(String branchName) {
        var aux = new Branch(branchName);
        branches.add(aux);
        return true;
    }

    public boolean addCustomer(String branchName, String customerName, double firstTransaction) {

        var branch = findBranch(branchName);

        if (branch != null) {
            branch.newCustomer(customerName, firstTransaction);
            return true;
        }
        return false;
    }

    public boolean addCustomerTransaction(String branchName, String customerName, double transaction) {

        var branch = findBranch(branchName);

        if (branch != null) {
            var customer = branch.findCustomer(customerName);
            customer.addTransaction(transaction);
            return true;
        }
        return false;
    }

    public Branch findBranch(String branchName) {
        for (int i = 0; i < branches.size(); i++) {
            if (branches.get(i).getBranchName().equals(branchName)) ;
            return branches.get(i);
        }
        return null;
    }

    public boolean listCustomers(String branchName, boolean printTransactions) {
        var branch = findBranch(branchName);
        if (branch != null) {
            System.out.println(branch.toString());
            for (int i = 0; i < branch.getCustomers().size(); i++) {
                Customer customer = branch.getCustomers().get(i);
                System.out.println("Customer: " + customer.getName() + "[" + (i + 1) + "]");
                if (printTransactions) {
                    System.out.println("Transactions");

                    for (int j = 0; j < customer.getTransactions().size(); j++) {
                        double transaction = customer.getTransactions().get(j);
                        System.out.println("[" + (j + 1) + "] Amount " + transaction);
                    }
                }
            }

        }return true;
    }
}

