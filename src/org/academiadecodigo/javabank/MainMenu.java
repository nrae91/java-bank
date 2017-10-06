package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.operations.*;

import java.util.HashMap;
import java.util.Map;

public class MainMenu {

    private String[] options = {"Deposit", "Withdraw", "Transfer", "Display Accounts", "Create Account"};
    private Map<Integer,Action> operations;

    public Action open(Customer customer) {

        buildOperationsMap();

        Prompt prompt = new Prompt(System.in, System.out);

        MenuInputScanner scanner = new MenuInputScanner(options);

        scanner.setMessage("Choose an option: ");

        int userInput = prompt.getUserInput(scanner);

        return operations.get(userInput);
    }

    private void buildOperationsMap(){
        operations.put(1, new Deposit());
        operations.put(2, new Withdraw());
        operations.put(3, new Transfer());
        operations.put(4, new DisplayAccounts());
        operations.put(5, new CreateAccount());
    }
}
