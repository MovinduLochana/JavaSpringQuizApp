# Spring Boot Application

## Overview

This is a Spring Boot application that leverages several powerful technologies to provide a robust microservices architecture. The stack includes:

- Spring Framework
- Spring Gateway
- Hibernate/JPA with MySQL
- Netflix OpenFeign (include automatic load balancing)
- Eureka Discovery Server

## Features

- **Microservices Architecture**: Decoupled services that can be independently developed, deployed, and scaled.
- **Service Discovery**: Eureka server for automatic registration and discovery of services.
- **Load Balancing**: Ribbon for client-side load balancing.
- **Declarative REST Clients**: OpenFeign for making REST calls to other services.
- **API Gateway**: Spring Gateway for routing and filtering requests.
- **Persistence**: Hibernate/JPA for ORM and MySQL as the database.

## Technologies

- Java 21
- Maven 3.6.0
- MySQL 8.0 or

## Setup Instructions

### Clone the Repository

```sh
git clone https://github.com/MovinduLochana/JavaSpringQuizApp.git
cd JavaSpringQuizApp
```

### Configuration
#### MySQL Configuration
```
spring.datasource.url=jdbc:mysql://localhost:3306/your-database
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
```
#### Eureka Client Configuration
```
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
eureka.client.registerWithEureka=true
eureka.client.fetchRegistry=true
```
#### Spring Gateway 
```
spring.cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.discovery.locator.lower-case-service-id=true
```

### Running App

- Running API-Gateway and Discovery server first is recommended 
- You can run the application either via a IDE or using Maven wrapper to make a jar file for each service
- You can change server port number in properties file

```
server.port=<PORT>
```
- Or configure VM Options and run multiple instances of a service

```
-Dserver.port=8082
```

### Building a Jar

- Use maven wrapper to build the application
- Go inside a service and run maven builder

```
cd <service>
mvn spring-boot:run
```

- Do this for all services and you'll get final jars for Run

- Configure VM options after build

```sh
java -jar -Dserver.port=8082 event-service.jar //
```


