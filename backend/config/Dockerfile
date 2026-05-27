FROM eclipse-temurin:25
RUN apt update && apt install -y curl
RUN mkdir /opt/app
COPY target/athly-config-*.jar /opt/app/athly-config.jar
CMD ["java", "-jar", "/opt/app/athly-config.jar"]
