package org.academiadecodigo.javabank.application;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.javabank.domain.Customer;

public class Transfer implements Action {

    private DisplayAccounts displayAccounts = new DisplayAccounts();

    @Override
    public void execute(Customer customer) {
        displayAccounts.execute(customer);

        Prompt prompt = new Prompt(System.in, System.out);

        IntegerSetInputScanner scanner = new IntegerSetInputScanner(customer.getAccounts().keySet());

        scanner.setMessage("\nType the account ID from which you wish to withdraw: ");

        int srcId = prompt.getUserInput(scanner);

        scanner.setMessage("\nType the account ID in which you wish to deposit: ");

        int dstId = prompt.getUserInput(scanner);

        DoubleInputScanner doubleScanner = new DoubleInputScanner();

        double amount = prompt.getUserInput(doubleScanner);

        customer.getAccountManager().transfer(srcId,dstId,amount);

        displayAccounts.execute(customer);

        //System.console().flush();
    }
}
