## tipsters

### REST api

1. Ensure a postgres database is installed with database name tipsters
2. Run the create db script at ```tipsters/web-server/src/test/resources/db/create.sql```
3. Insert the reference data by running ```tipsters/web-server/src/test/resources/db/insert.sql```
4. Build the executable jar ```mvn clean install```
5. Then run ```java -jar web-server/target/web-server-1.0-SNAPSHOT.jar```

### Build

[Jenkins](http://52.30.74.253:8080/)
