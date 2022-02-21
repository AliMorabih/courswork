FROM openjdk:latest
COPY ./target/courswork-0.1.0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "courswork-0.1.0.1-jar-with-dependencies.jar"]
