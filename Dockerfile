# Usa una imagen base válida de OpenJDK
FROM eclipse-temurin:21-jre

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR empaquetado de Maven al contenedor
COPY target/*.jar app.jar

# Expone el puerto en el que tu aplicación Spring Boot escucha
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]