# Stage 1: Build Stage
FROM gradle:8.4.0-jdk21 AS build

WORKDIR /app
COPY settings.gradle build.gradle ./
COPY gradle gradle
COPY src src

RUN gradle build -x test --no-daemon

# Stage 2: Runtime Stage
FROM amazoncorretto:21

WORKDIR /apartment-service
COPY --from=build /app/build/libs/*.jar apartmentservice.jar

EXPOSE 4110
ENTRYPOINT ["java", "-jar", "apartmentservice.jar"]
