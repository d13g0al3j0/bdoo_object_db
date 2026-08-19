# Progreso del proyecto

## Proyecto

**Sistema Universitario Orientado a Objetos**

Tecnologías principales:

- Java 17
- Maven
- Jakarta Persistence 3.2
- ObjectDB 2.9.5
- Jersey y Grizzly
- JSON-B
- Jakarta Validation
- JUnit 5
- Docker y Docker Compose
- HTML, CSS y JavaScript

## Fase actual

El proyecto se encuentra en la **Fase 10 avanzada / Fase 16 inicial**.

La arquitectura base ya funciona y existe una primera integración entre:

```text
Frontend -> API REST -> Servicio -> JPA -> ObjectDB
```

La aplicación puede iniciar mediante Docker, servir el frontend en el puerto `8080`, consultar estudiantes y conservar la base en:

```text
data/universidad.odb
```

## Fases completadas

### Fase 1: análisis del proyecto anterior

Completado:

- Revisión de `pom.xml`, Docker, Compose, README y pruebas.
- Identificación del modelo anterior:
  - `Cliente`
  - `Producto`
  - `Pedido`
  - `DetallePedido`
- Identificación de la configuración programática de JPA.
- Identificación del volumen Docker para ObjectDB.

### Fase 2: diseño del modelo universitario

Completado:

- Diseño de la jerarquía `Persona`.
- Diseño de `Estudiante`, `Docente` y `Administrador`.
- Diseño de universidad, facultad, carrera, plan y materias.
- Decisión de utilizar `PlanMateria` como entidad intermedia.
- Diseño de prerrequisitos entre materias.
- Diseño de inscripciones, calificaciones, pagos y auditoría.

### Fase 3: dependencias Maven

Completado:

- Java 17.
- Jakarta Persistence.
- ObjectDB 2.9.5.
- Jersey REST.
- Grizzly HTTP.
- JSON-B/Yasson.
- Jakarta Validation.
- Hibernate Validator.
- JUnit 5.

### Fase 4: entidades

Completado:

- Entidades de personas y herencia `JOINED`.
- Entidades institucionales.
- Entidades académicas.
- Entidades de inscripción.
- Evaluaciones, calificaciones y asistencia.
- Pagos y conceptos de pago.
- Usuarios, roles y auditoría.
- Enums de estados y tipos.

### Fase 5: configuración de ObjectDB

Completado:

- Unidad de persistencia programática `universidadPU`.
- Archivo configurado como `objectdb:data/universidad.odb`.
- Registro de entidades administradas.
- No se utiliza `persistence.xml`.

### Fase 6: repositorios

Completado:

- Repositorio genérico `JpaRepository`.
- Operaciones `findById`, `findAll`, `save`, `update` y `delete`.
- Repositorios específicos para las entidades principales.

### Fase 7: servicios y excepciones

Completado parcialmente:

- `InscripcionService`.
- Excepciones de entidad no encontrada.
- Excepciones de cupo insuficiente.
- Excepciones de inscripción duplicada.
- Excepciones de periodo no activo.
- Excepciones de prerrequisito no cumplido.

### Fase 8: transacciones

Completado parcialmente:

- Executor centralizado de transacciones.
- `begin()`.
- `commit()`.
- `rollback()`.
- Validación de estudiante activo.
- Validación de periodo activo.
- Validación de cupos.
- Validación de prerrequisitos.
- Reducción de cupos.
- Creación de inscripción y detalles.
- Registro de auditoría.

### Fases 9 y 10: API REST y DTOs

Completado parcialmente:

- `GET /api/estudiantes`.
- `GET /api/estudiantes/{id}`.
- `POST /api/inscripciones/transaccion`.
- `EstudianteDTO`.
- `InscripcionRequest`.
- `InscripcionResponse`.
- `ErrorResponse`.
- Respuestas JSON.

### Fases 12, 13 y 16: frontend y Docker

Completado parcialmente:

- Frontend HTML, CSS y JavaScript.
- Consumo REST mediante Fetch API.
- Tabla de estudiantes.
- Botón de actualización.
- Servidor en `http://localhost:8080`.
- Puerto Docker `8080`.
- Volumen `./data:/app/data`.
- Imagen basada en Java 17.
- Compilación dentro de Docker.

## Verificaciones realizadas

Se comprobó que:

- La imagen Docker se construye correctamente.
- Maven compila el proyecto dentro del contenedor.
- Las pruebas actuales pasan.
- El frontend responde por HTTP.
- La API de estudiantes responde JSON.
- ObjectDB crea `universidad.odb`.
- La configuración de Compose es válida.
- El proyecto ya no utiliza el modelo anterior de empresa.

## Limitación conocida

ObjectDB 2.9.5 en modo evaluación limita el almacenamiento a diez tipos persistibles. Debido a que JPA descubre tipos relacionados transitivamente, el inicializador demo carga actualmente solo dos estudiantes para permitir que la aplicación arranque sin una licencia comercial.

El modelo universitario completo sí está definido y registrado en el código. Para cargar todos los datos de demostración será necesario utilizar una licencia de ObjectDB que no tenga esa limitación.

## Trabajo pendiente inmediato

### 1. Completar CRUD de estudiantes

Crear:

```text
POST   /api/estudiantes
PUT    /api/estudiantes/{id}
DELETE /api/estudiantes/{id}
```

También agregar formularios para crear y editar estudiantes desde el frontend.

### 2. Completar CRUD académico

Crear endpoints para:

- Docentes.
- Carreras.
- Materias.
- Planes de estudio.
- Paralelos.
- Periodos académicos.
- Aulas.
- Horarios.

### 3. Completar inscripción

Agregar:

- `GET /api/inscripciones`.
- `GET /api/inscripciones/{id}`.
- `POST /api/inscripciones` como alias o contrato principal.
- `POST /api/inscripciones/{id}/anular`.
- Consulta de estudiantes inscritos en un paralelo.
- Consulta de materias de un estudiante.
- Historial académico.

### 4. Completar calificaciones

Agregar:

- CRUD de evaluaciones.
- Registro de calificaciones.
- Validación de notas de `0` a `100`.
- Cálculo de promedio.
- Determinación de aprobado o reprobado.
- Interfaz para docentes.

### 5. Completar pagos

Agregar:

- Consulta de pagos.
- Registro de pagos.
- Anulación de pagos.
- Validación de montos.
- Auditoría de operaciones de pago.
- Pantalla web de pagos.

### 6. Crear dashboard

Agregar `GET /api/dashboard` con:

- Total de estudiantes.
- Total de docentes.
- Total de carreras.
- Total de materias.
- Total de inscripciones.
- Total de pagos.
- Ingresos.
- Promedio general.

### 7. Completar consultas JPQL

Implementar y documentar:

- `JOIN`.
- `JOIN FETCH`.
- `LEFT JOIN`.
- `DISTINCT`.
- `ORDER BY`.
- `GROUP BY`.
- `COUNT`.
- `AVG`.
- `SUM`.
- Materias sin estudiantes.
- Paralelos sin cupos.
- Pagos pendientes.
- Estudiantes con promedio superior a un valor.

### 8. Manejo global de errores

Crear un `ExceptionMapper` global para devolver respuestas uniformes con:

```json
{
  "timestamp": "2026-08-19T19:00:00",
  "status": 400,
  "error": "Regla de negocio",
  "message": "Descripción del error",
  "transaction": "ROLLED_BACK"
}
```

### 9. Pruebas

Agregar pruebas para:

- Persistencia con ObjectDB.
- Inscripción válida.
- Inscripción duplicada.
- Inscripción sin cupo.
- Periodo cerrado.
- Prerrequisito no cumplido.
- Calificación inválida.
- Pago inválido.
- Rollback.
- Endpoints REST.
- Respuestas HTTP `400`, `404`, `409` y `500`.

### 10. Concurrencia

Implementar una demostración controlada de dos estudiantes intentando tomar el último cupo y documentar:

- Race condition.
- Lost update.
- Limitaciones de ObjectDB Evaluation.
- Estrategia de serialización o bloqueo compatible.

### 11. Documentación educativa

Crear o completar los ejercicios:

```text
01-modelo-objetos.md
02-herencia.md
03-persistencia.md
04-relaciones.md
05-consultas-jpql.md
06-transacciones.md
07-api-rest.md
08-frontend.md
09-concurrencia.md
10-evaluacion.md
```

## Orden recomendado para continuar

```text
CRUD de estudiantes
        ↓
CRUD académico
        ↓
Dashboard
        ↓
Calificaciones
        ↓
Pagos y auditoría
        ↓
Consultas JPQL avanzadas
        ↓
Pruebas de API
        ↓
Concurrencia
        ↓
Documentación final
```

## Comandos principales

```bash
docker compose build
docker compose up --build
docker compose up -d
docker compose logs -f
docker compose run --rm universidad-lab mvn test
docker compose down
```

Aplicación:

```text
http://localhost:8080
```

API actual:

```text
http://localhost:8080/api/estudiantes
```
