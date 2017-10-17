package org.academiadecodigo.javabank.services.dao;

import org.academiadecodigo.javabank.exceptions.TransactionException;
import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.persistence.dao.Dao;
import org.academiadecodigo.javabank.services.jpa.SessionManager;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class JpaGenericDao<T extends AbstractModel> implements Dao<T> {

    protected SessionManager sm;
    private Class<T> modelType;

    public JpaGenericDao(SessionManager sm, Class<T> modelType) {
        this.sm = sm;
        this.modelType = modelType;
    }

    @Override
    public List<T> findAll() {

        EntityManager em = sm.getCurrentSession();

        CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(modelType);
        Root<T> root = criteriaQuery.from(modelType);
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public T findById(Integer id) {

        try {

            EntityManager em = sm.getCurrentSession();

            return em.find(modelType, id);

        } catch (HibernateException ex) {
            throw new TransactionException();
        }

    }

    @Override
    public T saveOrUpdate(T modelObject) {

        try {

            EntityManager em = sm.getCurrentSession();

            em.getTransaction().begin();
            T savedObject = em.merge(modelObject);
            em.getTransaction().commit();

            return savedObject;

        } catch (HibernateException ex) {
            throw new TransactionException();
        }
    }

    @Override
    public void delete(Integer id) {

        try {

            EntityManager em = sm.getCurrentSession();

            em.getTransaction().begin();
            em.remove(em.find(modelType, id));
            em.getTransaction().commit();

        } catch (HibernateException ex) {
            throw new TransactionException();
        }
   }
}
