package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import java.util.*;

public class JpaCustomerService implements CustomerService {

    private EntityManagerFactory emf;

    public JpaCustomerService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void add(Customer customer) {

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
            List<Integer> ids = em.createQuery("select c.id from Customer c", Integer.class)
                  .getResultList();

            return new HashSet<>();

        } finally {

            if (em != null) {
                em.close();
            }
        }

    }

    @Override
    public double getBalance(int customerId) {

        EntityManager em = emf.createEntityManager();

        Customer customer = findById(customerId);

        List<Account> accounts = customer.getAccounts();

        double balance = 0;
        for (Account account : accounts) {
            balance += account.getBalance();
        }

        return balance;

    }

    @Override
    public Set<Integer> getCustomerAccountNumbers(Integer id) {

        Customer customer = findById(id);

        Set<Integer> accountIds = new HashSet<>();
        List<Account> accountList = customer.getAccounts();

        for (Account account : accountList) {
            accountIds.add(account.getId());
        }

        return accountIds;
    }

}
