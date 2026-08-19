FROM maven:3.9.11-eclipse-temurin-21

WORKDIR /app

# Copiar primero el pom para aprovechar la caché de Docker
COPY pom.xml .

# Descargar las dependencias
RUN mvn dependency:go-offline

# Copiar el código fuente
COPY src ./src

# Crear directorio para la base de datos ObjectDB
RUN mkdir -p /app/data

# Ejecutar el laboratorio
CMD ["mvn", "clean", "compile", "exec:java"]