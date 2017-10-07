package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;
import org.academiadecodigo.javabank.application.Action;
import org.academiadecodigo.javabank.application.MainMenu;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.managers.AccountManager;

import java.util.HashSet;
import java.util.Set;

public class JavaBank {

    private Bank bank;
    private AccountManager accountManager;

    public static void main(String[] args) {

        JavaBank javaBank = new JavaBank();
        MainMenu mainMenu = new MainMenu();
        javaBank.boot();

        boolean notDone = true;

        while (notDone) {

            Customer customer = javaBank.loadCustomer();
            Action action = mainMenu.open();
            action.execute(customer);

            Prompt prompt = new Prompt(System.in, System.out);

            Set<String> options = new HashSet<>();
            options.add("y");
            options.add("yes");
            options.add("n");
            options.add("no");

            StringSetInputScanner scanner = new StringSetInputScanner(options);

            scanner.setMessage("Do you wish to proceed? (y/n): ");

            String userInput = prompt.getUserInput(scanner);

            if (userInput.equals("y") || userInput.equals("yes")){
                notDone = true;
            } else if (userInput.equals("n") || userInput.equals("no")) {
                notDone = false;
            }

        }
    }

    private void boot() {
        this.accountManager = new AccountManager();
        this.bank = new Bank(this.accountManager);
        bank.addCustomer(new Customer(1, "Nuno"));
        bank.addCustomer(new Customer(2, "Diogo"));
        bank.getCustomer(1).setAccountManager(this.accountManager);
        bank.getCustomer(2).setAccountManager(this.accountManager);
        bank.getCustomer(1).openAccount(AccountType.CHECKING);
        accountManager.deposit(1,100);
    }

    private Customer loadCustomer() {

        Prompt prompt = new Prompt(System.in, System.out);

        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getCustomerIds());

        int userInput = prompt.getUserInput(scanner);

        return bank.getCustomer(userInput);
    }
}
