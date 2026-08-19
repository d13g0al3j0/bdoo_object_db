FROM maven:3.9.11-eclipse-temurin-17

WORKDIR /app

# Copiar primero el pom para aprovechar la caché de Docker
COPY pom.xml .

# Copiar el código fuente
COPY src ./src

# Copiar la interfaz estática que sirve Grizzly.
COPY frontend ./frontend

# Crear directorio para la base de datos ObjectDB
RUN mkdir -p /app/data

# Resolver dependencias y compilar durante la construcción de la imagen.
RUN mvn -DskipTests compile

# Ejecutar el laboratorio
CMD ["mvn", "-q", "exec:java"]