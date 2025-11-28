FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copier les fichiers de configuration Maven
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Télécharger les dépendances
RUN mvn dependency:go-offline -B

# Copier le code source
COPY src ./src

# Builder l'application (sans tests pour accélérer)
RUN mvn clean package -DskipTests

# Étape 2 : Image finale légère
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copier le JAR depuis l'étape de build
COPY --from=build /app/target/*.jar app.jar

# Exposer le port
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
```