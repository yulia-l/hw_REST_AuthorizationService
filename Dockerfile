FROM arm64v8/alpine:latest

RUN apk add --no-cache openjdk17

ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk

ENV PATH="${JAVA_HOME}/bin:${PATH}"

EXPOSE 8081

ADD target/authorizationService-1.0-SNAPSHOT.jar myapp.jar

ENTRYPOINT ["java","-jar","/myapp.jar"]