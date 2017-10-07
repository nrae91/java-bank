package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.javabank.application.Action;
import org.academiadecodigo.javabank.application.MainMenu;
import org.academiadecodigo.javabank.managers.AccountManager;

public class JavaBank {

    private Bank bank;
    private AccountManager accountManager;

    public static void main(String[] args) {

        JavaBank javaBank = new JavaBank();
        MainMenu mainMenu = new MainMenu();
        javaBank.boot();
        Customer customer = javaBank.loadCustomer();
        Action action = mainMenu.open();
        action.execute(customer);
    }

    private void boot() {
        this.accountManager = new AccountManager();
        this.bank = new Bank(this.accountManager);
    }

    private Customer loadCustomer() {

        // Not supposed to be here!!!!
        bank.addCustomer(new Customer(1, "Nuno"));
        bank.addCustomer(new Customer(2, "Diogo"));

        Prompt prompt = new Prompt(System.in, System.out);

        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getCustomerIds());

        int userInput = prompt.getUserInput(scanner);

        return bank.getCustomer(userInput);
    }
}
