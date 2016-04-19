## tipsters

[![Build Status](https://travis-ci.org/tapatron/tipsters.svg?branch=master)](https://travis-ci.org/tapatron/tipsters)

### REST api

1. Ensure a postgres database is installed with database name tipsters
2. Run the create db script at ```tipsters/web-server/src/test/resources/db/create.sql```
3. Insert the reference data by running ```tipsters/web-server/src/test/resources/db/insert.sql```
4. Build the executable jar ```mvn clean install```
5. Then run ```java -jar web-server/target/web-server-1.0-SNAPSHOT.jar```
6. Navigate to ```http://localhost:19000/api/tipsters/countries```

### Run stack using docker compose

***In progress***

So far built web-server and database builds, need to do the client container.

* web-server  ```docker build -t tipsters/tipsters-api .```
* db ```docker build -t tipsters/db .```
* web-client

```docker run --net=host -p 127.0.0.0:5432:5432 --name db tipsters/db```

```docker run --net=host -p 19000:19000 --name api tipsters/tipsters-api```

[Replace above with compose]

