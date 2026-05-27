FROM eclipse-temurin:25
RUN mkdir /opt/app
COPY target/athly-gateway-*.jar /opt/app/athly-gateway.jar
CMD ["java", "-jar", "/opt/app/athly-gateway.jar"]
