package org.academiadecodigo.javabank.view;

import org.academiadecodigo.javabank.controller.operations.BalanceOperationController;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;

import java.text.DecimalFormat;
import java.util.Set;

public class BalanceOperationView implements View {

    private BalanceOperationController controller;

    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    public void show() {

        System.out.println("\n" + controller.getCustomer().getName() + Messages.BALANCE_MESSAGE + "\n");

        displayAccounts();

        System.out.println("\n" + Messages.BALANCE_TOTAL_MESSAGE + df.format(controller.getCustomer().getBalance()));
        controller.onGettingBalance();
    }

    public void setController(BalanceOperationController controller) {
        this.controller = controller;
    }

    private void displayAccounts(){
        Set<Account> accounts = controller.getCustomer().getAccounts();

        for (Account account: accounts) {
            System.out.println(account.getId() + "\t" + account.getAccountType() + "\t" + df.format(account.getBalance()));
        }
    }
}
