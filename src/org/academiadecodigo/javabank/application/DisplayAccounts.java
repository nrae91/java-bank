package org.academiadecodigo.javabank.application;

import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;

import java.util.Map;

public class DisplayAccounts implements Action {

    @Override
    public void execute(Customer customer) {
        Map<Integer,Account> accounts = customer.getAccounts();
        System.out.println();
        System.out.println("\nID    TYPE        BALANCE");
        System.out.println("---------------------------");
        for (Account account : accounts.values()){
            System.out.println(" " + account.getId() + "    " + account.getAccountType() + "    " + account.getBalance());
        }
    }
}
