FROM java:8-jre
MAINTAINER Kyle Williamson <kyle.williamson@mycit.ie>

ADD ./pending-maintenance-scheduling-service-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/pending-maintenance-scheduling-service-0.0.1.jar"]

EXPOSE 8413