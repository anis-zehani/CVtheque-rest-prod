### STAGE 1: Build ###

FROM openjdk:8-alpine
ADD target/cvtheque-odix-1.jar cvtheque-odix-1.jar
RUN mkdir uploads
EXPOSE 8090
ENTRYPOINT ["java","-jar","/cvtheque-odix-1.jar"]
