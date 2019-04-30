############################
### Base for build image ###
############################
FROM maven:latest AS build

MAINTAINER Ruby Hale <ruby@d3adspace.de>

######################
### Copy all files ###
######################
COPY . .

################
### Build it ###
################
RUN mvn clean install package

########################
### Base for runtime ###
########################
FROM openjdk:11 AS runtime

WORKDIR /opt/app

COPY --from=build file-server/target/mantikor-file-server.jar /opt/app/server.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "server.jar" ]

