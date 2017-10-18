package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.Model;
import org.academiadecodigo.javabank.persistence.TransactionException;
import org.academiadecodigo.javabank.persistence.dao.Dao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class GenericJpaDao<T extends Model> implements Dao<T> {

    protected JpaSessionManager sm;
    protected Class<T> modelType;

    public GenericJpaDao(JpaSessionManager sm, Class<T> modelType) {
        this.sm = sm;
        this.modelType = modelType;
    }

    @Override
    public List<T> findAll() {

        try {

            EntityManager em = sm.getCurrentSession();

            CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(modelType);
            Root<T> root = criteriaQuery.from(modelType);
            return em.createQuery(criteriaQuery).getResultList();

            // Using JPQL
//            return em.createQuery( "from " + modelType.getSimpleName(), modelType).getResultList();

        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }

    @Override
    public T findById(Integer id) {

        try {

            EntityManager em = sm.getCurrentSession();
            return em.find(modelType, id);

        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }

    @Override
    public T saveOrUpdate(T modelObject) {

        try {

            EntityManager em = sm.getCurrentSession();
            return em.merge(modelObject);

        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }

    @Override
    public void delete(Integer id) {

        try {

            EntityManager em = sm.getCurrentSession();
            em.remove(em.find(modelType, id));

        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }
}
