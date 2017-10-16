package org.academiadecodigo.javabank.persistence.dao;

import org.academiadecodigo.javabank.model.AbstractModel;

import java.util.List;

public class GenericDao implements Dao {

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public AbstractModel findById(Integer id) {
        return null;
    }

    @Override
    public AbstractModel saveOrUpdate(AbstractModel modelObject) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
