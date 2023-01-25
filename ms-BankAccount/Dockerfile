FROM openjdk:17-oracle
ARG JAR_FILE=target/ms-bankaccount-1.0.0.jar
ADD ${JAR_FILE} ms-bankaccount-1.0.0.jar
ENTRYPOINT ["java","-jar","/ms-bankaccount-1.0.0.jar"]