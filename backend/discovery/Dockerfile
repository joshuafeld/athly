FROM eclipse-temurin:25
RUN apt update && apt install -y curl
RUN mkdir /opt/app
COPY target/athly-discovery-*.jar /opt/app/athly-discovery.jar
CMD ["java", "-jar", "/opt/app/athly-discovery.jar"]
