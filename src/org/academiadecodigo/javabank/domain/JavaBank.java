package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.MainMenu;
import org.academiadecodigo.javabank.operations.Action;
import org.academiadecodigo.javabank.managers.AccountManager;
import sun.applet.Main;

public class JavaBank {

    private Bank bank;
    private MainMenu mainMenu;

    public static void main(String[] args) {
        JavaBank javaBank = new JavaBank();
        MainMenu mainMenu = new MainMenu();
        javaBank.boot();
        Customer customer = javaBank.getCustomer();
        Action action = mainMenu.open(customer);
        action.execute();
    }

    private void boot() {
        this.bank = new Bank(new AccountManager());
    }

    private Customer getCustomer(){

        Prompt prompt = new Prompt(System.in, System.out);

        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getCustomerIds());

        return this.bank.getCustomer(prompt.getUserInput(scanner));
    }


    public Bank getBank() {
        return bank;
    }
}
