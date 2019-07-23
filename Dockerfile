FROM openjdk:8-alpine
ADD target/Cvtheque-1.jar Cvtheque-1.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/Cvtheque-1.jar"]