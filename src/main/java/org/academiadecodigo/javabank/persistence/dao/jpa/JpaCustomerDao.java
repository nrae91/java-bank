package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.TransactionException;
import org.academiadecodigo.javabank.persistence.dao.CustomerDao;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class JpaCustomerDao extends GenericJpaDao<Customer> implements CustomerDao {

    @PersistenceContext
    protected EntityManager em;
    public JpaCustomerDao() {
        super(Customer.class);
    }

    @Transactional
    public List<Integer> getCustomerIds() {
        try {

            return em.createQuery("select id from Customer", Integer.class)
                    .getResultList();

        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }
}
