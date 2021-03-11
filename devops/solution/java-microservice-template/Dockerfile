FROM maven:3-openjdk-11 AS build

WORKDIR /usr/src/app/src/

COPY db/ /usr/src/app/src/db/
COPY server/ /usr/src/app/src/server/
COPY server/pom.xml /usr/src/app/server/pom.xml

RUN mvn -f /usr/src/app/src/server/pom.xml clean package

FROM adoptopenjdk/openjdk11:debian

WORKDIR /opt/java-service

COPY --from=build /usr/src/app/src/server/target/app.jar /opt/java-service/app.jar
COPY --from=build /usr/src/app/src/server/src/main/resources/application.yaml /opt/java-service/application.yaml

ENV JAVA_OPTIONS="-server -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Dfile.encoding=UTF-8"
ENV DBHOST=""
ENV DBNAME=""
ENV DBUSER=""
ENV DBPASSWORD=""

EXPOSE 8080

ENTRYPOINT ["java","-Dspring.profiles.active=deployment","-jar","app.jar","${JAVA_OPTIONS}"]
