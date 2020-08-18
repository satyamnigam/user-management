package com.example.usermanagement.dataaccess;

import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractRepository {
    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Transactional
    public void persist(Object entity) {
        getEntityManager().persist(entity);
    }

    @Transactional
    public void detach(Object entity) {
        getEntityManager().detach(entity);
    }

    @Transactional
    public void flush() {
        getEntityManager().flush();
    }

    @Transactional
    public void clear() {
        getEntityManager().clear();
    }

    @Transactional
    public void remove(Object entity) {
        getEntityManager().remove(entity);
    }

    @Transactional
    public <T> T merge(T entity) {
        return getEntityManager().merge(entity);
    }

    @Transactional
    public <T> void refresh(T entity) {
        getEntityManager().refresh(entity);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

