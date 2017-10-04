package org.academiadecodigo.javabank.domain;

public abstract class Account {

    protected double balance = 0;
    private int id;

    // Constructor
    public Account(int id) {
        this.id = id;
    }

    // Methods
    public void credit(double amount) {
        balance += amount;
    }

    public void debit(double amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }

    // Getters
    public double getBalance() {
        return balance;
    }

    public int getId(){
        return this.id;
    };

    public abstract AccountType getAccountType();
}
