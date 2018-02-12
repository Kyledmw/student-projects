# Comments App

## About

Comments web application for multiple users.

Uses Vert.x "eventbus" publish & subscribe mechanism to allow React web clients to add new comments. All react web clients comments are synced.

## Technologies

| **Tech** | **Description** |
|----------|-----------------|
| Java | Programming language used for the web-service implementation. |
| Vert.x | Library used for implementing the web service. |
| ReactJS | Simple react application included to visualise the service |

## Setup / Installation

1. Setup machine with Java & Maven
2. Install dependencies through: mvn install
3. Package application into executable jar: mvn package

## License

Apache Version 2.0 © [Kyle Williamson ](https://github.com/kyledmw)
