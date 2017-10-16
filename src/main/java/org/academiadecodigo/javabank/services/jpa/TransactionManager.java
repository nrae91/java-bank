package org.academiadecodigo.javabank.services.jpa;

public interface TransactionManager {

    void beginRead();

    void beginWrite();

    void commit();

    void rollback();
}
