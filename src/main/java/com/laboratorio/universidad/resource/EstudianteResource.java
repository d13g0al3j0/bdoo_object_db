package com.laboratorio.universidad.resource;

import com.laboratorio.universidad.dto.EstudianteDTO;
import com.laboratorio.universidad.entity.Estudiante;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import jakarta.persistence.EntityManager;
import java.util.List;

@Path("/estudiantes")
@Produces(MediaType.APPLICATION_JSON)
public class EstudianteResource {
    private final EntityManager entityManager;

    public EstudianteResource(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GET
    public List<EstudianteDTO> listar() {
        return entityManager.createQuery("SELECT e FROM Estudiante e ORDER BY e.apellido, e.nombre", Estudiante.class)
                .getResultList().stream().map(EstudianteDTO::new).toList();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Long id) {
        Estudiante estudiante = entityManager.find(Estudiante.class, id);
        return estudiante == null
                ? Response.status(Response.Status.NOT_FOUND).build()
                : Response.ok(new EstudianteDTO(estudiante)).build();
    }
}
