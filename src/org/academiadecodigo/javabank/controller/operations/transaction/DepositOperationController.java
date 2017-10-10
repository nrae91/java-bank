package org.academiadecodigo.javabank.controller.operations.transaction;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.view.View;

public class DepositOperationController implements Controller {

    private Bank bank;
    private View view;
    private Controller nextController;

    @Override
    public void init() {
        view.show();
        nextController.init();

    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }

    public void onDepositing(int accountId, double amount){
        bank.getAccountManager().deposit(accountId, amount);
    }
}
