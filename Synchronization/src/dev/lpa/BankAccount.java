package dev.lpa;

public class BankAccount {

    private double balance;
    private String name;

    private final Object lockName = new Object();
    private final Object lockBalance = new Object();

    public BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        synchronized (lockName) {
            this.name = name;
            System.out.println(this.name);
        }
    }

    public void deposit(double amount) {
        try {
            System.out.println("Talking shit with the teller");
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lockBalance) {
            double originalBalance = balance;
            balance += amount;
            System.out.printf("STARTING BALANCE: %.0f, DEPOSIT(%.0f) : NEW BALANCE = %.0f%n", originalBalance, amount, balance);
            addPromoDollars(amount);
        }

    }

    private void addPromoDollars(double amount) {

        if (amount >= 5000) {
            synchronized (lockBalance) {
                System.out.println("Promotized!");
                balance += 25;
            }
        }
    }

    public synchronized void withdraw(double amount) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double originalBalance = balance;
        if (amount <= balance) {
            balance -= amount;
            System.out.printf("STARTING BALANCE: %.0f, WITHDRAWAL (%.0f) : NEW BALANCE = %.0f%n", originalBalance, amount, balance);
        } else {
            System.out.printf("STARTING BALANCE: %.0f, WITHDRAWAL (%.0f) : INSUFFICIENT FUNDS!%n", originalBalance, amount);
        }
    }


}
