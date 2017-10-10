package org.academiadecodigo.javabank.controller.transaction;

import org.academiadecodigo.javabank.controller.AbstractController;
import org.academiadecodigo.javabank.services.AuthenticationService;
import org.academiadecodigo.javabank.services.CustomerService;

import java.util.Set;

public abstract class AbstractAccountTransactionController extends AbstractController implements AccountTransactionController {

    protected AuthenticationService authenticationService;
    protected CustomerService customerService;

    public void setCustomerService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public Set<Integer> getActiveCostumerAccountIds(){
        return authenticationService.getAuthenticatedCustomer().getAccountIds();
    }

}
