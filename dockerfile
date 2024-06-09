# Use the official OpenJDK image from the Docker Hub
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven build files
COPY pom.xml ./
COPY src ./src

# Copy the Maven wrapper and settings (if you use the Maven wrapper)
COPY .mvn ./.mvn
COPY mvnw ./

# Build the application using Maven
RUN ./mvnw package

# Set the entry point for the container to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "target/BookShop-0.0.1-SNAPSHOT.jar"]
