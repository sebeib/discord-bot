FROM openjdk:17-jdk-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

LABEL org.opencontainers.image.source https://github.com/sebeib/discord-bot

ENTRYPOINT ["java","-jar","/app.jar"]