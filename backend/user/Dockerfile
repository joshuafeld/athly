FROM eclipse-temurin:25
RUN mkdir /opt/app
COPY target/athly-user-*.jar /opt/app/athly-user.jar
CMD ["java", "-jar", "/opt/app/athly-user.jar"]
