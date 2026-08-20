package com.laboratorio.universidad.resource;

import com.laboratorio.universidad.service.DashboardService;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/dashboard")
@Produces(MediaType.APPLICATION_JSON)
public class DashboardResource {
    private final DashboardService service;

    public DashboardResource(EntityManager entityManager) {
        this.service = new DashboardService(entityManager);
    }

    @GET
    public Object obtener() {
        return service.obtener();
    }
}
