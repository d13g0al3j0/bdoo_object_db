# Ejercicio 5: Evaluacion final

## Criterios de comprobacion

1. El proyecto compila con Java 17 dentro de Docker.
2. ObjectDB crea o abre `data/empresa.odb` sin usar un servidor externo.
3. Se almacenan `Cliente`, `Producto`, `Pedido` y `DetallePedido`.
4. Las relaciones entre pedido, cliente y producto se conservan.
5. Las consultas JPQL muestran los objetos guardados.
6. Una segunda ejecucion no duplica los datos de demostracion.
7. Las pruebas unitarias pasan con `mvn test`.

## Evidencia esperada

En la salida deben aparecer el cliente encontrado, la lista de productos, el
pedido consultado y su detalle. Tambien debe existir el archivo
`data/empresa.odb` en el equipo anfitrion.
