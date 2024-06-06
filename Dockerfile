FROM openjdk:17-jdk-alpine
MAINTAINER Dudkin Aleksandr
COPY target/springProject-0.0.1-SNAPSHOT.jar defaultpastebox.jar
ENTRYPOINT ["java","-jar","/defaultpastebox.jar"]
