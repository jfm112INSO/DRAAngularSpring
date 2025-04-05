# Etapa 1: build
FROM eclipse-temurin:21-jdk-alpine as builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Etapa 2: run
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]