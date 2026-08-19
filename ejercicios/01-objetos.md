# Ejercicio 1: Objetos persistentes

## Objetivo

Definir clases Java que puedan convertirse en entidades persistentes.

## Solucion

`Cliente`, `Producto`, `Pedido` y `DetallePedido` son clases Java normales con
la anotacion `@Entity`. Cada una tiene un constructor vacio, necesario para
que JPA pueda crear objetos al leerlos desde ObjectDB, y un atributo marcado
con `@Id` como identificador.

Los constructores con parametros hacen sencilla la creacion de objetos en
`App`. Los metodos `get` permiten leer sus valores y `toString` facilita
comprobar el resultado en consola.

## Comprobacion

La prueba `EntidadesTest` verifica la creacion de clientes y productos, y que
un detalle se agregue correctamente a un pedido.
