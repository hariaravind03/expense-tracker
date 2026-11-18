FROM amazoncorretto:21

WORKDIR /app

COPY target/Expense-Tracker-0.0.1-SNAPSHOT.jar expense-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar","expense-app.jar"]