#
# Build stage
#
FROM maven:3.8.2-openjdk-11 AS build
WORKDIR image_home_folder
# why not /image_app_folder/? After copp will the folder in the image be like /image_app_folder/src/src?
COPY src /image_app_folder/src
COPY pom.xml /image_app_folder
# This run is executing inside docker layer, but outcome has not committed to image
RUN mvn -f /image_app_folder/pom.xml clean package

#
# Package stage
#
FROM openjdk:11
# no need path like /image_home_folder/image_app_folder.....? why
COPY --from=build /image_app_folder/target/spring-state-machine-0.0.1-SNAPSHOT.jar /usr/local/lib/spring-state-machine-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/spring-state-machine-0.0.1-SNAPSHOT.jar"]