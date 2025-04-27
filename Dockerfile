# Stage 1: Build stage
FROM gradle:8.0-jdk21 AS build

# Set working directory
WORKDIR /app

# Copy Gradle wrapper and build scripts
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Copy application source code
COPY src src

# Build the project (without running tests)
RUN gradle build -x test

# Stage 2: Run stage
FROM amazoncorretto:21

# Set working directory for the final container
WORKDIR /apartment-service

# Copy the built JAR file from the build stage
COPY --from=build /app/build/libs/*.jar apartmentservice.jar

# Expose the application port
EXPOSE 4110

# Set the entrypoint to run the JAR file
ENTRYPOINT ["java", "-jar", "apartmentservice.jar"]
