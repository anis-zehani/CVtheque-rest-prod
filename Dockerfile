FROM openjdk:8-alpine
ADD target/Cvtheque-1.jar Cvtheque-1.jar
RUN mkdir -p /var/tmp/uploads && \
	mkdir -p /var/tmp/uploads/img && \
	mkdir -p /var/tmp/uploads/files && \
	mkdir -p /var/tmp/uploads/cvodix && \
	mkdir -p /var/tmp/uploads/cvoriginal
EXPOSE 8090
ENTRYPOINT ["java","-jar","/Cvtheque-1.jar"]