FROM frolvlad/alpine-oraclejdk8

MAINTAINER Stein Fletcher <steinfletcher@gmail.com>

COPY target/tipsters-api.jar /application.jar

EXPOSE 8080

CMD ["java", "-jar", "/application.jar"]
