package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    Customer findById(Integer id);

    double getBalance(Integer id);

    Set<Integer> getCustomerAccountIds(Integer id);

    List<Customer> getCustomerList();

    void deleteCustomer(Integer id);

}
