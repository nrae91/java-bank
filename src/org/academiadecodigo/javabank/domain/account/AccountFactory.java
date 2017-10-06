package org.academiadecodigo.javabank.domain.account;

public class AccountFactory {

    public Account create(AccountType accountType, int numberAccounts){
        switch(accountType){
            case SAVINGS:
                return new SavingsAccount(numberAccounts);
            case CHECKING:
                return new CheckingAccount(numberAccounts);
            default:
                return new CheckingAccount(numberAccounts);
        }
    }
}
