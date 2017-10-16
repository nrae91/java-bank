package org.academiadecodigo.javabank.services.jpa;

import javax.persistence.EntityManager;

public interface SessionManager {

    void startSession();

    void stopSession();

    EntityManager getCurrentSession();

}
