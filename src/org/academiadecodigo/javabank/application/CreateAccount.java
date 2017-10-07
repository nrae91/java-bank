package org.academiadecodigo.javabank.application;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;

public class CreateAccount implements Action {

    @Override
    public void execute(Customer customer) {
        Prompt prompt = new Prompt(System.in,System.out);

        String[] options = {"Checking","Savings"};

        MenuInputScanner scanner = new MenuInputScanner(options);

        int userInput = prompt.getUserInput(scanner);

        AccountType accountType;

        switch (userInput){
            case 1:
                accountType = AccountType.CHECKING;
            case 2:
                accountType = AccountType.SAVINGS;
            default:
                accountType = AccountType.CHECKING;
        }

        customer.openAccount(accountType);
    }
}
