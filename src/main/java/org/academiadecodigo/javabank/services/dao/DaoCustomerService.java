package org.academiadecodigo.javabank.services.dao;

import org.academiadecodigo.javabank.exceptions.TransactionException;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.dao.CustomerDao;
import org.academiadecodigo.javabank.services.CustomerService;
import org.academiadecodigo.javabank.services.jpa.TransactionManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DaoCustomerService implements CustomerService {

    private TransactionManager tm;
    private CustomerDao dao;

    public DaoCustomerService(TransactionManager tm, CustomerDao dao) {
        this.dao = dao;
        this.tm = tm;
    }

    @Override
    public double getBalance(Integer id) {

        double balance = 0;

        try {
            tm.beginRead();

            Customer customer = dao.findById(id);

            if (customer == null) {
                tm.rollback();
                throw new IllegalArgumentException("customer does not exist");
            }

            List<Account> accounts = customer.getAccounts();

            for (Account account : accounts) {
                balance += account.getBalance();
            }

            tm.commit();

        } catch (TransactionException ex) {
            tm.rollback();
        }

        return balance;
    }

    @Override
    public Set<Integer> getCustomerAccountIds(Integer id) {

        Set<Integer> accountIds = new HashSet<>();

        try {

            tm.beginRead();

            Customer customer = dao.findById(id);

            if(customer==null){
                tm.rollback();
                throw new IllegalArgumentException("Customer does not exist");
            }

            List<Account> accounts = customer.getAccounts();

            for (Account account : accounts) {
                accountIds.add(account.getId());
            }

        } catch (TransactionException ex) {
            tm.rollback();
        }

        return accountIds;
    }

    public Customer findById(Integer id) {

        Customer customer = null;

        try {

            tm.beginRead();

            customer = findById(id);

            tm.commit();

        } catch(TransactionException ex) {

            tm.rollback();
        }

        return customer;
    }
}
