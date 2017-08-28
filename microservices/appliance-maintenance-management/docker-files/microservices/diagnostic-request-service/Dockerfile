FROM java:8-jre
MAINTAINER Kyle Williamson <kyle.williamson@mycit.ie>

ADD ./diagnostic-request-service-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/diagnostic-request-service-0.0.1.jar"]

EXPOSE 8414