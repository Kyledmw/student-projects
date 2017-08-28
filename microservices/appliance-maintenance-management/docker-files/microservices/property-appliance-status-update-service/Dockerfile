FROM java:8-jre
MAINTAINER Kyle Williamson <kyle.williamson@mycit.ie>

ADD ./property-appliance-status-update-service-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/property-appliance-status-update-service-0.0.1.jar"]

EXPOSE 8416