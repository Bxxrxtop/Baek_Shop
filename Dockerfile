FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
<<<<<<< HEAD
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]
=======
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]
>>>>>>> 44bd96d6168e66bde106627dc745ea51dea454f2
