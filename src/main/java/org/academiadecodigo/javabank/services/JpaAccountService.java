package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JpaAccountService implements AccountService {

    private EntityManagerFactory emf;

    public JpaAccountService(EntityManagerFactory emf) {
        this.emf=emf;
    }

    public Account add(Account account) {

        EntityManager em = emf.createEntityManager();

        Account mergedAccount = null;

        try {

            em.getTransaction().begin(); // open transaction
            mergedAccount = em.merge(account);
            em.getTransaction().commit(); // close transaction

        } catch (RollbackException ex) {

            em.getTransaction().rollback(); // something went wrong, make sure db is consistent

        } finally {
            if (em != null) {
                em.close();
            }
        }

        return mergedAccount;
    }

    public void deposit(int id, double amount) {

        //EntityManager em = emf.createEntityManager();

        //Account account = em.createQuery("from Account account where account.id = id", Account.class)
                //.getSingleResult();

        Account account = getAccount(id);

        account.credit(amount);
        add(account);
    }

    public void withdraw(int id, double amount) {

        //EntityManager em = emf.createEntityManager();

        //Account account = em.createQuery("from Account account where account.id = id", Account.class)
                //.getSingleResult();

        Account account = getAccount(id);

        account.debit(amount);
        add(account);

    }

    public void transfer(int srcId, int dstId, double amount) {

        EntityManager em = emf.createEntityManager();

        Account srcAccount = getAccount(srcId);
        Account dstAccount = getAccount(dstId);

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            srcAccount.debit(amount);
            dstAccount.credit(amount);
        }

        add(srcAccount);
        add(dstAccount);
    }

    public Account getAccount(int accountId) {

        EntityManager em = emf.createEntityManager();

        try {

            // fetch a new customer by using its id
            return em.find(Account.class, accountId);

        } finally {

            // make sure we close the database connection
            if (em != null) {
                em.close();
            }
        }
    }
}
