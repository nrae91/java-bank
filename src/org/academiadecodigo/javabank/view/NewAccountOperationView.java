package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.controller.operations.NewAccountOperationController;

public class NewAccountOperationView implements View {

    private NewAccountOperationController controller;
    private String[] options = {"Checking", "Savings"};
    private Prompt prompt;

    @Override
    public void show() {
        MenuInputScanner scanner = new MenuInputScanner(options);

        int userInput = prompt.getUserInput(scanner);

        int accountId = customer.openAccount(accountTypeMap.get(userInput));

        System.out.println("\n" + Messages.CREATED_ACCOUNT + customer.getName() + " : " + accountId);
    }

    public void setController(NewAccountOperationController controller) {
        this.controller = controller;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }
}
