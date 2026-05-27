FROM amazoncorretto:25-jdk

WORKDIR /app

COPY target/athly-user-*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
