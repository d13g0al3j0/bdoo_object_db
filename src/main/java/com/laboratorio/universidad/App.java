package com.laboratorio.universidad;

import com.laboratorio.universidad.config.PersistenceConfig;
import com.laboratorio.universidad.config.RestApplication;
import com.laboratorio.universidad.config.DemoDataInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.net.URI;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws Exception {
        EntityManagerFactory entityManagerFactory = PersistenceConfig.createEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        DemoDataInitializer.initialize(entityManager);
        URI baseUri = URI.create("http://0.0.0.0:8080/api/");
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, new RestApplication(entityManager), false);
        server.getServerConfiguration().addHttpHandler(new StaticHttpHandler("frontend"), "/");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            entityManager.close();
            entityManagerFactory.close();
            server.shutdownNow();
        }));
        server.start();
        System.out.println("Sistema Universitario iniciado en http://localhost:8080");
        Thread.currentThread().join();
    }
}
