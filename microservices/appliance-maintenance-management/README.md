# Appliance Maintenance Management

## About

Microservice architecture applied to a Property & Appliance Maintenance Management solution. 

Uses Spring-Cloud / Netflix OSS, Docker. 

Android client application visualises the entire system operating.


## Technologies

### Android

| **Tech** | **Description** |
|----------|-----------------|
| Java | Programming language used for the android implementation. |
| Gradle | Android dependency management and build scripts. |
| GSON | Used to parse JSON to Java Objects for the android app |
| NFC | Used to tag and integrate appliances|

### Microservices

| **Tech** | **Description** |
|----------|-----------------|
| Java | Programming language used to implement services |
| Java | Service dependency management and build scripts |
| Spring Boot | Framework used for the individual services |
| Spring Cloud | Technology stack used for implementing the microservice architecture. Specific Netflix projects utilized below |
| Spring Configuration Server | Centralized configuration for services |
| Netflix OSS | Eureka, Hystrix, Feign, Robbin, Turbine, ZUUL |
| RabbitMQ | Used for gathering metrics from inter-service communication |
| MongoDB | Used for storing appliance information due to variety in data structure |
| PostgreSQL | Used for storing normalized data structures thorughout different services |

### Configuration Management

| **Tech** | **Description** |
|----------|-----------------|
| Docker | Container technology used for deployment of services |


## Setup / Installation

### Android

1. Setup android SDK, Java, Gradle on machine
2. Modify IP used in IWebConstants.JAVA to where the edge service is deployed
3. Build application through gradle CLI or android studio.

### Microservices

1. Setup Java, Maven and Docker on machine.
2. Run docker-compose on docker-files/microservices/docker-compose.yml

## License

Apache License v2.0 © [Kyle Williamson ](https://github.com/kyledmw)
