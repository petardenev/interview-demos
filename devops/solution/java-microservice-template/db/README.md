# DB Schema Migration
This module contains Liquibase change-logs to setup database schema and initial data.

### Configuration
The DB change-log is maintained in [dbchangelog.xml](src/main/resources/dbchangelog.xml) file. DB url, username and password are set as Maven properties (see [pom.xml](pom.xml))

## Setup/Migrate the DB Schema
There is support for `postgres` and `h2` databases. Default database is `postgres`. To setup the schema for `postgres` run the following command (local Postgres server should be running see below):
```
mvn liquibase:update
```
or 
```
mvn spring-boot:run
```
or using Java command line:
```
java -Dspring.profiles.active=development -jar target/database-migration.jar
```

### Rollback
To rollback one step:
```
mvn liquibase:rollback -Dliquibase.rollbackCount=1
```

## Useful Commands to setup a Database on local
### Create a database:
```
psql postgres -c 'create database template'
```
### Create a user:
```
psql postgres -c "create user test with encrypted password 'test'"
```
### Grant privileges: 
```
psql postgres -c "grant all privileges on database template to test"
```

ℹ️TODO: Update db connection properties and [dbchangelog.xml](src/main/resources/dbchangelog.xml) accordingly.
