# Multi-stage build
FROM maven:3.9-eclipse-temurin-21-alpine AS build

# Set working directory
WORKDIR /app

# Copy everything
COPY pom.xml .
COPY src ./src

# Build the application (Maven will download dependencies as needed)
RUN mvn clean package -DskipTests -B

# Runtime stage
FROM eclipse-temurin:21-jre-alpine

# Set working directory
WORKDIR /app

# Copy the built artifact from build stage
COPY --from=build /app/target/spring-burger-*.jar app.jar

# Expose port
EXPOSE 8080

# Set the active profile to docker
ENV SPRING_PROFILES_ACTIVE=docker

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
