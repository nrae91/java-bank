package org.academiadecodigo.javabank.application;

import org.academiadecodigo.javabank.domain.Customer;

public interface Action {
    void execute(Customer customer);
}
