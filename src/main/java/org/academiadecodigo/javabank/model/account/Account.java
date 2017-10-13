package org.academiadecodigo.javabank.model.account;

import org.academiadecodigo.javabank.model.Model;
import org.academiadecodigo.javabank.model.Customer;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="AccountType")
public abstract class Account extends Model {

    private double balance = 0;

    @ManyToOne
    private Customer customer;

    public void credit(double amount) {
        if (canCredit(amount)) {
            balance += amount;
        }
    }

    public void debit(double amount) {
        if (canDebit(amount)) {
            balance -= amount;
        }
    }

    public double getBalance() {
        return balance;
    }

    public abstract AccountType getAccountType();

    public boolean canDebit(double amount) {
        return amount > 0 && amount <= balance;
    }

    public boolean canCredit(double amount) {
        return amount > 0;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
