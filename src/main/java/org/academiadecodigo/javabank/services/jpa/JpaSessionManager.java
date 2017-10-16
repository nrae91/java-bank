package org.academiadecodigo.javabank.services.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

// responsible for managing the Session lifecycle
public class JpaSessionManager implements SessionManager {

    private EntityManagerFactory emf; // the persistence unit
    private EntityManager em; // the persistence context

    public JpaSessionManager(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void startSession() {

        if (em == null) {
            em = emf.createEntityManager();
        }
    }

    @Override
    public void stopSession() {

        if (em != null) {
            em.close();
        }

        em = null;
    }

    public EntityManager getCurrentSession() {
        startSession();
        return em;
    }
}
