package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.model.Bank;

public class LoginView implements View {

    private Bank bank;
    private Prompt prompt;
    private LoginController controller;

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    public void setController(LoginController controller) {
        this.controller = controller;
    }

    @Override
    public void show() {
        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getCustomerIds());
        scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        scanner.setError(Messages.ERROR_INVALID_CUSTOMER);
        controller.onAccessingCustomer(prompt.getUserInput(scanner));
    }
}
