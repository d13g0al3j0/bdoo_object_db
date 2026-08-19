# Laboratorio ObjectDB

Proyecto educativo en Java que demuestra como convertir objetos normales en
entidades persistentes usando Jakarta Persistence (JPA) y ObjectDB. El ejemplo
representa una pequena empresa que tiene clientes, productos, pedidos y
detalles de pedido.

La aplicacion usa ObjectDB en modo embebido. No existe un servidor de base de
datos separado: el almacenamiento se realiza en el archivo
`data/empresa.odb`.
# Laboratorio ObjectDB

Laboratorio práctico de Bases de Datos Orientadas a Objetos utilizando
Java, ObjectDB, Maven y Docker.

## Requisitos

- Git
- Docker
- Docker Compose

## Ejecutar

git clone https://github.com/d13g0al3j0/bdoo_object_db.git

cd bdoo_object_db

docker compose up --build

## Detener

docker compose down

## Tecnologias

- Java 17 como version objetivo del proyecto.
- Maven para compilar, resolver dependencias y ejecutar la aplicacion.
- Jakarta Persistence 3.2, la API estandar de persistencia.
- ObjectDB 2.9.5, implementacion de JPA y base de datos orientada a objetos.
- JUnit 5.11 para las pruebas unitarias.
- Docker Compose para ejecutar todo sin instalar Maven localmente.

## Estructura del proyecto

```text
.
|-- Dockerfile                 Imagen de ejecucion
|-- docker-compose.yml         Servicio y volumen de datos
|-- pom.xml                    Dependencias y configuracion Maven
|-- data/                      Archivo de base de datos ObjectDB
|-- ejercicios/                Explicacion de los ejercicios
|-- src/main/java/...          Codigo de la aplicacion y entidades
`-- src/test/java/...          Pruebas unitarias
```

## Como ejecutar

### Opcion recomendada: Docker

Desde la raiz del proyecto:

```bash
docker compose up --build
```

Este comando construye la imagen, compila el proyecto y ejecuta
`com.laboratorio.objectdb.App`. Para detener el servicio:

```bash
docker compose down
```

El archivo `docker-compose.yml` monta la carpeta local `./data` en
`/app/data`. Por eso el archivo ObjectDB permanece en el equipo aunque el
contenedor se elimine.

Para ejecutar la aplicacion una sola vez y eliminar el contenedor al terminar:

```bash
docker compose run --build --rm objectdb-lab
```

### Ejecutar las pruebas en Docker

```bash
docker compose run --build --rm objectdb-lab mvn test
```

### Ejecutar con Maven local

Si Java 17 y Maven estan instalados en el equipo:

```bash
mvn clean compile
mvn exec:java
mvn test
```

## Flujo completo de `App`

La clase `App` realiza estas operaciones en orden:

1. Muestra el titulo del laboratorio en la consola.
2. Crea una `PersistenceConfiguration` llamada `empresaPU`.
3. Configura la URL `objectdb:data/empresa.odb`.
4. Registra `Cliente`, `Producto`, `Pedido` y `DetallePedido` como clases
	administradas por JPA.
5. Crea un `EntityManagerFactory`, que representa la conexion y configuracion
	de la unidad de persistencia.
6. Crea un `EntityManager`, que permite buscar, guardar y consultar entidades.
7. Busca por identificador los objetos de demostracion:
	- Cliente `id = 1`.
	- Producto `id = 10`.
	- Pedido `id = 100`.
	- Detalle `id = 1000`.
8. Crea unicamente los objetos que no existan. Los datos creados son:
	- Cliente: Juan Perez, `juan@gmail.com`.
	- Producto: Teclado mecanico, precio `89.99`.
	- Pedido: `100`, asociado al cliente.
	- Detalle: cantidad `2`, precio unitario `89.99`, asociado al producto.
9. Si falta algun objeto, inicia una transaccion con `begin()`, usa `persist()`
	para guardar cada entidad faltante y confirma con `commit()`.
10. Si todos los objetos ya existen, informa que los datos de demostracion ya
	 estan almacenados y no los inserta de nuevo.
11. Busca y muestra todos los productos ordenados por nombre.
12. Busca y muestra todos los pedidos ordenados por identificador. Tambien
	 carga el cliente de cada pedido y recorre sus detalles.
13. Si ocurre una excepcion, hace `rollback()` cuando hay una transaccion activa
	 y muestra el error.
14. En todos los casos cierra el `EntityManager` y el
	 `EntityManagerFactory` en el bloque `finally`.

La configuracion se realiza directamente desde Java mediante
`PersistenceConfiguration`; por eso este proyecto no necesita un archivo
`persistence.xml`.

## Modelo de entidades

### `Cliente`

La clase representa a un cliente y tiene:

- `id`: identificador primario marcado con `@Id`.
- `nombre`: nombre completo.
- `correo`: correo electronico.

Tiene un constructor vacio para JPA, un constructor con datos, getters y un
`toString()` para mostrar el objeto.

### `Producto`

La clase representa un producto y tiene:

- `id`: identificador primario.
- `nombre`: nombre del producto.
- `descripcion`: detalle descriptivo.
- `precio`: valor monetario representado con `BigDecimal`.

Ademas de getters, tiene setters para modificar nombre, descripcion y precio.

### `Pedido`

La clase representa una compra y tiene:

- `id`: identificador del pedido.
- `cliente`: cliente que realizo el pedido.
- `detalles`: lista de productos incluidos en el pedido.

La anotacion `@ManyToOne` en `cliente` significa que varios pedidos pueden
pertenecer al mismo cliente. La anotacion `@OneToMany(mappedBy = "pedido")` en
`detalles` significa que un pedido puede tener muchos detalles y que el campo
`pedido` de `DetallePedido` controla esa relacion.

### `DetallePedido`

La clase representa una linea de un pedido y tiene:

- `id`: identificador del detalle.
- `pedido`: pedido al que pertenece.
- `producto`: producto incluido.
- `cantidad`: numero de unidades.
- `precioUnitario`: precio del producto en esa linea.

Sus campos `pedido` y `producto` usan `@ManyToOne`, porque muchos detalles
pueden referirse al mismo pedido o al mismo producto.

## Como se mantiene la relacion del pedido

El metodo `Pedido.addDetalle(detalle)` hace dos cosas:

```java
detalles.add(detalle);
detalle.setPedido(this);
```

La primera linea agrega el detalle a la coleccion del pedido. La segunda asigna
el pedido dentro del detalle. Asi ambos lados de la relacion quedan
sincronizados en memoria antes de guardar los objetos.

## Consultas JPQL

La aplicacion ejecuta esta consulta para recuperar productos:

```java
SELECT p FROM Producto p ORDER BY p.nombre
```

No consulta una tabla directamente: consulta la entidad `Producto` y devuelve
objetos `Producto` ordenados por su nombre.

Para los pedidos ejecuta:

```java
SELECT p FROM Pedido p JOIN FETCH p.cliente ORDER BY p.id
```

`JOIN FETCH p.cliente` solicita que el cliente relacionado se cargue junto con
cada pedido. Despues, el programa recorre `pedido.getDetalles()` y muestra el
producto, la cantidad y el precio unitario de cada detalle.

## Transacciones y manejo de errores

Las operaciones `persist()` se ejecutan dentro de una transaccion. `commit()`
confirma los cambios en `empresa.odb`. Si algo falla antes del commit,
`rollback()` cancela la transaccion activa para evitar guardar cambios
incompletos.

El `finally` cierra los recursos aunque la operacion termine con error. Esto
evita dejar abierta la base de datos o recursos de JPA.

## Resultado esperado

En la primera ejecucion debe aparecer una salida similar a:

```text
Datos de demostracion almacenados.
Cliente encontrado: Cliente{id=1, nombre='Juan Perez', correo='juan@gmail.com'}
Productos: [Producto{...}]
Pedido consultado: Pedido{... detalles=1}
  Detalle: DetallePedido{... cantidad=2, precioUnitario=89.99}
```

En ejecuciones posteriores debe aparecer:

```text
Los datos de demostracion ya existen.
```

Esto demuestra que los objetos fueron persistidos y luego recuperados desde
ObjectDB, no creados nuevamente en cada ejecucion.

## Ejercicios documentados

- [01 Objetos](ejercicios/01-objetos.md): clases, constructores y entidades.
- [02 Persistencia](ejercicios/02-persistencia.md): transacciones y ObjectDB.
- [03 Consultas](ejercicios/03-consultas.md): consultas JPQL.
- [04 Relaciones](ejercicios/04-relaciones.md): `ManyToOne` y `OneToMany`.
- [05 Evaluacion](ejercicios/05-evaluacion.md): criterios de comprobacion.

## Reiniciar la base de datos

Para comenzar de nuevo, detiene los contenedores y elimina el archivo local:

```bash
docker compose down
rm -f data/empresa.odb
docker compose up --build
```

Al eliminar el archivo se pierden los datos persistidos y la siguiente
ejecucion vuelve a crear los datos de demostracion.
