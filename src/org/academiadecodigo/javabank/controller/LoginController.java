package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.view.View;

public class LoginController implements Controller {

    private Bank bank;
    private View view;
    private Controller nextController;

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }

    @Override
    public void init() {
        view.show();
    }

    public void onAccessingCustomer(int customerId) {
        bank.setAccessingCostumerId(customerId);
        nextController.init();
    }


}
