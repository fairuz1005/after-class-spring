FROM openjdk:20-jdk-alpine
MAINTAINER baeldung.com
COPY target/auth-service-0.0.1-SNAPSHOT.jar auth-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/auth-service.jar"]