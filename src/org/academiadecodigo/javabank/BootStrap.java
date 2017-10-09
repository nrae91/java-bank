package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.controller.managers.AccountManager;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.view.LoginView;

public class BootStrap {

    public void boot(){

        Bank bank = new Bank();
        AccountManager accountManager = new AccountManager();
        bank.setAccountManager(accountManager);

        // Costumer data base (take out in due time)
        Customer c1 = new Customer(1,"Rui");
        Customer c2 = new Customer(2,"Sergio");
        Customer c3 = new Customer(3,"Bruno");
        bank.addCustomer(c1);
        bank.addCustomer(c2);
        bank.addCustomer(c3);

        Prompt prompt = new Prompt(System.in, System.out);

        LoginController loginController = new LoginController();
        LoginView loginView = new LoginView();
        loginController.setBank(bank);
        loginController.setView(loginView);
        loginView.setController(loginController);
        loginView.setBank(bank);
        loginView.setPrompt(prompt);

        loginController.init();

    }
}
