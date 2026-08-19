package com.laboratorio.universidad.service;

import jakarta.persistence.EntityManager;

import java.util.function.Supplier;

public final class TransactionExecutor {
    private TransactionExecutor() {
    }

    public static <T> T execute(EntityManager entityManager, Supplier<T> operation) {
        entityManager.getTransaction().begin();
        try {
            T result = operation.get();
            entityManager.getTransaction().commit();
            return result;
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw exception;
        }
    }
}
