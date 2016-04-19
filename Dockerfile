FROM frolvlad/alpine-oraclejdk8

MAINTAINER Stein Fletcher <steinfletcher@gmail.com>

RUN mkdir -p /usr/tipsters/app

WORKDIR /usr/tipsters/app

COPY web-server/target/tipsters-api.jar /usr/tipsters/app

EXPOSE 19000

CMD ["java", "-jar", "tipsters-api.jar"]
