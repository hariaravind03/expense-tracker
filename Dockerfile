FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM amazoncorretto:21

WORKDIR /app

COPY --from=build /app/target/Expense-Tracker-0.0.1-SNAPSHOT.jar expense-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "expense-app.jar"]