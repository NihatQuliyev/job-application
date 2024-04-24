FROM openjdk:17
COPY build/libs/job-application-0.0.1-SNAPSHOT.jar job-app
ENTRYPOINT ["java", "-jar","job-app"]