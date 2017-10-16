package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.dao.Dao;

import java.util.Set;

public interface CustomerService extends Dao<Customer> {

    double getBalance(Integer id);

    Set<Integer> getCustomerAccountIds(Integer id);

}
