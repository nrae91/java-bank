package org.academiadecodigo.javabank.view;

import org.academiadecodigo.javabank.controller.operations.BalanceOperationController;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;

import java.text.DecimalFormat;
import java.util.Set;

public class BalanceOperationView implements View {

    private Bank bank;
    private BalanceOperationController controller;
    private Customer customer;

    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    public void show() {
        setCustomer();
        System.out.println("\n" + customer.getName() + Messages.BALANCE_MESSAGE + "\n");
        Set<Account> accounts = customer.getAccounts();
        for (Account account: accounts) {
            System.out.println(account.getId() + "\t" + account.getAccountType() + "\t" + df.format(account.getBalance()));
        }
        System.out.println("\n\n" + Messages.BALANCE_TOTAL_MESSAGE + df.format(customer.getBalance()));
        controller.onGettingBalance();
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setController(BalanceOperationController controller) {
        this.controller = controller;
    }

    public void setCustomer() {
        this.customer = bank.getAccessingCustomer();
    }
}
