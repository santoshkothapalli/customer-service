FROM registry.access.redhat.com/openjdk/openjdk-11-rhel7

# Create app directory
WORKDIR /opt/app

# Refer to Maven build -> finalName
ARG JAR_FILE=target/customer-service-0.0.1-SNAPSHOT.jar

# cp target/customer-service-0.0.1-SNAPSHOT.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]