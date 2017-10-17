package org.academiadecodigo.javabank.persistence.dao;

import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.services.dao.JpaGenericDao;
import org.academiadecodigo.javabank.services.jpa.SessionManager;

import java.util.List;

public class JpaAccountDao extends JpaGenericDao<Account> implements AccountDao {

    public JpaAccountDao(SessionManager sm) {
        super(sm, Account.class);
    }
}
