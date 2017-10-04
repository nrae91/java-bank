package org.academiadecodigo.javabank.domain;


import java.util.HashMap;
import java.util.Map;

public class Manager {

    private Map<Integer, Account> allAccounts = new HashMap<>();

    // Methods
    public Account openAccount(Costumer costumer, AccountType accountType){

        if(accountType.equals(AccountType.SAVINGS)){
            return new SavingsAccount(1);
        } else {
            return new CheckingAccount(1);
        }

    }

    /*
    public double getBalance(int id) {
        return accounts.get(id).getBalance();
    }

    public double getBalance() {

        double balance = 0;

        for (Account account : accounts.values()) {
            balance += account.getBalance();
        }

        return balance;
    }*/
}
