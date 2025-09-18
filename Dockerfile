# ------------ Stage 1: Build the JAR using Maven ------------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies first (cache optimization)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# ------------ Stage 2: Run the JAR ------------
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Render provides PORT dynamically → bind it
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
