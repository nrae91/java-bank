package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;

public class AuthenticationService {

    private CustomerService customerService;
    private Customer authenticatedCustomer;

    public boolean authenticate(int id) {

        authenticatedCustomer = customerService.getCustomer(id);
        return authenticatedCustomer != null;

    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer getAuthenticatedCustomer() {
        return authenticatedCustomer;
    }

}
