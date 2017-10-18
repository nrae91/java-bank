package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.Model;
import org.academiadecodigo.javabank.persistence.TransactionException;
import org.academiadecodigo.javabank.persistence.dao.Dao;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class GenericJpaDao<T extends Model> implements Dao<T> {

    @PersistenceContext
    protected EntityManager em;
    protected Class<T> modelType;

    public GenericJpaDao(Class<T> modelType) {
        this.modelType = modelType;
    }

    @Transactional
    @Override
    public List<T> findAll() {

        try {

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

            return em.find(modelType, id);

        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }

    @Override
    public T saveOrUpdate(T modelObject) {

        try {

            return em.merge(modelObject);

        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }

    @Override
    public void delete(Integer id) {

        try {

            em.remove(em.find(modelType, id));

        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
