FROM openjdk:17
ADD target/Docker-User.jar Docker-User.jar
EXPOSE 3005
ENTRYPOINT ["java","-jar","Docker-User.jar"]