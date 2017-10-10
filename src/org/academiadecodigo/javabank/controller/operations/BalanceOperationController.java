package org.academiadecodigo.javabank.controller.operations;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.view.View;

public class BalanceOperationController implements Controller {

    private View view;
    private Controller nextController;
    private Bank bank;

    @Override
    public void init() {
        view.show();
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }

    public void onGettingBalance(){
        nextController.init();
    }

    public Customer getCustomer() {
        return bank.getAccessingCustomer();
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

}
