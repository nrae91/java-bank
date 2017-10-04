package org.academiadecodigo.javabank.domain;

public class SavingsAccount extends Account {

    public static final int MIN_SAVINGS_BALANCE = 100;

    public SavingsAccount(int id) {
        super(id);
    }

    @Override
    public void debit(double amount) {
        if (super.balance - amount >= MIN_SAVINGS_BALANCE){
            super.balance -= amount;
        }
    }

    @Override
    public AccountType getAccountType(){
        return AccountType.SAVINGS;
    }
}
