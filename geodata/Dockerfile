FROM openjdk:17-jdk-alpine

#Create user to run app (instead of root)
RUN addgroup -S app && adduser -S app -G app

#use User "app"
USER app

#Copy jar file into docker image
COPY target/*.jar geodata-0.0.1-SNAPSHOT.jar

EXPOSE 8082

#Run the jar
ENTRYPOINT ["java", "-jar", "/geodata-0.0.1-SNAPSHOT.jar"]

