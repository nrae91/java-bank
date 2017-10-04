package org.academiadecodigo.javabank.domain;

import java.util.HashMap;
import java.util.Map;

public class Customer {

    private Map<Integer, Account> accounts = new HashMap<>();

    public void requestAccount(Manager manager, AccountType accountType) {
        Account account = manager.openAccount(this, accountType);
        accounts.put(account.getId(), account);
    }

    public double getBalance(int id) {
        return accounts.get(id).getBalance();
    }

    public double getBalance() {

        double balance = 0;

        for (Account account : accounts.values()) {
            balance += account.getBalance();
        }

        return balance;
    }

    public void deposit(int id, double amount) {
        accounts.get(id).credit(amount);
    }

    public void withdraw(int id, double amount) {

        Account account = accounts.get(id);

        if (account.getAccountType() == AccountType.SAVINGS) {
            return;
        }

        account.debit(amount);

    }

    public void transfer(int srcId, int destId, double amount) {

        Account srcAccount = accounts.get(srcId);
        Account dstAccount = accounts.get(destId);

        double originalBalance = srcAccount.getBalance();

        // if there is no balance in src account do nothing
        if (srcAccount.getBalance() < amount) {
            return;
        }

        // Try to debit the account
        srcAccount.debit(amount);

        // Only if the amount is debited from the source account is the amount
        // credited to the destination account
        if (originalBalance != srcAccount.getBalance()) {
            dstAccount.credit(amount);
        }

    }

}
