package org.academiadecodigo.javabank.controller.operations;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.view.View;

import java.util.HashMap;
import java.util.Map;

public class NewAccountOperationController implements Controller {

    private Bank bank;
    private View view;
    private Controller nextController;

    private Map<Integer,AccountType> accountTypeMap = new HashMap<>();
    private Map<Integer,String> userOptions = new HashMap<>();

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

    public Customer getCustomer(){
        return bank.getAccessingCustomer();
    }
}
