package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    List<Customer> list();

    Customer get(Integer id);

    void delete(Integer id);

    Customer save(Customer customer);

    double getBalance(Integer id);

    Set<Integer> getCustomerAccountIds(Integer id);

}
