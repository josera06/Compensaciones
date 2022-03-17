FROM openjdk:17
ADD target/imss-compensaciones.jar imss-compensaciones.jar
EXPOSE 8585
ENTRYPOINT ["java","-jar","imss-compensaciones.jar"]