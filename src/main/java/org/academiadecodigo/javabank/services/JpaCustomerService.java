package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.RollbackException;
import java.util.*;

public class JpaCustomerService implements CustomerService {

    private EntityManagerFactory emf;

    public JpaCustomerService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void add(Customer customer) {

        if (customer.getId() == null) {
            customer.setId(getNextId());
        }

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin(); // open transaction
            em.merge(customer);
            em.getTransaction().commit(); // close transaction

        } catch (RollbackException ex) {

            em.getTransaction().rollback(); // something went wrong, make sure db is consistent

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Customer findById(Integer id) {
        // open a new connection to the database
        EntityManager em = emf.createEntityManager();

        try {

            // fetch a new customer by using its id
            return em.find(Customer.class, id);

        } finally {

            // make sure we close the database connection
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Customer> findAll() {

        EntityManager em = emf.createEntityManager();

        try {

            // fetch a list of customers with a given name
            return em.createQuery("from Customer", Customer.class)
                    .getResultList();

        } finally {

            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Set<Integer> getCustomerIds() {

         EntityManager em = emf.createEntityManager();

        try {

            // fetch a list of customers with a given name
            List<Integer> ids = em.createQuery("from Customer.id", Integer.class)
                  .getResultList();

            new Set<Integer> IdSet = new HashSet<>(ids);

        } finally {

            if (em != null) {
                em.close();
            }
        }

    }

    @Override
    public double getBalance(int customerId) {

        List<Account> accounts = customerMap.get(customerId).getAccounts();

        double balance = 0;
        for (Account account : accounts) {
            balance += account.getBalance();
        }

        return balance;

    }

    @Override
    public Set<Integer> getCustomerAccountNumbers(Integer id) {

        Set<Integer> accountIds = new HashSet<>();
        List<Account> accountList = customerMap.get(id).getAccounts();

        for (Account account : accountList) {
            accountIds.add(account.getId());
        }

        return accountIds;
    }

    private Integer getNextId() {
        return customerMap.isEmpty() ? 1 : Collections.max(customerMap.keySet()) + 1;
    }
}
