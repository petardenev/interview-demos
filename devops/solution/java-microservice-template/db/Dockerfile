FROM maven:3-openjdk-11 AS build

COPY . /usr/src/app/db/
COPY pom.xml /usr/src/app/db/pom.xml
WORKDIR /usr/src/app/db/
RUN mvn -f /usr/src/app/db/pom.xml clean package

FROM adoptopenjdk/openjdk11:debian

WORKDIR /opt/liquibase
COPY --from=build /usr/src/app/db/target/database-migration.jar /opt/liquibase/database-migration.jar
COPY --from=build /usr/src/app/db/src/main/resources/application.yaml /opt/liquibase/application.yaml

ENV JAVA_OPTIONS="-server -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Dfile.encoding=UTF-8"
ENV DBHOST=""
ENV DBNAME=""
ENV DBUSER=""
ENV DBPASSWORD=""

WORKDIR /opt/liquibase

ENTRYPOINT ["java","-Dspring.profiles.active=deployment","-jar","database-migration.jar","${JAVA_OPTIONS}"]
