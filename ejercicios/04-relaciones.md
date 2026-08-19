# Ejercicio 4: Relaciones entre entidades

## Objetivo

Representar un pedido con su cliente, sus detalles y los productos incluidos.

## Solucion

- `Pedido.cliente` usa `@ManyToOne`: varios pedidos pueden pertenecer a un cliente.
- `Pedido.detalles` usa `@OneToMany(mappedBy = "pedido")`.
- `DetallePedido.pedido` usa `@ManyToOne` y es el lado propietario de esa relacion.
- `DetallePedido.producto` usa `@ManyToOne`: un producto puede aparecer en muchos detalles.

El metodo `addDetalle` sincroniza la asociacion en memoria. En `App`, primero
se crea el pedido, despues su detalle y finalmente se persisten ambos dentro
de la misma transaccion.

El recorrido de `pedido.getDetalles()` demuestra que las relaciones se pueden
navegar como objetos Java.
