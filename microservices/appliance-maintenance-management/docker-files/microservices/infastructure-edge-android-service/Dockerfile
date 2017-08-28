FROM java:8-jre
MAINTAINER Kyle Williamson <kyle.williamson@mycit.ie>

ADD ./infastructure-edge-android-service-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/infastructure-edge-android-service-0.0.1.jar"]

EXPOSE 8200