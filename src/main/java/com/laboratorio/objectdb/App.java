package com.laboratorio.objectdb;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceConfiguration;
import java.math.BigDecimal;
import java.util.List;

public class App {

    public static void main(String[] args) {

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "       LABORATORIO OBJECTDB"
        );

        System.out.println(
                " BASE DE DATOS ORIENTADA A OBJETOS"
        );

        System.out.println(
                "=========================================="
        );

        /*
         * =====================================================
         * 1. CONFIGURACIÓN DE OBJECTDB
         * =====================================================
         *
         * A partir de Jakarta Persistence 3.2 podemos
         * configurar la unidad de persistencia directamente
         * desde Java.
         *
         * Por lo tanto NO necesitamos persistence.xml.
         */

        PersistenceConfiguration config =
                new PersistenceConfiguration("empresaPU")

                        /*
                         * Base de datos ObjectDB embebida.
                         *
                         * La base estará en:
                         *
                         * data/empresa.odb
                         */

                        .property(
                                "jakarta.persistence.jdbc.url",
                                "objectdb:data/empresa.odb"
                        )

                        /*
                         * Registrar las clases persistentes.
                         */

                        .managedClass(Cliente.class)
                        .managedClass(Producto.class)
                        .managedClass(Pedido.class)
                        .managedClass(DetallePedido.class);


        /*
         * =====================================================
         * 2. CREAR ENTITY MANAGER FACTORY
         * =====================================================
         */

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory(config);


        /*
         * =====================================================
         * 3. CREAR ENTITY MANAGER
         * =====================================================
         */

        EntityManager em =
                emf.createEntityManager();


        try {

            /*
             * =================================================
             * 4. CREAR OBJETO CLIENTE
             * =================================================
             */

            Cliente cliente = em.find(Cliente.class, 1);
            Producto producto = em.find(Producto.class, 10);
            Pedido pedido = em.find(Pedido.class, 100);


            System.out.println();

                        boolean necesitaPersistencia = false;
                        if (cliente == null) {
                                cliente = new Cliente(1, "Juan Perez", "juan@gmail.com");
                                necesitaPersistencia = true;
                        }
                        if (producto == null) {
                                producto = new Producto(
                        10,
                        "Teclado",
                        "Teclado mecanico",
                        new BigDecimal("89.99")
                );
                                necesitaPersistencia = true;
                        }
                        if (pedido == null) {
                pedido = new Pedido(100, cliente);
                                necesitaPersistencia = true;
                        }

                        DetallePedido detalle = em.find(DetallePedido.class, 1000);
                        if (detalle == null) {
                                detalle = new DetallePedido(
                                                1000,
                                                pedido,
                                                producto,
                                                2,
                                                producto.getPrecio()
                                );
                                pedido.addDetalle(detalle);
                                necesitaPersistencia = true;
                        }

                        if (necesitaPersistencia) {
                em.getTransaction().begin();
                                if (cliente.getId() == 1 && em.find(Cliente.class, 1) == null) {
                                        em.persist(cliente);
                                }
                                if (producto.getId() == 10 && em.find(Producto.class, 10) == null) {
                                        em.persist(producto);
                                }
                                if (em.find(Pedido.class, 100) == null) {
                                        em.persist(pedido);
                                }
                                if (em.find(DetallePedido.class, 1000) == null) {
                                        em.persist(detalle);
                                }
                em.getTransaction().commit();
                System.out.println("Datos de demostracion almacenados.");
            } else {
                System.out.println("Los datos de demostracion ya existen.");
            }


            System.out.println();

            System.out.println("Cliente encontrado: " + cliente);


            /*
             * =================================================
             * 8. MOSTRAR INFORMACIÓN
             * =================================================
             */

            System.out.println();

            List<Producto> productos = em.createQuery(
                    "SELECT p FROM Producto p ORDER BY p.nombre", Producto.class
            ).getResultList();
            System.out.println("Productos: " + productos);

            List<Pedido> pedidos = em.createQuery(
                    "SELECT p FROM Pedido p JOIN FETCH p.cliente ORDER BY p.id", Pedido.class
            ).getResultList();
            for (Pedido pedidoConsultado : pedidos) {
                System.out.println("Pedido consultado: " + pedidoConsultado);
                                for (DetallePedido detalleConsultado : pedidoConsultado.getDetalles()) {
                                        System.out.println("  Detalle: " + detalleConsultado);
                }
            }

            System.out.println("Base de datos: data/empresa.odb");


            System.out.println();

            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "        LABORATORIO FINALIZADO"
            );

            System.out.println(
                    "=========================================="
            );

        }

        catch (Exception e) {

            /*
             * Si ocurre un error durante la transacción,
             * intentamos realizar rollback.
             */

            if (em.getTransaction().isActive()) {

                em.getTransaction().rollback();
            }

            e.printStackTrace();

        }

        finally {

            /*
             * =================================================
             * 9. CERRAR RECURSOS
             * =================================================
             */

            em.close();

            emf.close();
        }
    }
}