package dev.bank;

import dev.dto.Transaction;

import java.util.HashMap;
import java.util.Map;

public class BankAccount {

    public enum AccountType {CHECKING, SAVING} //enums are immutable

    private final AccountType accountType;
    private double balance;
    private Map<Long, Transaction> transactions = new HashMap<>();


    BankAccount(AccountType accountType, double balance) {
        this.accountType = accountType;
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public Map<Long, String> getTransactions() {
        Map<Long, String> txMap = new HashMap<>();
        for (var tx : transactions.entrySet()){
            txMap.put(tx.getKey(), tx.getValue().toString());
        }
        return txMap;
    }

    @Override
    public String toString() {
        return "%s $%.2f".formatted(accountType, balance);
    }

    void commitTransaction(int routingNumber, long transactionId, String customerId, double amount) {

        balance +=amount;
        transactions.put(transactionId, new Transaction(routingNumber, transactionId, Integer.parseInt(customerId), amount));

    }
}
