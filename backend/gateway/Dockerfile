FROM amazoncorretto:25-jdk

WORKDIR /app

COPY target/athly-gateway-*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
