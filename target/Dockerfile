# Use Java 17
FROM openjdk:17-jdk-slim

# Copy jar file
COPY target/*.jar app.jar

# Run application
ENTRYPOINT ["java","-jar","/app.jar"]