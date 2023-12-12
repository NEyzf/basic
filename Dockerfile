FROM openjdk:8-jre-alpine
WORKDIR /app
COPY /target/basic-1.0-SNAPSHOT.jar /app

EXPOSE 20001
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar /app/basic-1.0-SNAPSHOT.jar"]
