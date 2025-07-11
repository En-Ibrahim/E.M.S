FROM openjdk:17-jdk-alpine
WORKDIR /ems-app
COPY target/ems-app-0.0.1-SNAPSHOT.jar ems-app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "ems-app.jar"]