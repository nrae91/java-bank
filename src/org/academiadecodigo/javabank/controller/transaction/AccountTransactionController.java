package org.academiadecodigo.javabank.controller.transaction;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.services.CustomerService;

import java.util.Set;

public interface AccountTransactionController extends Controller {

    void submitTransaction(int accountId, double amount);

    Set<Integer> getActiveCostumerAccountIds();
}
