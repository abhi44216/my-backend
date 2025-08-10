# Stage 1: Build the project with Maven
FROM maven:3.8.6-openjdk-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy all source code
COPY src ./src

# Build the jar file
RUN mvn clean package -DskipTests

# Stage 2: Run the jar in a lightweight OpenJDK image
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
