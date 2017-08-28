FROM java:8-jre
MAINTAINER Kyle Williamson <kyle.williamson@mycit.ie>

ADD ./appliance-service-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/appliance-service-0.0.1.jar"]

EXPOSE 8403