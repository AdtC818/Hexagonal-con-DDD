FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw -q package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/paymentservice-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "/app/app.jar"]