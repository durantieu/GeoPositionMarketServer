FROM openjdk:8
ADD /target/spring-boot-application.jar spring-boot-application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-application.jar"]