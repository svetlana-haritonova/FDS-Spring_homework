FROM openjdk:21-slim

WORKDIR /app

COPY /build/libs/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
