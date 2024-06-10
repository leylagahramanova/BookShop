# First stage: build the application
FROM maven:3.9.7-eclipse-temurin-21-alpine AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Second stage: run the application
FROM openjdk:23-ea-21-jdk

# Set the working directory in the container
WORKDIR /app
COPY target ./target

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/*.jar /app/my-springboot-app.jar

# Expose the port that the application runs on
EXPOSE 8081

# Run the JAR file
ENTRYPOINT ["java", "-jar","-Dspring.config.location=target/classes/application.properties", "/app/my-springboot-app.jar"]