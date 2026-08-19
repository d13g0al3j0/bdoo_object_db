package com.laboratorio.universidad.repository;

import com.laboratorio.universidad.entity.Pago;
import jakarta.persistence.EntityManager;

public class PagoRepository extends JpaRepository<Pago> {
    public PagoRepository(EntityManager entityManager) {
        super(entityManager, Pago.class);
    }
}
