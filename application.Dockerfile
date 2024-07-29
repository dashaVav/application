FROM openjdk:21-slim
LABEL authors="dashavav"
COPY target/application*.jar /application.jar
ENTRYPOINT ["java", "-jar", "/application.jar"]