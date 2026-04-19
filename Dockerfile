# Construcción
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from:build /app/target/*.jar app.jar


# Limitamos la RAM a 512MB para proteger el servidor
ENTRYPOINT ["java", "-Xmx512M", "-jar", "app.jar"]