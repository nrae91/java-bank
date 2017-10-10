package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.controller.MenuController;
import org.academiadecodigo.javabank.controller.managers.AccountManager;
import org.academiadecodigo.javabank.controller.operations.BalanceOperationController;
import org.academiadecodigo.javabank.controller.operations.NewAccountOperationController;
import org.academiadecodigo.javabank.controller.operations.transaction.DepositOperationController;
import org.academiadecodigo.javabank.controller.operations.transaction.WithdrawOperationController;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.view.*;

import java.util.HashMap;
import java.util.Map;

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

        MenuController menuController = new MenuController();
        MenuView menuView = new MenuView();

        BalanceOperationController balanceOperationController = new BalanceOperationController();
        BalanceOperationView balanceOperationView = new BalanceOperationView();
        balanceOperationController.setBank(bank);
        balanceOperationController.setView(balanceOperationView);
        balanceOperationController.setNextController(menuController);
        balanceOperationView.setController(balanceOperationController);

        DepositOperationController depositOperationController = new DepositOperationController();
        DepositOperationView depositOperationView = new DepositOperationView();
        depositOperationController.setBank(bank);
        depositOperationController.setView(depositOperationView);
        depositOperationController.setNextController(menuController);
        depositOperationView.setBank(bank);
        depositOperationView.setPrompt(prompt);
        depositOperationView.setController(depositOperationController);

        WithdrawOperationController withdrawOperationController = new WithdrawOperationController();
        WithdrawOperationView withdrawOperationView = new WithdrawOperationView();

        NewAccountOperationController newAccountOperationController = new NewAccountOperationController();
        NewAccountOperationView newAccountOperationView = new NewAccountOperationView();


        Map<Integer, Controller> map = new HashMap<>();
        map.put(UserOptions.GET_BALANCE.getOption(), balanceOperationController);
        map.put(UserOptions.DEPOSIT.getOption(), depositOperationController);
        map.put(UserOptions.WITHDRAW.getOption(), withdrawOperationController);
        map.put(UserOptions.OPEN_ACCOUNT.getOption(), newAccountOperationController);

        menuController.setControllerMap(map);
        menuController.setView(menuView);
        menuView.setController(menuController);
        menuView.setPrompt(prompt);

        loginController.setBank(bank);
        loginController.setView(loginView);
        loginController.setNextController(menuController);
        loginView.setController(loginController);
        loginView.setBank(bank);
        loginView.setPrompt(prompt);

        loginController.init();



    }
}
