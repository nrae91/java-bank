package org.academiadecodigo.javabank.domain.account;

public interface Account {

    int getId();

    AccountType getAccountType();

    double getBalance();

    void credit(double amount);

    void debit(double amount);

    boolean canDebit(double amount);

    boolean canCredit(double amount);

}
