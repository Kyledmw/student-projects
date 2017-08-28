FROM java:8-jre
MAINTAINER Kyle Williamson <kyle.williamson@mycit.ie>

ADD ./appliance-status-service-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/appliance-status-service-0.0.1.jar"]

EXPOSE 8404