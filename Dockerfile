# Utiliser une image OpenJDK
FROM openjdk:23-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR de l’application
COPY target/*.jar app.jar

# Exposer le port de l’application
EXPOSE 8080

# Exécuter l’application
ENTRYPOINT ["java", "-jar", "app.jar"]
