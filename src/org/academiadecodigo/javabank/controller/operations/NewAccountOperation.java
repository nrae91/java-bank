package org.academiadecodigo.javabank.controller.operations;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.view.BankApplication;
import org.academiadecodigo.javabank.view.Messages;
import org.academiadecodigo.javabank.model.account.AccountType;

import java.util.HashMap;
import java.util.Map;

public class NewAccountOperation extends AbstractBankOperation {

    private Map<Integer,AccountType> accountTypeMap = new HashMap<>();;
    private Map<Integer,String> userOptions = new HashMap<>();
    private String[] options = {"Checking", "Savings"};

    public NewAccountOperation(BankApplication bankApplication) {
        super(bankApplication);
        userOptions.put(1,"Checking");
        userOptions.put(2,"Savings");
        accountTypeMap.put(1,AccountType.CHECKING);
        accountTypeMap.put(2,AccountType.SAVINGS);
    }

    @Override
    public void execute() {

        MenuInputScanner scanner = new MenuInputScanner(options);

        int userInput = bankApplication.getPrompt().getUserInput(scanner);

        int accountId = customer.openAccount(accountTypeMap.get(userInput));

        System.out.println("\n" + Messages.CREATED_ACCOUNT + customer.getName() + " : " + accountId);

    }
}
