FROM openjdk:8-jdk-alpine
RUN apk update && apk add bash
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY wait-for-it.sh .
ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT ["./wait-for-it.sh", "-t", "30", "eureka-service:8761", "--", "java", "-jar", "app.jar"]