FROM ubuntu:latest
LABEL authors="YigitAli"

# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

WORKDIR /app
# Copy the Spring Boot JAR file into the container
COPY target/myapp-1.jar app.jar
# Expose the port your Spring Boot application runs on
EXPOSE 8005
# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]