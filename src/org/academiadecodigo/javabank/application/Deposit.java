package org.academiadecodigo.javabank.application;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.javabank.domain.Customer;

public class Deposit implements Action {

    private DisplayAccounts displayAccounts = new DisplayAccounts();

    @Override
    public void execute(Customer customer) {
        displayAccounts.execute(customer);

        Prompt prompt = new Prompt(System.in, System.out);

        IntegerSetInputScanner scanner = new IntegerSetInputScanner(customer.getAccounts().keySet());

        scanner.setMessage("\nType the account ID in which you wish to deposit: ");

        int dstId = prompt.getUserInput(scanner);

        DoubleInputScanner doubleScanner = new DoubleInputScanner();

        double amount = prompt.getUserInput(doubleScanner);

        customer.getAccountManager().deposit(dstId,amount);

        displayAccounts.execute(customer);
    }
}
