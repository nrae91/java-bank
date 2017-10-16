package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.persistence.dao.Dao;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AbstractJpaService<T extends AbstractModel> implements Dao<T> {

    protected SessionManager sm;
    private Class<T> modelType;

    public AbstractJpaService(SessionManager sm, Class<T> modelType) {
        this.sm = sm;
        this.modelType = modelType;
    }

    @Override
    public List<T> findAll() {

        EntityManager em = sm.getCurrentSession();

        try {

            CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(modelType);
            Root<T> root = criteriaQuery.from(modelType);
            return em.createQuery(criteriaQuery).getResultList();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public T findById(Integer id) {

        EntityManager em = sm.getCurrentSession();

        try {

            return em.find(modelType, id);

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public T saveOrUpdate(T modelObject) {

        EntityManager em = sm.getCurrentSession();

        try {

            em.getTransaction().begin();
            T savedObject = em.merge(modelObject);
            em.getTransaction().commit();

            return savedObject;

        } catch (RollbackException ex) {

            em.getTransaction().rollback();
            return null;

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void delete(Integer id) {

        EntityManager em = sm.getCurrentSession();

        try {

            em.getTransaction().begin();
            em.remove(em.find(modelType, id));
            em.getTransaction().commit();

        } catch (RollbackException ex) {

            em.getTransaction().rollback();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
