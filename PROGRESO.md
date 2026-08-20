# Progreso del proyecto

## Fase actual

El proyecto está en la **Fase 14 inicial / Fase 16 inicial**. La cadena principal funciona:

```text
Frontend -> API REST -> Service -> JPA -> ObjectDB
```

## Implementado

### Modelo y persistencia

- Java 17, Maven, Jakarta Persistence 3.2, ObjectDB 2.9.5, Jersey, Grizzly, JSON-B, Validation, JUnit y Docker.
- Herencia `Persona` -> `Estudiante`, `Docente` y `Administrador`.
- Universidad, facultades, carreras, planes, materias y prerrequisitos.
- Periodos, paralelos, aulas y horarios.
- Inscripciones, evaluaciones, calificaciones, asistencia, pagos, usuarios, roles y auditoría.
- Configuración programática `universidadPU` con `objectdb:data/universidad.odb`.
- Repositorio JPA genérico y repositorios específicos.
- Servicios y transacciones con `begin`, `commit` y `rollback`.

### API REST

Estudiantes, con CRUD completo:

```text
GET    /api/estudiantes
GET    /api/estudiantes/{id}
POST   /api/estudiantes
PUT    /api/estudiantes/{id}
DELETE /api/estudiantes/{id}
```

Docentes, carreras y materias, con `GET`, `POST`, `PUT` y `DELETE`:

```text
/api/docentes
/api/carreras
/api/materias
```

Catálogos académicos, con `GET` y `POST`:

```text
/api/planes-estudios
/api/periodos
/api/aulas
/api/paralelos
/api/horarios
```

Inscripción transaccional:

```text
POST /api/inscripciones/transaccion
```

Dashboard:

```text
GET /api/dashboard
```

### Frontend y Docker

- Frontend HTML, CSS y JavaScript servido desde el backend.
- Tabla de estudiantes.
- Formulario de alta y edición.
- Acciones de editar y eliminar.
- Métricas básicas del dashboard.
- Menú hamburguesa responsive con Dashboard, Estudiantes, Docentes, Carreras,
  Materias, Planes, Periodos, Aulas, Paralelos, Horarios e Inscripciones.
- Navegación accesible con `aria-expanded` y cierre automático al seleccionar una opción.
- Fetch API conectado a REST.
- Docker con Java 17, puerto `8080` y volumen `./data:/app/data`.

## Verificaciones

- `docker compose build` correcto.
- `mvn test` dentro de Docker correcto.
- Dos pruebas unitarias del modelo pasan.
- CRUD de estudiantes probado con `POST`, `PUT` y `DELETE`.
- CRUD inicial de docentes, carreras y materias probado por REST.
- Dashboard probado con JSON.
- Frontend probado con HTTP.
- ObjectDB crea `data/universidad.odb`.

## Limitación conocida

ObjectDB 2.9.5 en modo evaluación limita el almacenamiento a diez tipos persistibles. Las relaciones JPA pueden descubrir tipos transitivamente, por lo que el inicializador demo carga solo dos estudiantes para garantizar el arranque sin licencia comercial.

El modelo completo permanece definido y registrado. Para cargar todos los datos y validar todos los catálogos se recomienda una licencia de ObjectDB sin esa limitación.

## Pendiente

### Paralelos y horarios

- Integrar los formularios de paralelos y horarios en el frontend.
- Probar cruces de aula, día y hora con una base ObjectDB completa.
- Añadir actualización y eliminación de paralelos/horarios.

### Inscripciones

- `GET /api/inscripciones`.
- `GET /api/inscripciones/{id}`.
- `POST /api/inscripciones` como contrato general.
- Anulación de inscripciones.
- Materias de un estudiante.
- Estudiantes de un paralelo.
- Historial académico.
- Integración completa con la interfaz web.

### Calificaciones

- CRUD de evaluaciones.
- Registro de calificaciones.
- Validación de notas entre `0` y `100`.
- Promedios y estados aprobado/reprobado.
- Interfaz para docentes.

### Pagos y auditoría

- CRUD de pagos.
- Anulación de pagos.
- Conceptos de pago.
- Auditoría de pagos y anulaciones.
- Interfaz web de pagos.

### Dashboard avanzado

- Gráficos y filtros.
- Estadísticas por periodo.
- Ingresos por concepto.
- Promedios por carrera y materia.

### Calidad y documentación

- `ExceptionMapper` global para respuestas `400`, `404`, `409` y `500`.
- Consultas JPQL avanzadas: `JOIN`, `JOIN FETCH`, `LEFT JOIN`, `GROUP BY`, `COUNT`, `AVG` y `SUM`.
- Pruebas de persistencia, REST, rollback y reglas de negocio.
- Prueba de concurrencia con último cupo, `race condition` y `lost update`.
- Ejercicios educativos del 01 al 10.
- OpenAPI/Swagger y CORS explícito.

## Orden recomendado

```text
Formularios de paralelos y horarios
        ↓
Inscripciones completas
        ↓
Calificaciones
        ↓
Pagos y auditoría
        ↓
Errores globales
        ↓
JPQL avanzado
        ↓
Pruebas de API
        ↓
Concurrencia
        ↓
Documentación final
```

## Comandos

```bash
docker compose build
docker compose up --build
docker compose up -d
docker compose logs -f
docker compose run --rm universidad-lab mvn test
docker compose down
```

Aplicación: `http://localhost:8080`

API principal: `http://localhost:8080/api/estudiantes`
