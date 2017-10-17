package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.services.CustomerService;
import org.academiadecodigo.javabank.services.dao.JpaGenericDao;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaCustomerService extends JpaGenericDao<Customer> implements CustomerService {

    public JpaCustomerService(SessionManager sm) {
        super(sm, Customer.class);
    }

    @Override
    public double getBalance(Integer id) {

        EntityManager em = sm.getCurrentSession();

        try {

            Customer customer = em.find(Customer.class, id);

            if (customer == null) {
                throw new IllegalArgumentException("Customer does not exists");
            }

            List<Account> accounts = customer.getAccounts();

            double balance = 0;
            for (Account account : accounts) {
                balance += account.getBalance();
            }

            return balance;

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Set<Integer> getCustomerAccountIds(Integer id) {

        EntityManager em = sm.getCurrentSession();

        try {

            Set<Integer> accountIds = new HashSet<>();

            Customer customer = em.find(Customer.class, id);

            if (customer == null) {
                throw new IllegalArgumentException("Customer does not exists");
            }

            List<Account> accounts = customer.getAccounts();

            for (Account account : accounts) {
                accountIds.add(account.getId());
            }

            return accountIds;

        } finally {
            if (em != null) {
                em.close();
            }
        }

    }
}
