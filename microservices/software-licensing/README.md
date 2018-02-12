# Software Licensing

## About

Simple Vert.X microservice implemention for a software licensing system.

### Services

| **Service** | **Description** |
|----------|-----------------|
| Account | Service handling accounts and authentication. |
| Cluster Manager | Service handling integratiom of services through clustering. |
| License | Handles the provisioning of licenses of software. |
| Profile | Handles the user profile domain of an account. |
| Software | Handles the domain of various software that can be licensed. |
| Web Portal | Web server serving the web client application. |

## Technologies

| **Tech** | **Description** |
|----------|-----------------|
| Java | Programming language used for the service implementations. |
| Maven | Java dependency management and build scripts. |
| Vert.X | Library used to implement the different services |
| JWT | Used as a means for client and service authentication |
| MongoDB | Used to store data from the various domains |
| Hazelcast | Used to cluster the different services and share the Vert.x EventBus |

## License

Apache License v2.0 © [Kyle Williamson ](https://github.com/kyledmw)
