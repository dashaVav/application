FROM openjdk:21-slim
LABEL authors="dashavav"
COPY target/application*.jar /application.jar
EXPOSE 8093
ENTRYPOINT ["java", "-jar", "/application.jar"]