package org.academiadecodigo.javabank.persistence.dao;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.dao.JpaGenericDao;
import org.academiadecodigo.javabank.services.jpa.SessionManager;

import java.util.List;

public class JpaCustomerDao extends JpaGenericDao<Customer> implements CustomerDao {

    public JpaCustomerDao(SessionManager sm) {
        super(sm, Customer.class);
    }

}
