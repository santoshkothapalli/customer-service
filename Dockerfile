FROM registry.access.redhat.com/openjdk/openjdk-11-rhel7

# Create app directory
WORKDIR /opt/app

# Refer to Maven build -> finalName
ARG JAR_FILE=build/libs/customer-service-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} ./customerService.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","./customerService.jar"]