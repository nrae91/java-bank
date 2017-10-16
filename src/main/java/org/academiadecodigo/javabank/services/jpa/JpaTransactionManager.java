package org.academiadecodigo.javabank.services.jpa;

public class JpaTransactionManager implements TransactionManager {

    private SessionManager sm;

    public JpaTransactionManager(SessionManager sm) {
        this.sm = sm;
    }

    public void beginRead() {
        sm.startSession();
    }

    public void beginWrite() {
        sm.getCurrentSession().getTransaction().begin();
    }

    public void commit() {

        if (sm.getCurrentSession().getTransaction().isActive()) {
            sm.getCurrentSession().getTransaction().commit();
        }

        sm.stopSession();
    }

    public void rollback() {

        if (sm.getCurrentSession().getTransaction().isActive()) {
            sm.getCurrentSession().getTransaction().rollback();
        }

        sm.stopSession();
    }
}
