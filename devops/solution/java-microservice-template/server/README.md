# Server Component
Server component is the backend part of the microservice providing a REST-API. The server is a Java Spring-Boot based application. Contract-first development approach is used. The contract is designed using OpenAPI (v3) spec. The contoller interface and REST API objects are generated using OpenAPI code generator.

## Functional layers

### Database
This layer contains the entity model objects, and the Spring repository interfaces to access the data.

### Service 
This layer is a middle layer to gather the data from DAO and apply some business logic on the data if necessary.

### Controller
Controller is the REST server component exposing the endpoints and converting the data.

## Running Locally
Run following command to build and start the server (build, test and run the server):
```
mvn clean test spring-boot:run
```
or 
```
java -Dspring.profiles.active=development -jar  target/app.jar
```

### Calling API using Swagger-UI
Once tests pass, and the server is running a Swagger-UI interface will be available to call and test the REST-API using following URL:

http://localhost:8080/swaggerui.html

## Tips & Tricks
### OpenAPI editor
https://studio.apicur.io is as good online editor for OpenAPI.

