package com.laboratorio.objectdb;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class EntidadesTest {

    @Test
    void clienteDebeCrearYExponerDatos() {
        Cliente cliente = new Cliente(1, "Ana Gomez", "ana@email.com");

        assertEquals(1, cliente.getId());
        assertEquals("Ana Gomez", cliente.getNombre());
        assertEquals("ana@email.com", cliente.getCorreo());
        assertTrue(cliente.toString().contains("Ana Gomez"));
    }

    @Test
    void productoDebeCrearYGuardarPrecio() {
        Producto producto = new Producto(10, "Teclado", "Teclado mecánico", new BigDecimal("89.99"));

        assertEquals(10, producto.getId());
        assertEquals("Teclado", producto.getNombre());
        assertEquals(new BigDecimal("89.99"), producto.getPrecio());
    }

    @Test
    void pedidoYDetallePedidoDebenRelacionarse() {
        Cliente cliente = new Cliente(1, "Ana Gomez", "ana@email.com");
        Producto producto = new Producto(10, "Teclado", "Teclado mecánico", new BigDecimal("89.99"));
        Pedido pedido = new Pedido(100, cliente);
        DetallePedido detalle = new DetallePedido(1, pedido, producto, 2, new BigDecimal("89.99"));

        pedido.addDetalle(detalle);

        assertEquals(100, pedido.getId());
        assertEquals(cliente, pedido.getCliente());
        assertEquals(1, pedido.getDetalles().size());
        assertEquals(2, detalle.getCantidad());
    }
}
