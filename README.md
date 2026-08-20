# Sistema Universitario Orientado a Objetos

Laboratorio educativo de Bases de Datos Orientadas a Objetos con Java 17, JPA, ObjectDB, REST y una interfaz web. ObjectDB sigue siendo el motor de persistencia principal y almacena los objetos en `data/universidad.odb`.

## Estado del proyecto

El proyecto se encuentra en una **fase funcional inicial avanzada**, aproximadamente en las fases 14 y 16. Ya existe integración entre modelo JPA, ObjectDB, servicios, API REST, frontend y Docker.

### Fases realizadas

- **Fase 1:** análisis del laboratorio anterior, sus archivos, dependencias, Docker y modelo `Cliente`/`Producto`/`Pedido`.
- **Fase 2:** diseño del modelo universitario, herencia `Persona`, relaciones, composición, agregación y entidad intermedia `PlanMateria`.
- **Fase 3:** actualización de Maven con Java 17, Jakarta Persistence, ObjectDB, Jersey, Grizzly, JSON-B, Jakarta Validation y JUnit 5.
- **Fase 4:** creación de las entidades universitarias, enums, herencia `JOINED`, relaciones académicas, pagos, usuarios, roles y auditoría.
- **Fase 5:** configuración programática de `universidadPU` y conexión con `objectdb:data/universidad.odb`.
- **Fase 6:** repositorio genérico JPA y repositorios específicos para estudiantes, docentes, carreras, materias, paralelos, periodos, inscripciones, calificaciones, pagos y auditoría.
- **Fase 7:** excepciones de negocio y capa de servicios inicial.
- **Fase 8:** executor transaccional y `InscripcionService` con validación de estado, periodo, duplicados, cupos y prerrequisitos, además de `commit` y `rollback`.
- **Fases 9 y 10:** API REST inicial, DTOs, respuestas JSON y endpoint transaccional de inscripción.
- **Fases 12, 13 y 16:** frontend HTML/CSS/JavaScript, menú hamburguesa, CRUD web de estudiantes, dashboard visual, Fetch API y Docker con puerto `8080` y volumen persistente.

### Funcionalidades comprobadas

- `docker compose build` construye la imagen con Java 17.
- Maven compila el proyecto dentro de Docker.
- Las pruebas unitarias del modelo universitario pasan correctamente.
- `GET /api/estudiantes` responde JSON.
- El CRUD de estudiantes funciona con `POST`, `PUT` y `DELETE`.
- Los CRUD iniciales de docentes, carreras y materias funcionan mediante REST.
- Los catálogos de planes, periodos, aulas, paralelos y horarios tienen endpoints iniciales.
- `GET /api/dashboard` responde con métricas JSON.
- La página `http://localhost:8080` responde HTML.
- El menú hamburguesa muestra los módulos existentes.
- ObjectDB crea y conserva `data/universidad.odb`.
- La aplicación utiliza configuración programática y no `persistence.xml`.

## Trabajo pendiente

### Prioridad 1: inscripciones completas

- Crear `GET /api/inscripciones`.
- Crear `GET /api/inscripciones/{id}`.
- Añadir `POST /api/inscripciones` como contrato general.
- Añadir anulación de inscripciones.
- Exponer materias inscritas por estudiante.
- Exponer estudiantes por paralelo.
- Crear historial académico completo.
- Integrar la inscripción transaccional en una pantalla web.
- Mostrar visualmente los casos de `COMMIT` y `ROLLBACK`.

### Prioridad 2: calificaciones y pagos

- CRUD de evaluaciones.
- Registro y actualización de calificaciones.
- Validación de notas entre `0` y `100`.
- Cálculo de promedio y estado aprobado/reprobado.
- Interfaz web para docentes.
- CRUD de pagos y conceptos de pago.
- Anulación de pagos.
- Validación de montos.
- Auditoría de pagos y anulaciones.
- Pantalla web de pagos.

### Prioridad 3: frontend académico

- Formularios para paralelos y horarios.
- Formularios para docentes, carreras y materias.
- Vistas de planes, periodos y aulas.
- Dashboard con gráficos y filtros.
- Estadísticas por periodo, carrera y materia.
- Mensajes visuales uniformes para errores `400`, `404`, `409` y `500`.

### Prioridad 4: API y reglas de negocio

- Crear un `ExceptionMapper` global para respuestas JSON uniformes.
- Completar actualización y eliminación de planes, periodos, aulas, paralelos y horarios.
- Validar duplicados por CI, código y correo.
- Validar capacidad de aula contra cupos del paralelo.
- Completar conflictos de docente y aula.
- Añadir CORS explícito.
- Añadir OpenAPI/Swagger.

### Prioridad 5: consultas JPQL educativas

Implementar y documentar consultas con:

- `JOIN` y `JOIN FETCH`.
- `LEFT JOIN`.
- `DISTINCT` y `ORDER BY`.
- `GROUP BY`.
- `COUNT`, `AVG` y `SUM`.
- Materias sin estudiantes.
- Paralelos sin cupos.
- Pagos pendientes.
- Estudiantes por carrera.
- Historial académico.
- Carga académica de docentes.

### Prioridad 6: pruebas

- Pruebas de persistencia real con ObjectDB.
- Inscripción válida.
- Inscripción duplicada.
- Inscripción sin cupo.
- Periodo cerrado.
- Prerrequisito no cumplido.
- Rollback de inscripción.
- Calificación y pago inválidos.
- Pruebas REST para `GET`, `POST`, `PUT` y `DELETE`.
- Pruebas de respuestas `400`, `404`, `409` y `500`.
- Pruebas de formularios frontend.

### Prioridad 7: concurrencia y datos demo

- Demostrar dos estudiantes intentando tomar el último cupo.
- Documentar `race condition` y `lost update`.
- Definir una estrategia compatible con ObjectDB para serialización o bloqueo.
- Crear el cargador completo de datos ficticios solicitado.
- Ejecutar la carga completa con una licencia de ObjectDB sin el límite de evaluación.

### Prioridad 8: documentación educativa

- Explicar estados JPA: `NEW`, `MANAGED`, `DETACHED` y `REMOVED`.
- Completar ejercicios sobre modelo, herencia, relaciones y persistencia.
- Documentar JPQL, transacciones, REST, JSON y DTOs.
- Documentar Docker, volumen y conservación de `universidad.odb`.
- Completar ejercicios de concurrencia, API, frontend y evaluación final.

El trabajo pendiente es incremental: la base arquitectónica ya está separada en entidades, DTOs, repositorios, servicios, recursos, excepciones y configuración.

## Arquitectura

```text
Navegador -> HTML/CSS/JavaScript -> Jersey REST -> Service -> Repository/JPA -> ObjectDB
```

El código se organiza en `entity`, `dto`, `repository`, `service`, `resource`, `exception` y `config`. Los recursos REST no contienen reglas de negocio; la inscripción transaccional vive en `InscripcionService`.

## Modelo

```text
Persona
├── Estudiante
├── Docente
└── Administrador

Universidad -> Facultad -> Carrera -> PlanEstudios -> PlanMateria -> Materia
Materia -> prerrequisitos
Materia + Docente + PeriodoAcademico + Aula -> Paralelo -> Horario
Estudiante -> Inscripcion -> DetalleInscripcion -> Paralelo
Estudiante -> Calificacion, Asistencia, Pago
Usuario <-> Rol; Auditoria -> Usuario
```

La herencia usa `@Inheritance(strategy = JOINED)`: los atributos comunes permanecen en `Persona` y cada subtipo conserva sus atributos específicos. `PlanMateria` es una entidad intermedia porque almacena semestre, créditos y si la materia es obligatoria o electiva. `DetalleInscripcion` usa composición con `cascade = ALL` y `orphanRemoval = true`.

## ObjectDB y JPA

- Una entidad es un objeto Java administrado por JPA y marcado con `@Entity`.
- La identidad se define mediante `@Id` y `@GeneratedValue`.
- Las asociaciones usan `@OneToMany`, `@ManyToOne`, `@ManyToMany` y `@OneToOne`.
- JPQL consulta entidades y propiedades, no tablas SQL.
- `PersistenceConfig` crea `universidadPU` programáticamente y usa `objectdb:data/universidad.odb`; no se utiliza `persistence.xml`.
- Una transacción comienza con `begin()`, confirma con `commit()` y revierte con `rollback()`.
- Los estados JPA a estudiar son `NEW`, `MANAGED`, `DETACHED` y `REMOVED`, mediante `persist`, `find`, `merge`, `remove`, `detach` y `clear`.

## URLs disponibles

Con la aplicación ejecutándose mediante Docker, la interfaz web está disponible en:

```text
http://localhost:8080
```

### Estudiantes

```text
GET    http://localhost:8080/api/estudiantes
GET    http://localhost:8080/api/estudiantes/{id}
POST   http://localhost:8080/api/estudiantes
PUT    http://localhost:8080/api/estudiantes/{id}
DELETE http://localhost:8080/api/estudiantes/{id}
```

### Docentes

```text
GET    http://localhost:8080/api/docentes
POST   http://localhost:8080/api/docentes
PUT    http://localhost:8080/api/docentes/{id}
DELETE http://localhost:8080/api/docentes/{id}
```

### Carreras

```text
GET    http://localhost:8080/api/carreras
POST   http://localhost:8080/api/carreras
PUT    http://localhost:8080/api/carreras/{id}
DELETE http://localhost:8080/api/carreras/{id}
```

### Materias

```text
GET    http://localhost:8080/api/materias
POST   http://localhost:8080/api/materias
PUT    http://localhost:8080/api/materias/{id}
DELETE http://localhost:8080/api/materias/{id}
```

### Catálogos académicos

```text
GET  http://localhost:8080/api/planes-estudios
POST http://localhost:8080/api/planes-estudios
GET  http://localhost:8080/api/periodos
POST http://localhost:8080/api/periodos
GET  http://localhost:8080/api/aulas
POST http://localhost:8080/api/aulas
GET  http://localhost:8080/api/paralelos
POST http://localhost:8080/api/paralelos
GET  http://localhost:8080/api/horarios
POST http://localhost:8080/api/horarios
```

### Inscripciones y dashboard

```text
POST http://localhost:8080/api/inscripciones/transaccion
GET  http://localhost:8080/api/dashboard
```

La documentación y los endpoints disponibles corresponden a la fase actual del proyecto. Las rutas avanzadas de historial, calificaciones, pagos y auditoría todavía están pendientes.

La inscripción recibe:

```json
{
  "estudianteId": 1,
  "periodoId": 1,
  "paralelos": [100, 101],
  "usuarioId": 1
}
```

El servicio valida estudiante activo, periodo activo, duplicados, cupos y prerrequisitos. Cualquier error ejecuta `rollback()`; una operación correcta crea la inscripción, sus detalles, descuenta cupos y registra auditoría antes del `commit()`.

Los DTOs evitan exponer directamente el grafo JPA y permiten contratos JSON estables. Las excepciones de negocio se convierten en respuestas JSON con estado HTTP y `transaction: ROLLED_BACK`.

## Ejecución con Docker

```bash
docker compose build
docker compose up
docker compose up --build
docker compose up -d
docker compose logs -f
docker compose down
docker compose run --rm universidad-lab mvn test
```

Abrir:

```text
http://localhost:8080
http://localhost:8080/api/estudiantes
```

La carpeta local `./data` se monta como `/app/data`, por lo que `universidad.odb` sobrevive a la eliminación del contenedor. Para reiniciar la base:

```bash
docker compose down
rm -f data/universidad.odb
docker compose up --build
```

## Pruebas

Las pruebas unitarias cubren herencia y relaciones del modelo. La validación ejecutable recomendada es:

```bash
docker compose run --rm universidad-lab mvn test
```

## Concurrencia

ObjectDB embebido no debe tratarse como un sistema con bloqueo pesimista universal. La operación de inscripción agrupa validaciones y actualización de cupos en una transacción, pero una demostración de dos escritores concurrentes debe documentar el riesgo de `lost update` y validar el comportamiento con pruebas de integración. Una evolución posible es serializar el caso de negocio o usar una estrategia optimista compatible con la versión de ObjectDB.

La versión de evaluación de ObjectDB 2.9.5 limita el almacenamiento a diez tipos persistibles. Como las relaciones JPA hacen que ObjectDB descubra tipos relacionados transitivamente, el inicializador ejecutable carga actualmente solo dos estudiantes demo para garantizar el arranque sin licencia adicional. El modelo completo permanece registrado y puede utilizarse con una licencia de ObjectDB que elimine esa restricción.

## Ejercicios

Los ejercicios existentes se conservarán como material histórico y se ampliarán con:

1. Modelo, identidad y herencia.
2. Relaciones y composición.
3. ObjectDB y estados JPA.
4. Consultas JPQL con `JOIN`, `JOIN FETCH`, `GROUP BY` y agregados.
5. Servicios y repositorios.
6. Transacciones, commit y rollback.
7. API REST, JSON y DTO.
8. Frontend con Fetch API.
9. Concurrencia y auditoría.
10. Evaluación final.

## Antes y después

El laboratorio anterior modelaba `Cliente`, `Producto`, `Pedido` y `DetallePedido`. El nuevo dominio agrega herencia, planes de estudio, prerrequisitos, periodos, paralelos, inscripción atómica, calificaciones, asistencia, pagos y auditoría: un grafo de objetos mucho más útil para demostrar persistencia orientada a objetos.
