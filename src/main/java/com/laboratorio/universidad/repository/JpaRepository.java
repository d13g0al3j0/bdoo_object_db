package com.laboratorio.universidad.repository;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaRepository<T> {
    protected final EntityManager entityManager;
    private final Class<T> entityType;

    public JpaRepository(EntityManager entityManager, Class<T> entityType) {
        this.entityManager = entityManager;
        this.entityType = entityType;
    }

    public Optional<T> findById(Long id) {
        return Optional.ofNullable(entityManager.find(entityType, id));
    }

    public List<T> findAll() {
        return entityManager.createQuery("SELECT e FROM " + entityType.getSimpleName() + " e", entityType)
                .getResultList();
    }

    public T save(T entity) {
        if (entityManager.contains(entity)) {
            return entity;
        }
        entityManager.persist(entity);
        return entity;
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }
}
