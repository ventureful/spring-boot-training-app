FROM openjdk:17-jdk

ARG JAR_FILE=target/employee-1.0.0.jar

WORKDIR /opt/app
COPY ${JAR_FILE} app.jar

ENV TZ="Asia/Manila"
ENTRYPOINT ["java","-jar","app.jar"]