FROM maven:3.8.5-openjdk-17-slim AS build
VOLUME /tmp
COPY .. ./
RUN mvn clean package -DskipTests

FROM openjdk:17-oracle
VOLUME /tmp
COPY target/*.jar ms-bankaccount-1.0.0.jar
EXPOSE 8092
ENTRYPOINT ["java","-jar","/ms-bankaccount-1.0.0.jar"]