# Ejercicio 3: Consultas JPQL

## Objetivo

Recuperar entidades persistidas mediante consultas orientadas a objetos.

## Solucion

`App` obtiene todos los productos ordenados por nombre:

```java
SELECT p FROM Producto p ORDER BY p.nombre
```

Tambien obtiene los pedidos y carga de forma explicita el cliente relacionado:

```java
SELECT p FROM Pedido p JOIN FETCH p.cliente ORDER BY p.id
```

El resultado es una lista tipada (`List<Producto>` o `List<Pedido>`), evitando
trabajar con filas y columnas como en una consulta SQL tradicional.
