FROM openjdk:latest
COPY ./target/courswork.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "courswork.jar", "db:3306", "30000"]