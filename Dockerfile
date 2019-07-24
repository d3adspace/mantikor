############################
### Base for build image ###
############################
FROM gradle:jdk11 AS build

LABEL authors="Ruby Hale <ruby@d3adspace.de>, Felix Klauke <felix@felix-klauke.de>"

######################
### Copy all files ###
######################
COPY . .

################
### Build it ###
################
RUN ./gradlew build

########################
### Base for runtime ###
########################
FROM openjdk:11-stretch AS runtime

WORKDIR /opt/app

COPY --from=build /home/gradle/file-server/build/libs/mantikor-file-server.jar /opt/app/server.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "server.jar" ]

