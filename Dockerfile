### STAGE 1: Build ###

FROM openjdk:8-alpine
ADD target/Cvtheque-1.jar Cvtheque-1.jar
RUN mkdir uploads
EXPOSE 8090
ENTRYPOINT ["java","-jar","/Cvtheque-1.jar"]