package com.laboratorio.universidad.config;

import com.laboratorio.universidad.resource.EstudianteResource;
import com.laboratorio.universidad.resource.InscripcionResource;
import com.laboratorio.universidad.resource.DashboardResource;
import com.laboratorio.universidad.resource.AcademicResource;
import jakarta.persistence.EntityManager;
import org.glassfish.jersey.server.ResourceConfig;

public class RestApplication extends ResourceConfig {
    public RestApplication(EntityManager entityManager) {
        register(new EstudianteResource(entityManager));
        register(new InscripcionResource(entityManager));
        register(new DashboardResource(entityManager));
        register(new AcademicResource(entityManager));
    }
}
