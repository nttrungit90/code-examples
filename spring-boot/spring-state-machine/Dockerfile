#
# Build stage
#
FROM maven:3.8.2-openjdk-11 AS build
WORKDIR /image_home_folder
COPY src image_app_folder/src
COPY pom.xml image_app_folder/
RUN mvn -f image_app_folder/pom.xml clean package
## list everything inside root foler image_home_folder in our image for visibility
RUN du -a .

#
# Package stage
#
FROM openjdk:11
COPY --from=build /image_home_folder/image_app_folder/target/spring-state-machine-0.0.1-SNAPSHOT.jar /usr/local/lib/spring-state-machine-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/spring-state-machine-0.0.1-SNAPSHOT.jar"]