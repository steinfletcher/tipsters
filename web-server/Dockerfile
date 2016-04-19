FROM postgres:9.4

ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD postgres
ENV POSTGRES_DB tipsters

RUN mkdir -p /docker-entrypoint-initdb.d/

WORKDIR /usr/tipsters/db

ADD  src/test/resources/db/initdb.sql /docker-entrypoint-initdb.d

EXPOSE 5432
