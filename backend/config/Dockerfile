FROM amazoncorretto:25-jdk

WORKDIR /app

COPY target/athly-config-*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
