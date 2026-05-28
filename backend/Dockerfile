FROM eclipse-temurin:25-alpine
RUN apk update && apk add --no-cache curl
RUN mkdir /opt/app
COPY target/app.jar /opt/app/app.jar
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
