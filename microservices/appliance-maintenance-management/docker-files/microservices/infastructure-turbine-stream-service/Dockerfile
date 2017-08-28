FROM java:8-jre
MAINTAINER Kyle Williamson <kyle.williamson@mycit.ie>

ADD ./infastructure-turbine-stream-service-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/infastructure-turbine-stream-service-0.0.1.jar"]

EXPOSE 8103
EXPOSE 8300
EXPOSE 8301