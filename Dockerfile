# Fase de build
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app
COPY pom.xml .
RUN mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline

COPY src ./src
RUN mvn -B -e -o -T 1C verify

# Fase de execução
FROM openjdk:17-jdk-slim

WORKDIR /app
COPY --from=build /app/target/back-end-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

