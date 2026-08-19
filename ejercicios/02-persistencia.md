# Ejercicio 2: Persistencia con ObjectDB

## Objetivo

Guardar objetos Java en una base de datos embebida usando JPA.

## Solucion

`App` crea una `PersistenceConfiguration` llamada `empresaPU`, registra las
entidades y configura la URL `objectdb:data/empresa.odb`. Luego obtiene un
`EntityManager` para trabajar con la base.

Las operaciones de escritura se ejecutan entre `begin()` y `commit()`:

```java
em.getTransaction().begin();
em.persist(cliente);
em.persist(producto);
em.persist(pedido);
em.persist(detalle);
em.getTransaction().commit();
```

Si una operacion falla, el bloque `catch` hace `rollback` cuando la
transaccion sigue activa. El bloque `finally` libera los recursos.

## Resultado

El archivo `data/empresa.odb` conserva los objetos. La aplicacion consulta los
identificadores antes de insertar, por lo que se puede ejecutar varias veces.
