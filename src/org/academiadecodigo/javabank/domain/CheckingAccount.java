package org.academiadecodigo.javabank.domain;

public class CheckingAccount extends Account {

    private AccountType accountType;

    public CheckingAccount(int id) {
        super(id);
    }

    @Override
    public AccountType getAccountType(){
        return AccountType.CHECKING;
    }
}
