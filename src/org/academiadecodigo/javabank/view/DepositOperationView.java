package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.javabank.controller.operations.transaction.DepositOperationController;
import org.academiadecodigo.javabank.model.Bank;

public class DepositOperationView implements View {

    private Bank bank;
    private DepositOperationController controller;
    private Prompt prompt;

    @Override
    public void show() {

        if (!hasAccounts()) {
            System.out.println("\n" + Messages.ERROR_NO_ACCOUNT);
            return;
        }

        controller.onDepositing(scanAccount(),scanAmount());
    }

    private boolean hasAccounts() {
        return bank.getAccessingCustomer().getAccountIds().size() > 0;
    }

    private int scanAccount() {

        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getAccessingCustomer().getAccountIds());
        scanner.setMessage(Messages.CHOOSE_ACCOUNT);
        scanner.setError(Messages.ERROR_INVALID_ACCOUNT);

        return prompt.getUserInput(scanner);

    }

    private double scanAmount() {
        DoubleInputScanner scanner = new DoubleInputScanner();
        scanner.setMessage(Messages.CHOOSE_AMOUNT);
        scanner.setError(Messages.ERROR_INVALID_AMOUNT);

        return prompt.getUserInput(scanner);
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setController(DepositOperationController controller) {
        this.controller = controller;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }
}
