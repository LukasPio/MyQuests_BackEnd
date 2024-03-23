FROM alpine:latest AS build

RUN apk update && apk add openjdk17 maven
COPY . .

RUN mvn clean install

FROM openjdk:17-jdk-alpine

EXPOSE 8080

COPY --from=build /target/back-end-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]
