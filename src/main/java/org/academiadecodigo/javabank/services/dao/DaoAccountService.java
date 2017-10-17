package org.academiadecodigo.javabank.services.dao;

import org.academiadecodigo.javabank.exceptions.TransactionException;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.dao.AccountDao;
import org.academiadecodigo.javabank.services.AccountService;
import org.academiadecodigo.javabank.services.jpa.SessionManager;
import org.academiadecodigo.javabank.services.jpa.TransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

public class DaoAccountService  implements AccountService {

    private TransactionManager tm;
    private AccountDao dao;

    public DaoAccountService(TransactionManager tm, AccountDao dao) {
        this.tm = tm;
        this.dao = dao;
    }

    @Override
    public void deposit(Integer id, double amount) {

        try {

            tm.beginWrite();

            Account account = dao.findById(id);

            if (account == null) {
                tm.rollback();
                throw new IllegalArgumentException("invalid account id");
            }

            account.credit(amount);

            dao.saveOrUpdate(account);

            tm.commit();

        } catch (TransactionException ex) {
            tm.rollback();
        }
    }

    @Override
    public void withdraw(Integer id, double amount) {

        try {

            tm.beginWrite();

            Account account = dao.findById(id);

            if (account == null) {
                tm.rollback();
                throw new IllegalArgumentException("invalid account");
            }

            account.debit(amount);

            dao.saveOrUpdate(account);

            tm.commit();

        } catch (TransactionException ex) {

            tm.rollback();

        }
    }

    @Override
    public void transfer(Integer srcId, Integer dstId, double amount) {

        try {

            tm.beginWrite();

            Account srcAccount = dao.findById(srcId);
            Account dstAccount = dao.findById(dstId);

            if (srcAccount == null || dstAccount == null) {
                tm.rollback();
                throw new IllegalArgumentException("invalid account id");
            }

            // make sure transaction can be performed
            if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
                srcAccount.debit(amount);
                dstAccount.credit(amount);
            }

            dao.saveOrUpdate(srcAccount);
            dao.saveOrUpdate(dstAccount);

            tm.commit();

        } catch (RollbackException ex) {

            tm.rollback();

        }
    }

    @Override
    public Account saveOrUpdate(Account account) {

        try {

            tm.beginWrite();

            dao.saveOrUpdate(account);

            tm.commit();

        } catch (TransactionException ex) {

            tm.rollback();
        }

        return account;
    }
}
