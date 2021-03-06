package org.academiadecodigo.javabank.test;

import org.academiadecodigo.javabank.domain.*;
import org.academiadecodigo.javabank.managers.AccountManager;
import org.academiadecodigo.javabank.domain.account.AccountType;

public class BankTest {

    public boolean test() {

        AccountManager accountManager = new AccountManager();
        Bank bank = new Bank();
        bank.setAccountManager(accountManager);

        Customer c1 = new Customer(1, "Rui");
        Customer c2 = new Customer(2, "Sergio");
        bank.addCustomer(c1);
        bank.addCustomer(c2);

        int a1 = c1.openAccount(AccountType.CHECKING);
        int a2 = c2.openAccount(AccountType.CHECKING);

        accountManager.deposit(a1, 100);
        accountManager.deposit(a2, 100);

        // bank balance should equal sum of all customers balance
        if (bank.getBalance() != 200) {
            return false;
        }

        return true;
    }
}
