# Step 1: Use Maven image to build the project
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Use JDK image to run the app
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=builder /app/target/healthcare-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
